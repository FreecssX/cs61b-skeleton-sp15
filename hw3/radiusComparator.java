import java.util.Comparator;

/**
 * radiusComparator.java
 */

public class radiusComparator implements Comparator<Planet> {

    public radiusComparator() {
    }

    /** Returns the difference in mass as an int.
     *  Round after calculating the difference. */
    public int compare(Planet planet1, Planet planet2) {
        // REPLACE THIS LINE WITH YOUR SOLUTION
        return (int) (planet1.getRadius() - planet2.getRadius());
    }
}