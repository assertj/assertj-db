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
import org.assertj.db.type.Row;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on the comparator for {@code Change}
 *
 * @author Régis Pouiller
 */
public class ChangeComparator_Test extends AbstractTest {

  private static Row ROW_ID_PK_1_TEST;
  private static Row ROW_ID_PK_1_TEST1;
  private static Row ROW_ID_PK_1_TEST2;
  private static Row ROW_ID_PK_2_TEST1;
  private static Row ROW_ID_PK_2_TEST2;
  private static Row ROW_NAME_PK_1_TEST1;
  private static Row ROW_NAME_PK_1_TEST2;
  private static Row ROW_NAME_PK_1_NULL;
  private static Row ROW_NAME_PK_1_BYTES_0;
  private static Row ROW_NAME_PK_1_BYTES_1;
  private static Row ROW_NAME_PK_2_TEST1;
  private static Row ROW_NAME_PK_2_TEST2;
  private static Row ROW_NAME_PK_2_NULL;

  static {
    try {
      ROW_ID_PK_1_TEST = getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(1, "test"));
      ROW_ID_PK_1_TEST1 = getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(1, "test1"));
      ROW_ID_PK_1_TEST2 = getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(1, "test2"));
      ROW_ID_PK_2_TEST1 = getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(2, "test1"));
      ROW_ID_PK_2_TEST2 = getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(2, "test2"));
      ROW_NAME_PK_1_TEST1 = getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, "test1"));
      ROW_NAME_PK_1_TEST2 = getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, "test2"));
      ROW_NAME_PK_1_NULL = getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(1, null));
      ROW_NAME_PK_1_BYTES_0 = getRow(Arrays.asList("name"), Arrays.asList("id", "name"),
                                     Arrays.asList(1, new byte[] { 0 }));
      ROW_NAME_PK_1_BYTES_1 = getRow(Arrays.asList("name"), Arrays.asList("id", "name"),
                                     Arrays.asList(1, new byte[] { 1 }));
      ROW_NAME_PK_2_TEST1 = getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(2, "test1"));
      ROW_NAME_PK_2_TEST2 = getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(2, "test2"));
      ROW_NAME_PK_2_NULL = getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(2, null));
    } catch (Exception e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  /**
   * Test the {@code valueOf} method.
   */
  @Test
  public void test_valueOf() {
    assertThat(ChangeComparator.valueOf("INSTANCE")).isEqualTo(ChangeComparator.INSTANCE);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is 0 : because the two changes are equals (with id like primary key).</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_zero_because_the_change_are_equals_with_id_as_pk() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table", ROW_ID_PK_1_TEST1),
                                                 getTableCreationChange("table", ROW_ID_PK_1_TEST1))).isEqualTo(0);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is 0 : because the two changes are equals (with name like primary key).</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_zero_because_the_change_are_equals_with_name_as_pk() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table", ROW_NAME_PK_1_TEST1),
                                                 getTableCreationChange("table", ROW_NAME_PK_1_TEST1))).isEqualTo(0);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is 0 : because the two changes are equals (with null in the primary key).</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_zero_because_the_change_are_equals_with_name_as_pk_null() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table", ROW_NAME_PK_1_NULL),
                                                 getTableCreationChange("table", ROW_NAME_PK_1_NULL))).isEqualTo(0);
  }


  /**
   * Test the {@code compareTo} method.
   * <p>Result is 0 : because .</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_zero_because_() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table", getRow(Arrays.asList("id", "name"),
                                                                                        Arrays.asList("id", "name",
                                                                                                      "plus"),
                                                                                        Arrays.asList(1, "test1", null))),
                                                 getTableCreationChange("table", ROW_NAME_PK_1_TEST2))).isEqualTo(0);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is 0 : because the two changes are equals (difference on array of bytes are not used).</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_zero_because_the_change_are_equals_dont_care_of_differences_with_bytes() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table", ROW_NAME_PK_1_BYTES_0),
                                                 getTableCreationChange("table", ROW_NAME_PK_1_BYTES_1))).isEqualTo(0);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is 0 : because the two changes are equals (difference on array of bytes are not used).</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_zero_because_the_change_are_equals_dont_care_of_differences_with_bytes_2() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table", ROW_NAME_PK_1_BYTES_0),
                                                 getTableCreationChange("table", ROW_ID_PK_1_TEST))).isEqualTo(0);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is 0 : because the two changes are equals (difference on array of bytes are not used).</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_zero_because_the_change_are_equals_dont_care_of_differences_with_bytes_3() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table", ROW_ID_PK_1_TEST),
                                                 getTableCreationChange("table", ROW_NAME_PK_1_BYTES_1))).isEqualTo(0);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is -1 : because id column of primary key is different</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_negative_because_pk_is_id() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table", ROW_ID_PK_1_TEST1),
                                                 getTableCreationChange("table", ROW_ID_PK_2_TEST1))).isEqualTo(-1);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is -1 : because id column of primary key is different</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_negative_because_pk_is_id_2() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table", ROW_ID_PK_1_TEST1),
                                                 getTableCreationChange("table", ROW_ID_PK_2_TEST2))).isEqualTo(-1);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is -1 : because name column of primary key is different</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_negative_because_pk_is_name() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table", ROW_NAME_PK_1_TEST1),
                                                 getTableCreationChange("table", ROW_NAME_PK_2_TEST2))).isEqualTo(-1);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is -1 : because name column of primary key is different</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_negative_because_pk_is_name_2() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table", ROW_NAME_PK_1_TEST1),
                                                 getTableCreationChange("table", ROW_NAME_PK_2_TEST1))).isEqualTo(-1);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is -1 : because name column of primary key is different</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_negative_because_pk_is_name_3() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table", ROW_NAME_PK_1_TEST2),
                                                 getTableCreationChange("table", ROW_NAME_PK_2_NULL))).isEqualTo(-1);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is -1 : because table name is different</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_negative_because_table_name() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table1", ROW_NAME_PK_1_NULL),
                                                 getTableCreationChange("table2", ROW_NAME_PK_1_NULL))).isEqualTo(-1);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is -1 : because id column of primary key is equals but name column is different</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_negative_because_pk_are_equals_but_name_is_different() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table", ROW_ID_PK_1_TEST1),
                                                 getTableCreationChange("table", ROW_ID_PK_1_TEST2))).isEqualTo(-1);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is 1 : because id column of primary key is different</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_positive_because_pk_is_id() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table", ROW_ID_PK_2_TEST1),
                                                 getTableCreationChange("table", ROW_ID_PK_1_TEST2))).isEqualTo(1);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is 1 : because id column of primary key is different</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_positive_because_pk_is_id_2() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table", ROW_NAME_PK_1_TEST2),
                                                 getTableCreationChange("table", ROW_NAME_PK_2_TEST1))).isEqualTo(1);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is 1 : because name column of primary key is different</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_positive_because_pk_is_name() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table", ROW_NAME_PK_1_NULL),
                                                 getTableCreationChange("table", ROW_NAME_PK_2_TEST1))).isEqualTo(1);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is 1 : because name column of primary key is different</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_positive_because_pk_is_name_2() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableDeletionChange("table", ROW_NAME_PK_1_NULL),
                                                 getTableDeletionChange("table", ROW_NAME_PK_2_TEST1))).isEqualTo(1);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result is 1 : because table name is different</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_positive_because_table_name() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table2", ROW_NAME_PK_1_NULL),
                                                 getTableCreationChange("table1", ROW_NAME_PK_1_NULL))).isEqualTo(1);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result less than 0 : because type of change is different</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_less_than_zero() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableCreationChange("table", ROW_ID_PK_1_TEST),
                                                 getTableDeletionChange("table", ROW_ID_PK_1_TEST))).isLessThan(0);
  }

  /**
   * Test the {@code compareTo} method.
   * <p>Result greater than 0 : because type of change is different</p>
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo_greater_than_zero() throws Exception {
    assertThat(ChangeComparator.INSTANCE.compare(getTableDeletionChange("table", ROW_ID_PK_1_TEST),
                                                 getTableModificationChange("table", ROW_ID_PK_1_TEST,
                                                                            ROW_ID_PK_1_TEST))).isGreaterThan(0);
  }
}
