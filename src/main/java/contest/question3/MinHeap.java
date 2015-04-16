package contest.question3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wang on 2015/04/16.
 */
public class MinHeap {

    protected static Logger log = LoggerFactory.getLogger(MinHeap.class);

    private Node[] heapArray;

    private int capacity;

    private int current;


    public MinHeap(int size) {
        // assume legal capacity parameter
        this.capacity = size;
        this.current = 0;
        this.heapArray = new Node[size];
    }

    public Node getRoot() {
        if (heapArray != null && heapArray.length > 0) {
            return heapArray[0];
        }

        return null;
    }

    public boolean isEmpty() {
        return this.current == 0;
    }

    public boolean add(Node node) {
        if (current == capacity) {
            // overflow
            return false;
        }

        heapArray[current] = node;
        rise(current++);
        return true;
    }

    public boolean add(int key) {
        if (current == capacity) {
            // overflow
            return false;
        }

        Node newNode = new Node(key); // ignore data for convenience
        heapArray[current] = newNode;
        rise(current++);
        return true;
    }

    public void rise(int index) {
        int parent = (index - 1) / 2;
        Node bottom = heapArray[index];

        while(index > 0 && heapArray[parent].getKey() > bottom.getKey()) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }

        heapArray[index] = bottom;
    }

    public void sink(int index) {
        int smallerChild;
        Node top = heapArray[index];

        while(index < (current / 2)) { // while node has at least one child
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            if (rightChild < capacity && heapArray[leftChild].getKey() > heapArray[rightChild].getKey()) {
                smallerChild = rightChild;
            } else {
                smallerChild = leftChild;
            }

            if (top.getKey() <= heapArray[smallerChild].getKey()) {
                break;
            }

            // shift up
            heapArray[index] = heapArray[smallerChild];
            index = smallerChild;
        }

        heapArray[index] = top;
    }

    public boolean replaceRoot(Node newNode) {
        return replace(0, newNode);
    }

    public boolean replace(int index, Node newNode) {
        if (index < 0 || index >= current || newNode == null) {
            return false;
        }

        Node oldNode = heapArray[index];
        heapArray[index] = newNode;

        if (oldNode.getKey() < newNode.getKey()) {
            sink(index);
        } else {
            rise(index);
        }

        return true;
    }

    public void display() {
        log.info("min heap:");

        StringBuilder buf = new StringBuilder();
        for(int m = 0; m < current; m++) {
            if (heapArray[m] != null) {
                buf.append("[" + heapArray[m].getKey() + " - " + heapArray[m].getData() + "], ");
            } else {
                buf.append("-- ");
            }
        }

        log.info("\n\n" + buf + "\n");
    }

}
