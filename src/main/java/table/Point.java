package table;

/**
 * Created by grishberg on 18.06.16.
 */
public class Point {
    private String address;
    private int column;
    private long row;

    public Point(int column, long row, String address) {
        this.column = column;
        this.row = row;
        this.address = address;
    }

    public static Point fromCell(String address) {

        char c = address.charAt(0);
        int column = (int) c - 65;
        String rowString = address.substring(1);
        long row = Long.valueOf(rowString);
        return new Point(column, row, address);
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public long getRow() {
        return row;
    }

    public void setRow(long row) {
        this.row = row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public int hashCode() {
        int hash = address.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "Point address = " + address + ", col = " + column + ", row = " + row + ";";
    }
}
