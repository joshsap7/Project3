import java.io.IOException;

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
     * @throws Exception 
     */
    public void testMain() throws Exception {
        HeapSort dum = new HeapSort();
        ByteFileGenerator bfg = new ByteFileGenerator();
        CheckFile cf = new CheckFile();
        bfg.generate(1024);
        
        String[] strs = new String[3];
        
        strs[0] = "input_sample.txt";
        strs[1] = "4";
        strs[2] = "output_sample.txt";
        
        HeapSort.main(strs);
        
        assertTrue(cf.checkFile(strs[0]));
        
        //assertEquals(systemOut().getHistory(), ""); // check that nothing was printed out
    }
    

}
