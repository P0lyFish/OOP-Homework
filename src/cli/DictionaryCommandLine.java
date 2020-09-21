package cli;


import backend.DictionaryManagement;
import backend.Dictionary;

import java.util.Scanner;


public class DictionaryCommandLine {
    private Dictionary dict;

    public DictionaryCommandLine() {
        dict = new Dictionary();
    }

    public DictionaryCommandLine(Dictionary dict) {
        this.dict = dict;
    }

    public void setDict(Dictionary dict) {
        this.dict = dict;
    }

    public Dictionary getDict() {
        return this.dict;
    }

    public void dictionaryBasic() {
        DictionaryManagement.insertFromCommandline(dict);
        DictionaryCommandline.showAllWords(dict);
    }

    public void dictionaryAdvance() {
        DictionaryManagement.insertFromFile(dict);
        DictionaryCommandline.showAllWords(dict);

        Scanner cin = new Scanner(System.in);

        while (true) {
            DictionaryCommandline.lookup(dict);

            boolean stop = false;
            while (true) {
                System.out.print("Continue? [Y|n]: ");
                String s = cin.nextLine();
                if (s.equals("n")) {
                    stop = true;
                }
                if (s.equals("Y") || s.equals("n")) {
                    break;
                }
            }

            if (stop) {
                break;
            }
        }
    }

    public static void main(String []args) {
//         dictionaryBasic();
//         dictionaryAdvance();
        Dictionary dict = new Dictionary();
        DictionaryManagement.insertFromFile(dict);
        DictionaryManagement.dictionaryExportToFile(dict, "out.txt");
//        DictionaryCommandline.dictionarySearcher(dict);
//        DictionaryManagement.removeWordFromCmd(dict);
//        DictionaryCommandline.showAllWords(dict);
//        DictionaryManagement.addWordFromCmd(dict);
//        DictionaryCommandline.showAllWords(dict);
    }
}
