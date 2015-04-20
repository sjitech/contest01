package contest.question4;


import org.slf4j.Logger;

import java.util.Map;
import java.util.Objects;

/**
 * A simplify implementation of java.util.HashMap
 * ignore Serializable, multi-thread consideration for simplicity
 *
 * Created by wang on 2015/04/17.
 */
@SuppressWarnings("unchecked")
public class SimpleHashMap<K, V> {

    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;  // link list for duplicated key hash value

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            // use java internal hashCode() for simplicity
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Node) {
                Node<?,?> e = (Node<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    Node<K,V>[] table;

    int size;

    int threshold;

    static final int hash(Object key) {
        int h;
        // for reduce conflict of object.hashCode() % capacity
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    static int indexFor(int h, int length) {
        return h & (length-1); // h % (2^n - 1)
    }

    public SimpleHashMap(int initialCapacity) {
        // initialCapacity should be 2^n
        // ignore check initialCapacity
        this.table = new Node[initialCapacity];
        this.size = 0;
        this.threshold = (int)(initialCapacity * DEFAULT_LOAD_FACTOR);
    }

    public SimpleHashMap() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public V put(K key, V value) {
        // ignore null process
        if (key == null || value == null) {
            return null;
        }

        int hash = hash(key.hashCode());
        int pos = indexFor(hash, table.length);

        for (Node<K, V> e = table[pos]; e != null; e = e.next) {
            Object k;

            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                // override exists key
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }

        addNode(hash, key, value, pos);

        return null;
    }

    void addNode(int hash, K key, V value, int index) {
        Node<K, V> e = table[index];

        table[index] = new Node<>(hash, key, value, e);

        if (size++ >= threshold) {
            resize();
        }
    }

    void resize() {
        // ignore multi-thread consideration
        if (size < 1) {
            // no need to resize
            return;
        }

        Node<K, V>[] oldTable = table;

        int newCapacity = 0;

        if (size >= threshold) {
            newCapacity = table.length * 2;
        }

        if (size < (threshold / 2)) {
            newCapacity = table.length / 2;
        }

        if (newCapacity == 0) {
            // no need to resize
            return;
        }

        threshold = (int)(newCapacity * DEFAULT_LOAD_FACTOR);
        table = new Node[newCapacity];

        for (Node<K, V> node : oldTable) {
            if (node == null) {
                continue;
            }

            // put all data to new table with new key hash value
            int hash = hash(node.key.hashCode());
            int pos = indexFor(hash, newCapacity);

            for (Node<K, V> e = table[pos]; e != null; e = e.next) {
                Object k;

                if (e.hash == hash && ((k = e.key) == node.key || node.key.equals(k))) {
                    e.value = node.value;
                }
            }

            Node<K, V> nodeWithSameKeyHash = table[pos];
            table[pos] = new Node<>(hash, node.key, node.value, nodeWithSameKeyHash);
        }
    }

    public V get(Object key) {
        // ignore null process for key
        if (key == null) {
            return null;
        }

        int hash = hash(key.hashCode());

        for(Node<K, V> e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
            Object k;

            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                return e.value;
            }
        }

        return null;
    }

    public boolean exist(Object key) {
        return (get(key) != null);
    }

    public V remove(Object key) {
        int hash = hash(key.hashCode());
        int pos = indexFor(hash, table.length);
        Node<K, V> previous = null;
        Object k;

        for (Node<K, V> e = table[pos]; e != null; e = e.next) {

            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V removedValue = e.value;

                if (previous == null) {
                    table[pos] = e.next;
                } else {
                    previous.next = e.next;
                }

                return removedValue;
            }

            previous = e;
        }

        return null;
    }

    public int size() {
        return size;
    }

    public void dump(Logger log) {
        if (table == null || table.length == 0 || size == 0) {
            log.info("map is empty.");
        }

        log.info("map capacity is " + table.length);
        log.info("map size is " + size);
        log.info("----------------------------------------------------------------------------------------");

        StringBuilder line = null;

        for (Node<K, V> node : table) {
            line = new StringBuilder();

            if (node == null) {
                line.append("    [null] ");
            } else {
                line.append("    [" + node + "] ");

                for(Node<K, V> e = node.next; e != null; e = e.next) {
                    line.append(" -> [" + e + "] ");
                }
            }

            log.info(line.toString());
        }

        log.info("----------------------------------------------------------------------------------------");
    }

}

