package synthesizer;
public interface BoundedQueue {
	int capacity();
	int fillCount();
	boolean isEmpty();
	void enqueue(double x);
	double dequeue();
	double peek();
}