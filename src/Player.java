import java.util.ArrayList;

public class Player {
	Place place;
	Resource own;
	ArrayList<Place> housePlaces;
	int index;
	
	Player(int i){
		index = i;
	}
	
	void init() {
		own = new Resource(Constants.WOOD_TO_START, Constants.STEEL_TO_START, Constants.FOOD_TO_START);
		housePlaces = new ArrayList<Place>();
	}
	
	Action selectAction() {
		Action action = Utils.randomAction(Constants.PLAYER_ACTION_PROBABILITY);
		Utils.log("player choose action: "+ action.toString());
		return action;
	}
	
	EnemyAction selectEnemyAction() {
		EnemyAction action = Utils.randomEnemyAction(Constants.PLAYER_ENEMY_ACTION_PROBABILITY);
		Utils.log("player choose enemy action: "+ action.toString());
		return action;
	}
	
	void performEnemyAction(EnemyAction action) {
		Utils.log("player perform enemy action: "+action.toString());
		switch (action) {
			case KILL:
				own.steel -= Constants.STEEL_TO_KILL;
				place.enemy = 0;					
				break;
			case RUN:
				own.food -= Constants.FOOD_TO_MOVE;
				ArrayList<Place> noEnemyPlaces = Utils.placesToRunFrom(place);
				Place placeToRun = Utils.randomPlace(noEnemyPlaces);
				Utils.log("player run to place: "+ placeToRun.name);
				place = placeToRun;
				break;
		}
	}
	
	void run() {
	}
	
	void collectResource(ArrayList<Place> places) {
		for (Place place:places) {
			own.add(place.resource);
		}
	}
	
	void collectResource() {
		own.add(place.resource);
	}
	
	void collectFromHouses() {
		for (Place place:housePlaces) {
			if (place.enemy == 0) {
				own.add(place.resource);
			}
		}
	}
}
