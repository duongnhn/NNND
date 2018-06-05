enum Type {
	WOOD,
	STEEL,
	FOOD,
}


public class Resource {
	int wood;
	int steel;
	int food;
	
	Resource(int w, int g, int f){
		wood = w;
		steel = g;
		food = f;
	}
	
	void add(Resource r){
		wood += r.wood;
		steel += r.steel;
		food += r.food;
	}
}
