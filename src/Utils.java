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
	
	static void linkPlaces(Place p1, Place p2) {
		p1.connectedPlaces.add(p2);
		p2.connectedPlaces.add(p1);
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
}
