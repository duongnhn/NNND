import java.util.ArrayList;

enum Name {
	HA_NOI,
	HOA_BINH,
	THAI_BINH,
	THANH_HOA, // sea
	NGHE_AN,
	TUYEN_QUANG,
	CAO_BANG,
	YEN_BAI,
	THAI_NGUYEN,
	SON_LA,
	HAI_DUONG,  // sea
	HAI_PHONG, // sea
	HUE, // sea
	QUANG_NAM, // sea
	KON_TUM,
	QUANG_NGAI, // sea
	GIA_LAI,
	PHU_YEN, // sea
	DAK_LAK,
	KHANH_HOA, //sea
	TAY_NINH,
	LAM_DONG, // sea
	GIA_DINH, // sea
	KIEN_GIANG,
	CA_MAU, // sea
	LONG_HO // sea
}

public class Map {
	static Place[] places;
	private int count;
	private ArrayList<Place> permutationList = new ArrayList<Place>();
		
	void init() {
		places = new Place[Constants.NUMBER_OF_PLACES];
		places[Name.HA_NOI.ordinal()] = new Place(Name.HA_NOI.toString(), new Resource(0, 0, 1));//food
		places[Name.HOA_BINH.ordinal()] = new Place(Name.HOA_BINH.toString(), new Resource(0, 1, 0));//steel
		places[Name.THAI_BINH.ordinal()] = new Place(Name.THAI_BINH.toString(), new Resource(0, 0, 1));//food
		places[Name.THANH_HOA.ordinal()] = new Place(Name.THANH_HOA.toString(), new Resource(1, 0, 0));//wood
		places[Name.NGHE_AN.ordinal()] = new Place(Name.NGHE_AN.toString(), new Resource(1, 0, 0));//wood
		places[Name.TUYEN_QUANG.ordinal()] = new Place(Name.TUYEN_QUANG.toString(), new Resource(0, 1, 0));
		places[Name.CAO_BANG.ordinal()] = new Place(Name.CAO_BANG.toString(), new Resource(0, 1, 0));
		places[Name.YEN_BAI.ordinal()] = new Place(Name.YEN_BAI.toString(), new Resource(1, 0, 0));
		places[Name.THAI_NGUYEN.ordinal()] = new Place(Name.THAI_NGUYEN.toString(), new Resource(1, 0, 0));
		places[Name.SON_LA.ordinal()] = new Place(Name.SON_LA.toString(), new Resource(0, 1, 0));
		places[Name.HAI_DUONG.ordinal()] = new Place(Name.HAI_DUONG.toString(), new Resource(0, 0, 1));
		places[Name.HAI_PHONG.ordinal()] = new Place(Name.HAI_PHONG.toString(), new Resource(0, 0, 1));
		places[Name.HUE.ordinal()] = new Place(Name.HUE.toString(), new Resource(1, 0, 0));
		places[Name.QUANG_NAM.ordinal()] = new Place(Name.QUANG_NAM.toString(), new Resource(1, 0, 0));
		places[Name.KON_TUM.ordinal()] = new Place(Name.KON_TUM.toString(), new Resource(0, 1, 0));
		places[Name.QUANG_NGAI.ordinal()] = new Place(Name.QUANG_NGAI.toString(), new Resource(1, 0, 0));
		places[Name.GIA_LAI.ordinal()] = new Place(Name.GIA_LAI.toString(), new Resource(0, 1, 0));
		places[Name.PHU_YEN.ordinal()] = new Place(Name.PHU_YEN.toString(), new Resource(0, 0, 1));
		places[Name.DAK_LAK.ordinal()] = new Place(Name.DAK_LAK.toString(), new Resource(0, 1, 0));
		places[Name.KHANH_HOA.ordinal()] = new Place(Name.KHANH_HOA.toString(), new Resource(0, 0, 1));
		places[Name.TAY_NINH.ordinal()] = new Place(Name.TAY_NINH.toString(), new Resource(0, 0, 1));
		places[Name.LAM_DONG.ordinal()] = new Place(Name.LAM_DONG.toString(), new Resource(1, 0, 0));
		places[Name.GIA_DINH.ordinal()] = new Place(Name.GIA_DINH.toString(), new Resource(0, 0, 1));
		places[Name.KIEN_GIANG.ordinal()] = new Place(Name.KIEN_GIANG.toString(), new Resource(0, 0, 1));
		places[Name.CA_MAU.ordinal()] = new Place(Name.CA_MAU.toString(), new Resource(0, 0, 1));
<<<<<<< HEAD
		places[Name.LONG_HO.ordinal()] = new Place(Name.LONG_HO.toString(), new Resource(0, 0, 1));
=======
>>>>>>> 9021ef1bc24b295cb18b99e75200f7854ece4aaf
		
		//link places
		linkPlaces(Name.TUYEN_QUANG.ordinal(), Name.CAO_BANG.ordinal());
		linkPlaces(Name.TUYEN_QUANG.ordinal(), Name.YEN_BAI.ordinal());
		linkPlaces(Name.CAO_BANG.ordinal(), Name.THAI_NGUYEN.ordinal());
		linkPlaces(Name.SON_LA.ordinal(), Name.YEN_BAI.ordinal());
		linkPlaces(Name.YEN_BAI.ordinal(), Name.THAI_NGUYEN.ordinal());
		linkPlaces(Name.YEN_BAI.ordinal(), Name.HOA_BINH.ordinal());
		linkPlaces(Name.THAI_NGUYEN.ordinal(), Name.HA_NOI.ordinal());
		linkPlaces(Name.HOA_BINH.ordinal(), Name.HA_NOI.ordinal());
		linkPlaces(Name.HOA_BINH.ordinal(), Name.THANH_HOA.ordinal());
		linkPlaces(Name.HA_NOI.ordinal(), Name.HAI_DUONG.ordinal());
		linkPlaces(Name.HA_NOI.ordinal(), Name.THAI_BINH.ordinal());
		linkPlaces(Name.HAI_DUONG.ordinal(), Name.HAI_PHONG.ordinal());
		linkPlaces(Name.HAI_DUONG.ordinal(), Name.THAI_BINH.ordinal());
		linkPlaces(Name.THAI_BINH.ordinal(), Name.THANH_HOA.ordinal());
		linkPlaces(Name.THANH_HOA.ordinal(), Name.NGHE_AN.ordinal());
		linkPlaces(Name.NGHE_AN.ordinal(), Name.HUE.ordinal());
		linkPlaces(Name.HUE.ordinal(), Name.QUANG_NAM.ordinal());
		linkPlaces(Name.QUANG_NAM.ordinal(), Name.QUANG_NGAI.ordinal());
		linkPlaces(Name.QUANG_NAM.ordinal(), Name.KON_TUM.ordinal());
		linkPlaces(Name.KON_TUM.ordinal(), Name.QUANG_NGAI.ordinal());
		linkPlaces(Name.KON_TUM.ordinal(), Name.GIA_LAI.ordinal());
		linkPlaces(Name.QUANG_NGAI.ordinal(), Name.PHU_YEN.ordinal());
		linkPlaces(Name.GIA_LAI.ordinal(), Name.PHU_YEN.ordinal());
		linkPlaces(Name.GIA_LAI.ordinal(), Name.DAK_LAK.ordinal());
		linkPlaces(Name.PHU_YEN.ordinal(), Name.KHANH_HOA.ordinal());
		linkPlaces(Name.DAK_LAK.ordinal(), Name.LAM_DONG.ordinal());
		linkPlaces(Name.DAK_LAK.ordinal(), Name.TAY_NINH.ordinal());
		linkPlaces(Name.LAM_DONG.ordinal(), Name.KHANH_HOA.ordinal());
		linkPlaces(Name.LAM_DONG.ordinal(), Name.GIA_DINH.ordinal());
		linkPlaces(Name.TAY_NINH.ordinal(), Name.GIA_DINH.ordinal());
		linkPlaces(Name.LONG_HO.ordinal(), Name.GIA_DINH.ordinal());
		linkPlaces(Name.KIEN_GIANG.ordinal(), Name.CA_MAU.ordinal());
		linkPlaces(Name.KIEN_GIANG.ordinal(), Name.LONG_HO.ordinal());
		linkPlaces(Name.LONG_HO.ordinal(), Name.CA_MAU.ordinal());
	
		//enemy setup
		count = -1;
		for (Place place:places) {
			permutationList.add(place);
		}
	}

	public Place generateEnemy() {
		count++;
		if (count%Constants.NUMBER_OF_PLACES==0) {
			//start shuffle
			java.util.Collections.shuffle(permutationList);
			count = 0;
		}
		return permutationList.get(count);
	}
	
	static void linkPlaces(int i1, int i2) {
		Map.places[i1].connectedPlaces.add(Map.places[i2]);
		Map.places[i2].connectedPlaces.add(Map.places[i1]);
	}
	
	void updateWeight() {
		for (Place place:places) {
			place.updateWeight();
		}
	}
}
