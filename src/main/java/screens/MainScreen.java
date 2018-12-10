package screens;

import core.HotKeyListener;
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
 * This is the primary screen which simply displays the image and allows users to edit images. This class gets called by
 * the StartScreen class when the user has finished selecting their tools.
 */
public class MainScreen implements Screen {
    private HotKeyListener hotKeyListener;
    private Tool currentTool = null;
    private List<Tool> tools;
    private Map<String, Tool> keyToToolMap;

    // constants
    private final static int DIMENSIONS = 1000;

    // GUI
    private JFrame frame;

    public MainScreen(List<Tool> tools) {
        this.tools = Objects.requireNonNull(tools);
        prepareTools();
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(new Dimension(DIMENSIONS, DIMENSIONS));
    }

    private void prepareTools() {
        keyToToolMap = new HashMap<>();

        for(Tool tool : tools) {
            tool.onCreate();
            keyToToolMap.put(tool.getHotKeyShortcut(), tool);
        }

        hotKeyListener = new HotKeyListener(this);
    }

    public void setCurrentTool(Tool tool) {
        this.currentTool = tool;
    }


    public Map<String, Tool> getKeyToToolMap() {
        return this.keyToToolMap;
    }

    @Override
    public void draw() {
        // TODO: IMPLEMENT ME
    }
}
