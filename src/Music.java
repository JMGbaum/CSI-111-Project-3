/*
 * Name: Jordan Greenbaum
 * Instructor: Dr. Joseph Helsing
 * Course: CSI 111
 * Assignment: Project 3
 * Date: 04 December 2020
 * Description: A data storage class to hold a music album's information.
 */

/**
 * A data storage class to hold a information about a music album.
 * Holds the album's artist and number of tracks.
 * @author Jordan Greenbaum
 */
public class Music extends Item {
    private String artist; // This Music's artist
    private int tracks; // The number of songs in this album

    /*=============*
     * CONSTRUCTOR *
     *=============*/

    /**
     * Constructs a new Music object with the specified numeric Item ID, title, genre, number of copies, artist, and number of tracks.
     * @param ID this Item's ID
     * @param title the title of this album
     * @param genre this album's music genre
     * @param copies the number of copies of this album
     * @param artist this Music's artist
     * @param tracks the number of tracks on this album
     */
    public Music(int ID, String title, String genre, int copies, String artist, int tracks) {
        super(ID, title, genre, copies, "Music");
        this.artist = artist;
        this.tracks = tracks;
    }

    /*=========*
     * GETTERS *
     *=========*/

    /**
     * Gets this album's artist.
     * @return this album's artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Gets the number of tracks on this album.
     * @return the number of tracks on this album
     */
    public int getTracks() {
        return tracks;
    }

    /*=========*
     * SETTERS *
     *=========*/

    /**
     * Sets this album's artist.
     * @param artist this album's artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Sets the number of tracks on this album.
     * @param tracks the number of tracks on this album
     */
    public void setTracks(int tracks) {
        this.tracks = tracks;
    }
}
