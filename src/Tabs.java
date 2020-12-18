/*
 * Name: Jordan Greenbaum
 * Instructor: Dr. Joseph Helsing
 * Course: CSI 111
 * Assignment: Project 3
 * Date: 04 December 2020
 * Description: A JTabbedPane that displays the data from the secondary indices as JTables.
 */

import javax.swing.*; // To use swing GUI components
import java.awt.*; // To use Color objects

/**
 * A JTabbedPane that displays the data from the secondary indices as JTables.
 * @author Jordan Greenbaum
 */
public class Tabs extends JTabbedPane
{
    private JTable books; // Table to display Book info
    private JTable music; // Table to display Music info
    private JTable movies; // Table to display Movie info

    /**
     * Constructs a new JTabbedPane class with the specified data.
     * @param manager the DataManager object that stores and manages the data that will be displayed in the JTables
     */
    public Tabs(DataManager manager) {
        books = new JTable( // Make the Books JTable
                new MyTableModel(
                        TableData.BOOK_COLUMNS, // Column headers
                        TableData.fromBooks(manager.getBooks()) // Cell values
                )
        );
        books.setGridColor(Color.LIGHT_GRAY); // Make sure the grid lines show up
        books.setDragEnabled(false); // Disable the dragging of cells
        books.getTableHeader().setReorderingAllowed(false); // Disable the dragging of columns
        books.setAutoCreateRowSorter(true); // Allow users to sort tables by a specific column

        music = new JTable( // Make the Music JTable
                new MyTableModel(
                        TableData.MUSIC_COLUMNS, // Column headers
                        TableData.fromMusic(manager.getMusic()) // Cell values
                )
        );
        music.setGridColor(Color.LIGHT_GRAY); // Make sure the grid lines show up
        music.setDragEnabled(false); // Disable the dragging of cells
        music.getTableHeader().setReorderingAllowed(false); // Disable the dragging of columns
        music.setAutoCreateRowSorter(true); // Allow users to sort tables by a specific column

        movies = new JTable(
                new MyTableModel(
                        TableData.MOVIE_COLUMNS, // Column headers
                        TableData.fromMovies(manager.getMovies()) // Cell values
                )
        );
        movies.setGridColor(Color.LIGHT_GRAY); // Make sure the grid lines show up
        movies.setDragEnabled(false); // Disable the dragging of cells
        movies.getTableHeader().setReorderingAllowed(false); // Disable the dragging of columns
        movies.setAutoCreateRowSorter(true); // Allow users to sort tables by a specific column

        // Add the JTables as different tabs:
        add("Books", new JScrollPane(books));
        add("Music", new JScrollPane(music));
        add("Movies", new JScrollPane(movies));
    }

    /**
     * Gets the Books JTable object.
     * @return the Books JTable object
     */
    public JTable getBooks() {
        return books;
    }

    /**
     * Gets the Music JTable object.
     * @return the Music JTable object
     */
    public JTable getMusic() {
        return music;
    }

    /**
     * Gets the Movies JTable object.
     * @return the Movies JTable object
     */
    public JTable getMovies() {
        return movies;
    }
}