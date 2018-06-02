import java.util.Random;

public class Utils {
	static int randomNumber(int low, int high){
		Random r = new Random();
		return r.nextInt(high-low+1) + low ;
	}

	static Boolean flipCoin() {
		return randomNumber(0, 1) == 0;
	}
}
