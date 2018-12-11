package screens;

import tools.CropTool;
import tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * By: Yasasvi Hari
 *
 * This class will be the first screen that users see. The goal of this screen is to allow users to configure the tools
 * that they would like to use. If the user wants to return to this screen and reconfigure the tool, the MainScreen can
 * simply throw the ToolWindow which will facilitate this reconfiguration.
 */
public class StartScreen implements Screen {
    // constants
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 1000;

    private List<Tool> selectedTools = new ArrayList<>();
    private List<Tool> allTools = new ArrayList<>();

    // GUI
    private JFrame frame;

    private void onNext() {
        frame.dispose();
        MainScreen mainScreen = new MainScreen(selectedTools);
        mainScreen.draw();
    }

    /**
     *
     * This method adds the tools to the list above. As they are implemented, I will add the tools here.
     * There should also be no repeats here, to avoid confusion in the StartScreen.
     */
    private void prepareTools() {
        allTools.add(new CropTool());
    }


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
        prepareTools();
        ToolWindow toolWindow = new ToolWindow(allTools, selectedTools);
        panel.add(toolWindow.getPanel(),1);

        // put the buttons on the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JButton selectAllButton = new JButton("SELECT ALL");
        JButton nextButton = new JButton("Next");
        selectAllButton.addActionListener(e -> selectedTools = allTools);
        nextButton.addActionListener(e -> onNext());
        buttonPanel.add(selectAllButton);
        buttonPanel.add(nextButton);
        panel.add(buttonPanel, 2);

        // display
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
