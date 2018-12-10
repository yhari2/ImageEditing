package core;

import screens.MainScreen;
import tools.Tool;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * By: Yasasvi Hari
 *
 * The intention of this class is to support the user's hot-key shortcuts. Interviews with photographers show that some
 * photographers, particularly older users, have indicated that these keys are really useful. We can allow users to
 * customize what they would like their mappings to be as well.
 */
public class HotKeyListener implements KeyListener {
    private KeyEvent heldKey = null;
    private KeyEvent pressedKey = null;

    // used to notify the mainscreen to change the tool
    private MainScreen mainScreen;
    private Map<String, Tool> keyToToolMap;

    // will be the key in the
    private String currentKeyCombination = "";

    public HotKeyListener(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        this.keyToToolMap = mainScreen.getKeyToToolMap();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String keyText = e.getKeyText(e.getKeyCode());

        if(heldKey != null) {
            currentKeyCombination = currentKeyCombination.concat("+" + heldKey.getKeyText(heldKey.getKeyCode()));
        }

        currentKeyCombination = currentKeyCombination.concat("+" + keyText);

        if(keyToToolMap.containsKey(currentKeyCombination)) {
            mainScreen.setCurrentTool(keyToToolMap.get(currentKeyCombination));
            currentKeyCombination = "";
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.heldKey = e;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.heldKey = null;
    }
}