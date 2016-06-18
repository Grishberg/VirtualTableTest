package table;

/**
 * Created by grishberg on 18.06.16.
 */
public interface ITable { /**
 * Sets the value of the column to specified value.
 *
 * @param cell string in cell string format
 * @param value positive integer [1, 1_000_000_000] */
void set(String cell, long value);
    /**
     * Gets the value from the specified cell. if the cell is empty – return 0. *
     * @param cell cell reference
     * @return cell value, or 0 if cell is empty
     */
    long get(String cell);
    /**
     * Gets the sum of values in the range [fromCell:toCell] *
     * @param fromCell “from” cell reference
     * @param fromCell “to” cell reference
     * @return sum of values in the range */
    long sum(String fromCell, String toCell);
    /**
     * Gets the product (multiplying) of values in the range [fromCell:toCell] modulo 1_000_000; *
     * @param fromCell “from” cell reference
     * @param fromCell “to” cell reference
     * @return product of values in the range */
    long mult(String fromCell, String toCell);
}