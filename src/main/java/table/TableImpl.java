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
        int maxCol = p1.getColumn() > p2.getColumn() ? p1.getColumn() : p2.getColumn();
        int minCol = p1.getColumn() > p2.getColumn() ? p2.getColumn() : p1.getColumn();
        long maxRow = p1.getRow() > p2.getRow() ? p1.getRow() : p2.getRow();
        long minRow = p1.getRow() > p2.getRow() ? p2.getRow() : p1.getRow();
        long sum = 0;
        for (Map.Entry<Point, Record> entry : records.entrySet()) {
            Point point = entry.getKey();
            if (point.getColumn() >= minCol && point.getColumn() <= maxCol
                    && point.getRow() >= minRow && point.getRow() <= maxRow) {
                sum += entry.getValue().getData();
            }
        }

        return sum;
    }

    public long mult(String fromCell, String toCell) {
        Point p1 = Point.fromCell(fromCell);
        Point p2 = Point.fromCell(toCell);
        int maxCol = p1.getColumn() > p2.getColumn() ? p1.getColumn() : p2.getColumn();
        int minCol = p1.getColumn() > p2.getColumn() ? p2.getColumn() : p1.getColumn();
        long maxRow = p1.getRow() > p2.getRow() ? p1.getRow() : p2.getRow();
        long minRow = p1.getRow() > p2.getRow() ? p2.getRow() : p1.getRow();
        long mult = 1;

        for (Map.Entry<Point, Record> entry : records.entrySet()) {
            Point point = entry.getKey();
            if (point.getColumn() >= minCol && point.getColumn() <= maxCol
                    && point.getRow() >= minRow && point.getRow() <= maxRow) {
                mult *= entry.getValue().getData();
            }
        }

        return mult;
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
