package ngordnet;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.ArrayList;
public class WordNet {
    private ArrayList<String> intToword;
    private HashMap<String, String> wordToint;
    private Digraph hypGraph;
    private int wordNum;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
      wordNum = 0;
      intToword = new ArrayList<String>();
      wordToint = new HashMap<String, String>();
      In synIn = new In(synsetFilename);
      while(synIn.hasNextLine()) {
        wordNum += 1;
        String[] synWordsSet = synIn.readLine().split(",");
        intToword.add(synWordsSet[1]);
        String[] synWords = synWordsSet[1].split(" ");
        for(int i = 0; i < synWords.length; i += 1) {
          String value = wordToint.get(synWords[i]);
          if(value == null) {
            wordToint.put(synWords[i], synWordsSet[0]);
          } else {
            wordToint.put(synWords[i], value + "-" + synWordsSet[0]);
          }
        }
      }
      synIn.close();
      In hypIn = new In(hyponymFilename);
      hypGraph = new Digraph(wordNum + 100);
      while(hypIn.hasNextLine()) {
        String[] hypSets = hypIn.readLine().split(",");
        for(int i = 1; i < hypSets.length; i += 1) {
          hypGraph.addEdge((int)Integer.parseInt(hypSets[0]), (int)Integer.parseInt(hypSets[i]));
        }
      }
      hypIn.close();
    } 

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
      return !(wordToint.get(noun) == null);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
      HashSet<String> Nouns = new HashSet();
      for(int i = 0; i < intToword.size(); i += 1) {
        String[] words = intToword.get(i).split(" ");
        for(int j = 0; j < words.length; j += 1) {
          Nouns.add(words[j]);
        }
      }
      return Nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
      HashSet<String> hypWordsSet = new HashSet<String>();
      Set<Integer> descendantsId = new TreeSet<Integer>();
      String[] synsId = wordToint.get(word).split("-");
      for(int i = 0; i < synsId.length; i += 1) {
        int synsIdInteger = Integer.parseInt(synsId[i]);
        descendantsId.add(synsIdInteger);
        for(String s : intToword.get(synsIdInteger).split(" ")) {
          hypWordsSet.add(s);
        }
      }
      Set<Integer> hypIds = GraphHelper.descendants(hypGraph, descendantsId);
      for(Integer i : hypIds) {
        for(String s : intToword.get(i).split(" ")) {
          hypWordsSet.add(s);
        }
      }
      return hypWordsSet;
    }

}