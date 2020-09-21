package gui;


import backend.Dictionary;
import backend.DictionaryManagement;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;


public class SearchPanel {
    private final JTextField searchBar;
    private JList<String> matchingBox;
    private final JSplitPane panel;

    SearchPanel(JFrame F, DictionaryPanel mainPanel, Dictionary dict) {
        // int SEARCH_BAR_X = 0;
        // int SEARCH_BAR_Y = 0;
        // int SEARCH_BAR_WIDTH = 100;
        // int SEARCH_BAR_HEIGHT = 10;

        // int MATCHING_BOX_X = 0;
        // int MATCHING_BOX_Y = 0;
        // int MATCHING_BOX_WIDTH = 10;
        // int MATCHING_BOX_HEIGHT = 10;

        // int PANEL_BOX_X = 0;
        // int PANEL_BOX_Y = 0;
        // int PANEL_BOX_WIDTH = 10;
        // int PANEL_BOX_HEIGHT = 10;

        searchBar = new JTextField();
        matchingBox = new JList<String>();

        // searchBar.setBounds(SEARCH_BAR_X, SEARCH_BAR_Y, SEARCH_BAR_WIDTH, SEARCH_BAR_HEIGHT);
        // matchingBox.setBounds(MATCHING_BOX_X, MATCHING_BOX_Y, MATCHING_BOX_WIDTH, MATCHING_BOX_HEIGHT);

        matchingBox = new JList<String>();

        // searchBar.addActionListener(e -> updateMatchingBox(dict, searchBar.getText()));
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateMatchingBox(dict, searchBar.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                updateMatchingBox(dict, searchBar.getText());
            }

            public void changedUpdate(DocumentEvent e) {
                updateMatchingBox(dict, searchBar.getText());
            }
        });

        searchBar.addActionListener((event) -> mainPanel.updateWord(dict, searchBar.getText()));

        matchingBox.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String selectedWord = matchingBox.getSelectedValue();
                if (selectedWord != null) {
                    searchBar.setText(selectedWord);
                    mainPanel.updateWord(dict, selectedWord);
                }
            }
        });

        panel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, searchBar, matchingBox);
        // panel.setBounds(PANEL_BOX_X, PANEL_BOX_Y, PANEL_BOX_WIDTH, PANEL_BOX_HEIGHT);

        F.add(panel);
    }

    public JSplitPane getPanel() {
        return panel;
    }

    private void updateMatchingBox(Dictionary dict, String prefix) {
        List<String> matching_words = DictionaryManagement.getPrefixMatchedList(dict, prefix);

        DefaultListModel<String> listModel = new DefaultListModel<String>();
        for (String s : matching_words) {
            listModel.addElement(s);
        }

        matchingBox.setModel(listModel);
    }
}
