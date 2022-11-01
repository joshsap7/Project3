
/**
 * Stat Class
 * 
 * @author jsapirstein, nahomk
 * @version 10/25/22
 */
public class Stats {
	
    public static String fileName;
    public static int cacheHits = 0;
    public static int diskReads = 0;
    public static int diskWrites = 0;
    public static long executionTime = 0;


    /**
     * Creates the output to be written to the stat file
     *
     * @return String
     */
    public static String output()
    {
        String output = "";
        output += "\nSort on " + fileName;
        output += "\nCache Hits: " + cacheHits;
        output += "\nDisk Reads: " + diskReads;
        output += "\nDisk Writes: " + diskWrites;
        output += "\nTime is " + executionTime;

        return output;
    }
}

