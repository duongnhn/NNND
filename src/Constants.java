public class Constants {
	/* Only print log to console when set Debug to true */
	static final boolean DEBUG = false;
	
	/* Map settings */
	static final int NUMBER_OF_PLACES = Name.values().length;
	static final double INIT_WEIGHT_FOR_EACH_PLACE = NUMBER_OF_PLACES;
	static final double DISTANCE_FACTOR = 15;
	/* Formula to compute weight for each place 
	 * weight = init_weight/(distance_factor^distance_to_unvisited_place)
	 * with factor = 2, init_weight = 1
	 * distance = 0 -> weight = 1/2^0
	 * distance = 1 -> weight = 1/2^1
	 * distance = 2 -> weight = 1/2^2
	/* Game settings */
	static final int NUMBER_OF_PLAYERS = 4;
	static final int NUMBER_OF_RESOURCE_TYPES = 3;
	static final int NUMBER_OF_DRAW_FOR_ENEMY = 3;
	static final int TOTAL_ENEMY_LIMIT = NUMBER_OF_DRAW_FOR_ENEMY*NUMBER_OF_PLACES;
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
	static final int NUMBER_OF_TRIALS = 30000;
	
	/* Player configuration */
	static final double[] PLAYER_ACTION_PROBABILITY = {0.6, 0.1, 0.3}; 	// MOVE, STOP, BUILD
	static final double[] PLAYER_ENEMY_ACTION_PROBABILITY = {0.5, 0.5};  // KILL, RUN
	static final Role DEFAULT_ROLE = Role.MAC_DANG_DUNG; // Currently, all players have the same default role.
}
