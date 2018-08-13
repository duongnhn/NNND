import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

public class Utils {
	static Random gen = new Random();
	
	static int randomNumber(int low, int high){
		Random r = new Random();
		return r.nextInt(high-low+1) + low ;
	}

	static Boolean flipCoin() {
		return randomNumber(0, 1) == 0;
	}
		
	static Place randomPlace(ArrayList<Place> arr) {
		int i = randomNumber(0, arr.size()-1);
		return arr.get(i);
	}
	
	static Place randomPlace() {
		int index = Utils.randomNumber(0, Constants.NUMBER_OF_PLACES-1);
		return Map.places[index];		
	}
	
	static Place randomPlaceOnWeight(ArrayList<Place> arr) {
		int length = arr.size();
		double[] weights = new double[length];
		for (int i=0;i<length;i++) {
			Place place = arr.get(i);
			weights[i] = place.weight;
		}
		int i = randomNumber(weights);
		return arr.get(i);
	}
	
	static Action randomAction() {
		Action[] arr = Action.values();
		int i = randomNumber(0, arr.length-1);
		return arr[i];
	}
	
	static Action randomAction(double[] arr) {
		Action[] actionArr = Action.values();
		int i = randomNumber(arr);
		return actionArr[i];
	}
	
	static EnemyAction randomEnemyAction(double[] arr) {
		EnemyAction[] actionArr = EnemyAction.values();
		int i = randomNumber(arr);
		return actionArr[i];
	}
	
	static Resource randomBooty() {
		int i = randomNumber(0, Type.values().length-1);
		Resource r = new Resource(0, 0, 0);
		switch (i) {
			case 0: r.food = 1;
			case 1: r.steel = 1;
			case 2: r.wood = 1;
		}
		return r;
	}
	
	static void log(String str) {
		if (Constants.DEBUG) {
			System.out.println(str);
		}
	}
	
	static void log(HashSet<Place> places) {
		if (!Constants.DEBUG) {
			return;
		}
		Iterator<Place> iter = places.iterator();
		while (iter.hasNext()) {
		    System.out.print(iter.next().name+" ");
		}
		System.out.println("@_@");
	}
	
	static int randomNumber(double[] arr) {
		int length = arr.length;
		for (int i=0;i<length-1;i++) {
			arr[i+1] += arr[i]; 
		}
		for (int i=0;i<length;i++) {
			arr[i] = arr[i]/arr[length-1];
		}
		double change = gen.nextDouble();
		int n = 0;
		while (arr[n]<change) {
			n++;
		}
		return n;
	}
	
	static ArrayList<Place> placesToRunFrom(Place place) {
		ArrayList<Place> places = new ArrayList<Place>();
		for (Place placeToCheck:place.connectedPlaces) {
			if (placeToCheck.enemy == 0) {
				places.add(placeToCheck);
			}
		}
		return places;
	}
	
	static boolean isSeparetedByEnemy(Place place1, Place place2) {
		if (place1.hasEnemy() || place2.hasEnemy()) {
			return true;
		}
		HashSet<Place> hs = new HashSet<Place>();
		Stack<Place> st = new Stack<Place>();
		st.push(place1);
		hs.add(place1);
		while (!st.isEmpty()) {
			Place currentPlace = st.pop();
			if (currentPlace == place2) {
				//found a path without enemy
				return false;
			}
			for (Place place:currentPlace.connectedPlaces) {
				if (!hs.contains(place)&&!place.hasEnemy()) {
					st.push(place);
				}
			}
		}
		return true;
	}
}
