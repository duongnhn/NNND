
public class Player {
	Resource skill;
	Place place;
	Resource own;
	
	void init() {
		skill = new Resource(1, 0, 0);
		own = new Resource(3, 3, 3);//start with 3 gold, wood, food
	}
	
	void perform(Action action) {
		//update place
		//update resource
	}
}
