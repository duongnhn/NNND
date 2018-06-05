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

	void init() {
		//initialize players
		players = new Player[1];
		players[0] = new Player();
		players[0].init();
		//initialize Map
		map = new Map();
		map.init();
		drawPlayersLocation();
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
			for (Player currentPlayer: players) {
				//player choose action
				Place newPlace = currentPlayer.perform();
				if (currentPlayer.own.food < 1) {
					newPlace = currentPlayer.place;//no food to go, no choice other than stay
				}
				applyRule(currentPlayer, newPlace);
			}
			Place enemyPlace = map.generateEnemy();
			applyEnemy(enemyPlace);
			if (isGameEnd()) return;
		}
	}
	
	Boolean isGameEnd() {
		if (status == Status.CONTINUE) return false;
		return true;
	}
	
	void applyRule(Player player, Place newPlace) {
		//update resource
		if (newPlace != player.place) {
			player.own.add(new Resource(0, 0, -1));//move need 1 food
		}
		player.own.add(newPlace.resource);//collect resource from new place
		//check enemy
		player.own.steel -= newPlace.enemy>0? newPlace.enemy+1:0;
		newPlace.enemy = 0;
		//update place
		player.place = newPlace;
		visitedPlaces.add(newPlace);
		if (visitedPlaces.size() == Constants.NUMBER_OF_PLACES) {
			status = Status.WIN;
		}
	}
	
	void applyEnemy(Place place) {
		for (Player currentPlayer:players) {
			if (currentPlayer.place == place) {
				if (place.enemy >= Constants.MAX_ENEMY) {
					status = Status.LOSE;
					return;
				}
				currentPlayer.own.steel -= place.enemy;
				if (currentPlayer.own.steel < 0) {
					status = Status.LOSE;
				}
				return;
			}
		}
	}
}
