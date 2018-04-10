package ngordnet;
import java.util.Collection;
public class WordLengthProcessor implements YearlyRecordProcessor {
    public double process(YearlyRecord yearlyRecord) {
    	Collection<String> words = yearlyRecord.words();
    	long numerator = 0;
    	long denominator = 0;
    	for(String word: words) {
    		numerator = numerator + word.split("").length * yearlyRecord.count(word); 
    		denominator = denominator + yearlyRecord.count(word); 
    	}
    	return numerator / 100000.0 / (denominator / 100000.0);

    }
}