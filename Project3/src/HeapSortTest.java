import org.junit.Test;

import student.TestCase;

public class HeapSortTest extends TestCase {
	
	private HeapSort dum;
	
	public void setUp() {
		//HeapSort dum = new HeapSort();
	}

    /**
     * An artificial test to get initial coverage for the
     * main method. Delete or modify this test.
     */
    @Test
    public void testMain() {
        //HeapSort dum = new HeapSort();
        assertNotNull(dum);
        //HeapSort.main(new String[3]);
        assertEquals(systemOut().getHistory(), ""); // check that nothing was printed out
    }
    
    
    public void testHeapSort() {
    	
		int[] arr = {5, 2, 4, 12, 6, 3, 1, 56, 32, 523, 234, 134, 12314};
    	
    	int[] sorted = MaxHeap.sort(arr);
    	
    	String printableArr = "[";
    	
    	for(int i = 0; i < sorted.length; i++) {
    		printableArr += sorted[i] + ", ";
    	}
    	
    	System.out.println(printableArr);
    }

}
