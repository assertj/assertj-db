package org.assertj.db.navigation;

import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.global.AbstractElement;
import org.assertj.db.type.DbElement;

import java.util.List;

/**
 * Position during navigation.
 *
 * @param <E> The class of the actual position (an sub-class of {@link org.assertj.db.global.AbstractElement} and of {@link org.assertj.db.navigation.Navigation}).
 * @param <N> The class of the next position where the navigation go (an sub-class of {@link org.assertj.db.global.AbstractElement} and of {@link org.assertj.db.navigation.Navigation}).
 * @param <D> The class of the database element on which is the next position (an sub-class of {@link org.assertj.db.type.DbElement}).
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
public abstract class PositionWithColumns<E extends AbstractElement & Navigation, N extends AbstractElement & Navigation, D extends DbElement> extends Position<E, N, D> {

  /**
   * Constructor.
   *
   * @param elementClass Class of the element of navigation (used to make instance).
   */
  public PositionWithColumns(E myself, Class<N> elementClass) {
    super(myself, elementClass);
  }

  /**
   * Gets an instance of element of navigation corresponding to the column name.
   * If this instance is already instanced, the method returns it from the cache.
   *
   * @param elementsList    List of elements.
   * @param columnsNameList List of the columns name.
   * @param columnName      Name of the column of the element on which is the instance of element of navigation.
   * @return The instance of element of navigation.
   */
  public N getInstance(List<D> elementsList, List<String> columnsNameList, String columnName) {
    if (columnName == null) {
      throw new NullPointerException("Column name must be not null");
    }
    int index = columnsNameList.indexOf(columnName.toUpperCase());
    if (index == -1) {
      throw new AssertJDBException("Column <%s> does not exist", columnName);
    }
    return getInstance(elementsList, index);
  }
}
