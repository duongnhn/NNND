
public class Player {
	Place place;
	Resource own;
	
	void init() {
		own = new Resource(3, 3, 3);//start with 3 wood, steel, food
	}
	
	Place perform() {
		Action action = Utils.randomAction();
		//update place
		Place newPlace = place;
		switch (action) {
		case MOVE:
			newPlace = Utils.randomPlace(place.connectedPlaces);
			break;
		case STOP:
			break;
		default:
			break;
		}
		return newPlace;
	}
}
