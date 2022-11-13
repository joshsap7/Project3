/**
 * Stat class
 * 
 * @author Josh Sapirstein (jsapirstein)
 * @author Nahom Kifetew (nahomk)
 * @version 11/1/22
 */
public class Stats {

    /**
     * Private fields that keep track of all stats
     */
    private static String fileName;
    private static int cacheHit = 0;
    private static int cacheMiss = 0;
    private static int diskRead = 0;
    private static int diskWrite = 0;
    private static long time = 0;
    
    /**
     * Output
     * @return
     */
    public static String output() {

        String output = "Heap sort statistics";
        output += "\nData file name: " + fileName;
        output += "\nCache hits: " + cacheHit;
        output += "\nCache misses: " + cacheMiss;
        output += "\nDisk reads: " + diskRead;
        output += "\nDisk writes: " + diskWrite;
        output += "\nSort execution time: " + time + "ms";
        output += "\n\n";

        return output;
    }

    /**
     * Sets the file name
     * 
     * @param name name
     */
    public static void setFileName(String name) {
        fileName = name;
    }

    /**
     * Sets the time
     * 
     * @param t t
     */
    public static void setTime(long t) {
        time = t;
    }

    /**
     * Increments cacheHit
     */
    public static void incrementCH() {
        cacheHit++;
    }

    /**
     * Increments cacheMiss
     */
    public static void incrementCM() {
        cacheMiss++;
    }

    /**
     * Increments diskRead
     */
    public static void incrementDR() {
        diskRead++;
    }

    /**
     * Increments disk write
     */
    public static void incrementDW() {
        diskWrite++;
    }
}