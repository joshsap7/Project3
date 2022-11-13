
import student.TestCase;

/**
 * HeapSortTest
 * @author joshuasapirstein
 * @version 11/11/22
 */
public class HeapSortTest extends TestCase {
	
	/**
	 * Setup
	 */
	public void setUp() {
		//HeapSort dum = new HeapSort();
	}

    /**
     * An artificial test to get initial coverage for the main method. Delete or
     * modify this test.
     * 
     * @throws Exception
     */
    public void testMain() throws Exception {
        
        ByteFileGenerator bfg = new ByteFileGenerator();
        CheckFile cf = new CheckFile();
        bfg.generate(1024);

        String[] strs = new String[3];

        strs[0] = "input_sample2.txt";
        strs[1] = "12";
        strs[2] = "output_sample.txt";

        HeapSort.main(strs);

        assertTrue(cf.checkFile(strs[0]));

    }
    

}
