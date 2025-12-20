/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2025 the original author or authors.
 */
package org.assertj.db.util;

import java.util.Comparator;

import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.Row;

/**
 * Comparator for the {@code Change}.
 *
 * @author Régis Pouiller
 */
public enum ChangeComparator implements Comparator<Change> {

  /**
   * The singleton
   */
  INSTANCE;

  /**
   * {@inheritDoc}
   */
  @Override
  public int compare(Change change1, Change change2) {
    ChangeType changeType1 = change1.getChangeType();
    ChangeType changeType2 = change2.getChangeType();
    int compare = changeType1.compareTo(changeType2);
    if (compare != 0) {
      return compare;
    }
    String dataName1 = change1.getDataName();
    String dataName2 = change2.getDataName();
    compare = dataName1.compareTo(dataName2);
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
