
public class Game {
	
	Player[] players;
	Map map;
	Boolean win = false;

	void init() {
		//initialize players
		players = new Player[1];
		players[0] = new Player();
		//initialize Map
		map = new Map();
		map.init();
		drawPlayersLocation();
	}
	
	void drawPlayersLocation() {
		for (Player player:players) {
			int index = Utils.randomNumber(0, Constants.NUMBER_OF_PLACES-1);
			player.place = map.places[index];
			//TODO: make sure that no place has more than 2 players
		}
	}
	
	Boolean perform() {
		init();				
		while (true) {
			//start a turn
			for (Player currentPlayer: players) {
				//TODO: draw action
				Action action = Action.MOVE;
				currentPlayer.perform(action);
				//TODO: apply action
			}
			map.generateEnemy();
			//TODO: apply enemy
			if (isGameEnd()) return win;
		}
	}
	
	Boolean isGameEnd() {
		//TODO: check game end
		win = Utils.flipCoin();
		if (Utils.flipCoin()) return true;
		else {
			return false;//lose
		}
		
	}
	
	void applyAction() {
		//TODO:
	}
	
	void applyEnemy() {
		//TODO:
	}
}
