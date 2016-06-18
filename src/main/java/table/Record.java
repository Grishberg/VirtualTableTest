package table;

import java.util.Calendar;

/**
 * Created by grishberg on 18.06.16.
 */
public class Record {
    //private Point address;
    private long data;

    public Record(long data) {
        this.data = data;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }
}
