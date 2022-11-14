/**
 * 
 * @author nahom
 *
 * @param <E>
 */
public class DLList<E> {
    /**
     * How many nodes are in the list
     */
    private int size;

    /**
     * The first node in the list. THIS IS A SENTINEL NODE AND AS SUCH DOES NOT
     * HOLD ANY DATA. REFER TO init()
     */
    private Node<E> head;

    /**
     * The last node in the list. THIS IS A SENTINEL NODE AND AS SUCH DOES NOT
     * HOLD ANY DATA. REFER TO init()
     */
    private Node<E> tail;

    /**
     * Create a new DLList object.
     */
    public DLList(int size) {

        this.size = size;
        init();
    }


    /**
     * Initializes the object to have the head and tail nodes
     */
    private void init() {
        head = new Node<E>(null);
        tail = new Node<E>(null);
        head.setNext(tail);
        tail.setPrevious(head);
        populate();
    }


    /**
     * Gets the number of elements in the list
     *
     * @return the number of elements
     */
    public int size() {
        return size;
    }


    /*
     * Populates the linkedlist with nulls to begin with
     */
    public void populate() {
        int counter = 0;
        while (counter < size) {
            Node<E> node = new Node<E>(null);
            head.next().setPrevious(node);
            node.setNext(head.next());
            node.setPrevious(head);
            head.setNext(node);
            counter++;
        }
    }


    /**
     * Gets the object at the given position
     *
     * @param index
     *            where the object is located
     * @return The object at the given position
     * @throws IndexOutOfBoundsException
     *             if there no node at the given index
     */
    public E get(int index) {
        return getNodeAtIndex(index).getData();
    }


    /**
     * 
     * @param data
     * @return E
     *         the last element
     */
    public E add(E obj) {

        Node<E> node = new Node<E>(obj);
        node.setPrevious(head);
        node.setNext(head.next());
        head.next().setPrevious(node);
        head.setNext(node);

        Node<E> last_element = tail.previous();
        last_element.previous().setNext(tail);
        tail.setPrevious(last_element.previous());

        return (E)last_element.getData();
    }


    /**
     * gets the node at that index
     * 
     * @param index
     * @return node at index
     */
    private Node<E> getNodeAtIndex(int index) {
        if (index < 0 || size() <= index) {
            throw new IndexOutOfBoundsException("No element exists at "
                + index);
        }
        Node<E> current = head.next(); // as we have a sentinel node
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        return current;
    }


    /**
     * Take a node, and remove it from its current position in the list, and
     * move it to the head of the list.
     * 
     * @param node
     *            The node to promote
     */
    private void prioritize(Node<E> node) {
        // Remove from the current position
        deleteNode(node);

        // Insert at head.
        node.setPrevious(head);
        node.setNext(head.next());
        head.next().setPrevious(node);
        head.setNext(node);
    }


    /**
     * Makes given node a head node
     * 
     * @param data
     *            data that will be the new head
     * @return tail
     */
    public E makeHead(E data) {
        // Always insert on null insertions.
        if (data == null) {
            return add(data);
        }

        Node<E> current = head.next();
        while (!current.equals(tail)) {
            if (data.equals(current.getData())) {
                prioritize(current);
                return null;
            }

            current = current.next();
        }

        return add(data);
    }


    /**
     * Removes the first object in the list that .equals(obj)
     *
     * @param obj
     *            the object to remove
     * @return true if the object was found and removed
     */

    public boolean remove(E obj) {
        Node<E> current = head.next();
        while (!current.equals(tail)) {
            if (current.getData().equals(obj)) {
                current.next().setPrevious(current.previous());
                current.previous().setNext(current.next());

                // Replace the removed node with null node
                Node<E> nodeToReplace = new Node<E>(null);
                nodeToReplace.setNext(tail);
                nodeToReplace.setPrevious(tail.previous());

                tail.previous().setNext(nodeToReplace);
                tail.setPrevious(nodeToReplace);

                return true;
            }
            current = current.next();
        }
        return false;
    }


    /**
     * Remove a node from the list
     * 
     * @param node
     *            The node to remove
     */
    private void deleteNode(Node<E> node) {
        // Remove from the current position
        node.next().setPrevious(node.previous());
        node.previous().setNext(node.next());
    }

}
