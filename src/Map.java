enum Name {
	HA_NOI,
	HOA_BINH,
	THAI_BINH,
	THANH_HOA,
	NGHE_AN,
}

public class Map {
	static Place[] places;
		
	void init() {
		places = new Place[Constants.NUMBER_OF_PLACES];
		places[Name.HA_NOI.ordinal()] = new Place("Ha Noi", new Resource(0, 0, 1));//food
		places[Name.HOA_BINH.ordinal()] = new Place("Hoa Binh", new Resource(0, 1, 0));//steel
		places[Name.THAI_BINH.ordinal()] = new Place("Thai Binh", new Resource(0, 0, 1));//food
		places[Name.THANH_HOA.ordinal()] = new Place("Thanh Hoa", new Resource(1, 0, 0));//wood
		places[Name.NGHE_AN.ordinal()] = new Place("Nghe An", new Resource(1, 0, 0));//wood
		
		//link places
		linkPlaces(Name.HA_NOI.ordinal(), Name.HOA_BINH.ordinal());
		linkPlaces(Name.HA_NOI.ordinal(), Name.THAI_BINH.ordinal());
		linkPlaces(Name.HOA_BINH.ordinal(), Name.THANH_HOA.ordinal());
		linkPlaces(Name.THAI_BINH.ordinal(), Name.THANH_HOA.ordinal());
		linkPlaces(Name.THANH_HOA.ordinal(), Name.NGHE_AN.ordinal());
	}

	public Place generateEnemy() {
		int index = Utils.randomNumber(0, Constants.NUMBER_OF_PLACES-1);
		places[index].enemy += 1;
		return places[index];
	}
	
	static void linkPlaces(int i1, int i2) {
		Map.places[i1].connectedPlaces.add(Map.places[i2]);
		Map.places[i2].connectedPlaces.add(Map.places[i1]);
	}
}
