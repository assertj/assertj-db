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
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.type;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on the primary keys name of {@code Row}.
 * 
 * @author Régis Pouiller
 * 
 */
public class Row_GetPksNameList_Test extends AbstractTest {

  /**
   * This method test the result when getting the primary keys name with one primary key.
   * 
   * @throws Exception Exception
   */
  @Test
  public void test_when_getpksnamelist_with_one_pk() throws Exception {
    assertThat(
        getRow(Arrays.asList("col1"), Arrays.asList("col1", "col2", "col3"),
            Arrays.asList(getValue(null, "val1"), getValue(null, "val2"), getValue(null, "val3"))).getPksNameList()).containsExactly(
            "col1");
  }

  /**
   * This method test the result when getting the primary keys name with two primary keys.
   * 
   * @throws Exception Exception
   */
  @Test
  public void test_when_getpksnamelist_with_two_pks() throws Exception {
    assertThat(
        getRow(Arrays.asList("col3", "col1"), Arrays.asList("col1", "col2", "col3"),
            Arrays.asList(getValue(null, "val1"), getValue(null, 1), getValue(null, 2))).getPksNameList()).containsExactly("col3", "col1");
  }

}
