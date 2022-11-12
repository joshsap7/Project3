import java.util.NoSuchElementException;

/**
 * Array based queue class from CS 2114
 * 
 * @author Nahom Kifetew
 * @version 2022.11.11
 * @param <T>
 *            generic type
 */
public class ArrayQueue<T> {

    // Field Variables
    /**
     * Default capacity if not set
     */
    public static final int DEFAULT_CAPACITY = 20;
    private T[] queue;
    private int enqueueIndex;
    private int dequeueIndex;
    private int size;

    /**
     * Default constructor
     */
    public ArrayQueue() {

        this(DEFAULT_CAPACITY);
    }


    /**
     * Constructor
     * 
     * @param capacity
     *            of planet
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {

        queue = (T[])new Object[DEFAULT_CAPACITY + 1];
        size = 0;
        enqueueIndex = 0;
        dequeueIndex = 0;
    }


    /**
     * Getter method
     * 
     * @return length of the queue
     */
    public int getLengthOfUnderlyingArray() {

        return queue.length;
    }


    /**
     * Getter method
     * 
     * @return size of queue
     */
    public int getSize() {

        return size;
    }


    /**
     * @return True if queue is empty and False otherwise
     */
    public boolean isEmpty() {

        return size == 0;
    }


    /**
     * @return True if queue is full and False otherwise
     */
    private boolean isFull() {

        return queue.length == size;
    }


    /**
     * Adds elements to out array queue
     * 
     * @param anEntry
     *            element being added
     */
    public void enqueue(T anEntry) {

        ensureCapacity();
        queue[enqueueIndex] = anEntry;
        enqueueIndex = incrementIndex(enqueueIndex);
        size++;
    }


    /**
     * This helper method can be used to upgrade the length of the underlying
     * array when the queue is full.
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {

        if (isFull()) {

            throw new IllegalStateException();
        }

        if (size == (queue.length - 1)) {

            T[] newQueue = (T[])new Object[size * 2 + 1];

            int count = 0;
            int dequeue = dequeueIndex;

            while (dequeue != enqueueIndex) {

                newQueue[count] = queue[dequeue];
                count++;
                dequeue = incrementIndex(dequeue);
            }

            dequeueIndex = 0;
            enqueueIndex = size;
            queue = newQueue;
        }

    }


    /**
     * Removes elemets
     * 
     * @return the removed element
     */
    public T dequeue() {

        if (isEmpty()) {

            throw new NoSuchElementException("Queue is Empty");
        }

        T head = queue[dequeueIndex];
        queue[enqueueIndex] = null;
        dequeueIndex = incrementIndex(dequeueIndex);
        size--;

        return head;

    }


    /**
     * Getter method
     * 
     * @return the front element
     */
    public T getFront() {

        if (isEmpty()) {

            throw new NoSuchElementException("Queue is Empty");
        }

        return queue[dequeueIndex];

    }


    /**
     * Clears the array queue
     */
    @SuppressWarnings("unchecked")
    public void clear() {

        dequeueIndex = 0;
        enqueueIndex = 0;
        size = 0;
        queue = (T[])new Object[DEFAULT_CAPACITY + 1];
    }


    /**
     * optional helper method, but will help you with the circular queue
     * wrapping logic
     * 
     * @param index
     *            position in the queue
     * @return an int after incrementation
     */
    private int incrementIndex(int index) {

        return ((index + 1) % queue.length);
    }


    /**
     * Gives client code the option of accessing the data in the queue without
     * interfering with the integrity of the queue.
     * 
     * @return underlying array entries start at index 0, which might not be
     *         true of the circular array
     */
    @SuppressWarnings("unchecked")
    public Object[] toArray() {

        if (isEmpty()) {
            throw new NoSuchElementException("Queue is Empty");
        }

        int pos = dequeueIndex;
        int tempPos = 0;

        T[] toArray = (T[])new Object[size];

        while (pos != enqueueIndex) {
            toArray[tempPos] = queue[pos];

            tempPos++;
            pos = incrementIndex(pos);
        }

        return toArray;

    }


    /**
     * Concatenate the toString()of each Object in the ArrayQueue separated by a
     * comma and space
     * 
     * @return concatenated strings
     */
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        int count = 0;
        int pos = dequeueIndex;

        while (count < size) {

            sb.append(queue[pos]);
            pos = incrementIndex(pos);
            count++;

            if (count != size) {
                sb.append(", ");
            }

        }

        sb.append("]");

        return sb.toString();
    }


    /**
     * For two ArrayQueues to be equal, they have to contain the same elements
     * in the same order.
     * 
     * @param obj
     *            to be compared
     * @return True if two array queues are equal, False otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (obj.getClass() == this.getClass()) {

            ArrayQueue<T> other = (ArrayQueue<T>)obj;

            if (size == other.getSize()) {

                for (int i = 0; i < this.size; i++) {

                    T element = queue[(dequeueIndex + i) % queue.length];
                    T element2 = other.queue[(other.dequeueIndex + i)
                        % other.queue.length];

                    if (!element.equals(element2)) {
                        return false;
                    }

                }

                return true;
            }

        }

        return false;

    }

}