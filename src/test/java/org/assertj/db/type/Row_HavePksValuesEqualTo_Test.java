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
package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on the primary keys value of {@code Row}.
 * 
 * @author Régis Pouiller
 * 
 */
public class Row_HavePksValuesEqualTo_Test extends AbstractTest {

  /**
   * This method test the result when getting the primary keys value with one primary key.
   * 
   * @throws Exception Exception
   */
  @Test
  public void test_when_havepksvaluesequalto_with_one_pk() throws Exception {
    assertThat(
        getRow(Arrays.asList("col1"), Arrays.asList("col1", "col2", "col3"),
            Arrays.asList((Object) "val1", "val2", "val3")).havePksValuesEqualTo(new Object[] { "val1" })).isTrue();
    assertThat(
        getRow(Arrays.asList("col1"), Arrays.asList("col1", "col2", "col3"),
            Arrays.asList((Object) "val1", "val2", "val3")).havePksValuesEqualTo(new Object[] { "val2" })).isFalse();
  }

  /**
   * This method test the result when getting the primary keys value with two primary keys.
   * 
   * @throws Exception Exception
   */
  @Test
  public void test_when_havepksvaluesequalto_with_two_pks() throws Exception {
    assertThat(
        getRow(Arrays.asList("col3", "col1"), Arrays.asList("col1", "col2", "col3"),
            Arrays.asList((Object) "val1", 1, 2)).havePksValuesEqualTo(new Object[] { 2, "val1" })).isTrue();
    assertThat(
        getRow(Arrays.asList("col3", "col1"), Arrays.asList("col1", "col2", "col3"),
            Arrays.asList((Object) "val1", 1, 2)).havePksValuesEqualTo(new Object[] { 1, "val1" })).isFalse();
    assertThat(
        getRow(Arrays.asList("col3", "col1"), Arrays.asList("col1", "col2", "col3"),
            Arrays.asList((Object) "val1", 1, 2)).havePksValuesEqualTo(new Object[] { 2, "val2" })).isFalse();
  }

}
