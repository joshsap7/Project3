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
 * Contains the main method where the main circuit of the program is done. 
 * Program recieves a file of binary data and sorts it accordingly with Heap sort.
 * 
 * 
 * @author Josh Sapirstein (jsapirstein)
 * @author Nahom Kifetew (nahomk)
 * @version 11/1/22
 */
public class HeapSort {
	/**
	 * The entry point for this program.
	 * @param arg The array of command line arguments passed in.
	 */
	public static void main(String[] arg)
	{
		// Set up the argument names to be more meaningful.
		String datafile = arg[0];
		int num_buffers = Integer.parseInt(arg[1]);
		String statfile = arg[2];
		
		// Inform Stats of the datafile name
		Stats.fileName = datafile;
		
		// Load up the Record Array
		Records array = null;
		try {
			array = new Records(datafile, num_buffers);
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + datafile);
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		// Start the timer.
		long t1 = System.currentTimeMillis();
		
		// Perform the sort.
		sort(array);
		array.flush();
		
		// Stop the timer.
		Stats.time = System.currentTimeMillis() - t1;
		
		//Append stats file
		try {
			// Make sure the file exists.
			File stats = new File(statfile);
			stats.createNewFile();
			
			// Write the data.
			FileWriter statfileWriter = new FileWriter(stats, true);
			BufferedWriter statOut = new BufferedWriter(statfileWriter);
			statOut.write(Stats.output());
			System.out.print(Stats.output());
			statOut.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Print the output of the sort. The first record of every block.
		final int RECS_PER_ARRAY = 1024;
		for (int i = 0; i < (array.size() / RECS_PER_ARRAY); i++) {
			// Print the first record of the i-th block.
			System.out.printf("%5d %5d ", array.getKey(i * RECS_PER_ARRAY), 
										  array.getValue(i * RECS_PER_ARRAY));
			
			if (((i + 1) % 8 == 0)) { System.out.print("\n"); } // new line
		}
		System.out.print("\n"); // Final new line.
	}

	/**
	 * Perform the heap sort on the record array passed in.
	 * @param array The array to sort.
	 */
	public static void sort(Records array)
	{
		int size = array.size();
		MaxHeap heap = new MaxHeap(array);
		for (int i = 0; i < size; i++) {
			heap.removeMax();
		}
	}
}
