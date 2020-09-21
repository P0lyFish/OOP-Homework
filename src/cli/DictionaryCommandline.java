package cli;


import backend.Dictionary;
import backend.DictionaryManagement;

import java.util.List;
import java.util.Scanner;


class DictionaryCommandline {

    public static final int COLUMN1_WIDTH = 6;
    public static final int COLUMN2_WIDTH = 15;

    public static void showAllWords(Dictionary dict) {

        int maxWordLen = 0;
        for (int i = 0; i < dict.size(); ++i) {
            maxWordLen = Math.max(maxWordLen, dict.get(i).getWordTarget().length());
        }

        StringBuilder upperBar_col1 = new StringBuilder("No");
        while (upperBar_col1.length() < COLUMN1_WIDTH) {
            upperBar_col1.append(" ");
        }
        StringBuilder upperBar_col2 = new StringBuilder("| English");
        while (upperBar_col2.length() < COLUMN2_WIDTH) {
            upperBar_col2.append(" ");
        }
        String upperBar_col3 = "| Vietnamese";
        System.out.println(upperBar_col1.toString() + upperBar_col2.toString() + upperBar_col3);

        for (int i = 0; i < dict.size(); ++i) {
            StringBuilder col1 = new StringBuilder(String.format("%03d", i + 1));
            StringBuilder col2 = new StringBuilder("| " + dict.get(i).getWordTarget());
            String col3 = "| " + dict.get(i).getWordExplain();

            while (col1.length() < COLUMN1_WIDTH) {
                col1.append(" ");
            }

            while (col2.length() < COLUMN2_WIDTH) {
                col2.append(" ");
            }

            System.out.println(col1.toString() + col2.toString() + col3);
        }
    }

    public static void dictionarySearcher(Dictionary dict) {
        System.out.print("Seach for: ");
        Scanner cin = new Scanner(System.in);
        String prefix = cin.nextLine();

        List<String> matchedList = DictionaryManagement.getPrefixMatchedList(dict, prefix);

        System.out.printf("Found %d matched words: ", matchedList.size());
        for (String s : matchedList) {
            System.out.println(s);
        }
    }

    public static void lookup(Dictionary dict) {
        System.out.print("Lookup for: ");
        Scanner cin = new Scanner(System.in);

        String lookup_word = cin.nextLine();
        String[] result = DictionaryManagement.dictionaryLookup(dict, lookup_word);

        if (result == null) {
            System.out.println(lookup_word + " is not found in the dictionary");
        }
        else {
            System.out.println("Pronunciation: " + result[1]);
            System.out.println("Meaning: " + result[0]);
        }
    }
}
