/*
 * Name: Jordan Greenbaum
 * Instructor: Dr. Joseph Helsing
 * Course: CSI 111
 * Assignment: Project 3
 * Date: 04 December 2020
 * Description: A data storage class to hold a Book's information.
 */

/**
 * A data storage class to hold a information about a Book.
 * Holds the Book's author number of pages.
 * @author Jordan Greenbaum
 */
public class Book extends Item {
    private String author; // The author of this Book
    private int pages; // The number of pages in this Book

    /*=============*
     * CONSTRUCTOR *
     *=============*/

    /**
     * Constructs a new Book object with the specified numeric Item ID, title, genre, number of copies, author, and number of pages.
     * @param ID this Item's ID
     * @param title this Book's title
     * @param genre this Book's genre
     * @param copies the number of copies of this Book
     * @param author the author of this Book
     * @param pages the number of pages in this Book
     */
    public Book(int ID, String title, String genre, int copies, String author, int pages) {
        super(ID, title, genre, copies, "Book");
        this.author = author;
        this.pages = pages;
    }

    /*=========*
     * GETTERS *
     *=========*/

    /**
     * Gets this Book's author.
     * @return the author of this Book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the number of pages in this Book.
     * @return the number of pages in this Book
     */
    public int getPages() {
        return pages;
    }

    /*=========*
     * SETTERS *
     *=========*/

    /**
     * Sets the author of this Book.
     * @param author the author of this Book
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Sets the number of pages in this Book.
     * @param pages the number of pages in this Book
     */
    public void setPages(int pages) {
        this.pages = pages;
    }
}
