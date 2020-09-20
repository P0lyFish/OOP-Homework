package cli;


import java.util.ArrayList;
import java.util.List;


class Dictionary {
    private final List<Word> words;

    Dictionary() {
        words = new ArrayList<Word>();
    }

    Dictionary(List<Word> words) {
        this.words = words;
    }

    void add(String word_target, String word_explain) {
        words.add(new Word(word_target, word_explain));
    }

    int size() {
        return words.size();
    }

    Word get(int i) {
        return words.get(i);
    }
}
