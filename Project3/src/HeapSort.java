import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

// On my honor:
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
 //  - Josh Sapirstein (jsapirstein)
/**
 * A memory manager package for variable length
 * records, and a spatial data structure to support
 * geographical queryies.
 * 
 * @author jsapirstein
 * @version 10/25/22
 */
public class HeapSort {
	
	private File file;
	private int numRecords;
	private BufferPool buffPool;
	private int numBuffer;
	private final static int BLOCK_SIZE = 4096;
	private final static int RECORD_SIZE = 4;
	
	/**
	 * HeapSort constructor
	 * @param fileName
	 * 					name of file
	 * @param numBuffer
	 * 					number of buffers
	 * @throws IOException
	 */
	public HeapSort(String fileName, int numBuffer) throws IOException {
		
		file = new File(fileName);
		buffPool = new BufferPool(file, numBuffer);
		this.setNumBuffer(numBuffer);
		numRecords = buffPool.getNumBlocks() * BLOCK_SIZE / RECORD_SIZE;
		
		
	}
	
	/**
     * This is the entry point of the application
     * 
     * @param args
     *            Command line arguments
     */
    public static void main(String[] args) throws IOException {
    	
    	String fileName = args[0];
    	int numBuffer = Integer.parseInt(args[1]);
    	String statFile = args[2];
    	
    	Stats.fileName = fileName;
    	
    	long start = System.currentTimeMillis();
    	
    	HeapSort heap = new HeapSort(fileName, numBuffer);
    	
    	heap.sort(null);
    	heap.flush();
    	
    	long end = System.currentTimeMillis();
    	
    	Stats.executionTime = end - start;
    	
    	File stat = new File(statFile);
    	stat.createNewFile();
    	FileWriter statFileWriter = new FileWriter(stat, true);
    	BufferedWriter  statOut = new BufferedWriter(statFileWriter);
    	statOut.write(Stats.output());
    	statOut.flush();
    	statOut.close();
        
    }
	
	/**
	 * Gets the number of records in the file
	 * @return int
	 */
	public int getNumRecords() {
		return numRecords;
	}
	
	/**
	 * Gets the numBuffer
	 * @return int
	 */
	public int getNumBuffer() {
		return numBuffer;
	}

	/**
	 * Sets the numBuffer
	 * @param numBuffer
	 * 					the numBuffer to set 
	 */
	public void setNumBuffer(int numBuffer) {
		this.numBuffer = numBuffer;
	}
	
	static void heapsort(Comparable[] A) {
		  // The heap constructor invokes the buildheap method
		  MaxHeap H = new MaxHeap(A, A.length, A.length);
		  for (int i=0; i<A.length; i++) {  // Now sort
		    H.removemax(); // Removemax places max at end of heap
		  }
		}

	//TODO
	/**
	 * Sorts the file
	 * @param arr
	 * @return
	 */
	public int[] sort(int arr[])
	  {
	      int n = arr.length;

	      // Build heap (rearrange array)
	      for (int i = n / 2 - 1; i >= 0; i--)
	          heapify(arr, n, i);

	      // One by one extract an element from heap
	      for (int i=n-1; i>=0; i--)
	      {
	          // Move current root to end
	          int temp = arr[0];
	          arr[0] = arr[i];
	          arr[i] = temp;

	          // call max heapify on the reduced heap
	          heapify(arr, i, 0);
	      }
	      
	      return arr;
	  }

	  //TODO
	  // To heapify a subtree rooted with node i which is
	  // an index in arr[]. n is size of heap
	  public void heapify(int arr[], int n, int i)
	  {
		  //Buffer largest = buffPool.getBuffer(1);
		  
	      int largest = i;  // Initialize largest as root
	      int l = 2*i + 1;  // left = 2*i + 1
	      int r = 2*i + 2;  // right = 2*i + 2

	      // If left child is larger than root
	      if (l < n && arr[l] > arr[largest])
	          largest = l;

	      // If right child is larger than largest so far
	      if (r < n && arr[r] > arr[largest])
	          largest = r;

	      // If largest is not root
	      if (largest != i)
	      {
	          int swap = arr[i];
	          arr[i] = arr[largest];
	          arr[largest] = swap;

	          // Recursively heapify the affected sub-tree
	          heapify(arr, n, largest);
	      }
	  }
	
	public void flush() {
		
	}
	
	public void output() {
		
	}


}
