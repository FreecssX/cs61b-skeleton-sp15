import java.util.Set; /* java.util.Set needed only for challenge problem. */
import java.util.Iterator;

/** A data structure that uses a linked list to store pairs of keys and values.
 *  Any key must appear at most once in the dictionary, but values may appear multiple
 *  times. Supports get(key), put(key, value), and contains(key) methods. The value
 *  associated to a key is the value in the last call to put with that key. 
 *
 *  For simplicity, you may assume that nobody ever inserts a null key or value
 *  into your map.
 */ 
public class ULLMap<K, V> implements Map61B<K, V>, Iterable<K>{ //FIX ME
    /** Keys and values are stored in a linked list of Entry objects.
      * This variable stores the first pair in this linked list. You may
      * point this at a sentinel node, or use it as a the actual front item
      * of the linked list. 
      */
    private Entry front;
    private int size;
    public ULLMap() {
        this.front = null;
        this.size = 0;
    }
    @Override
    public V get(K k) { //FIX ME
        if(front == null) {
            return null;
        }
        Entry e = front.get(k);
        if(e == null) {
            return null;
        }
        return e.val; //FIX ME
    }

    @Override
    public void put(K k, V v) { //FIX ME
        if(front == null) {
            front = new Entry(k, v, front);
            size += 1;
        }
        Entry e = front.get(k);
        if(e == null) {
            front = new Entry(k, v, front);
            size += 1;
        } else {
            e.val = v;
        }
    }

    @Override
    public boolean containsKey(K k) { //FIX ME
        return !(front == null || front.get(k) == null);
    }

    @Override
    public int size() {
        return size; // FIX ME (you can add extra instance variables if you want)
    }

    @Override
    public void clear() {
        size = 0;
        front = null;//FILL ME IN
    }

    @Override
    public Iterator<K> iterator() {
        return new mapIterator();
    }

    public static <K, V> ULLMap<V, K> invert(ULLMap<K, V> um) {
        ULLMap<V, K> umInvert = new ULLMap<V, K>();
        for(K k : um) {
            umInvert.put(um.get(k), k);
        }
        return umInvert;
    }
    /** iterator class */
    private class mapIterator implements Iterator<K> {
        private Entry e = front; 
        private int i;
        public mapIterator() {
            i = 0;
        }
        @Override
        public boolean hasNext() {
            return i < size;
        }
        @Override
        public K next() {
            if(hasNext()) {
                K k = e.key;
                e = e.next;
                i += 1;
                return k;
            }
            throw new UnsupportedOperationException("It's the end");
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Nice try, bozo.");
        }
    } 

    /** Represents one node in the linked list that stores the key-value pairs
     *  in the dictionary. */
    private class Entry {
    
        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        public Entry(K k, V v, Entry n) { //FIX ME
            key = k;
            val = v;
            next = n;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        public Entry get(K k) { //FIX ME
            if(this.key.equals(k)) {
                return this;
            }
            if(this.next == null) {
                return null;
            }
            return this.next.get(k);//FIX ME
        }
        /** Stores the key of the key-value pair of this node in the list. */
        private K key; //FIX ME
        /** Stores the value of the key-value pair of this node in the list. */
        private V val; //FIX ME
        /** Stores the next Entry in the linked list. */
        private Entry next;
    
    }

    /* Methods below are all challenge problems. Will not be graded in any way. 
     * Autograder will not test these. */
    @Override
    public V remove(K key) { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }


}