package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        boolean isE = arb.isEmpty();
        assertEquals(true, isE);
        arb.enqueue(1);
        arb.enqueue(3);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(8);
        arb.enqueue(9);
        arb.enqueue(2);
        arb.enqueue(7);
        arb.enqueue(4);
        isE = arb.isEmpty();
        assertEquals(false, isE);
        boolean isF = arb.isFull();
        assertEquals(false,isF);
        arb.enqueue(4);
        isF = arb.isFull();
        assertEquals(true,isF);
        double q = arb.peek();
        assertEquals(1.0, q, 0.0001);
        isF = arb.isFull();
        assertEquals(true,isF);
        q = arb.dequeue();
        assertEquals(1.0, q, 0.0001);
        isF = arb.isFull();
        assertEquals(false,isF);
        arb.enqueue(8);
        isF = arb.isFull();
        assertEquals(true,isF);
        q = arb.dequeue();
        assertEquals(3.0, q, 0.0001);
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        q = arb.dequeue();
        assertEquals(8.0, q, 0.0001);
        isE = arb.isEmpty();
        assertEquals(true, isE);




    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 