import java.util.Arrays;

public class RSHeap {
	public int[] heap;
	private int heapMaxSize;
	private int deadSpaceSize;

	public RSHeap(int heapSize) {
		this.heapMaxSize = heapSize;
		heap = new int[heapSize];
	}

	public void buildHeap(int[] array) {
		for (int i = 0; i < array.length; i++) {
			heap[i] = array[i];
		}
		percolateDown();
	}

	private int childLeftPosition(int parentPos) {
		return 2 * parentPos + 1;
	}

	private int childRightPosition(int parentPos) {
		return 2 * parentPos + 2;
	}

	private void swap(int position1, int position2) {
		int temp = heap[position1];
		heap[position1] = heap[position2];
		heap[position2] = temp;
	}

	private void percolateDown() {

		for (int j = heap.length-1; j >= 0; j --) {
			
			int parentPos = 0;
			if (j > 0) {
				parentPos = (j - 1) / 2;
			}

			int parent = heap[parentPos];

			int rightChildPos = childRightPosition(parentPos);
			int rightChild = heap[rightChildPos];
			System.out.println(parentPos);
			if (rightChild < parent) {
				swap(parentPos, rightChildPos);
				System.out.println(Arrays.toString(heap));
			}
			int leftChildPos = rightChildPos - 1;
			int leftChild = heap[leftChildPos];
			if (leftChild < parent) {
				swap(parentPos, leftChildPos);
				System.out.println(Arrays.toString(heap));
			}
		}
	}

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
