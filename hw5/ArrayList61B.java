import java.util.AbstractList;
public class ArrayList61B<E> extends AbstractList<E> {
	private int capacity;
	private int size;
	private E[] A;
	public ArrayList61B(int initialCapacity) {
		if(initialCapacity < 1) {
			throw new IllegalArgumentException("capacity less than 1");
		}
		size = 0;
		A = (E[]) new Object[initialCapacity];
		capacity = initialCapacity;
	}
	
	public ArrayList61B() {
		size = 0;
		capacity = 1;
		A = (E[]) new Object[1];
	}

	public E get(int i) {
		if(i < 0 || i >= size) {
			throw new IllegalArgumentException("illegal index");
		}
		return A[i];
	}
	public boolean add(E item) {
		if(size == capacity) {
			resize();
		}
		A[size] = item;
		size += 1;
		return true;
	}
	public void resize() {
		capacity = 2 * capacity;
		E[] B = (E[]) new Object[capacity];
		for(int i = 0; i < size; i += 1) {
			B[i] = A[i];
		}
		A = B;
	}
	public int size() {
		return size;
	}
}