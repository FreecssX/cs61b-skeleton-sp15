import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;

/** ULLMapTest. You should write additional tests.
 *  @author Josh Hug
 */

public class ULLMapTest {
    @Test
    public void testBasic() {
        ULLMap<String, String> um = new ULLMap<String, String>();
        //String e = null;
        //System.out.println(e.equals(null));
        um.put("Gracias", "Dios Basado");
        
        //System.out.println(um.get("Gracias"));
        //assertEquals(um.get("Gracias"), "Dios Basado");
    }

    
    @Test
    public void testIterator() {
        ULLMap<Integer, String> um = new ULLMap<Integer, String>();
        um.put(0, "zero");
        um.put(1, "one");
        um.put(2, "two");
        Iterator<Integer> umi = um.iterator();
        assertEquals(2, (int)umi.next());
        assertEquals(1, (int)umi.next());
        assertEquals(0, (int)umi.next());

    }
    @Test
    public void testInvert() {
        ULLMap<Integer, String> um = new ULLMap<Integer, String>();
        um.put(0, "zero");
        um.put(1, "one");
        um.put(2, "two");
        ULLMap<String, Integer> umInvert = ULLMap.invert(um);
        Iterator<String> umInverti = umInvert.iterator();
        assertEquals("zero", umInverti.next());
        assertEquals("one", umInverti.next());
        assertEquals("two", umInverti.next());
    }

    /** Runs tests. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(ULLMapTest.class);
    }
} 