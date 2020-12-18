/*
 * Name: Jordan Greenbaum
 * Instructor: Dr. Joseph Helsing
 * Course: CSI 111
 * Assignment: Project 3
 * Date: 04 December 2020
 * Description: A JTable model that defines how table data should be handled.
 */

import javax.swing.table.AbstractTableModel;

/**
 * A JTable model that defines how table data should be handled.
 * @author Jordan Greenbaum
 */
class MyTableModel extends AbstractTableModel {
    private String[] columns; // Column headers
    private Object[][] data; // Table values

    // Fill the column headers and data in the constructor:
    public MyTableModel(String[] columns, Object[][] data) {
        this.columns = columns;
        this.data = data;
    }

    /**
     * Gets the number of rows in the JTable.
     * @return the number of rows in the JTable
     */
    @Override
    public int getRowCount() {
        return data.length;
    }

    /**
     * Gets the number of columns in the JTable.
     * @return the number of columns in the JTable
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Gets the name of the specified column.
     * @param index the index of the column for which to get the name
     * @return the name of the specified column
     */
    @Override
    public String getColumnName(int index) {
        return columns[index];
    }

    /**
     * Gets the class of the specified column.
     * @param index the index of the column for which to get the class
     * @return the class of the specified column
     */
    @Override
    public Class<?> getColumnClass(int index) {
        return data[0][index].getClass();
    }

    /**
     * Gets the value of the cell at the specified position.
     * @param row the row index of the cell
     * @param column the column index of the cell
     * @return the value stored in the specified cell
     */
    @Override
    public Object getValueAt(int row, int column) {
        return data[row][column];
    }

    /**
     * Sets the value of the cell at the specified position.
     * @param value the new value of the cell
     * @param row the row index of the cell
     * @param column the column index of the cell
     */
    @Override
    public void setValueAt(Object value, int row, int column) {
        data[row][column] = value;
        fireTableCellUpdated(row, column); // Signal that data in the specified cell has changed
    }

    /**
     * Find the index of the row with the given ID.
     * @param id the ID to look for
     * @return the index of the row with the specified ID, or -1 if the ID is not in the table
     */
    public int findRowIndex(Object id) {
        // Find the row whose ID value matches the specified ID:
        for (int i = 0; i < data.length; i++) {
            if (data[i][0].equals(id))
                return i;
        }

        return -1; // Return -1 if no matching value was found
    }

    /**
     * Overwrites the table data.
     * @param data the new table data
     */
    public void setData(Object[][] data) {
        this.data = data;
        fireTableDataChanged(); // Signal that all table data has changed
    }
}