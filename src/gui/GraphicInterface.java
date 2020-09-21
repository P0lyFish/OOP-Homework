package gui;

import backend.Dictionary;

import javax.swing.*;

public class GraphicInterface {
    GraphicInterface(Dictionary dict) {
        int FRAME_WIDTH = 800;
        int FRAME_HEIGHT = 800;

        JFrame f = new JFrame("Dictionary");
        f.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        DictionaryPanel mainPanel = new DictionaryPanel(f);
        SearchPanel searchPanel = new SearchPanel(f, mainPanel, dict);

        JSplitPane gui = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, searchPanel.getPanel(),
                                        mainPanel.getWordExplainPanel());
        gui.setResizeWeight(0.3);

        f.add(gui);

        f.setVisible(true);
    }
}