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
		players = new Player[1];
		players[0] = new Player();
		players[0].init();
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
			player.place = map.places[index];
			visitedPlaces.add(player.place);
		}
	}
	
	void perform() {
		init();				
		while (true) {
			//start a turn
			System.out.println("Start a turn");
			for (Player currentPlayer: players) {
				//player choose action
				Place newPlace = currentPlayer.perform();
				//no food to go or can not kill enemy then no choice other than stay
				if ((currentPlayer.own.food < 1) || (currentPlayer.own.steel < newPlace.enemy+1)) {
					newPlace = currentPlayer.place;
				} 
				applyRule(currentPlayer, newPlace);
				if (isGameEnd()) return;
			}
			for (int i=0;i<Constants.NUMBER_OF_ENEMY_PER_TURN;i++) {
				Place enemyPlace = map.generateEnemy();
				applyEnemy(enemyPlace);				
				if (isGameEnd()) return;
			}
			//end a turn
			System.out.println("End a turn");
		}
	}
	
	Boolean isGameEnd() {
		if (status == Status.CONTINUE) return false;
		return true;
	}
	
	void applyRule(Player player, Place newPlace) {
		System.out.println("newPlace: "+ newPlace.name);
		//update resource
		if (newPlace != player.place) {
			player.own.add(new Resource(0, 0, -1));//move need 1 food
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
		player.own.add(newPlace.resource);//collect resource from new place
		visitedPlaces.add(newPlace);
		if (visitedPlaces.size() == Constants.NUMBER_OF_PLACES) {
			status = Status.WIN;
		}
	}
	
	void applyEnemy(Place place) {
		System.out.println("enemyPlace: "+place.name);
		numberOfGeneratedEnemy++;
		//check if number of generated enemy reach limit
		if (numberOfGeneratedEnemy == Constants.TOTAL_ENEMY_LIMIT) {
			status = Status.LOSE;
			return;
		}
		for (Player currentPlayer:players) {
			if (currentPlayer.place == place) {
				if (place.enemy >= Constants.MAX_ENEMY_PER_PLACE) {
					status = Status.LOSE;
					return;
				}
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
