package lecture11;

import edu.princeton.cs.introcs.In;
import java.util.*;

/* Use of implementation of interfaces that extend Collection interface:
* List
* Set
* Map;
*
* Interfaces: ADTs
* Implementations: concrete Classes */
public class WordDealer {

    public static String cleanString(String s) {
        return s.toLowerCase().replaceAll("[^a-z]", "");
    }

    /* Get a list of words in the given file */
    public static List<String> getWords(String inputFileName) {
        /* List-interface, new ArrayList-concrete class */
        List<String> words = new ArrayList<>();
        In in = new In(inputFileName);

        while (! in.isEmpty()) {
            String singleWord = cleanString(in.readString());
            words.add(singleWord);
        }

        return words;
    }

    /* Return the number of unique words in the given file */
    public static int countUniqueWords(List<String> words) {

        /* prepare the Set to hold unique words */
        Set<String> uniqueSet = new HashSet<>();
        /* a Set contains no duplicates */
        for (String ithWord : words) {
            uniqueSet.add(ithWord);
        }

        return uniqueSet.size();
    }

    /* Return the count of specific words in the given file mapping a target wordlist */
    public static Map<String, Integer> findWordCount(List<String> words, List<String> targets) {

        Map<String, Integer> res = new HashMap<>();

        for (String singleTarget : targets) {
            res.put(singleTarget, 0);
        }

        for (String single : words) {
            if (res.containsKey(single)) {
                res.put(single, res.get(single) + 1);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        /* input filename should include its source root "lecture11" */
        List<String> words = getWords("lecture11/words.txt");
        System.out.println("Its size is: " + words.size());
        System.out.println("Its unique words count is: " + countUniqueWords(words));
        List<String> targets = Arrays.asList(new String[]{"this", "the", "a"});
        // List<String> targets = new ArrayList<>();
        // targets.add("this");
        // targets.add("the");
        Map<String, Integer> map = findWordCount(words, targets);
        System.out.println("\'this\' occurs " + map.get("this") + "times.");
        System.out.println("\'the\' occurs " + map.get("the") + "times.");
    }
}
