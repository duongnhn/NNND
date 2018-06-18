import java.util.ArrayList;

public class Player {
	Place place;
	Resource own;
	int index;
	
	Player(int i){
		index = i;
	}
	
	void init() {
		own = new Resource(3, 3, 3);//start with 3 wood, steel, food
	}
	
	Place perform() {
		Action action = Utils.randomAction();
		Utils.log("action: "+ action.toString());
		//update place
		Place newPlace = null;
		switch (action) {
		case MOVE:
			newPlace = Utils.randomPlace(place.connectedPlaces);
			break;
		case STOP:
			newPlace = place;
			break;
		}
		return newPlace;
	}
	
	void collectResource(ArrayList<Place> places) {
		for (Place place:places) {
			own.add(place.resource);
		}
	}
}
