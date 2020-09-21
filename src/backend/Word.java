package backend;


public class Word {
    private String word_target;
    private String word_explain;
    private String word_pronunciation;

    Word() {
        word_target = "no data";
        word_explain = "no data";
        word_pronunciation = "no data";
    }

    Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    Word(String word_target, String word_explain, String word_pronunciation) {
        this.word_target = word_target;
        this.word_explain = (word_explain.length() > 0 ? word_explain : "no data");
        this.word_pronunciation = (word_pronunciation.length() > 0 ? word_pronunciation : "no data");
    }

    public void setWordTarget(String word_target) {
        this.word_target = word_target;
    }

    public String getWordTarget() {
        return word_target;
    }

    public void setWordExplain(String word_explain) {
        this.word_explain = (word_explain.length() > 0 ? word_explain : "no data");
    }

    public String getWordExplain() {
        return word_explain;
    }

    public void setWordPronunciation(String word_pronunciation) {
        this.word_pronunciation = (word_pronunciation.length() > 0 ? word_pronunciation : "no data");
    }

    public String getWordPronunciation() {
        return word_pronunciation;
    }
}
