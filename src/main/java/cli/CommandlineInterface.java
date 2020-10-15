package cli;

import backend.Dictionary;
import backend.DictionaryManagement;

import java.util.Scanner;

public class CommandlineInterface {
    public static void main(String []args) {
        DictionaryCommandLine dict_cmd = new DictionaryCommandLine();
        Dictionary dict = dict_cmd.getDict();
        Scanner cin = new Scanner(System.in);

        System.out.println("Dictionary - Commandline Interface Version");
        System.out.println("Usage:");
        System.out.println("insertFromFile    : Import dictionary from file");
        System.out.println("insertFromCmd     : import dictionary from commandline");
        System.out.println("lookup            : Search for a word");
        System.out.println("add               : Add a word");
        System.out.println("remove            : Remove a word");
        System.out.println("export            : Export the current dictionary to file");
        System.out.println("showAll           : Show all words");
        System.out.println("match             : Find all words that match a given prefix");
        System.out.println("basic             : Call insertFromCmd and showAll");
        System.out.println("advance           : Call insertFromFile, showAll and lookup");
        System.out.println("exit              : Exit the program");

        while (true) {
            System.out.print(">>> ");

            String cmd = cin.nextLine();
            boolean stop = false;
            switch (cmd) {
                case "insertFromFile": {DictionaryManagement.insertFromFile(dict); break;}
                case "insertFromCmd": {DictionaryManagement.insertFromCommandline(dict); break;}
                case "lookup": {DictionaryCommandline.lookup(dict); break;}
                case "add": {DictionaryManagement.addWordFromCmd(dict); break;}
                case "remove": {DictionaryManagement.removeWordFromCmd(dict_cmd.getDict()); break;}
                case "export": {
                    System.out.print("File name [dictionaries.txt]: ");
                    String filename = cin.nextLine();
                    if (filename.isEmpty()) {
                        filename = "dictionaries.txt";
                    }
                    DictionaryManagement.dictionaryExportToFile(dict, filename);
                    break;
                }
                case "showAll": {DictionaryCommandline.showAllWords(dict); break;}
                case "match": {DictionaryCommandline.dictionarySearcher(dict); break;}
                case "basic": {dict_cmd.dictionaryBasic(); break;}
                case "advance": {dict_cmd.dictionaryAdvance(); break;}
                case "exit": {stop = true; break;}
                default: System.out.println("Invalid command");
            }

            if (stop) {
                break;
            }
        }
    }
}
