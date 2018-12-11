package screens;

import tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * By: Yasasvi Hari
 *
 * This class will be the first screen that users see. The goal of this screen is to allow users to configure the tools
 * that they would like to use. If the user wants to return to this screen and reconfigure the tool
 */
public class StartScreen implements Screen {
    // constants
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 1000;

    private List<Tool> selectedTools = new ArrayList<>();
    private List<Tool> allTools = new ArrayList<>();

    // GUI
    private JFrame frame;
    private JPanel leftMiddle = new JPanel();
    private JPanel rightMiddle = new JPanel();

    private void next() {
        frame.dispose();
        MainScreen mainScreen = new MainScreen(selectedTools);
        mainScreen.draw();
    }

    private void onLeftButtonClick() {

    }

    private void makeMiddleLeftPanel() {
        leftMiddle.setLayout(new BoxLayout(leftMiddle, BoxLayout.Y_AXIS));

        for(Tool tool : allTools) {
            JButton button = new JButton(tool.getName());
            button.addActionListener(e -> onLeftButtonClick());
            leftMiddle.add(button);
        }
    }

    /**
     *
     * Handles all GUI stuff.
     */
    @Override
    public void draw() {
        frame = new JFrame();
        frame.setLayout(new GridLayout(3,1));
        frame.setSize(new Dimension(WIDTH, HEIGHT));

        JPanel panel = new JPanel();
        panel.setSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // put a label on the top
        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("Please select the tools below.");
        topPanel.add(label);
        panel.add(topPanel, 0);

        // set up the middle panel
        JPanel midPanel = new JPanel(new GridLayout(1, 2));
        makeMiddleLeftPanel();
        
        frame.add(midPanel);

        // put the buttons on the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JButton selectAllButton = new JButton("SELECT ALL");
        JButton nextButton = new JButton("Next");
        selectAllButton.addActionListener(e -> selectedTools = allTools);
        nextButton.addActionListener(e -> next());
        buttonPanel.add(selectAllButton);
        buttonPanel.add(nextButton);
        panel.add(buttonPanel, 2);

        // display
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
