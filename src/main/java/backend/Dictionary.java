package backend;

import util.Trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import util.LevenShteinDistance;


class SortByLevenDist implements Comparator<Word>  { 
    private String target;
    LevenShteinDistance metric;
    
    SortByLevenDist(String target, LevenShteinDistance metric) {
        this.target = target;
        this.metric = metric;
    }
    
    public int compare(Word w1, Word w2) { 
        return metric.calcDist(target, w1.getWordTarget())
                - metric.calcDist(target, w2.getWordTarget());
    } 
} 


public class Dictionary {
    final int LEV_THRESH = 4;
    final int MAX_RECOMMENDATIONS_SIZE = 5;
    
    Trie<Word> words;
    LevenShteinDistance levDist = new LevenShteinDistance();

    public Dictionary() {
        words = new Trie<Word>();
    }

    public void add(String word_target, String word_explain) {
        // System.out.println(word_target);
        words.insert(word_target.toLowerCase(), new Word(word_target.toLowerCase(), word_explain));
    }

    public void add(String word_target, String word_explain, String word_pronunciation) {
        words.insert(word_target.toLowerCase(), new Word(word_target.toLowerCase(), word_explain, word_pronunciation));
    }

    public int size() {
        return words.size();
    }

    public List<Word> get(String prefix) {
        if (!words.contains(prefix.toLowerCase())) {
            return new ArrayList<>();
        }
        return words.queryPrefix(prefix.toLowerCase());
    }
    
    public List<Word> getRecommendations(String wordTarget) {
        List<Word> allWords = words.queryPrefix("");
        List<Word> res = new ArrayList<>();
        
        for (Word candidate : allWords) {
            if (levDist.calcDist(candidate.getWordTarget(), wordTarget) < LEV_THRESH) {
                res.add(candidate);
            }
        }
        
        Collections.sort(res, new SortByLevenDist(wordTarget, levDist));
        
        if (res.size() <= MAX_RECOMMENDATIONS_SIZE) {
            return res;
        }
        
        return res.subList(0, MAX_RECOMMENDATIONS_SIZE);
    }

    public Trie<Word> getWords() {
        return words;
    }
    
    public boolean contains(String word_target) {
        return words.contains(word_target);
    }

    public boolean remove(String word_target) {
        return words.remove(word_target);
    }
}
