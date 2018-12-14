package screens;

import tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * By: Yasasvi Hari
 *
 * This is the window in which users can select their tools. Both the StartScreen and the MainScreen will call this
 * to collect the panel. This window will be split into two halves, one containing a button for each tool and one on
 * the right containing the selected tools.
 */
class ToolWindow implements Window {
    // constants
    private final static int HEIGHT = 400;
    private final static int WIDTH = 600;
    private final static int BUFFER = 50; // there will be three buffers, one from each edge, and one between the 2

    // parameters
    private List<Tool> tools;
    private List<Tool> selectedTools;
    private Map<String, Tool> toolMap;

    // GUI
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    ToolWindow(List<Tool> tools, List<Tool> selectedTools) {
        this.tools = Objects.requireNonNull(tools);
        this.selectedTools = Objects.requireNonNull(selectedTools);
        this.toolMap = getToolMap();
    }

    private Map<String, Tool> getToolMap() {
        Map<String, Tool> map = new HashMap<>();

        for(Tool tool : tools) {
            map.put(tool.getName(), tool);
        }

        return map;
    }

    /**
     * Unnecessary.
     */
    @Override
    public void onCreate() {
    }

    private void onRightPanelButtonClick(JButton button) {
        JButton leftButton = new JButton(button.getText());
        leftPanel.add(leftButton);
        button.setVisible(false);
    }

    private void prepareRightPanel() {
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
        leftPanel.add(new JLabel("Selected Tools"), 0);
    }

    private void onLeftPanelButtonClick(JButton button) {
        JButton newButton = new JButton(button.getText());
        newButton.addActionListener(e -> onRightPanelButtonClick(button));
        rightPanel.add(newButton);
        button.setVisible(false);
    }

    private void prepareLeftPanel() {
        leftPanel.setLayout(new GridLayout(2, 1));
        leftPanel.add(new JLabel("Tools"), 0);

        JPanel panel = new JPanel();
        panel.setSize(new Dimension(WIDTH, HEIGHT));

        for(Tool tool : tools) {
            JButton button = new JButton(tool.getName());
            button.addActionListener((e) -> onLeftPanelButtonClick(button));
            panel.add(button);
        }

        leftPanel.add(panel, 1);
    }

    /**
     *
     * @return panel split into two halves-- one right and one left. The left panel will contain the buttons, and the
     * right panel will be empty initially.
     */
    @Override
    public JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        prepareLeftPanel();
        prepareRightPanel();

        panel.add(leftPanel, 0);
        panel.add(rightPanel, 1);

        return panel;
    }
}