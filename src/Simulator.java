
public class Simulator {
	
	int numberOfTrials;
	int numberOfWins;
	
	void init() {
		numberOfTrials = Constants.NUMBER_OF_TRIALS;
		numberOfWins = 0;
	}
	
	void start() {
		System.out.println("Start simulation for "+ numberOfTrials);
		for (int trial=1;trial<=numberOfTrials;trial++) {
			Game game = new Game();
			game.perform();
			if (game.status == Status.WIN) numberOfWins++;
		}
		System.out.println("Number of wins: "+ numberOfWins);
	}
	
	public static void main(String[] args) {
		Simulator sim = new Simulator();
		sim.init();
		sim.start();
	}

}
