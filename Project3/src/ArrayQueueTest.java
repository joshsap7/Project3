import student.TestCase;
import java.util.NoSuchElementException;

/**
 * Test class for ArrayQueue
 * 
 * @author Nahom Kifetew
 * @version 2022.11.11
 */
public class ArrayQueueTest extends TestCase {

    // Field Variables
    private ArrayQueue<String> arrayQueue;

    /**
     * Sets up
     */
    public void setUp() {
        arrayQueue = new ArrayQueue<String>();
    }


    /**
     * Tests the getLengthOfUnderlyingArray() method
     */
    public void testGetLengthOfUnderlyingArray() {

        assertEquals(21, arrayQueue.getLengthOfUnderlyingArray());
    }


    /**
     * Tests the getSize() method
     */
    public void testGetSize() {
        // Empty Array
        assertEquals(0, arrayQueue.getSize());

        // After adding element
        arrayQueue.enqueue("Nahom");
        arrayQueue.enqueue("Nahom 2");

        assertEquals(2, arrayQueue.getSize());
    }


    /**
     * Tests the isEmpty() method
     */
    public void testIsEmpty() {

        // Empty array
        assertTrue(arrayQueue.isEmpty());

        // After addition
        arrayQueue.enqueue("Nahom");
        assertFalse(arrayQueue.isEmpty());
    }


    /**
     * Test the enqueue() method
     */
    public void testEnqueue() {

        assertEquals(0, arrayQueue.getSize());

        arrayQueue.enqueue("Element 1");
        arrayQueue.enqueue("Element 2");
        arrayQueue.enqueue("Element 3");

        assertEquals(3, arrayQueue.getSize());

    }


    /**
     * Test the ensureCapacity() method
     */
    public void testEnsureCapacity() {

        Exception exp = null;
        try {
            for (int i = 0; i < 1000; i++) {
                arrayQueue.enqueue("Element");
            }
        }
        catch (IllegalStateException isc) {
            exp = isc;
        }

        assertFalse(exp instanceof IllegalStateException);

    }


    /**
     * Test the dequeue() method
     */
    public void testDequeue() {

        arrayQueue.enqueue("Element 1");
        arrayQueue.enqueue("Element 2");
        arrayQueue.enqueue("Element 3");
        assertEquals(3, arrayQueue.getSize());

        assertEquals("Element 1", arrayQueue.dequeue());
        assertEquals(2, arrayQueue.getSize());

        assertEquals("Element 2", arrayQueue.dequeue());
        assertEquals(1, arrayQueue.getSize());

        assertEquals("Element 3", arrayQueue.dequeue());
        assertEquals(0, arrayQueue.getSize());
    }


    /**
     * Test the dequeue() method on an empty array
     */
    public void testDequeueException() {

        Exception thrown = null;
        try {

            arrayQueue.dequeue();
        }
        catch (Exception exp) {

            thrown = exp;
        }

        assertNotNull(thrown);
        assertTrue(thrown instanceof NoSuchElementException);
    }


    /**
     * Test the getFront() method
     */
    public void testGetFront() {

        arrayQueue.enqueue("Element 1");
        arrayQueue.enqueue("Element 2");
        arrayQueue.enqueue("Element 3");

        assertEquals("Element 1", arrayQueue.getFront());
        arrayQueue.dequeue();
        assertEquals("Element 2", arrayQueue.getFront());
    }


    /**
     * Test the getFront() method
     */
    public void testGetFrontException() {

        Exception thrown = null;
        try {

            arrayQueue.getFront();
        }
        catch (Exception exp) {

            thrown = exp;
        }

        assertNotNull(thrown);
        assertTrue(thrown instanceof NoSuchElementException);
    }


    /**
     * Test the clear() method
     */

    public void testClear() {

        arrayQueue.enqueue("Element 1");
        arrayQueue.enqueue("Element 2");
        arrayQueue.enqueue("Element 3");

        arrayQueue.clear();
        assertEquals(0, arrayQueue.getSize());
    }


    /**
     * Test the toArray() method on empty array
     */
    public void testToArrayException() {

        Exception thrown = null;
        try {

            arrayQueue.toArray();
        }
        catch (Exception exp) {

            thrown = exp;
        }

        assertNotNull(thrown);
        assertTrue(thrown instanceof NoSuchElementException);
    }


    /**
     * Test the toArray() method
     */
    public void testToArray() {

        arrayQueue.enqueue("Element 1");
        arrayQueue.enqueue("Element 2");
        arrayQueue.enqueue("Element 3");

        String[] stringArray = { "Element 1", "Element 2", "Element 3" };

        for (int i = 0; i < stringArray.length; i++) {

            assertEquals(stringArray[i], arrayQueue.toArray()[i]);
        }

    }


    /**
     * Tests the toString() method
     */
    public void testToString() {

        // Empty array
        assertEquals("[]", arrayQueue.toString());

        arrayQueue.enqueue("Element 1");
        arrayQueue.enqueue("Element 2");
        arrayQueue.enqueue("Element 3");

        assertEquals("[Element 1, Element 2, Element 3]", arrayQueue
            .toString());
    }


    /**
     * Tests the equals() method
     */
    public void testEquals() {

        // Test itself
        assertTrue(arrayQueue.equals(arrayQueue));

        // Test null
        ArrayQueue<String> nullArrayQueue = null;
        assertFalse(arrayQueue.equals(nullArrayQueue));

        // Test different objects class
        Object obj = new Object();
        assertFalse(arrayQueue.equals(obj));

        // Test same class objects with different params
        ArrayQueue<String> newArrayQueue = new ArrayQueue<String>();
        arrayQueue.enqueue("Element 1");
        arrayQueue.enqueue("Element 2");
        arrayQueue.enqueue("Element 3");

        // Same size
        newArrayQueue.enqueue("Element 4");
        newArrayQueue.enqueue("Element 5");
        newArrayQueue.enqueue("Element 6");
        assertFalse(arrayQueue.equals(newArrayQueue));

        // Different size
        newArrayQueue.enqueue("Element 7");
        assertFalse(arrayQueue.equals(newArrayQueue));

        // Test same class objects with same params
        ArrayQueue<String> newArrayQueue1 = new ArrayQueue<String>();

        newArrayQueue1.enqueue("Element 1");
        newArrayQueue1.enqueue("Element 2");
        newArrayQueue1.enqueue("Element 3");
        assertTrue(arrayQueue.equals(newArrayQueue1));
        newArrayQueue1.clear();

        newArrayQueue1.enqueue("Element 1");
        newArrayQueue1.enqueue("Element 4");
        newArrayQueue1.enqueue("Element 3");
        assertFalse(arrayQueue.equals(newArrayQueue1));

    }

}
