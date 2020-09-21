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
        words.add(new Word(word_target.toLowerCase(), word_explain));
    }

    public int size() {
        return words.size();
    }

    public Word get(int i) {
        return words.get(i);
    }

    public boolean remove(String word_target) {
        for (int i = 0; i < words.size(); ++i) {
            if (words.get(i).getWordTarget().equals(word_target)) {
                words.set(i, words.get(words.size() - 1));
                words.remove(words.size() - 1);

                return true;
            }
        }

        return false;
    }
}
