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

import org.assertj.core.api.Assertions;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.DataType;
import org.junit.Test;

import java.util.Arrays;

/**
 * Tests on {@code getIndexesOfModifiedColumns} method.
 *
 * @author Régis Pouiller
 */
public class Changes_GetIndexesOfModifiedColumns_Test extends AbstractTest {

  /**
   * This method tests the {@code getIndexesOfModifiedColumns} method.
   */
  @Test
  public void test_get_indexes_of_modified_columns() throws Exception {
    Change creationChange = getChange(DataType.TABLE, "test", ChangeType.CREATION,
      null,
      getRow(Arrays.asList("var1"),
        Arrays.asList("var1", "var2", "var3"),
        Arrays.asList(getValue("var1", 1),
          getValue("var2", "test"),
          getValue("var3", null))));

    Change modificationChange = getChange(DataType.TABLE, "test", ChangeType.MODIFICATION,
      getRow(Arrays.asList("var1"),
        Arrays.asList("var1", "var2", "var3", "var4"),
        Arrays.asList(getValue("var1", 1),
          getValue("var2", null),
          getValue("var3", "text1"),
          getValue("var4", null))),
      getRow(Arrays.asList("var1"),
        Arrays.asList("var1", "var2", "var3", "var4"),
        Arrays.asList(getValue("var1", 1),
          getValue("var2", "test"),
          getValue("var3", null),
          getValue("var4", null))));

    Change deletionChange = getChange(DataType.TABLE, "test", ChangeType.DELETION,
      getRow(Arrays.asList("var1"),
        Arrays.asList("var1", "var2", "var3"),
        Arrays.asList(getValue("var1", 1),
          getValue("var2", "test1"),
          getValue("var3", null))),
      null);

    Integer[] creationIndexes = Changes.getIndexesOfModifiedColumns(creationChange);
    Integer[] modificationIndexes = Changes.getIndexesOfModifiedColumns(modificationChange);
    Integer[] deletionIndexes = Changes.getIndexesOfModifiedColumns(deletionChange);

    Assertions.assertThat(creationIndexes).contains(0, 1);
    Assertions.assertThat(modificationIndexes).contains(1, 2);
    Assertions.assertThat(deletionIndexes).contains(0, 1);
  }
}
