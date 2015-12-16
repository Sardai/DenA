import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Apl {

	public static void main(String[] args) {
		String fileName = "unsorted.txt";
		createRandomNumberFile(fileName, 100000,100);
		System.out.println("Created random number file.");
		Runs runs = new Runs(5000);
		runs.createFromFile(fileName);
		System.out.println("Created runs from file.");
	}

	private static void createRandomNumberFile(String fileName, int amount,int bound) {
		try {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			Random r = new Random();
			for (int i = 0; i < amount; i++) {
				writer.write(r.nextInt(bound)+"   ");
			}
			writer.close();
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
		}

	}

}
