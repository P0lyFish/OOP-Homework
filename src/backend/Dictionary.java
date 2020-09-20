package backend;


import java.util.ArrayList;
import java.util.List;


public class Dictionary {
    private final List<Word> words;

    public Dictionary() {
        words = new ArrayList<Word>();
    }

    public Dictionary(List<Word> words) {
        this.words = words;
    }

    public void add(String word_target, String word_explain) {
        words.add(new Word(word_target, word_explain));
    }

    public int size() {
        return words.size();
    }

    public Word get(int i) {
        return words.get(i);
    }
}
