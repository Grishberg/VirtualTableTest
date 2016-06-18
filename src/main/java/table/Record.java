package table;

import java.util.Calendar;

/**
 * Created by grishberg on 18.06.16.
 */
public class Record implements Comparable<Record> {
    //private Point address;
    private long data;
    private long timestamp;

    public Record(long data) {
        this.data = data;
        timestamp = Calendar.getInstance().getTimeInMillis();
    }

    public int compareTo(Record o) {
        if (o == null) {
            return 1;
        }

        if (timestamp < o.timestamp) {
            return -1;
        }
        if (timestamp > o.timestamp) {
            return 1;
        }
        return 0;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }
}
