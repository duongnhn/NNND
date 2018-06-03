public class Place {
	Resource resource;
	Place[] connectedPlaces;
	Boolean hasEnemy = false;
	String name;
	
	Place(String str, Resource r) {
		name = str;
		resource = r;
	}
}
