package backend;


import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

import static java.lang.System.out;
import java.util.List;
import java.util.ArrayList;


public class DictionaryManagement {
    public static String FILENAME = "resources/dictionaries.txt";

    public static void insertFromCommandline(Dictionary dict) {
        Scanner cin = new Scanner(System.in);

        out.println("Reading new words from commandline...");

        int nWords;

        while (true) {
            out.print("Number of words: ");
            String inp = cin.nextLine();

            try {
                nWords = Integer.parseInt(inp);
                break;
            }
            catch (NumberFormatException e) {
                out.println("Invalid number! Please enter again.");
            }
        }

        for (int i = 0; i < nWords; ++i) {
            out.print("New word: ");
            String word_target = cin.nextLine();
            out.print("Explaination: ");
            String word_explain = cin.nextLine();

            dict.add(word_target, word_explain);
        }
    }

    public static void insertFromFile(Dictionary dict) {
        out.println("Importing data from dictionaries.txt");
        try (Scanner importFile = new Scanner(new File(FILENAME))) {
            while (importFile.hasNextLine()) {
                String inp = importFile.nextLine();
                String []data = inp.split("\t", -1);
                String word = data[0];
                String meaning = data[1];

                dict.add(word, meaning);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            out.println("Invalid import file!");
        }
    }

    public static String dictionaryLookup(Dictionary dict, String word_target) {
        for (int i = 0; i < dict.size(); ++i) {
            Word w = dict.get(i);
            if (w.getWordTarget().equals(word_target)) {
                return w.getWordExplain();
            }
        }

        return null;
    }

    public static void addWordFromCmd(Dictionary dict) {
        Scanner cin = new Scanner(System.in);

        out.print("New word: ");
        String word_target = cin.nextLine();

        out.print("Corresponding meaning: ");
        String word_explain = cin.nextLine();

        dict.add(word_target, word_explain);
    }

    public static void removeWordFromCmd(Dictionary dict) {
        Scanner cin = new Scanner(System.in);

        out.print("word need to be removed: ");
        String word_target = cin.nextLine();

        if (dict.remove(word_target)) {
            out.println(word_target + " has been removed");
        }
        else {
            out.println(word_target + " is not belong to the dictionary");
        }
    }

    public static List<String> getPrefixMatchedList(Dictionary dict, String prefix) {
        List<String> ret = new ArrayList<String>();

        for (int i = 0; i < dict.size(); ++i) {
            String word_target = dict.get(i).getWordTarget();

            if (word_target.substring(0, prefix.length()).equals(prefix)) {
                ret.add(word_target);
            }
        }

        return ret;
    }
}
