package table;

import java.util.*;

/**
 * Created by grishberg on 18.06.16.
 * implementation of table interface
 */
public class TableImpl implements ITable {
    public static final int INITIAL_CAPACITY = 10000;
    //private final File tableFile;
    private final Map<Point, Record> records;

    public TableImpl() {
        records = new HashMap<Point, Record>(INITIAL_CAPACITY);
    }

    public void set(String cell, long value) {
        Point point = Point.fromCell(cell);
        setItem(point, value);
    }

    public long get(String cell) {
        Record item = getItem(Point.fromCell(cell));
        if (item == null) {
            return 0L;
        }
        return item.getData();
    }

    public long sum(String fromCell, String toCell) {
        Point p1 = Point.fromCell(fromCell);
        Point p2 = Point.fromCell(toCell);
        int maxCol = getMaxColumn(p1, p2);
        int minCol = getMinColumn(p1, p2);
        long maxRow = getMaxRow(p1, p2);
        long minRow = getMinRow(p1, p2);
        long sum = 0;
        for (Map.Entry<Point, Record> entry : records.entrySet()) {
            Point point = entry.getKey();
            if (isInRange(maxCol, minCol, maxRow, minRow, point)) {
                sum += entry.getValue().getData();
            }
        }

        return sum;
    }


    public long mult(String fromCell, String toCell) {
        Point p1 = Point.fromCell(fromCell);
        Point p2 = Point.fromCell(toCell);
        int maxCol = getMaxColumn(p1, p2);
        int minCol = getMinColumn(p1, p2);
        long maxRow = getMaxRow(p1, p2);
        long minRow = getMinRow(p1, p2);
        long mult = 1;

        for (Map.Entry<Point, Record> entry : records.entrySet()) {
            Point point = entry.getKey();
            if (isInRange(maxCol, minCol, maxRow, minRow, point)) {
                mult *= entry.getValue().getData();
            }
        }

        return mult;
    }

    /**
     * Check point in range
     * @param maxCol
     * @param minCol
     * @param maxRow
     * @param minRow
     * @param point
     * @return
     */
    private boolean isInRange(int maxCol, int minCol, long maxRow, long minRow, Point point) {
        return point.getColumn() >= minCol && point.getColumn() <= maxCol
                && point.getRow() >= minRow && point.getRow() <= maxRow;
    }

    private long getMinRow(Point p1, Point p2) {
        return p1.getRow() > p2.getRow() ? p2.getRow() : p1.getRow();
    }

    private long getMaxRow(Point p1, Point p2) {
        return p1.getRow() > p2.getRow() ? p1.getRow() : p2.getRow();
    }

    private int getMinColumn(Point p1, Point p2) {
        return p1.getColumn() > p2.getColumn() ? p2.getColumn() : p1.getColumn();
    }

    private int getMaxColumn(Point p1, Point p2) {
        return p1.getColumn() > p2.getColumn() ? p1.getColumn() : p2.getColumn();
    }


    private Record getItem(Point point) {
        return records.get(point);
    }

    private void setItem(Point point, long value) {
        if (!records.containsKey(point) && records.size() >= INITIAL_CAPACITY) {
            // new key, find oldest value in table
            Point key = Collections.min(records.keySet());
            // remove oldest value
            records.remove(key);
        }
        records.put(point, new Record(value));
    }
}
