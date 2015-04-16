package contest.question3;

/**
 * Created by wang on 2015/04/16.
 */
public class Node {
    private int key;

    private Object data;

    public Node(int key) {
        this.key = key;
    }

    public Node(int key, Object data) {
        this.key = key;
        this.data = data;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
