import java.util.Set;

public class  BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    
	private BSTNode root;
	private BSTMap<K, V> rightM;
	private BSTMap<K, V> leftM;
    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
    	rightM = null;
		leftM = null;
		root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
    	if(root == null) return false;
		else if(root.getKey().compareTo(key) == 0) return true;
		else if(root.getKey().compareTo(key) > 0) return leftM.containsKey(key);
		else return rightM.containsKey(key);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. 
     */
    @Override
    public V get(K key) {
    	if(root == null) return null;
		else if(root.getKey().compareTo(key) == 0) {
			return root.getValue();}
		else if(root.getKey().compareTo(key) > 0) {
			if(leftM == null) return null;
			else return leftM.get(key);
		} else {
			if(rightM == null) return null;
			else return rightM.get(key);
		}
    }

   /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
    	if(root == null) {
			return 0;
		} else {
			int rs = 0;
			if(rightM != null) rs = rightM.size();
			int ls = 0;
			if(leftM != null) ls = leftM.size(); 
			return 1 + rightM.size() + leftM.size();
		}
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
    	if(root == null) {
			root = new BSTNode(key, value);
		}
		else if(root.getKey().compareTo(key) == 0) {
			root.put(value);
		} else if(root.getKey().compareTo(key) > 0) {
			if(leftM == null) leftM = new BSTMap<K, V>();
			leftM.put(key, value);
		} else {
			if(rightM == null) rightM = new BSTMap<K, V>();
			rightM.put(key, value);
		}
    }
    public void printInOrder() {
    	if(root == null) return;
		if(leftM != null) {
			leftM.printInOrder();
		}
		System.out.print(root.getValue() + " ");
		if(rightM != null) {
			rightM.printInOrder();
		}
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for HW6. */
    @Override
    public V remove(K key) {
    	throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for HW6a. */
    @Override
    public V remove(K key, V value) {
    	throw new UnsupportedOperationException();
    }

    /* Returns a Set view of the keys contained in this map. Not required for HW6. */
    @Override
    public Set<K> keySet() {
    	throw new UnsupportedOperationException();
    }    

    private class BSTNode {
		private K key;
		private V value;
		public BSTNode(K k, V v) {
			key = k;
			value = v;
		}
		public void put(V v) {
			value = v;
		}
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
	}
}