package cli;


import java.util.Scanner;


public class DictionaryManagement {
    public static void insertFromCommandline(Dictionary dict) {
        Scanner cin = new Scanner(System.in);

        System.out.println("Reading new words from commandline...");

        int nWords;

        while (true) {
            System.out.print("Number of words: ");
            String inp = cin.nextLine();

            try {
                nWords = Integer.parseInt(inp);
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid number! Please enter again.");
            }
        }

        for (int i = 0; i < nWords; ++i) {
            System.out.print("New word: ");
            String word_target = cin.nextLine();
            System.out.print("Explaination: ");
            String word_explain = cin.nextLine();

            dict.add(word_target, word_explain);
        }
    }
}
