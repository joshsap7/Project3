import java.io.File;

// Max-heap implementation
// use `java -ea` to enable assertions that check valid heap positions
public class MaxHeap {
  private File heap; // Pointer to the heap array
  private final static int BLOCK_SIZE = 4096; // Maximum size of the heap //maxSize =
  private int n; // Number of things now in heap
  private File file;
  private int numRecords;
  private BufferPool buffPool;

  // Constructor supporting preloading of heap contents
  MaxHeap(String fileName) {
	  heap = new File(fileName);
	  n = 0;
	  buildHeap();
  }

  // Return current size of the heap
  public int heapSize() { return n; }

  // Return true if pos a leaf position, false otherwise
  public boolean isLeaf(int pos) { 
	  return (n / 2 <= pos ) && (pos < n); 
  }

	// Return position for left child of pos
  public static int leftChild(int pos) {
	  return 2 * pos + 1;
  }

  // Return position for right child of pos
  public static int rightChild(int pos) {
	  return 2 * pos + 2;
  }

  // Return position for parent
  public static int parent(int pos) {
	  return (pos - 1) / 2; 
  }

  // Heapify contents of Heap
  private void buildHeap() {
    for (int i = parent(n - 1); i >= 0; i--) {
      siftDown(i);
    }
  }

  // Moves an element down to its correct place
  private void siftDown(int pos) {
    while (!isLeaf(pos)) {
      int child = leftChild(pos);
      if ((child + 1 < n) && isGreaterThan(child + 1, child)) {
        child = child + 1; // child is now index with the greater value
      }
      if (!isGreaterThan(child, pos)) {
        return; // stop early
      }
      swap(pos, child);
      pos = child; // keep sifting down
    }
  }


  // swaps the elements at two positions
  private void swap(int pos1, int pos2) {
    Comparable temp = heap[pos1];
    heap[pos1] = heap[pos2];
    heap[pos2] = temp;
  }

  // does comparison used for checking heap validity
  private boolean isGreaterThan(int pos1, int pos2) {
    return heap[pos1].compareTo(heap[pos2]) > 0;
  }
}