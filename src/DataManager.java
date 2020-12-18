/*
 * Name: Jordan Greenbaum
 * Instructor: Dr. Joseph Helsing
 * Course: CSI 111
 * Assignment: Project 3
 * Date: 04 December 2020
 * Description: Manages primary and secondary indices for a database of Book, Music, and Movie items.
 */

import java.io.*; // For file I/O
import java.util.HashMap; // To store the primary index
import java.util.LinkedList; // To store secondary indices
import javax.swing.*; // To display dialogs
import java.util.Scanner; // To read files

/**
 * Manages primary and secondary indices for a database of Book, Music, and Movie items.
 * @author Jordan Greenbaum
 */
public class DataManager {
    private HashMap<Integer, Item> items; // The primary index
    private LinkedList<Book> books; // Secondary Book index
    private LinkedList<Music> music; // Secondary Music index
    private LinkedList<Movie> movies; // Secondary Movie index

    /*==============*
     * CONSTRUCTORS *
     *==============*/

    /**
     * Constructs a new DataManager object and fills it with the specified primary index.
     * @param items the primary index
     */
    public DataManager(HashMap<Integer, Item> items)
    {
        this.items = items;

        // Iterate through primary index to place Items into their proper secondary indices.
        for (Item i : items.values()) {

            // If the current item is a Book, place it in the appropriate secondary index:
            if (i instanceof Book) {
                books.add( (Book) i ); // Convert type to Book
            }
            // Otherwise, if the current item is Music, place it in the appropriate secondary index:
            else if (i instanceof Music) {
                music.add( (Music) i ); // Convert type to Music
            }
            // Otherwise, if the current item is a Movie, place it in the appropriate secondary index:
            else if (i instanceof Movie) {
                movies.add( (Movie) i ); // Convert type to Movie
            }

        }

    }

    /**
     * Constructs a new, empty DataManager object.
     */
    public DataManager() {
        this.items = new HashMap<Integer, Item>();
        this.books = new LinkedList<Book>();
        this.music = new LinkedList<Music>();
        this.movies = new LinkedList<Movie>();
    }

    /*=========*
     * METHODS *
     *=========*/

    /**
     * Loads data from a file into the database.
     * @return whether or not a file was selected
     * @throws IOException when some exception occurs while reading from the file
     */
    public Boolean loadFile() throws IOException {
        JFileChooser chooser = new JFileChooser(); // Allows the user to select a file for opening
        int chosen; // Whether or not the user chose a file

        chosen = chooser.showOpenDialog(null); // Allow the user to choose a file for opening

        items = new HashMap<Integer, Item>(); // Reset the primary index

        // Reset the secondary indices:
        books = new LinkedList<Book>();
        music = new LinkedList<Music>();
        movies = new LinkedList<Movie>();

        // Store the selected file in a File object if the user successfully chose a file to open,
        // then read the file's data
        if (chosen == JFileChooser.APPROVE_OPTION) {
            File inputFile; // The file selected by the user to open
            Scanner input; // To read from the input file
            int id; // The numeric ID oc the current Item
            String type; // The item type of the current Item


            inputFile = chooser.getSelectedFile(); // Store the file selected by the user in a File object
            input = new Scanner(inputFile); // Connect the file object to a Scanner to read it

            // Stop the Scanner at both commas and newline characters
            input.useDelimiter("[,\n]");

            // Iterate through the file and load in each Item
            while(input.hasNext()) {
                id = Integer.parseInt(input.next());
                type = input.next().trim().toLowerCase();

                // Figure out the Item's type and load it:
                if (type.equals("book"))
                    loadBook(id, input);
                else if (type.equals("music"))
                    loadMusic(id, input);
                else if (type.equals("movie"))
                    loadMovie(id, input);
                else
                    throw new IllegalArgumentException("Could not understand the type '" + type + "'.");
            };

            input.close(); // Close the connection to the file

            // Show a success message
            JOptionPane.showMessageDialog(null, "Data successfully loaded!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Return true if everything was successful
            return true;
        }
        else
            // Return false if the user didn't select a file
            return false;
    }

    /**
     * Loads a Book object into the database.
     * @param id the Item's numeric ID
     * @param input a Scanner object connected to the input file
     */
    private void loadBook(int id, Scanner input) {
        String title = input.next().trim(); // Get the book title
        String author = input.next().trim(); // Get the author
        String genre = input.next().trim(); // Get the genre
        int pages = Integer.parseInt(input.next().trim()); // Get the number of pages
        int copies = Integer.parseInt(input.next().trim()); // Get the number of copies

        Book b = new Book(id, title, genre, copies, author, pages); // Create a new Book object

        items.put(id, b); // Add the Book to the primary index
        books.add(b); // Add the Book to the secondary index
    }

    /**
     * Loads a Music object into the database.
     * @param id the Item's numeric ID
     * @param input a Scanner object connected to the input file
     */
    private void loadMusic(int id, Scanner input) {
        String album = input.next().trim(); // Get the album title
        String artist = input.next().trim(); // Get the artist
        String genre = input.next().trim(); // Get the genre
        int tracks = Integer.parseInt(input.next().trim()); // Get the number of tracks
        int copies = Integer.parseInt(input.next().trim()); // Get the number of copies

        Music m = new Music(id, album, genre, copies, artist, tracks); // Create a new Music object

        items.put(id, m); // Add the Music to the primary index
        music.add(m); // Add the Music to the secondary index
    }

    /**
     * Loads a Music object into the database.
     * @param id the Item's numeric ID
     * @param input a Scanner object connected to the input file
     */
    private void loadMovie(int id, Scanner input) {
        String title = input.next().trim(); // Get the movie title
        String genre = input.next().trim(); // Get the genre
        int length = Integer.parseInt(input.next().trim()); // Get the length of the Movie
        int copies = Integer.parseInt(input.next().trim()); // Get the number of copies

        Movie m = new Movie(id, title, genre, copies, length); // Create a new Movie object

        items.put(id, m); // Add the Movie to the primary index
        movies.add(m); // Add the Movie to the secondary index
    }

    /**
     * Saves data to a file.
     * @throws IOException when some exception occurs while writing to the file
     */
    public void saveFile() throws IOException {
        JFileChooser chooser = new JFileChooser(); // Allows the user to select a file for saving
        int chosen; // Whether or not the user chose a file

        chosen = chooser.showSaveDialog(null); // Allow the user to choose a file for saving

        // Store the selected file in a File object if the user successfully chose a file to save,
        // then save the data
        if (chosen == JFileChooser.APPROVE_OPTION) {
            File outputFile = chooser.getSelectedFile(); // The file selected by the user in which the data will be saved
            PrintWriter output = new PrintWriter(outputFile); // To write data to the file

            // Add all Book objects to the file:
            for (Book b : books) {
                output.println(
                        b.getID() + // ID
                        ",Book," + // Type
                        b.getTitle() + "," + // Book title
                        b.getAuthor() + "," + // Author
                        b.getGenre() + "," + // Genre
                        b.getPages() + "," + // Number of pages
                        b.getCopies() // Number of copies
                );
            }

            // Add all Music objects to the file:
            for (Music m : music) {
                output.println(
                        m.getID() + // ID
                        ",Music," + // Type
                        m.getTitle() + "," + // Album name
                        m.getArtist() + "," + // Artist
                        m.getGenre() + "," + // Genre
                        m.getTracks() + "," + // Number of tracks
                        m.getCopies() // Number of copies
                );
            }

            // Add all Movie objects to the file:
            for (Movie m : movies) {
                output.println(
                        m.getID() + // ID
                        ",Movie," + // Type
                        m.getTitle() + "," + // Movie title
                        m.getGenre() + "," + // Genre
                        m.getLength() + "," + // Length (in minutes)
                        m.getCopies() // Number of copies
                );
            }

            output.close(); // Close the connection to the file

            // Show a success message:
            JOptionPane.showMessageDialog(null, "Data successfully saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Gets the secondary index for the Book objects as an array.
     * @return an array of all of the Books in the database
     */
    public Object[] getBooks() {
        return this.books.toArray();
    }

    /**
     * Gets the secondary index for the Music objects as an array.
     * @return an array of all of the Music in the database
     */
    public Object[] getMusic() {
        return this.music.toArray();
    }

    /**
     * Gets the secondary index for the Movie objects as an array.
     * @return an array of all of the Movies in the database
     */
    public Object[] getMovies() {
        return this.movies.toArray();
    }

    /**
     * Prompts a user for an Item ID using the specified prompt, then converts the response to an Integer.
     * @param prompt the prompt asking the user to input the Item ID
     * @return the numeric ID specified by the user, parsed as an Integer
     */
    public int promptID(String prompt)
    {
        String input; // The user input from the input dialog
        Boolean inputParsable = true; // Whether or not the user input was successfully converted to an integer (assume true)
        int id = -1; // The Item ID, parsed as an integer

        // Get the Item ID:
        do {
            // Prompt the user to input an Item ID, then get their response:
            if (!inputParsable)
                input = JOptionPane.showInputDialog("Your input was invalid! The Item ID must be an integer. " + prompt);
            else
                input = JOptionPane.showInputDialog(prompt);

            inputParsable = true; // Assume true

            try {
                if (input != null)
                    id = Integer.parseInt(input.trim()); // Attempt to parse the user input
            }
            catch(NumberFormatException e) {
                inputParsable = false; // If the parsing fails, update the boolean flag
            }
        }
        while(inputParsable == false);

        return id; // return the parsed ID
    }

    /**
     * Updates the number of copies displayed in the secondary index JTable for the specified Item.
     * @param tabs the Tabs object in which the secondary index JTables are stored
     * @param id the ID of the item whose row should be updated
     * @param bookColumn the column number to update if the specified Item is a Book object
     * @param musicColumn the column number to update if the specified Item is a Music object
     * @param movieColumn the column number to update if the specified Item is a Movie object
     * @see MyTableModel#setValueAt(Object, int, int)
     */
    private void updateTableCopies(Tabs tabs, int id, int bookColumn, int musicColumn, int movieColumn)
    {
        int row; // The row index of the specified Item

        switch (this.items.get(id).getType()) // Determine the Item's type
        {
            case "Book":
                // Attempt to find the Book in the Book secondary index JTable
                row = ((MyTableModel) tabs.getBooks().getModel()).findRowIndex(id);
                if (row > -1)
                    // Change the target cell's value
                    tabs.getBooks().setValueAt(this.items.get(id).copies, tabs.getBooks().convertRowIndexToView(row), bookColumn);
                break;

            case "Music":
                // Attempt to find the Music in the Music secondary index JTable
                row = ((MyTableModel) tabs.getMusic().getModel()).findRowIndex(id);
                if (row > -1)
                    // Change the target cell's value
                    tabs.getMusic().setValueAt(this.items.get(id).copies, tabs.getMusic().convertRowIndexToView(row), musicColumn);
                break;

            case "Movie":
                // Attempt to find the Movie in the Movie secondary index JTable
                row = ((MyTableModel) tabs.getMovies().getModel()).findRowIndex(id);
                if (row > -1)
                    // Change the target cell's value
                    tabs.getMovies().setValueAt(this.items.get(id).copies, tabs.getMovies().convertRowIndexToView(row), movieColumn);
                break;
        }
    }

    /**
     * Checks an item into the database if the database contains the specified Item.
     * @param tabs the Tabs object that stores the secondary index JTables to update
     */
    public void checkIn(Tabs tabs)
    {
        int id = promptID("Enter the ID of the Item you want to check in:"); // The user-specified Item ID, parsed as an integer

        // Add a copy of the Item to the HashMap if the user entered a valid item and it exists in the database, otherwise display an error message:
        if (this.items.containsKey(id))
        {
            this.items.get(id).addCopies(1); // Add a copy to the database
            JOptionPane.showMessageDialog( // Show a succcess message
                    null,
                    "Successfully checked in a copy of " + this.items.get(id).title + " (ID: " + id + ")! There are now " + this.items.get(id).copies + " copies in-stock.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
            updateTableCopies(tabs, id, 5, 5, 4); // Update JTable data
        }
        else if (id == -1) // Dialog window was canceled/exited
            return;
        else
            // Show an error message:
            JOptionPane.showMessageDialog(null, "That item doesn't belong here! This database does not contain an item with the ID " + id + ".", "Invalid", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Checks an item out from the database if the database contains the specified Item and there are enough copies.
     * @param tabs the Tabs object that stores the secondary index JTables to update
     */
    public void checkOut(Tabs tabs)
    {
        int id = promptID("Enter the ID of the Item you want to check out:"); // The user-specified Item ID, parsed as an integer

        // Remove a copy of the Item from the HashMap if the user entered a valid item and it exists in the database, otherwise display an error message:
        if (this.items.containsKey(id))
        {
            if(this.items.get(id).removeCopies(1)) // Attempt to remove a copy
                JOptionPane.showMessageDialog( // Show success message
                        null,
                        "Successfully checked out a copy of " + this.items.get(id).title + " (ID: " + id + ")!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                updateTableCopies(tabs, id,5, 5, 4); // Update JTable data
        }
        else if (id == -1) // Dialog window was canceled/exited
            return;
        else
            // Show error message:
            JOptionPane.showMessageDialog(null, "The item you requested does not exist! This database does not contain an item with the ID " + id + ".", "Invalid", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Tells the user how many copies of the specified Item there currently are.
     */
    public void checkCopies()
    {
        int id = promptID("Enter the ID of the Item you want to check the number of copies for:"); // The user-specified Item ID, parsed as an integer

        // Display the number of copies of the Item if it exists in the HashMap, otherwise display an error message:
        if (this.items.containsKey(id))
            JOptionPane.showMessageDialog(null, "There are currently " + this.items.get(id).copies + " copies of " + this.items.get(id).title + " (ID: " + id + ")!", "Success", JOptionPane.INFORMATION_MESSAGE);
        else if (id == -1) // Dialog window was canceled/exited
            return;
        else
            JOptionPane.showMessageDialog(null, "The item you requested does not exist! This database does not contain an item with the ID " + id + ".", "Invalid", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows info for a specific type of Item by changing the tab on the JTable.
     * @param tabs the Tabs object containing the secondary index JTables
     */
    public void displayInfo(Tabs tabs) {
        // Prompt the user for the type of Item they would like to see information for using preset button options:
        String[] options = {"Books", "Music", "Movies"};
        int type = JOptionPane.showOptionDialog(
                null,
                "Which type of Item would you like to display information for?",
                "Item Type",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        // Show the tab requested by the user:
        if (type > -1)
            tabs.setSelectedIndex(type);
    }
}
