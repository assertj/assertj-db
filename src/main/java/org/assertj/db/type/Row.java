package org.assertj.db.type;

import java.util.List;

/**
 * Row in a <code>{@link AbstractDbData}</code>.
 * <p>
 * A row can have many columns with a value in front of this column.
 * </p>
 * <p>
 * Note : you never instantiate directly this class. You will get an object of this class from a {@link Table} or a
 * {@link Request} by using {@link AbstractDbData#getRow(int)} or with the list by using
 * {@link AbstractDbData#getRowsList()}.
 * </p>
 * 
 * @author Régis Pouiller
 * 
 */
public class Row {

  /**
   * The list of columns name.
   */
  private List<String> columnsNameList;
  /**
   * The list of value.
   */
  private List<Object> valuesList;

  /**
   * Constructor of the row with visibility in the package.
   * 
   * @param pColumnsNameList The list of the columns name.
   * @param pValuesList The values in the row.
   */
  Row(List<String> pColumnsNameList, List<Object> pValuesList) {
    this.columnsNameList = pColumnsNameList;
    this.valuesList = pValuesList;
  }

  /**
   * Returns the list of the columns name.
   * 
   * @return The list of the columns name.
   */
  public List<String> getColumnsNameList() {
    return columnsNameList;
  }

  /**
   * Returns the list of the values for the datas from database.
   * 
   * @return The list of the values.
   */
  public List<Object> getValuesList() {
    return valuesList;
  }

  /**
   * Returns the value corresponding to the column index.
   * 
   * @param index The index
   * @return The value
   */
  public Object getColumnValue(int index) {
    return valuesList.get(index);
  }

  /**
   * Returns the value corresponding to the column name in the {@code Row}.
   * 
   * @param columnName The column name (must be not {@code null}).
   * @return The value
   * @throws NullPointerException If the {@code columnName} parameter is {@code null}.
   */
  public Object getColumnValue(String columnName) {
    if (columnName == null) {
      throw new NullPointerException("Column name must be not null");
    }
    String name = columnName.toUpperCase();
    int index = getColumnsNameList().indexOf(name);
    if (index == -1) {
      return null;
    }
    return getColumnValue(index);
  }
}
