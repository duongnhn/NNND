import java.util.ArrayList;
import java.util.HashSet;

enum Status {
	WIN,
	LOSE,
	CONTINUE,
}

public class Game {
	
	Player[] players;
	Map map;
	HashSet<Place> visitedPlaces = new HashSet<Place>(); 
	Status status = Status.CONTINUE;
	int numberOfGeneratedEnemy;

	void init() {
		//initialize players
		players = new Player[Constants.NUMBER_OF_PLAYERS];
		for (int i=0;i<Constants.NUMBER_OF_PLAYERS;i++) {
			players[i] = new Player(i);
			players[i].init();
		}
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
		}
	}
	
	void perform() {
		init();				
		while (true) {
			//start a turn
			Utils.log("\n Start a turn");
			for (Player currentPlayer: players) {
				//player choose action
				Place newPlace = null;
				Place oldPlace = currentPlayer.place;
				ArrayList<Place> visitedPlacesInTurn = new ArrayList<Place>();
				//player can move until he/she choose to stop or can not move
				while (newPlace != oldPlace) {
					newPlace = currentPlayer.perform();
					//no food to go or can not kill enemy then no choice other than stay
					if ((currentPlayer.own.food < 1) || (currentPlayer.own.steel < newPlace.enemy+1)) {
						newPlace = currentPlayer.place;
					}
					oldPlace = currentPlayer.place;
					applyRule(currentPlayer, newPlace);
					visitedPlacesInTurn.add(newPlace);
					if (isGameEnd()) return;					
				}
				//player collect resource
				currentPlayer.collectResource(visitedPlacesInTurn);
			}
			for (int i=0;i<Constants.NUMBER_OF_ENEMY_PER_TURN;i++) {
				Place enemyPlace = map.generateEnemy();
				applyEnemy(enemyPlace);				
				if (isGameEnd()) return;
			}
			//end a turn
			Utils.log("End a turn");
		}
	}
	
	boolean isGameEnd() {
		if (status == Status.CONTINUE) return false;
		return true;
	}
	
	void applyRule(Player player, Place newPlace) {
		Utils.log("newPlace: "+ newPlace.name);
		//update resource
		if (newPlace != player.place) {
			player.own.food--;//move need 1 food
		}
		//kill enemy if any and get booty
		player.own.steel -= newPlace.enemy>0? newPlace.enemy+1:0;
		for (int i=0;i<newPlace.enemy;i++) {
			Resource booty = Utils.randomBooty();
			player.own.add(booty);			
		}
		newPlace.enemy = 0;
		//update player and place
		player.place = newPlace;
		visitedPlaces.add(newPlace);
		if (visitedPlaces.size() == Constants.NUMBER_OF_PLACES) {
			status = Status.WIN;
		}
	}
		
	void applyEnemy(Place place) {
		Utils.log("enemyPlace: "+place.name);
		numberOfGeneratedEnemy++;
		//check if number of generated enemy reach limit
		if (numberOfGeneratedEnemy == Constants.TOTAL_ENEMY_LIMIT) {
			status = Status.LOSE;
			return;
		}
		for (Player currentPlayer:players) {
			if (currentPlayer.place == place) {
				// need to kill enemy
				currentPlayer.own.steel -= place.enemy+1;
				if (currentPlayer.own.steel < 0) {
					status = Status.LOSE;
					return;
				}
				place.enemy = 0;
				return;
			}
		}
	}
}
