
public class Game {
	
	Player[] players;
	Map map;

	void init() {
		//initialize players
		players = new Player[1];
		players[0] = new Player();
		//initialize Map
		map = new Map();
		map.init();
		//TODO: draw player first place
	}
	
	Boolean perform() {
		init();				
		while (true) {
			//start a turn
			for (Player currentPlayer: players) {
				//TODO: draw action
				Action action = Action.MOVE;//
				currentPlayer.perform(action);
			}
			map.generateEnemy();
			//TODO: check game end
			if (Utils.flipCoin()) break;
			else {
				return false;
			}
		}
		return true;
	}
	
}
