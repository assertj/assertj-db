package org.assertj.db.util;

import java.util.Comparator;

import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.Row;

/**
 * Comparator for the {@code Change}.
 * 
 * @author Régis Pouiller
 * 
 */
public enum ChangeComparator implements Comparator<Change> {

  /**
   * The singleton
   */
  INSTANCE;

  /** {@inheritDoc} */
  @Override
  public int compare(Change change1, Change change2) {
    ChangeType changeType1 = change1.getChangeType();
    ChangeType changeType2 = change2.getChangeType();
    int compare = changeType1.compareTo(changeType2);
    if (compare != 0) {
      return compare;
    }
    Row row1 = change1.getRowAtStartPoint();
    Row row2 = change2.getRowAtStartPoint();
    if (row1 == null) {
      row1 = change1.getRowAtEndPoint();
    }
    if (row2 == null) {
      row2 = change2.getRowAtEndPoint();
    }
    return RowComparator.INSTANCE.compare(row1, row2);
  }
}
