package table;

import static org.junit.Assert.*;

/**
 * Created by grishberg on 18.06.16.
 */
public class TableFileImplTest {
    @org.junit.Test
    public void testSumAndMult() throws Exception {
        ITable table = new TableImpl();

        table.set("A1", 2);
        table.set("B1000000000", 3);
        assertEquals(table.get("A1"), 2L);
        assertEquals(table.get("B1"), 0L);
        assertEquals(table.sum("C1", "C1"), 0L);
        assertEquals(table.mult("C1", "C1"), 1L); //see https://en.wikipedia.org/wiki/Empty_product assertEquals(table.sum("A1", "A1"), 2L);
        assertEquals(table.sum("A1", "B3"), 2L);
        assertEquals(table.sum("A1", "B1000000000"), 5L);
        assertEquals(table.mult("B1000000000", "A1"), 6L);
        assertEquals(table.mult("B100000000", "B1000000000"), 3L);
        table.set("B2", 5);
        assertEquals(table.sum("A1", "B1000000000"), 10L);
    }

    @org.junit.Test
    public void testDoubleValuesInsert() throws Exception {
        ITable table = new TableImpl();

        table.set("A1", 2);
        table.set("A2", 3);
        assertEquals(table.get("A1"), 3L);
    }

    @org.junit.Test
    public void testMultipleInsert() throws Exception {
        ITable table = new TableImpl();

        for (long i = 1; i < 1000000001L; i++) {
            table.set(String.format("A%d", i), i);
        }
        assertEquals(table.get(String.format("A%d", 1000000000L)),
                1000000000L);
    }
}