/*
 * Name: Jordan Greenbaum
 * Instructor: Dr. Joseph Helsing
 * Course: CSI 111
 * Assignment: Project 3
 * Date: 04 December 2020
 * Description: The front-end graphical user interface that allows the user to dynamically interact with data.
 */

import javax.swing.*; // For swing GUI components
import javax.swing.border.*; // To give components custom borders
import java.awt.*; // To get the screen size of the device
import java.awt.event.*; // To add event listeners
import java.io.*; // For file i/o

/**
 * The front-end graphical user interface that allows the user to dynamically interact with data.
 * @author Jordan Greenbaum
 */
public class GUI extends JFrame {
    private DataManager manager; // The DataManager used to control this GUI

    private JSplitPane split; // Divide the window between the tabbed pane and the menu panel

    private Tabs tabs; // Tabbed menu containing Book, Music, and Movie info.
    private MenuPanel menu; // Menu container to hold functional buttons

    /**
     * Constructs a new GUI object.
     * @param manager the DataManager object that holds the data do display in the GUI
     */
    public GUI(DataManager manager)
    {
        this.manager = manager;

        setTitle("Project 3 — Database"); // Set the window title
        setSize(Toolkit.getDefaultToolkit().getScreenSize()); // Set the window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the program when the user closes the window

        tabs = new Tabs(manager); // Create a tabs panel object
        menu = new MenuPanel(); // Create a menu panel object

        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabs, menu); // Split the window into panes
        split.setDividerLocation(Toolkit.getDefaultToolkit().getScreenSize().width * 3 / 4); // Set the divider location

        add(split); // Add the split panes to the main window
    }

    /**
     * A menu panel to be used in the GUI.
     */
    private class MenuPanel extends JPanel
    {
        private JLabel menuTitle = new JLabel("Menu"); // To display the text "Menu" at the top of the panel

        private JPanel cat1 = new JPanel(); // Category to hold check in/out buttons
        private JButton checkIn = new JButton("Check In"); // Checks in an item
        private JButton checkOut = new JButton("Check Out"); // Checks out an item

        private JPanel cat2 = new JPanel(); // Category to hold item information and availability buttons
        private JButton itemAvail = new JButton("Check Availability"); // Checks item availability
        private JButton itemInfo = new JButton("Item Information"); // Checks item information

        private JPanel cat3 = new JPanel(); // Category to hold inventory save/load buttons
        private JButton save = new JButton("Save Inventory"); // Saves the current inventory
        private JButton load = new JButton("Load Inventory"); // Loads a new inventory

        private JButton exit = new JButton("Exit"); // Exits the program

        /**
         * Constructs a new MenuPanel object.
         */
        public MenuPanel()
        {
            // Make the first button category:
            cat1.setBorder(new CompoundBorder( // Set the border
                    new EmptyBorder(5,5,5,5),
                    new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 1, true), "Manage Items")
            ));
            cat1.add(checkIn); // Add the check-in button to the category
            cat1.add(checkOut); // Add the check-out button to the category
            checkIn.addActionListener(new ButtonListener()); // Listen for button clicks
            checkOut.addActionListener(new ButtonListener()); // Listen for button clicks


            // Make the second button category:
            cat2.setBorder(new CompoundBorder( // Set the border
                    new EmptyBorder(5,5,5,5),
                    new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 1, true), "Item Info.")
            ));
            cat2.add(itemAvail); // Add the item availability button to the category
            cat2.add(itemInfo); // Add the item info button to the category
            itemAvail.addActionListener(new ButtonListener()); // Listen for button clicks
            itemInfo.addActionListener(new ButtonListener()); // Listen for button clicks


            // Make the third button category:
            cat3.setBorder(new CompoundBorder( // Set the border
                    new EmptyBorder(1,5,1,5),
                    new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 1, true), "Manage Inventory")
            ));
            cat3.add(save); // Add the save button to the category
            cat3.add(load); // Add the load button to the category
            save.addActionListener(new ButtonListener()); // Listen for button clicks
            load.addActionListener(new ButtonListener()); // Listen for button clicks

            exit.addActionListener(new ButtonListener()); // Listen for button clicks

            // Make sure all the components align at the center:
            menuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            cat1.setAlignmentX(Component.CENTER_ALIGNMENT);
            cat2.setAlignmentX(Component.CENTER_ALIGNMENT);
            cat3.setAlignmentX(Component.CENTER_ALIGNMENT);
            exit.setAlignmentX(Component.CENTER_ALIGNMENT);

            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Use a vertical box layout for the menu

            // Add all of the components to the panel:
            add(menuTitle);
            add(cat1);
            add(cat2);
            add(cat3);
            add(exit);
            add(new Box.Filler( // Add space at the bottom to remove whitespace from the category panels
                    null,
                    new Dimension(300, 10000), // Set the preferred size to a huge height to make the space vertically as large as possible, in turn making the other components as small as possible
                    null)
            );
        }
    }

    /**
     * Calls methods when JButtons are clicked.
     */
    private class ButtonListener implements ActionListener {

        /**
         * Calls the correct methods when a specific JButton is clicked.
         * @param e the ActionEvent fired by the button click
         */
        public void actionPerformed(ActionEvent e) {
            try {
                // Determine which button was pressed and call the appropriate methods:
                switch (e.getActionCommand()) {

                    case "Check In":
                        manager.checkIn(tabs);
                        return;
                    case "Check Out":
                        manager.checkOut(tabs);
                        return;
                    case "Check Availability":
                        manager.checkCopies();
                        return;
                    case "Item Information":
                        manager.displayInfo(tabs);
                        return;
                    case "Save Inventory":
                        manager.saveFile();
                        return;
                    case "Load Inventory":
                        if(manager.loadFile()) {
                            // If a file was chosen and successfully loaded, update the secondary index JTables to display the new data
                            ((MyTableModel) tabs.getBooks().getModel()).setData(TableData.fromBooks(manager.getBooks()));
                            ((MyTableModel) tabs.getMusic().getModel()).setData(TableData.fromMusic(manager.getMusic()));
                            ((MyTableModel) tabs.getMovies().getModel()).setData(TableData.fromMovies(manager.getMovies()));
                        }
                        return;
                    case "Exit":
                        System.exit(0);
                        return;
                }

            }
            // Catch File I/O Exceptions and display an appropriate dialog:
            catch(IOException err) {
                JOptionPane.showMessageDialog(null,"Something went wrong saving/loading your selected file!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
}