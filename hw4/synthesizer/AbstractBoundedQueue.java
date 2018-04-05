package synthesizer;
public abstract class AbstractBoundedQueue implements BoundedQueue {
	protected int fillCount;
	protected int capacity;
	public int capacity() {
		return this.capacity;
	}
	public int fillCount() {
		return this.fillCount;
	}
	public boolean isEmpty() {
		return this.fillCount == 0;
	}
	public boolean isFull() {
		return this.fillCount == this.capacity;
	}
} 