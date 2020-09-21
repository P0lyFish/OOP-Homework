package gui;

import backend.Dictionary;
import backend.DictionaryManagement;
import backend.Word;

import javax.swing.*;

public class DictionaryPanel {
    JTextArea wordExplainPanel;
    DictionaryPanel(JFrame f) {
        wordExplainPanel = new JTextArea();
        wordExplainPanel.setEditable(false);
        f.add(wordExplainPanel);
    }

    public void updateWord(Dictionary dict, String word_target) {
        wordExplainPanel.setText("");

        String[] word_data = DictionaryManagement.dictionaryLookup(dict, word_target);
        if (word_data == null) {
            wordExplainPanel.setText(word_target + " not found");
            return;
        }

        String word_explain = word_data[0];
        String word_pronunciation = word_data[1];
        wordExplainPanel.append(word_target + '\n');
        wordExplainPanel.append("\n");
        wordExplainPanel.append("Pronunciation: " + word_pronunciation + '\n');
        wordExplainPanel.append("\n");
        wordExplainPanel.append("Meaning: \n" + word_explain + '\n');
    }

    public JTextArea getWordExplainPanel() {
        return wordExplainPanel;
    }
}
