package org.assertj.db.type;

import java.util.List;

/**
 * Column in a <code>{@link AbstractDbDatas}</code>.
 * <p>
 * A column can have many rows with a value for each row.
 * </p>
 * <p>
 * Note : you never instantiate directly this class. You will get an object of this class from a {@link Table} or a
 * {@link Request} by using {@link AbstractDbDatas#getColumn(int)} and {@link AbstractDbDatas#getColumn(String)}.
 * </p>
 * 
 * @author Régis Pouiller.
 * 
 */
public class Column {

  /**
   * The name of the column.
   */
  private String name;
  /**
   * The values of the column.
   */
  private List<Object> valuesList;

  /**
   * Constructor of the column with visibility in the package.
   * 
   * @param name The name of the column.
   * @param valuesList The values in the column.
   */
  Column(String name, List<Object> valuesList) {
    this.name = name;
    this.valuesList = valuesList;
  }

  /**
   * Returns the name of the column.
   * 
   * @return The name of the column.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the values of the column.
   * 
   * @return The values of the column.
   */
  public List<Object> getValuesList() {
    return valuesList;
  }

  /**
   * Returns the value corresponding to the row index.
   * 
   * @param index The index
   * @return The value
   */
  public Object getRowValue(int index) {
    return valuesList.get(index);
  }
}
