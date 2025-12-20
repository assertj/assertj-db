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
package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Test on the type got from {@code valueOf} method from {@code DataType} enum.
 *
 * @author Régis Pouiller
 */
public class DataType_ValueOf_Test {

  /**
   * This method tests the result of {@code valueOf} method from {@code DataType} enum.
   */
  @Test
  public void test_result_of_valueOf() {
    assertThat(DataType.valueOf("REQUEST")).isEqualTo(DataType.REQUEST);
    assertThat(DataType.valueOf("TABLE")).isEqualTo(DataType.TABLE);
  }
}
