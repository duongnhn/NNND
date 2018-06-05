enum Name {
	THANG_LONG,
	HOA_BINH,
	THAI_BINH,
}

public class Map {
	Place[] places;
		
	void init() {
		places = new Place[Constants.NUMBER_OF_PLACES];
		places[Name.THANG_LONG.ordinal()] = new Place("Thang Long", new Resource(1, 0, 0));
		places[Name.HOA_BINH.ordinal()] = new Place("Hoa Binh", new Resource(0, 1, 0));
		places[Name.THAI_BINH.ordinal()] = new Place("Thai Binh", new Resource(0, 0, 1));
		
		//link places
		Utils.linkPlaces(places[0], places[1]);
		Utils.linkPlaces(places[0], places[2]);
	}

	public Place generateEnemy() {
		int index = Utils.randomNumber(0, Constants.NUMBER_OF_PLACES-1);
		places[index].enemy += 1;
		return places[index];
	}
}
