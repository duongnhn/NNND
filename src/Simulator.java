
public class Simulator {
	
	int numberOfTrials;
	int numberOfWins;
	
	void init() {
		numberOfTrials = 5;
		numberOfWins = 0;
		//TODO
	}
	
	void start() {
		System.out.println("Start simulation for "+ numberOfTrials);
		for (int trial=1;trial<=numberOfTrials;trial++) {
			Game game = new Game();
			game.init();
			if (game.perform()) numberOfWins++;
		}
		System.out.println("Number of wins: "+ numberOfWins);
	}
	
	public static void main(String[] args) {
		Simulator sim = new Simulator();
		sim.init();
		sim.start();
	}

}
