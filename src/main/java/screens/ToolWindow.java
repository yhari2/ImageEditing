package screens;

import tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class ToolWindow {
    // constants
    private final static int SCROLL_PANE_DIMS = 250;

    private List<Tool> allTools;
    private List<Tool> selectedTools;

    public ToolWindow(List<Tool> tools) {
        this.allTools = tools;
        selectedTools = new ArrayList<>();
    }

    JScrollPane getPane() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(SCROLL_PANE_DIMS, SCROLL_PANE_DIMS));

        for(Tool tool : allTools) {

        }

        return scrollPane;
    }
}
