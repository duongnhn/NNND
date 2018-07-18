import java.util.ArrayList;
import java.util.HashSet;

enum Status {
	WIN,
	LOSE,
	CONTINUE,
}

public class Game {
	
	ArrayList<Player> players;
	Map map;
	HashSet<Place> visitedPlaces = new HashSet<Place>(); 
	Status status = Status.CONTINUE;
	int numberOfGeneratedEnemy;

	void init() {
		//initialize players
		players = new ArrayList<Player>();
		for (int i=0;i<Constants.NUMBER_OF_PLAYERS;i++) {
			Player player = new Player(i);
			player.init();
			players.add(player);
		}
		Player.own = new Resource(Constants.NUMBER_OF_PLAYERS*Constants.WOOD_TO_START, 
				Constants.NUMBER_OF_PLAYERS*Constants.STEEL_TO_START, 
				Constants.NUMBER_OF_PLAYERS*Constants.FOOD_TO_START);
		//initialize Map
		map = new Map();
		map.init();
		drawPlayersLocation();
		//start with no enemy
		numberOfGeneratedEnemy = 0;
	}
	
	void drawPlayersLocation() {
		for (Player player:players) {
			int index = Utils.randomNumber(0, Constants.NUMBER_OF_PLACES-1);
			player.place = Map.places[index];
			visitedPlaces.add(player.place);
			player.place.isVisited = true;
			player.place.updateWeight();
		}
	}
	
	void perform() {
		init();				
		while (true) {
			//start a turn
			Utils.log("++++++++++++++++++++++");
			Utils.log("Start a turn");
			ArrayList<Player> playersToRemove = new ArrayList<Player>();
			for (Player currentPlayer: players) {
				Utils.log("Start a turn for player "+ currentPlayer.index);
				//player choose action
				Place newPlace = null;
				Place currentPlace = currentPlayer.place;
				ArrayList<Place> visitedPlacesInTurn = new ArrayList<Place>();
				boolean canBuild = true;
				//player can move until he/she choose to stop or can not move
				while (newPlace != currentPlace) {
					//player choose an action
					Action action = currentPlayer.selectAction();
					newPlace = currentPlace;
					switch (action) {
						case MOVE:
							//no food to go or can not kill enemy then no choice other than stay
							if ((Player.own.food < Constants.FOOD_TO_MOVE) || (Player.own.steel < newPlace.enemy+Constants.STEEL_TO_KILL)) {
								Utils.log("can not MOVE");
								break;
							}
							//can not build and move in the same turn
							canBuild = false;
							newPlace = Utils.randomPlaceOnWeight(currentPlace.connectedPlaces);
							//re-assign before move
							currentPlace = currentPlayer.place;
							applyRuleForMove(currentPlayer, newPlace);
							break;
						case STOP:
							break;
						case BUILD:
							//cannot build if exists house or already move or not enough wood
							if (currentPlace.hasHouse || (Player.own.wood < Constants.WOOD_TO_BUILD) || !canBuild) {
								Utils.log("can not BUILD");
								break;
							}
							applyRuleForBuild(currentPlayer, currentPlace);
							break;
					}
					Utils.log("Player "+currentPlayer.index+" at place "+ currentPlayer.place.name);
					visitedPlacesInTurn.add(newPlace);
					if (isGameEnd()) return;
					//update weight for map
					map.updateWeight();
				}
				//player collect resource from current place and house
				currentPlayer.collectResource();
				currentPlayer.collectFromHouses();
				//put enemy
				ArrayList<Player> playersToRemoveForThisEnemy;
				for (int i=0;i<Constants.NUMBER_OF_ENEMY_PER_TURN;i++) {
					Place enemyPlace = map.generateEnemy();
					playersToRemoveForThisEnemy=applyEnemy(enemyPlace);		
					for (Player player:playersToRemoveForThisEnemy) {
						playersToRemove.add(player);
					}
					if (isGameEnd()) return;
				}
				//end a turn for one player
				Utils.log("End a turn for player "+currentPlayer.index);
			}
			//remove any dead players before ending a turn
			removePlayers(playersToRemove);
			if (isGameEnd()) return;
			Utils.log("End a turn");
			Utils.log("Visistedplaces: ");
			Utils.log(visitedPlaces);
		}
	}

	void removePlayers(ArrayList<Player> playersToRemove) {
		for (Player player:playersToRemove) {
			players.remove(player);
		}
		// check if no player left
		if (players.size() == 0) {
			status = Status.LOSE;
			return;			
		}
	}
	
	boolean isGameEnd() {
		if (status == Status.CONTINUE) return false;
		return true;
	}
	
	void applyRuleForBuild(Player player, Place place) {
		Player.own.wood -= Constants.WOOD_TO_BUILD;
		player.housePlaces.add(place);
		place.hasHouse = true;
	}
		
	void applyRuleForMove(Player player, Place newPlace) {
		Utils.log("newPlace: "+ newPlace.name);
		//move need 1 food
		Player.own.food -= Constants.FOOD_TO_MOVE;
		//kill enemy if any and get booty
		Player.own.steel -= newPlace.enemy>0? newPlace.enemy+Constants.STEEL_TO_KILL:0;
		for (int i=0;i<newPlace.enemy;i++) {
			Resource booty = Utils.randomBooty();
			Player.own.add(booty);			
		}
		newPlace.enemy = 0;
		//update player and place
		player.place = newPlace;
		checkWinAfterMove(newPlace);
	}	

	void checkWinAfterMove(Place newPlace) {
		newPlace.isVisited = true;
		visitedPlaces.add(newPlace);
		if (visitedPlaces.size() == Constants.NUMBER_OF_PLACES) {
			status = Status.WIN;
		}		
	}
	
	ArrayList<Player> applyEnemy(Place place) {
		ArrayList<Player> playersToRemove = new ArrayList<Player>();
		Utils.log("enemyPlace: "+place.name);
		numberOfGeneratedEnemy++;
		//check if number of generated enemy reach limit
		if (numberOfGeneratedEnemy == Constants.TOTAL_ENEMY_LIMIT) {
			status = Status.LOSE;
			return playersToRemove;
		}
		ArrayList<Place> placesToRun = Utils.placesToRunFrom(place);
		for (Player currentPlayer:players) {
			if (currentPlayer.place == place) {
				// currentPlayer face enemy
				boolean canKill = Player.own.steel >= Constants.STEEL_TO_KILL;
				boolean canRun = (Player.own.food >= Constants.FOOD_TO_MOVE) && (placesToRun.size()>0);
				if (!canKill && !canRun) {
					//currentPlayer is dead
					playersToRemove.add(currentPlayer);
					continue;
				}
				EnemyAction playerEnemyAction = currentPlayer.selectEnemyAction();
				if (!canKill) {
					playerEnemyAction = EnemyAction.RUN;
				}
				if (!canRun) {
					playerEnemyAction = EnemyAction.KILL;
				}
				currentPlayer.performEnemyAction(playerEnemyAction);
				checkWinAfterMove(currentPlayer.place);
			}
		}
		return playersToRemove;
	}
}
