
public class Player {
	Place place;
	Resource own;
	
	void init() {
		own = new Resource(3, 3, 3);//start with 3 wood, steel, food
	}
	
	Place perform() {
		Action action = Utils.randomAction();
		System.out.println("action: "+ action.toString());
		//update place
		Place newPlace = null;
		switch (action) {
		case MOVE:
			newPlace = Utils.randomPlace(place.connectedPlaces);
			break;
		case STOP:
			newPlace = place;
			break;
		default:
			break;
		}
		return newPlace;
	}
}
