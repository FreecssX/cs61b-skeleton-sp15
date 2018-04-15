import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;
public class MyHashMap<K, V> implements Map61B<K, V> {
	/** Removes all of the mappings from this map. */
	private ArrayList<Entry<K, V>>[] table; 
	private static final int INITIAL_SIZE = 16;
	private static final int MAXIMUM_SIZE = 1 << 30;
	private int size;
	private int threshold;
	private int capacity;
	private static final float LOADFACTOR = 0.75f;
	private float loadFactor;
	public MyHashMap() {
		this(INITIAL_SIZE, LOADFACTOR);
	}
	public MyHashMap(int initialSize) {
		this(initialSize, LOADFACTOR);
	}
	public MyHashMap(int initialSize, float loadFactor) {
		if(initialSize > MAXIMUM_SIZE) {
			this.capacity = MAXIMUM_SIZE;
		}

		this.capacity = trimTopowerOftwo(initialSize);
		this.table = new ArrayList[capacity]; //泛型数组
		this.loadFactor = loadFactor;
		this.threshold = Math.round(this.capacity * this.loadFactor);
		this.size = 0;

	}
    public void clear() {
    	for(int i = 0; i < capacity; i += 1) {
    		if(table[i] != null) {
    			table[i].clear();
    		}
    	}
    }

    /* Returns true if this map contains a mapping for the specified key. 
     * Should run on average constant time when called on a HashMap. 
     */
    public boolean containsKey(K key) {
    	int bucket = hash(key.hashCode());
    	if(table[bucket] !=null) {
    		for(Entry<K, V> e: table[bucket]) {
    			if(e.getKey().equals(key)) return true;
    		}
    	}
    	return false;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. Should run on average constant time
     * when called on a HashMap. 
     */
    public V get(K key) {
    	int bucket = hash(key.hashCode());
    	if(table[bucket] !=null) {
    		for(Entry<K, V> e: table[bucket]) {
    			if(e.getKey().equals(key)) return e.getValue();
    		}
    	}
    	return null;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
    	return size;
    }

    /* Associates the specified value with the specified key in this map. 
     * Should run on average constant time when called on a HashMap. */
    public void put(K key, V value) {
    	if(size == threshold) {
    		if(capacity == MAXIMUM_SIZE) throw new RuntimeException("Exceeding maximum capacity!");
    		capacity = capacity * 2;
    		Set<Entry<K, V>> es = entrySet();
    		threshold  = Math.round(capacity * loadFactor);
    		size = 0;
    		table = new ArrayList[capacity];
    		rehash(es);

    	}
    	int bucket = hash(key.hashCode());
    	Entry<K, V> et = new Entry<K, V>(key, value);
    	if(table[bucket] == null) {
    		table[bucket] = new ArrayList<Entry<K, V>>();
    	}
    	table[bucket].add(et);
    	size += 1;
    }

    /* Removes the mapping for the specified key from this map if present. 
     * Should run on average constant time when called on a HashMap. */
    public V remove(K key) {
    	int bucket = hash(key.hashCode());
    	if(table[bucket] !=null) {
    		for(Entry<K, V> e: table[bucket]) {
    			if(e.getKey().equals(key)) {
    				V value = e.getValue();
    				table[bucket].remove(e);
    				size -= 1;
    				return value;
    			} 
    		}
    	}
    	return null;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Should run on average constant time when called on 
     * a HashMap. */
    public V remove(K key, V value) {
    	int bucket = hash(key.hashCode());
    	Entry<K, V> et = new Entry<K, V>(key, value);
    	if(table[bucket] !=null) {
    		for(Entry<K, V> e: table[bucket]) {
    			if(et.equals2(e)) {
    				V v = e.getValue();
    				table[bucket].remove(e);
    				size -= 1;
    				return v;
    			} 
    		}
    	}
    	return null;
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
    	if(size == 0) return null;
    	Set<K> ks = new HashSet<K>();
    	for(int i = 0; i < capacity; i += 1) {
    		if(table[i] != null) {
    			for(Entry<K, V> e: table[i]) {
    				ks.add(e.getKey());
    			}
    		}	
    	}
    	return ks;
    }

    private class Entry<K, V> {
    	private K key;
    	private V value;
    	public Entry(K k, V v) {
    		key = k;
    		value = v;
    	}
    	public boolean equals1(Object o) {
    		if(o instanceof Entry) {
    			return key.equals(((Entry) o).getKey());
    		}
    		return false;
    	}
    	public boolean equals2(Object o) {
    		if(o instanceof Entry) {
    			return key.equals(((Entry) o).getKey()) && value.equals(((Entry) o).getValue());
    		}
    		return false;
    	}
    	public K getKey() {
    		return key;
    	}
    	public V getValue() {
    		return value;
    	}
    	@Override
    	public int hashCode() {
    		return key.hashCode();
    	}
    }
    /************************* Helper Function ***********************************/
    public int trimTopowerOftwo(int initialSize) {
    	int c = 1;
    	while(c < initialSize) {
    		c *= 2;
    	}
    	return c;
    }
    private void rehash(Set<Entry<K, V>> es) {
    	for(Entry<K, V> e: es) {
    		put(e.getKey(), e.getValue());
    	}
    }
    private Set<Entry<K, V>> entrySet() {
    	Set<Entry<K, V>> es = new HashSet<Entry<K, V>>();
    	for(int i = 0; i < capacity; i += 1) {
    		if(table[i] != null) {
    			for(Entry<K, V> e: table[i]) {
    				es.add(e);
    			}
    		}
    	}
    	return es;
    }
    private int hash(int hashCode) {
    	return negativeTopositive(hashCode) % capacity;
    }
    private int negativeTopositive(int n) {
    	while(n < 0) {
    		n += capacity;
    	}
    	return n;
    }
}