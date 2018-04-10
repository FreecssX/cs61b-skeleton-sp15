package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Collection;
public class NGramMap {
    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */
    private HashMap<Integer, YearlyRecord> yearlyMap;
    private HashMap<String, TimeSeries<Integer>> timeSeriesMap;
    private TimeSeries<Long> yearTonum;
    public NGramMap(String wordsFilename, String countsFilename) {
        yearlyMap = new  HashMap<Integer, YearlyRecord>();
        timeSeriesMap = new HashMap<String, TimeSeries<Integer>>();
        yearTonum = new TimeSeries<Long>();
        
        In wordsIn = new In(wordsFilename);
        while(wordsIn.hasNextLine()) {
            String[] ws = wordsIn.readLine().split("\t");
            int year = Integer.parseInt(ws[1]);
            int num = Integer.parseInt(ws[2]);
            if(yearlyMap.get(year) == null) {
                YearlyRecord yr = new YearlyRecord();
                yr.put(ws[0], num);
                yearlyMap.put(year, yr);
            } else {
                yearlyMap.get(year).put(ws[0], num);
            }

            if(timeSeriesMap.get(ws[0]) == null) {
                TimeSeries<Integer> ts = new TimeSeries<Integer>();
                ts.put(year, num);
                timeSeriesMap.put(ws[0], ts);
            } else {
                timeSeriesMap.get(ws[0]).put(year, num);
            }
        }
        wordsIn.close();

        In numIn = new In(countsFilename);
        while(numIn.hasNextLine()) {
            String[] nums = numIn.readLine().split(",");
            yearTonum.put(Integer.parseInt(nums[0]), Long.parseLong(nums[1]));
        }
        numIn.close(); 
    }
    
    /** Returns the absolute count of WORD in the given YEAR. If the word
      * did not appear in the given year, return 0. */
    public int countInYear(String word, int year) {
        TimeSeries<Integer> ts = timeSeriesMap.get(word);
        if(ts == null) {
            return 0;
        } else {
            if(ts.get(year) == null) {
                return 0;
            }
            else {
                return ts.get(year);
            }
        }
    }

    /** Returns a defensive copy of the YearlyRecord of YEAR. */
    public YearlyRecord getRecord(int year) {
        return yearlyMap.get(year);
    }

    /** Returns the total number of words recorded in all volumes. */
    public TimeSeries<Long> totalCountHistory() {
        return yearTonum;
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Integer> countHistory(String word, int startYear, int endYear) {
        TimeSeries<Integer> ts = timeSeriesMap.get(word);
        if(ts == null) {
            return null;
        } else {
            TimeSeries<Integer> ts1 = new TimeSeries<Integer>();
            for(int i = startYear; i <= endYear; i += 1) {
                Integer data = ts.get(i);
                if(data != null) {
                    ts1.put(i, data);
                }
            }
            return ts1;
        }
    }

    /** Provides a defensive copy of the history of WORD. */
    public TimeSeries<Integer> countHistory(String word) {
        return timeSeriesMap.get(word);
    }

    /** Provides the relative frequency of WORD between STARTYEAR and ENDYEAR. */
    public TimeSeries<Double> weightHistory(String word, int startYear, int endYear) {
        TimeSeries<Integer> ts = countHistory(word, startYear, endYear);
        return ts.dividedBy(yearTonum);
    }

    /** Provides the relative frequency of WORD. */
    public TimeSeries<Double> weightHistory(String word) {
        TimeSeries<Integer> ts = timeSeriesMap.get(word);
        if(ts == null) {
            return null;
        } else {
            return ts.dividedBy(yearTonum);
        }
    }

    /** Provides the summed relative frequency of all WORDS between
      * STARTYEAR and ENDYEAR. If a word does not exist, ignore it rather
      * than throwing an exception. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words, 
                              int startYear, int endYear) {
        TimeSeries<Double> tsSum = new TimeSeries<Double>();
        for(String word: words) {
            TimeSeries<Integer> ts = countHistory(word, startYear, endYear);
            if(ts != null) {
                tsSum = tsSum.plus(ts);
            }

        }
        return tsSum.dividedBy(yearTonum);
    }
    
    /** Returns the summed relative frequency of all WORDS. */
    public TimeSeries<Double> summedWeightHistory(Collection<String> words) {
        TimeSeries<Double> tsSum = new TimeSeries<Double>();
        for(String word: words) {
            TimeSeries<Integer> ts = countHistory(word);
            if(ts != null) {
                tsSum = tsSum.plus(ts);
            }

        }
        return tsSum.dividedBy(yearTonum);
    }

    /** Provides processed history of all words between STARTYEAR and ENDYEAR as processed
      * by YRP. */
    //public TimeSeries<Double> processedHistory(int startYear, int endYear,
                                              // YearlyRecordProcessor yrp)

    /** Provides processed history of all words ever as processed by YRP. */
    //public TimeSeries<Double> processedHistory(YearlyRecordProcessor yrp) 
}