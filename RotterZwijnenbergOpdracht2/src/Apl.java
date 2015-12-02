import java.util.Arrays;

public class Apl {

	public static void main(String[] args) {
		 RSHeap rsHeap = new RSHeap(15);
		 int[] array = new int[]{2,5,8,6,4,9,7,0,3,10,11,12,14,15,1};
		 System.out.println(Arrays.toString(array));
		 rsHeap.buildHeap(array);		 
		 System.out.println(Arrays.toString(rsHeap.heap));
		 rsHeap.displayHeap();
	}
 
}
