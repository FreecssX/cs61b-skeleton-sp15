import ngordnet.*;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.In;
public class NgordnetUI {
    public static void main(String[] args) {
    	int startYear = 1999;
    	int endYear = 2018;
    	WordNet wn = new WordNet("./ngordnet/wordnet/synsets.txt", "./ngordnet/wordnet/hyponyms.txt");
    	NGramMap ngm = new NGramMap("./ngordnet/ngrams/all_words.csv", "./ngordnet/ngrams/total_counts.csv");
    	while (true) {
            System.out.print("> ");
            String line = StdIn.readLine();
            String[] rawTokens = line.split(" ");
            String command = rawTokens[0];
            String[] tokens = new String[rawTokens.length - 1];
            System.arraycopy(rawTokens, 1, tokens, 0, rawTokens.length - 1);
            switch (command) {
                case "quit": 
                    return;
                case "help":
                    In in = new In("help.txt");
                    String helpStr = in.readAll();
                    System.out.println(helpStr);
                    break;  
                case "range": 
                	try {
                    	startYear = Integer.parseInt(tokens[0]); 
                    	endYear = Integer.parseInt(tokens[1]);
                    	System.out.println("Start date: " + startYear);
                    	System.out.println("End date: " + endYear);
                	} catch(Exception e) {
                		System.out.println("wrong command: " + e);
                	}
                    break;
                case "count":
                    try {
                    	String word = tokens[0]; 
                    	int year = Integer.parseInt(tokens[1]);
                    	System.out.println("The count of the word in year " + year + ": " + ngm.countInYear(word, year));
                    	Plotter.plotCountHistory(ngm, word,startYear,endYear);
                	} catch(Exception e) {
                		System.out.println("wrong command: " + e);
                	}
                    break;
                case "hyponyms":
                    try {
                    	String word = tokens[0]; 
                    	System.out.println(wn.hyponyms(word));
                	} catch(Exception e) {
                		System.out.println("wrong command: " + e);
                	}
                    break;
                case "history":
                    try {
                    	Plotter.plotAllWords(ngm, tokens, startYear, endYear);
                	} catch(Exception e) {
                		System.out.println("wrong command: " + e);
                	}
                    break;
                case "hypohist":
                    try {
                    	Plotter.plotCategoryWeights(ngm, wn, tokens, startYear, endYear);
                	} catch(Exception e) {
                		System.out.println("wrong command: " + e);
                	}
                    break;
                case "wordlength":
                	try {
                		Plotter.plotWordLength(ngm, startYear, endYear);
                	} catch(Exception e) {
                		System.out.println("wrong command: " + e);
                	}
                    break;
                case "zipf":
                    try {
                        int year = Integer.parseInt(tokens[0]);
                        Plotter.plotZipfsLaw(ngm, year);
                    } catch(Exception e) {
                        System.out.println("wrong command: " + e);
                    }
                    break;
                default:
                    System.out.println("Invalid command.");  
                    break;
            }
        }
    }
}