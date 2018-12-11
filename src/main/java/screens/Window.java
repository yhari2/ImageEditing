package screens;

import javax.swing.*;

/**
 * By: Yasasvi Hari
 *
 *
 * This interface is used to throw windows up in specific scenarios. For example, a window will be thrown in the
 * MainScreen whenever the user presses "T", facilitating a ToolWindow. The intention here is that developers could
 * add even develop even entire modes, enriching them with these windows.
 */
public interface Window {
    /**
     *
     * This will be called when immediately after the Screen initializes the Window, and will allow the developer to
     * preprocess any information that the window needs (i.e. hotkey dictionaries).
     */
    void onCreate();

    /**
     *
     * This will be called by the
     * @return non-null panel containing the window.
     */
    JPanel getPanel();
}
