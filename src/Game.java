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
		// initialize Map
		map = new Map();
		map.init();
		// initialize players
		players = new ArrayList<Player>();
		for (int i=0;i<Constants.NUMBER_OF_PLAYERS;i++) {
			Player player = new Player(i, Constants.DEFAULT_ROLE);
			player.init();
			visitedPlaces.add(player.place);
			player.place.addPlayer();
			player.place.updateWeight();
			players.add(player);
		}
		Player.own = new Resource(Constants.NUMBER_OF_PLAYERS*Constants.WOOD_TO_START, 
				Constants.NUMBER_OF_PLAYERS*Constants.STEEL_TO_START, 
				Constants.NUMBER_OF_PLAYERS*Constants.FOOD_TO_START);
		// start with no enemy
		numberOfGeneratedEnemy = 0;
	}
		
	void perform() {
		init();				
		while (true) {
			// start a turn
			Utils.log("++++++++++++++++++++++");
			Utils.log("Start a turn");
			ArrayList<Player> playersToRemove = new ArrayList<Player>();
			for (Player currentPlayer: players) {
				Utils.log("Start a turn for player "+ currentPlayer.index);
				// player choose action
				Place newPlace = null;
				Place currentPlace = currentPlayer.place;
				ArrayList<Place> visitedPlacesInTurn = new ArrayList<Place>();
				boolean canBuild = true;
				// player can move until he/she choose to stop or can not move
				while (newPlace != currentPlace) {
					// player choose an action
					Action action = currentPlayer.selectAction();
					newPlace = currentPlace;
					switch (action) {
						case MOVE:
							newPlace = Utils.randomPlaceOnWeight(currentPlace.connectedPlaces);
							// no food to go or can not kill enemy then no choice other than stay
							if (!currentPlayer.canMove() || !currentPlayer.canKillEnemyInPlace(newPlace)) {
								Utils.log("can not MOVE, have to STOP");
								newPlace = currentPlace;
								break;
							}
							// can not build and move in the same turn
							canBuild = false;
							// re-assign before move
							currentPlace = currentPlayer.place;
							applyRuleForMove(currentPlayer, newPlace);
							break;
						case STOP:
							break;
						case BUILD:
							// cannot build if exists house or already move or not enough wood
							if (currentPlace.hasHouse || !currentPlayer.canBuild() || !canBuild) {
								Utils.log("can not BUILD, have to STOP");
								break;
							}
							// build house at currentPlayer.place
							applyRuleForBuild(currentPlayer);
							break;
					}
					Utils.log("Player "+currentPlayer.index+" at place "+ currentPlayer.place.name);
					visitedPlacesInTurn.add(newPlace);
					if (isGameEnd()) return;
					// update weight for map
					map.updateWeight();
				}
				// player collect resource from current place and house
				currentPlayer.collectResource();
				currentPlayer.collectFromHouses();
				// draw enemy
				ArrayList<Player> playersToRemoveForThisEnemy;
				for (int i=0;i<Constants.NUMBER_OF_ENEMY_PER_TURN;i++) {
					Place enemyPlace = map.generateEnemy();
					playersToRemoveForThisEnemy=applyEnemy(enemyPlace);		
					for (Player player:playersToRemoveForThisEnemy) {
						playersToRemove.add(player);
					}
					if (isGameEnd()) return;
				}
				// end a turn for one player
				Utils.log("End a turn for player "+currentPlayer.index);
			}
			// remove any dead players before ending a turn
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
	
	void applyRuleForBuild(Player player) {
		player.build();
		player.housePlaces.add(player.place);
		player.place.hasHouse = true;
	}
		
	void applyRuleForMove(Player player, Place newPlace) {
		Utils.log("newPlace: "+ newPlace.name);
		player.moveToNewPlace(newPlace);
		// get booty
		for (int i=0;i<newPlace.enemy;i++) {
			Resource booty = Utils.randomBooty();
			Player.own.add(booty);			
		}
		newPlace.enemy = 0;
		// update player and place
		player.place.removePlayer();
		player.place = newPlace;
		player.place.addPlayer();
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
		// check if number of generated enemy reach limit
		if (numberOfGeneratedEnemy == Constants.TOTAL_ENEMY_LIMIT) {
			status = Status.LOSE;
			return playersToRemove;
		}
		ArrayList<Place> placesToRun = Utils.placesToRunFrom(place);
		for (Player currentPlayer:players) {
			if (currentPlayer.place == place) {
				// currentPlayer face enemy
				boolean inDefense = true;
				boolean canKill = currentPlayer.canKill(inDefense);
				boolean canRun = currentPlayer.canMove() && (placesToRun.size()>0);
				if (!canKill && !canRun) {
					// currentPlayer is dead
					playersToRemove.add(currentPlayer);
					continue;
				}
				EnemyAction playerEnemyAction = currentPlayer.selectEnemyAction();
				if (!canRun) {
					playerEnemyAction = EnemyAction.KILL;
				}
				else {
					playerEnemyAction = EnemyAction.RUN;
				}
				currentPlayer.performEnemyAction(playerEnemyAction);
				checkWinAfterMove(currentPlayer.place);
			}
		}
		return playersToRemove;
	}
}
