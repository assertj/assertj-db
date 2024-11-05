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
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.api.assertions;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnDataType} class :
 * {@link org.assertj.db.api.assertions.AssertOnDataType#isOnTable()} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class AssertOnDataType_IsOnTable_Test extends AbstractTest {

  /**
   * This method tests the {@code isOnTable} assertion method.
   */
  @Test
  @NeedReload
  public void test_is_on_table() throws Exception {
    Table table = new Table(jdbcConnectionProvider, "actor");
    Changes changes = new Changes(table).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeAssert changeAssert = assertThat(changes).change();
    ChangeAssert changeAssert2 = changeAssert.isOnTable();
    Assertions.assertThat(changeAssert).isSameAs(changeAssert2);
  }

  /**
   * This method should fail because the data type is different.
   */
  @Test
  @NeedReload
  public void should_fail_because_data_type_is_different() throws Exception {
    Request request = new Request(jdbcConnectionProvider, "select * from actor");
    Changes changes = new Changes(request).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    try {
      assertThat(changes).change().isOnTable();
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Change at index 0 of Changes on 'select * from actor' request of 'sa/jdbc:h2:mem:test'] %n"
        + "Expecting:%n"
        + "to be on data type%n"
        + "  <TABLE>%n"
        + "but was on data type%n"
        + "  <REQUEST>"));
    }
  }
}
