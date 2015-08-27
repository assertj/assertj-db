/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.util;

import org.assertj.db.type.Change;
import org.assertj.db.type.Row;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Utility methods related to changes.
 *
 * @author Régis Pouiller
 */
public class Changes {

  /**
   * Private constructor.
   */
  private Changes() {
    // Empty
  }

  /**
   * Returns the indexes of the modified columns.
   * @param change    The change.
   * @return The indexes.
   */
  public static Integer[] getIndexesOfModifiedColumns(Change change) {
    List<Integer> indexesList = new ArrayList<>();
    Row rowAtStartPoint = change.getRowAtStartPoint();
    Row rowAtEndPoint = change.getRowAtEndPoint();
    if (rowAtStartPoint != null && rowAtEndPoint != null) {
      List<Object> valuesListAtStartPoint = rowAtStartPoint.getValuesList();
      List<Object> valuesListAtEndPoint = rowAtEndPoint.getValuesList();
      Iterator<Object> iteratorAtEndPoint = valuesListAtEndPoint.iterator();
      int index = 0;
      for (Object valueAtStartPoint : valuesListAtStartPoint) {
        Object valueAtEndPoint  = iteratorAtEndPoint.next();

        if ((valueAtStartPoint == null && valueAtEndPoint != null) ||
            (valueAtStartPoint != null && !valueAtStartPoint.equals(valueAtEndPoint))) {

          indexesList.add(index);
        }
        index++;
      }
    }
    else if (rowAtStartPoint != null) {
      List<Object> valuesListAtStartPoint = rowAtStartPoint.getValuesList();
      int index = 0;
      for (Object valueAtStartPoint : valuesListAtStartPoint) {
        if (valueAtStartPoint != null) {
          indexesList.add(index);
        }
        index++;
      }
    }
    else {
      List<Object> valuesListAtEndPoint = rowAtEndPoint.getValuesList();
      int index = 0;
      for (Object valueAtEndPoint : valuesListAtEndPoint) {
        if (valueAtEndPoint != null) {
          indexesList.add(index);
        }
        index++;
      }
    }

    return indexesList.toArray(new Integer[indexesList.size()]);
  }

}
