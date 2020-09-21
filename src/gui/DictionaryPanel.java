package gui;

import backend.Dictionary;
import backend.DictionaryManagement;
import backend.Word;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class DictionaryPanel {
    private final JTextPane wordExplainPanel;
    private final SimpleAttributeSet word_target_style;
    private final SimpleAttributeSet pronunciation_style;
    private final SimpleAttributeSet heading_style;
    private final SimpleAttributeSet explanation_style;

    DictionaryPanel(JFrame f) {
        wordExplainPanel = new JTextPane();
        wordExplainPanel.setEditable(false);
        f.add(wordExplainPanel);
        word_target_style = new SimpleAttributeSet();
        pronunciation_style = new SimpleAttributeSet();
        explanation_style = new SimpleAttributeSet();
        heading_style = new SimpleAttributeSet();

        StyleConstants.setFontSize(word_target_style, 30);
        StyleConstants.setBold(word_target_style, true);
        StyleConstants.setForeground(word_target_style, Color.blue);

        StyleConstants.setFontSize(heading_style, 20);
        StyleConstants.setBold(heading_style, true);

        StyleConstants.setFontSize(pronunciation_style, 15);
        StyleConstants.setForeground(pronunciation_style, Color.green);

        StyleConstants.setFontSize(explanation_style, 15);
    }

    public void updateWord(Dictionary dict, String word_target) {
        wordExplainPanel.setText("");
        Document doc = wordExplainPanel.getStyledDocument();

        String[] word_data = DictionaryManagement.dictionaryLookup(dict, word_target);
        if (word_data == null) {
            wordExplainPanel.setText(word_target + " not found");
            return;
        }

        String word_explain = word_data[0];
        String word_pronunciation = word_data[1];
        try {
            doc.insertString(doc.getLength(), word_target + "\n", word_target_style);
            doc.insertString(doc.getLength(), "Pronunciation: ", heading_style);
            doc.insertString(doc.getLength(), word_pronunciation + "\n", pronunciation_style);
            doc.insertString(doc.getLength(), "Meaning:\n", heading_style);
            doc.insertString(doc.getLength(), word_explain, explanation_style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public JTextPane getWordExplainPanel() {
        return wordExplainPanel;
    }
}
