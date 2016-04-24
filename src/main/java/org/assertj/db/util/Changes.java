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
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.db.util;

import org.assertj.db.type.Change;
import org.assertj.db.type.Row;
import org.assertj.db.type.Value;

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
      List<Value> valuesListAtStartPoint = rowAtStartPoint.getValuesList();
      List<Value> valuesListAtEndPoint = rowAtEndPoint.getValuesList();
      Iterator<Value> iteratorAtEndPoint = valuesListAtEndPoint.iterator();
      int index = 0;
      for (Value valueAtStartPoint : valuesListAtStartPoint) {
        Value valueAtEndPoint  = iteratorAtEndPoint.next();
        Object objectAtStartPoint = valueAtStartPoint.getValue();
        Object objectAtEndPoint = valueAtEndPoint.getValue();

        if ((objectAtStartPoint == null && objectAtEndPoint != null) ||
            (objectAtStartPoint != null && !objectAtStartPoint.equals(objectAtEndPoint))) {

          indexesList.add(index);
        }
        index++;
      }
    }
    else if (rowAtStartPoint != null) {
      List<Value> valuesListAtStartPoint = rowAtStartPoint.getValuesList();
      int index = 0;
      for (Value valueAtStartPoint : valuesListAtStartPoint) {
        if (valueAtStartPoint.getValue() != null) {
          indexesList.add(index);
        }
        index++;
      }
    }
    else {
      List<Value> valuesListAtEndPoint = rowAtEndPoint.getValuesList();
      int index = 0;
      for (Value valueAtEndPoint : valuesListAtEndPoint) {
        if (valueAtEndPoint.getValue() != null) {
          indexesList.add(index);
        }
        index++;
      }
    }

    return indexesList.toArray(new Integer[indexesList.size()]);
  }

}
