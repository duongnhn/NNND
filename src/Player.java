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
	
	Action perform() {
		Action action = Utils.randomAction(Constants.PLAYER_ACTION_PROBABILITY);
		Utils.log("player choose action: "+ action.toString());
		//update place
		return action;
	}
		
	void collectResource(ArrayList<Place> places) {
		for (Place place:places) {
			own.add(place.resource);
		}
	}
	
	void collectFromHouses() {
		for (Place place:housePlaces) {
			if (place.enemy == 0) {
				own.add(place.resource);
			}
		}
	}
}
