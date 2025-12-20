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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on the comparator for {@code Row}
 *
 * @author Régis Pouiller
 */
public class RowComparator_Test extends AbstractTest {

  /**
   * Test the {@code valueOf} method.
   */
  @Test
  public void test_valueOf() {
    assertThat(RowComparator.valueOf("INSTANCE")).isEqualTo(RowComparator.INSTANCE);
  }

  /**
   * Test the {@code compareTo} method.
   *
   * @throws Exception Exception
   */
  @Test
  public void test_compareTo() throws Exception {
    assertThat(
      RowComparator.INSTANCE.compare(
        getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          "test1"))),
        getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 2), getValue(null,
          "test2"))))).isEqualTo(
      -1);
    assertThat(
      RowComparator.INSTANCE.compare(
        getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          "test1"))),
        getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          "test2"))))).isEqualTo(
      -1);
    assertThat(
      RowComparator.INSTANCE.compare(
        getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          "test1"))),
        getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          "test1"))))).isEqualTo(
      0);
    assertThat(
      RowComparator.INSTANCE.compare(
        getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 2), getValue(null,
          "test1"))),
        getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          "test2"))))).isEqualTo(1);
    assertThat(
      RowComparator.INSTANCE.compare(
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          "test1"))),
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 2), getValue(null,
          "test2"))))).isEqualTo(-1);
    assertThat(
      RowComparator.INSTANCE.compare(
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          "test1"))),
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 2), getValue(null,
          "test1"))))).isEqualTo(-1);
    assertThat(
      RowComparator.INSTANCE.compare(
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          "test1"))),
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          "test1"))))).isEqualTo(0);
    assertThat(
      RowComparator.INSTANCE.compare(
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          "test2"))),
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 2), getValue(null,
          "test1"))))).isEqualTo(1);
    assertThat(
      RowComparator.INSTANCE.compare(
        getRow(Arrays.asList("id", "name"), Arrays.asList("id", "name", "plus"), Arrays.asList(getValue(null, 1), getValue(
          null, "test1"), getValue(null, null))),
        getRow(Arrays.asList("id"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 2), getValue(null,
          "test2"))))).isEqualTo(0);
    assertThat(
      RowComparator.INSTANCE.compare(
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          "test2"))),
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 2), getValue(null,
          null))))).isEqualTo(-1);
    assertThat(
      RowComparator.INSTANCE.compare(
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          null))),
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 2), getValue(null,
          "test1"))))).isEqualTo(1);
    assertThat(
      RowComparator.INSTANCE.compare(
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          null))),
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          null))))).isEqualTo(0);
    assertThat(
      RowComparator.INSTANCE.compare(
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          new byte[]{0}))),
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          new byte[]{1}))))).isEqualTo(
      0);
    assertThat(
      RowComparator.INSTANCE.compare(
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          new byte[]{0}))),
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          "test"))))).isEqualTo(
      0);
    assertThat(
      RowComparator.INSTANCE.compare(
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          "test"))),
        getRow(Arrays.asList("name"), Arrays.asList("id", "name"), Arrays.asList(getValue(null, 1), getValue(null,
          new byte[]{1}))))).isEqualTo(0);
  }
}
