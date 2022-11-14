import student.TestCase;

/**
 *
 * @author Nahom Kifetew
 * @version 10/28/2021
 *
 */
public class DLListTest extends TestCase {
    /**
     * the list we will use
     */
    private DLList<String> list;
    private DLList<String> allNull;

    /**
     * run before every test case
     */
    @Override
    public void setUp() {
        list = new DLList<String>(10);
        allNull = new DLList<String>(4);
    }


    /*
     * Test the size method
     */
    public void testSize() {
        assertEquals(10, list.size());
    }


    /**
     * Tests that an IndexOutOfBounds exception is thrown when the index is
     * greater than or equal to size and less than zero
     */
    public void testRemove() {

        // list --> [C,B,A] index --> 0,1,2
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("C", list.get(0));

        Exception e = null;
        try {
            list.remove("C");
        }
        catch (Exception exception) {
            e = exception;
        }
        assertFalse(e instanceof IndexOutOfBoundsException);

    }


    /**
     * Tests the add method. Ensures that object is added to the front
     */
    public void testAdd() {
        assertNull(list.get(0));
        list.add("A");
        assertEquals("A", list.get(0));
        list.add("B");
        assertEquals("B", list.get(0));
        assertEquals("A", list.get(1));
    }


    /**
     * This tests that the add method throws a null pointer exception when
     * adding null data to the list
     */
    public void testAddNullException() {
        Exception e = null;
        try {
            list.add(null);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertFalse(e instanceof IllegalArgumentException);
    }


    /**
     * Tests get when the index is greater than or equal to size and when the
     * index is less than zero
     */
    public void testGetException() {
        Exception exception = null;
        try {
            list.get(-1);
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof IndexOutOfBoundsException);
        exception = null;
        list.add("A");
        try {
            list.get(1);
        }
        catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertFalse(exception instanceof IndexOutOfBoundsException);
    }


    /**
     * Test the makeHead method
     */
    public void testMakeHead() {
        String putOnTop = "Above Top";
        list.add("Bottom");
        list.add("Middle");
        list.add("Top");
        assertEquals("Top", list.get(0));

        list.makeHead(putOnTop);
        assertEquals("Above Top", list.get(0));
    }


    /**
     * Test the populate method
     */
    public void testPopulate() {
        allNull.populate();
        assertNull(allNull.get(0));
        assertNull(allNull.get(1));
        assertNull(allNull.get(2));
        assertNull(allNull.get(3));

        Exception exception = null;
        try {
            assertNull(allNull.get(4));
        }
        catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue(exception instanceof IndexOutOfBoundsException);

    }
}
