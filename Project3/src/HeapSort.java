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

    /**
     * This is the entry point of the application
     * 
     * @param args
     *            Command line arguments
     */
    public static void main(String[] args) {
        // do things and stuff
    }
    
    @SuppressWarnings("rawtypes")
	public static MaxHeap heapsort(Comparable[] A) {
    	  // The heap constructor invokes the buildheap method
    	  MaxHeap H = new MaxHeap(A, A.length, A.length);
    	  for (int i=0; i<A.length; i++) {  // Now sort
    	    H.removeMax(); // Removemax places max at end of heap
    	  }
    	  
    	  return H;
    }
}
