/*
 * Name: Jordan Greenbaum
 * Instructor: Dr. Joseph Helsing
 * Course: CSI 111
 * Assignment: Project 3
 * Date: 04 December 2020
 * Description: Sets up a new database by linking together new DataManager and GUI objects.
 */

/**
 * Sets up a new database by linking together new DataManager and GUI objects.
 * @author Jordan Greenbaum
 */
public class UserInterface {

    /**
     * Creates new DataManager and GUI objects and links them together.
     * @param args commandline arguments
     */
    public static void main(String[] args)
    {
        DataManager manager = new DataManager(); // Create a new DataManager object to store and manage data
        GUI window = new GUI(manager); // Create a new GUI object to display and allow the user to interact with data

        window.setVisible(true); // Make the main window visible
    }
}
