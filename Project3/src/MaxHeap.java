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
	 * @param records records
	 */
	public MaxHeap(Records records)
	{
		this.records = records;
		size = records.size();
		heapify();
	}
	

    /**
     * isLeaf
     * 
     * @param index index
     * @return boolean
     */
    public boolean isLeaf(int index) {
        return ((index >= size() / 2) && (index < size()));
    }

	/**
	 * leftChild
	 * @param index index of parent
	 * @return int
	 */
	public int leftChild(int index) {
		assert (index < size() / 2) : "Position has no left child";
		return 2 * index + 1;
	}

	/**
	 * rightChild
	 * @param index index of parent
	 * @return int
	 */
	public int rightChild(int index) {
		assert (index < (size() - 1) / 2) : "Position has no right child";
		return 2 * index + 2;
	}

	/**
	 * parent
	 * @param index child index
	 * @return int
	 */
	public int parent(int index) {
		assert (index > 0) : "Position has no parent";
		return (index - 1) / 2;
	}
	
	/**
	 * Gets numRecords
	 * @return int
	 */
	public int size()
	{
		return size;
	}
	
	/**
	 * Build heap
	 */
	public void heapify()
	{
		for (int i = (size / 2) - 1; i >= 0; i--) {
			siftdown(i);
		}
	}
	
	/**
	 * Sift down
	 * @param index index
	 */
	private void siftdown(int index)
	{
		assert ((index >= 0) && (index < size)) : "Illegal heap index";
		while (!isLeaf(index)) {
			int child = leftChild(index);
	
			if ((child < (size - 1)) && 
				(records.getKey(child) < records.getKey(child + 1))) {
				child++;
			}

			if (records.getKey(index) >= records.getKey(child)) { 
			    return; 
			}
			records.swap(index, child);
			index = child;
		}
	}
	
	/**
	 * RemoveMax
	 */
	public void removeMax()
	{
		assert (size > 0) : "Removing from empty heap.";
		size--;
		records.swap(0, size);
		if (size != 0) {
			siftdown(0);
		}
	}
	
}
