/*
 * Name: Jordan Greenbaum
 * Instructor: Dr. Joseph Helsing
 * Course: CSI 111
 * Assignment: Project 3
 * Date: 04 December 2020
 * Description: An abstract data storage class
 */

import javax.swing.JOptionPane; // To create dialogs

/**
 * An abstract data storage =class.
 * @author Jordan Greenbaum
 */
public abstract class Item {
    protected final int ID; // This Item's ID
    protected String title; // This Item's title
    protected String genre; // This Item's genre
    protected int copies; // The number of in-stock copies of this Item
    private String type; // This Item's type

    /*=============*
     * CONSTRUCTOR *
     *=============*/

    /**
     * Constructs a new Item object with the specified ID, title, genre, and number of copies.
     * @param ID the new Item's numeric ID
     * @param title the title of the new Item
     * @param genre the genre of the new Item
     * @param copies the number of copies of the new Item
     * @param type the type of the new Item
     */
    public Item(int ID, String title, String genre, int copies, String type) {
        this.ID = ID;
        this.title = title;
        this.genre = genre;
        this.copies = copies;
        this.type = type;
    }

    /*=========*
     * GETTERS *
     *=========*/

    /**
     * Gets this Item's numeric ID.
     * @return this Item's numeric ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets the title of this Item.
     * @return the title of this Item
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the genre of this Item.
     * @return the genre of this Item
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Gets the number of copies of this Item.
     * @return the number of copies of this Item
     */
    public int getCopies() {
        return copies;
    }

    /**
     * Gets the type of this Item.
     * @return the type of this Item
     */
    public String getType() {
        return type;
    }

    /*=========*
     * SETTERS *
     *=========*/

    /**
     * Sets the title of this Item.
     * @param title the new title of this Item
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the genre of this Item.
     * @param genre the new genre of this Item.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /*===============*
     * OTHER METHODS *
     *===============*/

    /**
     * Adds copies of this Item to the stock.
     * @param copies the number of copies of this Item to add
     */
    public void addCopies(int copies) {
        this.copies += copies;
    }

    /**
     * Removes copies of this Item from the stock.
     * @param copies the number of copies of this Item to remove
     * @return whether or not a copy was successfully removed
     */
    public Boolean removeCopies(int copies) {
        // Send an error message if there aren't enough copies
        if (this.copies - copies < 0) {
            JOptionPane.showMessageDialog(null, "You can't do that! There are not enough copies of this item left!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Otherwise, remove the specified number of copies
        else {
            this.copies -= copies;
            return true;
        }
    }
}
