import java.util.ArrayList;
import java.util.Random;

public class Utils {
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
	
	static int randomNumber(double[] arr) {
		int length = arr.length;
		for (int i=0;i<length-1;i++) {
			arr[i+1] += arr[i]; 
		}
		double change = Math.random();
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
}
