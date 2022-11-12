/**
 * Stat class
 * 
 * @author Josh Sapirstein (jsapirstein)
 * @author Nahom Kifetew (nahomk)
 * @version 11/1/22
 */
public class Stats {
	
	/**
	 * Public fields that keep track of all stats
	 */
	public static String fileName;
	public static int cacheHit = 0;
	public static int cacheMiss = 0;
	public static int diskRead = 0;
	public static int diskWrite = 0;
	public static long time = 0;
	
	public static String output() {
		
		String output = "Heap sort statistics";
		output += "\nData file name: " + fileName;
		output += "\nCache hits: " + cacheHit;
		output += "\nCache misses: " + cacheMiss;
		output += "\nDisk reads: " + diskRead;
		output += "\nDisk writes: " + diskWrite;
		output += "\nSort execution time: " + time + "ms";
		
		return output;
	}
}