public class Constants {
	/* Only print log to console when set Debug to true */
	static final boolean DEBUG = false;
	
	/* Game settings */
	static final int NUMBER_OF_PLACES = Name.values().length;
	static final int NUMBER_OF_PLAYERS = 2;
	static final int NUMBER_OF_RESOURCE_TYPES = 3;
	static final int TOTAL_ENEMY_LIMIT = 3*NUMBER_OF_PLACES;
	static final int NUMBER_OF_ENEMY_PER_TURN = 1;
	
	/* resource to start */
	static final int WOOD_TO_START = 3;
	static final int STEEL_TO_START = 3;
	static final int FOOD_TO_START = 3;
	
	/* resource to perform action */
	static final int WOOD_TO_BUILD = 3;
	static final int FOOD_TO_MOVE = 1;
	static final int STEEL_TO_KILL = 1;
	
	/* Simulation configuration */
	static final int NUMBER_OF_TRIALS = 100;
	
	/* Player configuration */
	static final double[] PLAYER_ACTION_PROBABILITY = {0.7, 0.1, 0.2}; 	// MOVE, STOP, BUILD
	static final double[] PLAYER_ENEMY_ACTION_PROBABILITY = {0.5, 0.5};  // KILL, RUN
}
