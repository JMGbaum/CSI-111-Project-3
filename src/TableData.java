/*
 * Name: Jordan Greenbaum
 * Instructor: Dr. Joseph Helsing
 * Course: CSI 111
 * Assignment: Project 3
 * Date: 04 December 2020
 * Description: Tools to format primary and secondary index data for use in a JTable.
 */

/**
 * Tools to format primary and secondary index data for use in a JTable.
 * @author Jordan Greenbaum
 */
public class TableData {
    final static String[] BOOK_COLUMNS = { // The column headers for a JTable of Book objects
            "ID",
            "Title",
            "Author",
            "# Pages",
            "Genre",
            "Copies"
    };

    final static String[] MUSIC_COLUMNS = { // The column headers for a JTable of Music objects
            "ID",
            "Album",
            "Artist",
            "# Tracks",
            "Genre",
            "Copies"
    };

    final static String[] MOVIE_COLUMNS = { // The column headers for a JTable of Movie objects
            "ID",
            "Title",
            "Length (minutes)",
            "Genre",
            "Copies"
    };

    /**
     * Makes a three-dimensional array of Book objects using the secondary index of Books.
     * @param books the secondary index of Books, stored as an Array
     * @return a three-dimensional array containing data from all items in the secondary index of Book objects
     */
    public static Object[][] fromBooks(Object[] books)
    {
        Object[][] data = new Object[books.length][6]; // A three-dimensional array of Objects to hold all data
        Book b; // The current Book object

        // Iterate through each Book object and store its data in the three-dimensional array:
        for (int i = 0; i < books.length; i++) {
            b = (Book) books[i];
            data[i][0] = b.getID();
            data[i][1] = b.getTitle();
            data[i][2] = b.getAuthor();
            data[i][3] = b.getPages();
            data[i][4] = b.getGenre();
            data[i][5] = b.getCopies();
        }

        return data; // return the three-dimensional array full of data
    }

    /**
     * Makes a three-dimensional array of Music objects using the secondary index of Music.
     * @param music the secondary index of Music, stored as an Array
     * @return a three-dimensional array containing data from all items in the secondary index of Music objects
     */
    public static Object[][] fromMusic(Object[] music)
    {
        Object[][] data = new Object[music.length][6]; // A three-dimensional array of Objects to hold all data
        Music m; // The current Music object

        // Iterate through each Music object and store its data in the three-dimensional array:
        for (int i = 0; i < music.length; i++) {
            m = (Music) music[i];
            data[i][0] = m.getID();
            data[i][1] = m.getTitle();
            data[i][2] = m.getArtist();
            data[i][3] = m.getTracks();
            data[i][4] = m.getGenre();
            data[i][5] = m.getCopies();
        }

        return data; // return the three-dimensional array full of data
    }

    /**
     * Makes a three-dimensional array of Movie objects using the secondary index of Movies.
     * @param movies the secondary index of Movies, stored as an Array
     * @return a three-dimensional array containing data from all items in the secondary index of Movie objects
     */
    public static Object[][] fromMovies(Object[] movies)
    {
        Object[][] data = new Object[movies.length][5]; // A three-dimensional array of Objects to hold all data
        Movie m; // The current Movie object

        // Iterate through each Movie object and store its data in the three-dimensional array:
        for (int i = 0; i < movies.length; i++) {
            m = (Movie) movies[i];
            data[i][0] = m.getID();
            data[i][1] = m.getTitle();
            data[i][2] = m.getLength();
            data[i][3] = m.getGenre();
            data[i][4] = m.getCopies();
        }

        return data; // return the three-dimensional array full of data
    }
}