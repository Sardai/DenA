import java.util.ArrayList;

/**
 * Min-heap to create sorted runs from numbers.
 * 
 * @author Chris,Rick
 *
 */
public class RsHeap {

	public int[] heap;
	private int heapMaxSize;
	private int heapSize;
	private boolean isFull;
	private int deadSpace;
	private int run = 0;

	/**
	 * Instanciate the heap with a maximum heap size.
	 * 
	 * @param heapMaxSize
	 *            the maximum heap size
	 */
	public RsHeap(int heapMaxSize) {
		this.heapMaxSize = heapMaxSize;
		heap = new int[heapMaxSize];
	}

	/**
	 * First pops a value from the heap and then inserts the new value. if push
	 * value is smaller than pop value then a new run is created.
	 * 
	 * @param newValue
	 *            the value to insert
	 * @return the popped value
	 */
	public int popPush(int newValue) {
		int firstValue = heap[0];

		// if new value is the same as first value, do nothing and return new
		// value.
		if (newValue == firstValue) {
			return newValue;
		}
		// if new value is greater than first value, it will be added to this
		// run.
		// else is will be added to dead space.
		if (newValue > firstValue) {
			delete();
			heap[heapSize] = newValue;
			perculateUp(heapSize);
			heapSize++;
		} else {
			delete();
			heap[heapSize] = newValue;
			deadSpace++;
			if (heapSize == 0) {
				heapSize = heapMaxSize;
				deadSpace = 0;
				buildHeap();
				run++;
			}
		}

		return firstValue;
	}

	/**
	 * Inserts a value to the heap. when heap is full it builds the heap.
	 * 
	 * @param i
	 */
	public void push(int i) {
		assert heap.length > heapSize; // length of heap must be greater than
										// the amount of inserted numbers.
		heap[heapSize] = i;
		heapSize++;
		if (heapSize == heapMaxSize) {
			isFull = true;
			buildHeap();
		}
	}

	/**
	 * Build the heap to a valid min-heap.
	 */
	public void buildHeap() {

		for (int i = getParent(heapSize); i >= 0; i--) {
			perculateDown(i);
		}
	}

	/**
	 * Pops the value from the heap.
	 * 
	 * @return the popped value
	 */
	public int pop() {
		int value = heap[0];
		delete();
		// return last value;
		if (heapSize == 0) {
			if (deadSpace > 0) {
				heapSize = deadSpace;
				deadSpace = 0;
				run++;
				buildHeap();
			} else {
				return value;
			}
		}
		perculateUp(heapSize - 1);
		return value;
	}

	/**
	 * @return if the heap size is 0
	 */
	public boolean isEmpty() {
		return heapSize == 0;
	}

	/**
	 * @return the run the heap is currently working with.
	 */
	public int getRun() {
		return run;
	}

	/**
	 * deletes a value by swapping it with the first value and decreasing the
	 * heap size. the swapped value will be perculated down to make to heap
	 * valid again if neccesary.
	 */
	private void delete() {
		swap(0, --heapSize);
		perculateDown(0);
	}

	/**
	 * Perculate a value down to make the heap valid again.
	 * 
	 * @param index
	 *            the index of the parent
	 */
	private void perculateDown(int index) {
		if (!isLeaf(index)) {
			if (heap[index] > heap[getLeftChild(index)] && heap[getLeftChild(index)] < heap[getRightChild(index)]) {
				swap(index, getLeftChild(index));
				perculateDown(getLeftChild(index));
			} else if (heap[index] > heap[getRightChild(index)]) {
				swap(index, getRightChild(index));
				perculateDown(getRightChild(index));
			}
		}
	}

	/**
	 * Perculate a value up to make the heap valid again.
	 * 
	 * @param index
	 *            the index
	 */
	private void perculateUp(int index) {
		while (heap[index] < heap[getParent(index)]) {
			swap(index, getParent(index));
			index = getParent(index);
		}
	}

	/**
	 * Get the parent index from a child index.
	 * 
	 * @param index
	 *            the child index
	 * @return the parent index
	 */
	private int getParent(int index) {
		return index / 2;
	}

	/**
	 * Get left child index.
	 * 
	 * @param index
	 *            the parent index
	 * @return the left child index
	 */
	private int getLeftChild(int index) {
		return (2 * index);
	}

	/**
	 * Get right child index.
	 * 
	 * @param index
	 *            the parent index
	 * @return the right child index
	 */
	private int getRightChild(int index) {
		return (2 * index) + 1;
	}

	/**
	 * Checks if the index is a leaf.
	 * 
	 * @param index
	 *            the index to check
	 * @return if the index is a leaf
	 */
	private boolean isLeaf(int index) {
		if (index >= (heapSize / 2) && index <= heapSize) {
			return true;
		}
		return false;
	}

	/**
	 * Swap the value of position 1 with position 2.
	 * 
	 * @param position1
	 * @param position2
	 */
	private void swap(int position1, int position2) {
		int temp = heap[position1];
		heap[position1] = heap[position2];
		heap[position2] = temp;
	}

	/**
	 * @return if the heap is full.
	 */
	public boolean isFull() {
		return isFull;
	}

	/**
	 * Displays the heap in the console.
	 * This code is copy-pasted from the internet. 
	 */
	public void displayHeap() {

		int currentSize = heap.length;
		// heap format
		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0; // current item

		while (currentSize > 0) // for each heap item
		{
			if (column == 0) // first item in row?
				for (int k = 0; k < nBlanks; k++) // preceding blanks
					System.out.print(' ');
			// display item
			System.out.print(String.format("%02d", heap[j]));

			if (++j == currentSize) // done?
				break;

			if (++column == itemsPerRow) // end of row?
			{
				nBlanks /= 2; // half the blanks
				itemsPerRow *= 2; // twice the items
				column = 0; // start over on
				System.out.println(); // new row
			} else // next item on row
				for (int k = 0; k < nBlanks * 2 - 2; k++)
					System.out.print(' '); // interim blanks
		} // end for
		System.out.println("\n-----");
	}

}
