import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;


//On my honor:
// - I have not used source code obtained from another student,
//   or any other unauthorized source, either modified or
//   unmodified.
//
// - All source code and documentation used in my program is
//   either my original work, or was derived by me from the
//   source code published in the textbook for this course.
//
// - I have not discussed coding details about this project
//   with anyone other than my partner (in the case of a joint
//   submission), instructor, ACM/UPE tutors or the TAs assigned
//   to this course. I understand that I may discuss the concepts
//   of this program with other students, and that another student
//   may help me debug my program so long as neither of us writes
//   anything during the discussion or modifies any computer file
//   during the discussion. I have violated neither the spirit nor
//   letter of this restriction.
/**
 * Contains the main method
 * Program recieves a file of binary data 
 * and sorts it accordingly with Heap sort.
 * 
 * 
 * @author Josh Sapirstein (jsapirstein)
 * @author Nahom Kifetew (nahomk)
 * @version 11/1/22
 */
public class HeapSort {
	
	/**
	 * Main method
	 * @param arg
	 * 			Arguments
	 */
	public static void main(String[] arg)
	{
		String data = arg[0];
		int numBuffers = Integer.parseInt(arg[1]);
		String stat = arg[2];
		Stats.setFileName(data);
		
		Records records = null;
		
		try {
			
			records = new Records(data, numBuffers);
			
		} 
		catch (FileNotFoundException e)
		{
			//System.err.println("File not found: " + data);
			System.exit(-1);
		} 
		catch (IOException e) {
			
			//e.printStackTrace();
			System.exit(-1);
		}
		
		long start = System.currentTimeMillis();
		
		//Sort
		sort(records);
		records.flush();
		
		long time = System.currentTimeMillis() - start;
		Stats.setTime(time);
		
		try {
			File stats = new File(stat);
			stats.createNewFile();
			FileWriter statfileWriter = new FileWriter(stats, true);
			BufferedWriter statOut = new BufferedWriter(statfileWriter);
			statOut.write(Stats.output());
			statOut.flush();
		} 
		catch (IOException e) {

			//e.printStackTrace();
		}
		
//		final int RECS_PER_ARRAY = 1024;
//		for (int i = 0; i < (records.size() / RECS_PER_ARRAY); i++) {
//			// Print the first record of the i-th block.
//			System.out.printf("%5d %5d ", records.getKey(i * RECS_PER_ARRAY), 
//										  records.getValue(i * RECS_PER_ARRAY));
//			
//			if (((i + 1) % 8 == 0)) { System.out.print("\n"); } // new line
//		}
//		System.out.print("\n"); // Final new line.
	}

	/**
	 * Heapsort
	 * @param records
	 * 				records
	 */
	public static void sort(Records records)
	{
		int size = records.size();
		MaxHeap heap = new MaxHeap(records);
		for (int i = 0; i < size; i++) {
			heap.removeMax();
		}
	}
}
