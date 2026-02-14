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

import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * These tests are on the return from the {@code assertThat} method of {@code Table}.
 * <p>
 *
 * @author Jonathan Maniquet
 */
public class Table_AssertThat_Test extends AbstractTest {

  /**
   * This method tests the result of {@code assertThat} method from {@code Table}.
   */
  @Test
  public void test_result_of_assertThat() {
    Table table = assertDbConnection.table("actor").build();

    TableAssert assertObject = table.assertThat();
    assertThat(assertObject).isNotNull()
        .extracting("actual").isSameAs(table);
  }
}
