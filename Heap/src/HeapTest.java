import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class HeapTest {

	private static final double HEAP_SIZE = 5000000;
	private static final int AMOUNT_OF_RANDOM_NUMBERS = 100000000;
	private static final int BOUND = 10;
	
		
	private int prevValue;
	private int amount;
	private double sumRunSize;
	
	private int currentRun = 0;
	private RsHeap heap = new RsHeap((int)HEAP_SIZE);
	private ArrayList<Integer> run = new ArrayList<>();

	@Test
	public void test() {
		List<Integer> randoms = new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < AMOUNT_OF_RANDOM_NUMBERS; i++) {
			int number = r.nextInt(10);
			randoms.add(number);
			if (heap.isFull()) {
				int value = heap.popPush(number);
				addToRun(value);			 
			} else {
				heap.push(number);
			}
		}

		while (!heap.isEmpty()) {
			int value = heap.pop();
			addToRun(value);			
		}
		sumRunSize += run.size()/HEAP_SIZE;
		currentRun++;
		
		System.out.println("runs:" + currentRun);
		System.out.println("Average run size");
		System.out.println(sumRunSize/currentRun);
		
		
		//test if there are no numbers lost.
		assertEquals(AMOUNT_OF_RANDOM_NUMBERS, amount);
		
//		System.out.println("run:" + currentRun);
//		System.out.println(run);
//		System.out.println("end");
		
		

	}

	private void addToRun(int value) {
		run.add(value);
		amount++;
		if (value < prevValue) {
			System.out.println("failed run: "+currentRun);
			System.out.println(run);
			fail("new value is smaller than previous value. new value:" + value + " <  prev value: " + prevValue);
		}
		prevValue = value;
		if (heap.getRun() != currentRun) {
			currentRun = heap.getRun();
//			System.out.println("run: " + currentRun);
//			System.out.println(run);
//			
			sumRunSize += run.size()/HEAP_SIZE;
			System.out.println(run.size()/HEAP_SIZE);
			int max = Integer.MIN_VALUE;
			for (int i : run) {
				if (i < max) {
					fail("Heap does not create sorted run.");
				}
				if (i > max) {
					max = i;
				}

			}				
			
			run.clear();
			prevValue = Integer.MIN_VALUE;
		}

	}
}
