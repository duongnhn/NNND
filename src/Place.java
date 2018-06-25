import java.util.ArrayList;

public class Place {
	Resource resource;
	ArrayList<Place> connectedPlaces = new ArrayList<Place>();
	Boolean hasHouse = false;
	int enemy = 0;
	String name;
	
	Place(String str, Resource r) {
		name = str;
		resource = r;
	}
	
	boolean hasEnemy() {
		return enemy>0;
	}
}
