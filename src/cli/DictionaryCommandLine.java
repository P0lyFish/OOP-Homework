package cli;


import backend.DictionaryManagement;
import backend.Dictionary;

import java.util.Scanner;


public class DictionaryCommandLine {
    public static void dictionaryBasic() {
        Dictionary dict = new Dictionary();
        DictionaryManagement.insertFromCommandline(dict);
        DictionaryCommandline.showAllWords(dict);
    }

    public static void dictionaryAdvance() {
        Dictionary dict = new Dictionary();
        DictionaryManagement.insertFromFile(dict);
        DictionaryCommandline.showAllWords(dict);

        while (true) {
            System.out.print("Lookup for: ");
            Scanner cin = new Scanner(System.in);

            String lookup_word = cin.nextLine();
            String result = DictionaryManagement.dictionaryLookup(dict, lookup_word);

            if (result == null) {
                System.out.println(lookup_word + " is not found in the dictionary");
            }
            else {
                System.out.println("Meaning: " + result);
            }

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
        // dictionaryBasic();
        dictionaryAdvance();
    }
}
