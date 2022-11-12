/**
 * Edited version of the MaxHeap class for OpenDSA
 * 
 * @author Josh Sapirstein (jsapirstein)
 * @author Nahom Kifetew (nahomk)
 * @version 11/1/22
 */
public class MaxHeap {
	
	/**
	 * Private fields of a MaxHeap
	 */
	private Records records;
	private int size;
	
	/**
	 * Creates a new Max heap
	 * @param records
	 */
	public MaxHeap(Records records)
	{
		this.records = records;
		size = records.size();
		heapify();
	}

	/**
	 * Checks if that node is a leaf
	 * @param pos
	 * 			the position
	 * @return boolean
	 */
	public boolean isLeaf(int pos) {
		
		return ((pos >= size()/2) && (pos < size()));
		
	}

	/**
	 * Gets the left child of that position
	 * @param pos
	 * 			the position
	 * @return int
	 */
	public static int leftChild(int pos) {
		
		return 2 * pos + 1;
	}

	/**
	 * Gets the right child of that position
	 * @param pos
	 * 			the position
	 * @return int
	 */
	public static int rightChild(int pos) {
		
		return 2 * pos + 2;
	}

	/**
	 * Gets the parent of that position
	 * @param pos
	 * 			the position
	 * @return int
	 */
	public int parent(int pos) {
		
		return (pos - 1) / 2;
	}
	
	/**
	 * Gets the number of records in the heap
	 * @return int
	 */
	public int size()
	{
		return size;
	}
	
	/**
	 * Heapifies the file
	 */
	public void heapify()
	{
		for (int i = (size/2) - 1; i >= 0; i--) {
			siftdown(i);
		}
	}
	
	/**
	 * Moves a record to the correct place
	 * @param index
	 */
	private void siftdown(int pos)
	{
		assert ((pos >= 0) && (pos< size)) : "Illegal heap position";
		
		while (!isLeaf(pos)) {
			int child = leftChild(pos);
			
			if ((child < (size - 1)) && 
				(records.getKey(child) < records.getKey(child + 1))) {
				child++;
			}
			
			if (records.getKey(pos) >= records.getKey(child)) { 
				return; 
			}
			
			records.swap(pos, child);
			pos = child;
		}
	}
	
	/**
	 * Remove the max record of the Heap.
	 * Decrement the apparent size of the heap by one, Move the top item of
	 * the heap to the end by swapping with the 'last' item in physical order,
	 * decrement the apparent size of the heap by one, and reheapify by 
	 * sifting down the new root of the heap.
	 */
	public void removeMax()
	{
		assert (size > 0) : "Removing from empty heap.";
		
		// Decrement the size of the heap
		size--;
		
		// Swap the root with the last item in the array
		records.swap(0, size);
		
		// As long as we aren't the last element, we need to siftdown
		if (size != 0) {
			siftdown(0);
		}
	}
	
}
