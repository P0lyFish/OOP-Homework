package gui;

import backend.Dictionary;
import backend.DictionaryManagement;

public class DictionaryApplication {
    public void runApplication() {
        Dictionary dict = new Dictionary();
        DictionaryManagement.insertFromFile(dict);

        GUI gui = new GUI(dict);
    }

    public static void main(String []args) {
        DictionaryApplication app = new DictionaryApplication();
        app.runApplication();
    }
}
