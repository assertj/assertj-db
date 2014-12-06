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
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.util;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.DataType;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on the comparator for {@code Change}
 * 
 * @author Régis Pouiller
 * 
 */
public class ChangeComparator_Test extends AbstractTest {

  /**
   * Test the {@code valueOf} method.
   */
  @Test
  public void test_valueOf() {
    assertThat(ChangeComparator.valueOf("INSTANCE")).isEqualTo(ChangeComparator.INSTANCE);
  }

  /**
   * Test the {@code compareTo} method.
   * 
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo() throws Exception {
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(1, "test1"))),
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(2, "test2"))))).isEqualTo(-1);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(1, "test1"))),
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(1, "test2"))))).isEqualTo(-1);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(1, "test1"))),
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(1, "test1"))))).isEqualTo(0);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(2, "test1"))),
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(1, "test2"))))).isEqualTo(1);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, "test1"))),
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(2, "test2"))))).isEqualTo(-1);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, "test1"))),
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(2, "test1"))))).isEqualTo(-1);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, "test1"))),
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, "test1"))))).isEqualTo(0);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, "test2"))),
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(2, "test1"))))).isEqualTo(1);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(
                DataType.TABLE, 
                "table",
                ChangeType.CREATION,
                null,
                getRow(Arrays.asList("id", "name"), Arrays.asList("id", "name", "plus"),
                    Arrays.asList(1, "test1", null))),
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(2, "test2"))))).isEqualTo(0);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, "test2"))),
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(2, null))))).isEqualTo(-1);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, null))),
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(2, "test1"))))).isEqualTo(1);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, null))),
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, null))))).isEqualTo(0);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, new byte[] { 0 }))),
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, new byte[] { 1 })))))
        .isEqualTo(0);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, new byte[] { 0 }))),
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, "test"))))).isEqualTo(0);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, "test"))),
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, new byte[] { 1 })))))
        .isEqualTo(0);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table", ChangeType.CREATION, null,
                getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(1, "test"))),
            getChange(DataType.TABLE, "table", ChangeType.DELETION,
                getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(1, "test")), null))).isLessThan(
        0);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table", ChangeType.DELETION,
                getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(1, "test")), null),
            getChange(DataType.TABLE, "table", ChangeType.MODIFICATION,
                getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(1, "test")),
                getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(1, "test"))))).isGreaterThan(0);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table", ChangeType.DELETION,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, null)), null),
            getChange(DataType.TABLE, "table", ChangeType.DELETION,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(2, "test1")), null)))
        .isEqualTo(1);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table1", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, null))),
            getChange(DataType.TABLE, "table2", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, null))))).isEqualTo(-1);
    assertThat(
        ChangeComparator.INSTANCE.compare(
            getChange(DataType.TABLE, "table2", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, null))),
            getChange(DataType.TABLE, "table1", ChangeType.CREATION, null,
                getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, null))))).isEqualTo(1);
  }
}
