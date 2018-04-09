package ngordnet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Collection;
public class TimeSeries<T extends Number> extends TreeMap<Integer, T> {    
    /** Constructs a new empty TimeSeries. */
    public TimeSeries() {

    }

    /** Returns the years in which this time series is valid. Doesn't really
      * need to be a NavigableSet. This is a private method and you don't have 
      * to implement it if you don't want to. */
    //private NavigableSet<Integer> validYears(int startYear, int endYear)

    /** Creates a copy of TS, but only between STARTYEAR and ENDYEAR. 
     * inclusive of both end points. */
    public TimeSeries(TimeSeries<T> ts, int startYear, int endYear) {
      for(int i = startYear; i <= endYear; i += 1) {
        this.put(i, ts.get(i));
      }
    }

    /** Creates a copy of TS. */
    public TimeSeries(TimeSeries<T> ts) {
      for(Integer i: ts.keySet()) {
        this.put(i, ts.get(i));
      }
    }

    /** Returns the quotient of this time series divided by the relevant value in ts.
      * If ts is missing a key in this time series, return an IllegalArgumentException. */
    public TimeSeries<Double> dividedBy(TimeSeries<? extends Number> ts) {
      TimeSeries<Double> t  = new TimeSeries<Double>();
      for(Integer i: this.keySet()) {
        Number k = ts.get(i);
        if(k == null) {
          throw new IllegalArgumentException("no such year");
        } else {
          t.put(i, this.get(i).doubleValue() / k.doubleValue());
        }
      }
      return t;
    }

    /** Returns the sum of this time series with the given ts. The result is a 
      * a Double time series (for simplicity). */
    public TimeSeries<Double> plus(TimeSeries<? extends Number> ts) {
      TimeSeries<Double> t  = new TimeSeries<Double>();
      for(Integer i: ts.keySet()) {
        Number k = this.get(i);
        if(k == null) {
          k = 0;
        }
        t.put(i, ts.get(i).doubleValue() + k.doubleValue());
      }
      return t;
    }

    /** Returns all years for this time series (in any order). */
    public Collection<Number> years() {
      Collection c = new TreeSet<Number>();
      for(Integer i: this.keySet()) {
        c.add(i);
      }
      return c;
    }

    /** Returns all data for this time series. 
      * Must be in the same order as years(). */
    public Collection<Number> data() {
      Collection c = new TreeSet<Number>();
      for(Integer i: this.keySet()) {
      	c.add(this.get(i));
      }
      return c;
    }
}
