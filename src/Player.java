import java.util.ArrayList;

public class Player {
	Place place;
	static Resource own;//all players share the same resource
	ArrayList<Place> housePlaces;
	int index;
	
	Player(int i){
		index = i;
	}
	
	void init() {
		housePlaces = new ArrayList<Place>();
	}
	
	Action selectAction() {
		Action action = Utils.randomAction(Constants.PLAYER_ACTION_PROBABILITY);
		Utils.log("player choose action: "+ action.toString());
		return action;
	}
	
	EnemyAction selectEnemyAction() {
		EnemyAction action = Utils.randomEnemyAction(Constants.PLAYER_ENEMY_ACTION_PROBABILITY);
		Utils.log("player choose enemy action: "+ action.toString());
		return action;
	}
	
	void performEnemyAction(EnemyAction action) {
		Utils.log("player perform enemy action: "+action.toString());
		switch (action) {
			case KILL:
				own.steel -= Constants.STEEL_TO_KILL;
				place.enemy = 0;					
				break;
			case RUN:
				own.food -= Constants.FOOD_TO_MOVE;
				ArrayList<Place> noEnemyPlaces = Utils.placesToRunFrom(place);
				Place placeToRun = Utils.randomPlaceOnWeight(noEnemyPlaces);
				Utils.log("player run to place: "+ placeToRun.name);
				place = placeToRun;
				place.isVisited = true;
				place.updateWeight();
				break;
		}
	}
		
	void collectResource(ArrayList<Place> places) {
		for (Place place:places) {
			own.add(place.resource);
		}
	}
	
	void collectResource() {
		own.add(place.resource);
	}
	
	void collectFromHouses() {
		for (Place place:housePlaces) {
			if (place.enemy == 0) {
				own.add(place.resource);
			}
		}
	}
	
	boolean canKill() {
		return own.steel>=Constants.STEEL_TO_KILL;
	}
	
	boolean canBuild() {
		return own.wood>=Constants.WOOD_TO_BUILD;
	}
	
	boolean canMove() {
		return own.food>=Constants.FOOD_TO_MOVE;
	}
	
	boolean canKillEnemyInPlace(Place newPlace) {
		return own.steel>=Constants.STEEL_TO_KILL+newPlace.enemy;
	}
	
	void build() {
		own.wood -= Constants.WOOD_TO_BUILD;
	}
	
	void move() {
		own.food -= Constants.FOOD_TO_MOVE;
	}
	
	void kill() {
		own.steel -= Constants.STEEL_TO_KILL;
	}
	
	void moveToNewPlace(Place newPlace) {
		//move need 1 food
		own.food -= Constants.FOOD_TO_MOVE;
		//kill enemy if any
		own.steel -= newPlace.enemy>0? newPlace.enemy+Constants.STEEL_TO_KILL:0;
	}
}
