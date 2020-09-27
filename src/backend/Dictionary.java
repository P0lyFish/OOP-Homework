package backend;

import util.Trie;

import java.util.ArrayList;
import java.util.List;


public class Dictionary {
    Trie<Word> words;

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
        return words.queryPrefix(prefix.toLowerCase());
    }

    public Trie<Word> getWords() {
        return words;
    }

    public boolean remove(String word_target) {
        return words.remove(word_target);
    }
}
