package tools;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * By: Yasasvi Hari
 *
 * This interface is used to allow developers to quickly extend the functionalities that I have provided here.
 * Each tool implementation will be instantiated by the Main class, added to a list, and presented to the user.
 * The intention of this class is so that users can select exactly which and how many tools they want. One of the major
 * problems that users have disclosed to me is that their interfaces were cluttered and confusing, and the intention is
 * to declutter the interface. Each tool could also implement its own version of hotkeys, so that the user retains full
 * functionality of the keyboard.
 *
 * NOTE: Tools should not use "T" as a hotkey, as that it is already being used to show the ToolWindow.
 *
 */
public interface Tool {
    /**
     *
     * This method is called by the Main class and is used to do any preprocessing that the tool needs to do to run.
     * If the tool doesn't need any preprocessing, developers can leave this method empty.
     */
    void onCreate();

    /**
     *
     * This method will be called by the StartScreen as well as the main screen
     *
     * @return the name of the tool to be presented to the user in the menu from the start screen.
     */
    String getName();


    /**
     *
     * This method will be called by the MainScreen class when it creates a HashMap. Any combination of the keys should
     * be deliminated by "+" (i.e. "Shift+J+T", where the three keys pressed are the Shift Key, J, and T).
     * NOTE: there should be no spaces in the hotkey shortcut
     *
     * @return a String representing the keyboard shortcut
     */
    String getHotKeyShortcut();

    /**
     *
     * This method will be called from the MainScreen when the user clicks.
     *
     * @param image = the input image
     * @param x = The x coordinate of the selection (0 <= x && y < image.size())
     * @param y = The y coordinate of the selection (0 <= y && y < image.size())
     * @return returns a new image
     */
    BufferedImage onClick(BufferedImage image, int x, int y);

    /**
     *
     * This method will be called from the MainScreen when the user presses a keyboard key. This is where each tool can
     * have its own hotkey mappings.
     *
     * @param image = input image
     * @param e = KeyEvent
     * @return resulting non-null buffered image
     */
    BufferedImage onKey(BufferedImage image, KeyEvent e);
}
