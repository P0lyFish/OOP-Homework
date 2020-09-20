package cli;


public class DictionaryCommandLine {
    public static void dictionaryBasic() {
        Dictionary dict = new Dictionary();
        DictionaryManagement.insertFromCommandline(dict);
        DictionaryCommandline.showAllWords(dict);
    }

    public static void dictionaryAdvance() {
        Dictionary dict = new Dictionary();
        DictionaryManagement.
    }

    public static void main(String []args) {
        dictionaryBasic();
    }
}
