import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Place {
	Resource resource;
	ArrayList<Place> connectedPlaces = new ArrayList<Place>();
	Boolean hasHouse;
	int enemy;
	int player;
	String name;
	Boolean isVisited;
	double weight;//the bigger the more important to visit
	
	Place(String str, Resource r) {
		name = str;
		resource = r;
		isVisited = false;
		weight = Constants.INIT_WEIGHT_FOR_EACH_PLACE;
		enemy = 0;
		player = 0;
		hasHouse = false;
	}
	
	boolean hasEnemy() {
		return enemy>0;
	}	
	
	int distance() {
		HashMap<Place, Integer> hm = new HashMap<Place, Integer>();
		Queue<Place> q = new LinkedList<Place>();
		q.add(this);
		hm.put(this, 0);
		int distance = 0;//how far to an unvisited place 
		while (!q.isEmpty()) {
			Place currentPlace = q.poll();
			distance = hm.get(currentPlace);
			if (!currentPlace.isVisited) {
				//found an unvisited place
				return distance;
			}
			for (Place place:currentPlace.connectedPlaces) {
				if (!hm.containsKey(place)) {
					q.add(place);
					hm.put(place, distance+1);
				}
			}
		}
		return distance;
	}
	
	void updateWeight() {
		weight = Constants.INIT_WEIGHT_FOR_EACH_PLACE/Math.pow(Constants.DISTANCE_FACTOR, distance());
		weight = weight<0? 0: weight;
	}
	
	void addPlayer() {
		isVisited = true;
		player++;
	}
	
	void removePlayer() {
		player--;
	}
	
	int numberOfPlayersAround() { // include this place and neighbors
		int number = player;
		for (Place place:connectedPlaces) {
			number += place.player;
		}
		return number;
	}
}
