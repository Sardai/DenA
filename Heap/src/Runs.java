import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Runs {

	private RsHeap heap;
	private int currentRun;
	private PrintWriter writer;

	public Runs(int heapSize) {
		heap = new RsHeap(heapSize);

	}

	public void createFromFile(String fileName) {
		File file = new File(fileName);

		try {
			writer = new PrintWriter("run" + currentRun + ".txt", "UTF-8");

			Scanner sc = new Scanner(file);

			while (sc.hasNextLine()) {
				int i = sc.nextInt();
				toHeap(i);
			}

			while (!heap.isEmpty()) {
				int value = heap.pop();
				addToRun(value);
			}

			writer.close();
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private void toHeap(int newValue) throws FileNotFoundException, UnsupportedEncodingException {
		if (heap.isFull()) {
			int value = heap.popPush(newValue);
			addToRun(value);
		} else {
			heap.push(newValue);
		}
	}

	private void addToRun(int value) throws FileNotFoundException, UnsupportedEncodingException {

		writer.write(value + "   ");
		if (heap.getRun() != currentRun) {
			currentRun = heap.getRun();

			writer.close();
			writer = new PrintWriter("run" + currentRun + ".txt", "UTF-8");

		}

	}

}
