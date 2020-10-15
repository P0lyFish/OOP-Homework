package backend;


import util.Trie;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

    public static void insertFromFileAnhViet190K(Dictionary dict) {
        out.println("Importing data from anhviet190K database");

        File file = new File("resources/anhviet109K.txt");
        try (FileInputStream fileReader = new FileInputStream(file)) {
            byte[] byte_data = new byte[(int)file.length()];
            fileReader.read(byte_data);
            String data = new String(byte_data, StandardCharsets.UTF_8);
            System.out.println(data.length());

            String[] words = data.split("@");
            int cnt = 0;
            for (String word : words) {
                String []target_pronunciation_explain = word.split("\n", 2);
                if (target_pronunciation_explain.length != 2) {
                    continue;
                }
                String []target_pronunciation = target_pronunciation_explain[0].split("/", 2);
                String word_target = target_pronunciation[0];
                word_target = word_target.trim();
                // System.out.println(target_pronunciation_explain[0] + ' ' + target_pronunciation[1]);
                String word_pronunciation = "";
                if (target_pronunciation.length > 1) {
                    word_pronunciation = target_pronunciation[1];
                    word_pronunciation = "/" + word_pronunciation;
                }
                else {
                    word_pronunciation = "no data";
                }
                String word_explain = target_pronunciation_explain[1];

                if (word_target.length() > 0) {
                    dict.add(word_target, word_explain, word_pronunciation);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            out.println("Invalid import file!");
        }
    }

    public static String[] dictionaryLookup(Dictionary dict, String word_target) {
        // for (int i = 0; i < dict.size(); ++i) {
        //     Word w = dict.get(i);
        //     if (w.getWordTarget().equals(word_target.toLowerCase())) {
        //         return new String[] {w.getWordExplain(), w.getWordPronunciation()};
        //     }
        // }
        List<Word> w = dict.get(word_target);
        if (w.size() != 0) {
            return new String[] {w.get(0).getWordExplain(), w.get(0).getWordPronunciation()};
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
        List<Word> wordMatchedList = dict.get(prefix);

        List<String> ret = new ArrayList<String>();
        for (Word w : wordMatchedList) {
            ret.add(w.getWordTarget());
        }

        return ret;
    }

    public static void dictionaryExportToFile(Dictionary dict, String dst) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(dst), StandardCharsets.UTF_8))) {
            List<Word> allWord = dict.get("");
            for (int i = 0; i < allWord.size(); ++i) {
                String word_target = allWord.get(i).getWordTarget();
                String word_explain = allWord.get(i).getWordExplain();
                writer.write(String.format("%s\t%s\n", word_target, word_explain));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
