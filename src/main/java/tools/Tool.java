package tools;

/**
 *
 * By: Yasasvi Hari
 *
 * This interface is used to allow developers to quickly extend the functionalities that I have provided here.
 * Each tool implementation will be instantiated by the Main class, added to a list, and presented to the user.
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
     * @return the name of the tool to be presented to the user in the menu from the main screen
     */
    String getName();
}
