package ngordnet;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
import java.util.Comparator;
import java.util.Collection;
import java.util.ArrayList;
public class YearlyRecord {
    private Map<String, Integer> hMap;
    private Map<String, Integer> tMap;
    private Map<String, Integer> rMap;
    private boolean hasPut;
    private ArrayList<Number> counts;
    private boolean hasPut2;
    /** Creates a new empty YearlyRecord. */
    public YearlyRecord() {
        counts = new ArrayList<Number>();
        hasPut = false;
        hasPut2 = false;
        hMap = new HashMap<String, Integer>();
        rMap = new HashMap<String, Integer>();
        tMap = new TreeMap<String, Integer>(new Comparator<String>() {
            public int compare(String word1, String word2) {
                Integer count1 = hMap.get(word1);
                Integer count2 = hMap.get(word2);
                if(count1 == null || count2 == null) {
                    return 1;
                } else {
                    return (int)count1 -(int)count2;
                }
            }
        });
    }

     /** Creates a YearlyRecord using the given data. */
    public YearlyRecord(HashMap<String, Integer> otherCountMap) {
        this();
        hMap = otherCountMap;
        tMap.putAll(hMap);
        int i = this.size();
        hasPut = true;
     }

     /** Returns the number of times WORD appeared in this year. */
     public int count(String word)  {
        return (int) (hMap.get(word));
     }

     /** Records that WORD occurred COUNT times in this year. */
     public void put(String word, int count) {
        hMap.put(word, count);
        tMap.put(word, count);
        hasPut = true;
        hasPut2 = true;
     }

     /** Returns the number of words recorded this year. */
     public int size() {
        return hMap.size();
     }

     /** Returns all words in ascending order of count. */
     public Collection<String> words() {
        return tMap.keySet();
     } 

     /** Returns all counts in ascending order of count. */
     public Collection<Number> counts() {
        if(hasPut2) {
            ArrayList<Number> c = new ArrayList<Number>();
            for(String s: tMap.keySet()) {
                c.add(hMap.get(s));
            }
            counts = c;
            hasPut2 = false;
        }
        return counts;
     }

     /** Returns rank of WORD. Most common word is rank 1. 
       * If two words have the same rank, break ties arbitrarily. 
       * No two words should have the same rank.
       */
     public int rank(String word) {
        if(hasPut) {
            int i = this.size();
            for(String s:tMap.keySet()) {
                rMap.put(s, i);
                i -= 1;
            }
            hasPut = false;
        }
        return rMap.get(word);
     }
} 

