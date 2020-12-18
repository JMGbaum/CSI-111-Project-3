/*
 * Name: Jordan Greenbaum
 * Instructor: Dr. Joseph Helsing
 * Course: CSI 111
 * Assignment: Project 3
 * Date: 04 December 2020
 * Description: A data storage class to hold information about a Movie.
 */

/**
 * A data storage class to hold a information about a Movie.
 * Holds the length (in minutes) of the movie.
 * @author Jordan Greenbaum
 */
public class Movie extends Item {
    private int length; // The length of this Movie (in minutes)

    /*=============*
     * CONSTRUCTOR *
     *=============*/

    /**
     * Constructs a new Music object with the specified numeric Item ID, title, genre, number of copies, and length (in minutes).
     * @param ID this Item's numeric ID
     * @param title the title of this Movie
     * @param genre the film genre of this Movie
     * @param copies the number of copies of this Movie
     * @param length the length of this Movie (in minutes)
     */
    public Movie(int ID, String title, String genre, int copies, int length) {
        super(ID, title, genre, copies, "Movie");
        this.length = length;
    }

    /*=========*
     * GETTERS *
     *=========*/

    /**
     * Gets the length (in minutes) of this Movie.
     * @return the length (in minutes) of this Movie
     */
    public int getLength() {
        return length;
    }

    /*=========*
     * SETTERS *
     *=========*/

    /**
     * Sets the length (in minutes) of this Movie.
     * @param length the length (in minutes) of this Movie
     */
    public void setLength(int length) {
        this.length = length;
    }
}