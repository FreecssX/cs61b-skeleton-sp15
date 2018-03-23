import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    /* Do not change this to be private. For silly testing reasons it is public. */
    public Calculator tester;

    /**
     * setUp() performs setup work for your Calculator.  In short, we 
     * initialize the appropriate Calculator for you to work with.
     * By default, we have set up the Staff Calculator for you to test your 
     * tests.  To use your unit tests for your own implementation, comment 
     * out the StaffCalculator() line and uncomment the Calculator() line.
     **/
    @Before
    public void setUp() {
        //tester = new StaffCalculator(); // Comment me out to test your Calculator
        tester = new Calculator();   // Un-comment me to test your Calculator
    }

    // TASK 1: WRITE JUNIT TESTS
    // YOUR CODE HERE
    /* Test the addition method */
    @Test
    public void TestAddition() {
    	int a = tester.add(1, 2);
    	int b = tester.add(1, 0);
    	int c = tester.add(-13, 1);
    	assertEquals(3, a);
    	assertEquals(1, b);
    	assertEquals(-12, c);
    }
    /* Test the multiply method */ 
	@Test
    public void TestMultiply() {
    	int a = tester.multiply(1, 2);
    	int b = tester.multiply(1, 0);
    	int c = tester.multiply(-13, 1);
    	assertEquals(0, b);
    	assertEquals(2, a); 
    	assertEquals(-13, c);
    }
    /* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.textui.runClasses(CalculatorTest.class);
    }       
}