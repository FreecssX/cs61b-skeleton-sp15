package ngordnet;
import java.util.ArrayList;
import com.xeiam.xchart.Chart;
import com.xeiam.xchart.QuickChart;
import com.xeiam.xchart.SwingWrapper;
import com.xeiam.xchart.StyleManager.ChartTheme;
import com.xeiam.xchart.ChartBuilder;
import java.util.Set;


/** Utility class for generating plots. */
public class Plotter {
    /** Creates a plot of the TimeSeries TS. Labels the graph with the
      * given TITLE, XLABEL, YLABEL, and LEGEND. */
    public static void plotTS(TimeSeries<? extends Number> ts, String title, 
                              String xlabel, String ylabel, String legend) {
      ArrayList<Number> xValues = new ArrayList<Number>();
      ArrayList<Number> yValues = new ArrayList<Number>();
       
      for (int i: ts.keySet()) {
        xValues.add(i);
        yValues.add(ts.get(i));
      }        

      // Create Chart

      Chart chart = QuickChart.getChart(title, ylabel, xlabel, legend, xValues, yValues);
      // Show it
      new SwingWrapper(chart).displayChart();
    }

    /** Creates a plot of the absolute word counts for WORD from STARTYEAR
      * to ENDYEAR, using NGM as a data source. */
    public static void plotCountHistory(NGramMap ngm, String word, 
                                      int startYear, int endYear) {
      TimeSeries<Integer> ts = ngm.countHistory(word, startYear, endYear);
      plotTS(ts, "CountHistory_" + word, "year", "count", word);
    }

    /** Creates a plot of the normalized weight counts for WORD from STARTYEAR
      * to ENDYEAR, using NGM as a data source. */
    public static void plotWeightHistory(NGramMap ngm, String word, 
                                       int startYear, int endYear) {
      TimeSeries<Double> ts = ngm.weightHistory(word, startYear, endYear);
      plotTS(ts, "WeightHistory_" + word, "year", "count", word);
    }

    /** Creates a plot of the processed history from STARTYEAR to ENDYEAR, using
      * NGM as a data source, and the YRP as a yearly record processor. */
    //public static void plotProcessedHistory(NGramMap ngm, int startYear, int endYear,
    //                                        YearlyRecordProcessor yrp) {
    //}

    /** Creates a plot of the total normalized count of WN.hyponyms(CATEGORYLABEL)
      * from STARTYEAR to ENDYEAR using NGM and WN as data sources. */
    public static void plotCategoryWeights(NGramMap ngm, WordNet wn, String categoryLabel,
                                            int startYear, int endYear) {
      Set<String> hyps = wn.hyponyms(categoryLabel);
      TimeSeries<Double> ts = ngm.summedWeightHistory(hyps, startYear, endYear);
      plotTS(ts, "TotalWeight_" + categoryLabel, "year", "count", categoryLabel);

    }

    /** Creates overlaid category weight plots for each category label in CATEGORYLABELS
      * from STARTYEAR to ENDYEAR using NGM and WN as data sources. */
    public static void plotCategoryWeights(NGramMap ngm, WordNet wn, String[] categoryLabels,
                                            int startYear, int endYear) {
      Chart chart = new ChartBuilder().width(800).height(600).xAxisTitle("year").yAxisTitle("TotalWeight").build();
      for (String word: categoryLabels) {
        Set<String> hyps = wn.hyponyms(word);
        TimeSeries<Double> ts = ngm.summedWeightHistory(hyps, startYear,endYear);
        ArrayList<Number> xValues = new ArrayList<Number>();
        ArrayList<Number> yValues = new ArrayList<Number>();
        for(int i = startYear; i <= endYear; i += 1) {
          xValues.add(i);
          yValues.add(ts.get(i));
        }

        chart.addSeries(word, new ArrayList<Number>(xValues), new ArrayList<Number>(yValues));
      } 
      // Show it
      new SwingWrapper(chart).displayChart();
    }

    /** Makes a plot showing overlaid individual normalized count for every word in WORDS
      * from STARTYEAR to ENDYEAR using NGM as a data source. */
    public static void plotAllWords(NGramMap ngm, String[] words, int startYear, int endYear) {
      Chart chart = new ChartBuilder().width(800).height(600).xAxisTitle("year").yAxisTitle("TotalWeight").build();
      for (String word: words) {
        TimeSeries<Double> ts = ngm.weightHistory(word, startYear,endYear);
        ArrayList<Number> xValues = new ArrayList<Number>();
        ArrayList<Number> yValues = new ArrayList<Number>();
        for(int i = startYear; i <= endYear; i += 1) {
          xValues.add(i);
          yValues.add(ts.get(i));
        }
        chart.addSeries(word, new ArrayList<Number>(xValues), new ArrayList<Number>(yValues));
      } 
      // Show it
      new SwingWrapper(chart).displayChart();
    }

    /** Plots the length of the average word from start to end. */
    public static void plotWordLength(NGramMap ngm, int startYear, int endYear) {
      TimeSeries<Double> ts = ngm.processedHistory(startYear, endYear, new WordLengthProcessor());
      plotTS(ts, "average_wordlength", "year", "wordlength", "wordlength");
    }


    /** Returns the numbers from max to 1, inclusive in decreasing order. 
      * Private, so you don't have to implement if you don't want to. */
    //private static Collection<Number> downRange(int max)

    /** Plots the count (or weight) of every word against the rank of every word on a
      * log-log plot. Uses data from YEAR, using NGM as a data source. */
    public static void plotZipfsLaw(NGramMap ngm, int year) {
      YearlyRecord yr = ngm.getRecord(year);
      ArrayList<Number> xValues = new ArrayList<Number>();
      ArrayList<Number> yValues = new ArrayList<Number>();
      for(String word: yr.words()) {
        xValues.add(yr.rank(word));
        yValues.add(yr.count(word));
      }
      String xlabel = "rank_log";
      String ylabel = "count_log";
      String legend = "count vs rank";
      Chart chart = new ChartBuilder().width(800).height(600).xAxisTitle(ylabel).yAxisTitle(xlabel).build();
      chart.getStyleManager().setYAxisLogarithmic(true);
      chart.getStyleManager().setXAxisLogarithmic(true);
      chart.addSeries(legend, xValues, yValues);

        // Show it
      new SwingWrapper(chart).displayChart();
    }
} 
