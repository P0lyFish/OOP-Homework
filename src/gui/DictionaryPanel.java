package gui;

import backend.Dictionary;
import backend.DictionaryManagement;
import backend.Word;

import javax.swing.*;

public class DictionaryPanel {
    JTextArea wordExplainPanel;
    DictionaryPanel(JFrame f) {
        wordExplainPanel = new JTextArea();
        f.add(wordExplainPanel);
    }

    public void updateWord(Dictionary dict, String word_target) {
        wordExplainPanel.setText("");

        String word_explain = DictionaryManagement.dictionaryLookup(dict, word_target);
        if (word_explain == null) {
            wordExplainPanel.setText(word_target + " not found");
            return;
        }

        wordExplainPanel.append(word_target + '\n');
        wordExplainPanel.append(word_explain + '\n');
    }

    public JTextArea getWordExplainPanel() {
        return wordExplainPanel;
    }
}
