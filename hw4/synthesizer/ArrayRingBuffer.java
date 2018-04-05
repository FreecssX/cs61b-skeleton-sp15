package synthesizer;

public class ArrayRingBuffer extends AbstractBoundedQueue {
  /* Index for the next dequeue or peek. */
  private int first;           
  /* Index for the next enqueue. */
  private int last;             
  /* Array for storing the buffer data. */
  private double[] rb;

  /** Create a new ArrayRingBuffer with the given capacity. */
  public ArrayRingBuffer(int capacity) {
    this.rb = new double[capacity];
    this.capacity = capacity;
    this.first = 0;
    this.last = 0;
    this.fillCount = 0;
  }

  /** Adds x to the end of the ring buffer. If there is no room, then
    * throw new RuntimeException("Ring buffer overflow") 
    */
  public void enqueue(double x) {
    if(this.isFull()) {
      throw new RuntimeException("The queue is full.");
    } else {
      rb[this.last] = x;
      this.last += 1;
      this.fillCount += 1;
      if(this.last == this.capacity) {
        this.last = 0;
      }
    }
  }

  /** Dequeue oldest item in the ring buffer. If the buffer is empty, then
    * throw new RuntimeException("Ring buffer underflow");
    */
  public double dequeue() {
    if(this.isEmpty()) {
      throw new RuntimeException("The queue is empty.");
    } else {
      double result = rb[this.first];
      this.first += 1;
      this.fillCount -= 1;
      if(this.first == this.capacity) {
        this.first = 0;
      }
      return result;
    }
  }

  /** Return oldest item, but don't remove it. */
  public double peek() {
    if(this.isEmpty()) {
      throw new RuntimeException("The queue is empty.");
    } else {
      return rb[first];
    }

  }

}
