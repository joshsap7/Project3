// Max-heap implementation
// use `java -ea` to enable assertions that check valid heap positions
public class MaxHeap {
  @SuppressWarnings("rawtypes")
private Comparable[] heap; // Pointer to the heap array
  private int maxSize; // Maximum size of the heap
  private int n; // Number of things now in heap

  // Constructor supporting preloading of heap contents
  @SuppressWarnings("rawtypes")
MaxHeap(Comparable[] h, int heapSize, int max) {
    heap = h;
    n = heapSize;
    maxSize = max;
    buildHeap();
  }

  // Return current size of the heap
  public int heapSize() { return n; }

  // Return true if pos a leaf position, false otherwise
  public boolean isLeaf(int pos) 
  { return (n / 2 <= pos ) && (pos < n); }

  // Return position for left child of pos
  public static int leftChild(int pos) 
  { return 2 * pos + 1; }

  // Return position for right child of pos
  public static int rightChild(int pos) 
  { return 2 * pos + 2; }

  // Return position for parent
  public static int parent(int pos) 
  { return (pos - 1) / 2; }

  // Insert val into heap
  public void insert(Comparable<?> key) {
    assert n < maxSize : "Heap is full; cannot insert";
    heap[n] = key;
    n++;
    siftUp(n - 1);
  }

  // Heapify contents of Heap
  private void buildHeap() {
    for (int i = parent(n - 1); i >= 0; i--) {
      siftDown(i);
    }
  }

  // Moves an element down to its correct place
  private void siftDown(int pos) {
    //assert (0 <= pos && pos < n) : "Invalid heap position";
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

  // Moves an element up to its correct place
  private void siftUp(int pos) {
    assert (0 <= pos && pos < n) : "Invalid heap position";
    while (pos > 0) {
      int parent = parent(pos);
      if (isGreaterThan(parent, pos)) {
        return; // stop early
      }
      swap(pos, parent);
      pos = parent; // keep sifting up
    }
  }

  // Remove and return maximum value
  public Comparable<?> removeMax() {
    assert n > 0 : "Heap is empty; cannot remove";
    n--;
    swap(0, n);  // Swap maximum with last value
    siftDown(0); // Put new heap root val in correct place
    return heap[n];
  }

  // Remove and return element at specified position
  public Comparable<?> remove(int pos) {
    assert (0 <= pos && pos < n) : "Invalid heap position";
    n--;
    swap(pos, n); // Swap with last value
    update(pos);  // Move other value to correct position
    return heap[n];
  }

  // Modify the value at the given position
  public void modify(int pos, Comparable<?> newVal) {
    assert (0 <= pos && pos < n) : "Invalid heap position";
    heap[pos] = newVal;
    update(pos);
  }

  // The value at pos has been changed, restore the heap property
  private void update(int pos) {
    siftUp(pos);   // priority goes up
    siftDown(pos); // unimportant goes down
  }

  // swaps the elements at two positions
  private void swap(int pos1, int pos2) {
    Comparable<?> temp = heap[pos1];
    heap[pos1] = heap[pos2];
    heap[pos2] = temp;
  }

  // does comparison used for checking heap validity
  @SuppressWarnings("unchecked")
  private boolean isGreaterThan(int pos1, int pos2) {
    return heap[pos1].compareTo(heap[pos2]) > 0;
  }

  @SuppressWarnings("rawtypes")
  public Comparable[] getHeap() {
	  return heap;
  }
  
  public static int[] sort(int arr[])
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

  // To heapify a subtree rooted with node i which is
  // an index in arr[]. n is size of heap
  public static void heapify(int arr[], int n, int i)
  {
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
}
