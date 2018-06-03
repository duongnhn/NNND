public class Map {
	Place[] places;
		
	void init() {
		places = new Place[Constants.NUMBER_OF_PLACES];
		places[0] = new Place("Thang Long", new Resource(1, 0, 0));
		places[1] = new Place("Hoa Binh", new Resource(0, 1, 0));
		places[2] = new Place("Thai Binh", new Resource(0, 0, 1));
	}

	public void generateEnemy() {
		int index = Utils.randomNumber(0, Constants.NUMBER_OF_PLACES-1);
		places[index].hasEnemy = true;
	}
}
