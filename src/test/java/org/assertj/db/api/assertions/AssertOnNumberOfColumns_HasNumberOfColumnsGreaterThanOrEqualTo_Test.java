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
import org.assertj.db.api.ChangeRowAssert;
import org.assertj.db.api.TableAssert;
import org.assertj.db.api.TableRowAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnNumberOfColumns} class :
 * {@link org.assertj.db.api.assertions.AssertOnNumberOfColumns#hasNumberOfColumnsGreaterThanOrEqualTo(int)} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class AssertOnNumberOfColumns_HasNumberOfColumnsGreaterThanOrEqualTo_Test extends AbstractTest {

  /**
   * This method tests the {@code hasNumberOfColumnsGreaterThanOrEqualTo} assertion method.
   */
  @Test
  @NeedReload
  public void test_has_number_of_columns() {
    Table table = assertDbConnection.table("actor").build();
    Changes changes = assertDbConnection.changes().tables(table).build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeAssert changeAssert = assertThat(changes).change();
    ChangeAssert changeAssert2 = changeAssert.hasNumberOfColumnsGreaterThanOrEqualTo(5);
    Assertions.assertThat(changeAssert).isSameAs(changeAssert2);

    ChangeRowAssert changeRowAssert = assertThat(changes).change().rowAtEndPoint();
    ChangeRowAssert changeRowAssert2 = changeRowAssert.hasNumberOfColumnsGreaterThanOrEqualTo(5);
    Assertions.assertThat(changeRowAssert).isSameAs(changeRowAssert2);

    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = tableAssert.hasNumberOfColumnsGreaterThanOrEqualTo(5);
    Assertions.assertThat(tableAssert).isSameAs(tableAssert2);

    TableRowAssert tableRowAssert = assertThat(table).row();
    TableRowAssert tableRowAssert2 = tableRowAssert.hasNumberOfColumnsGreaterThanOrEqualTo(5);
    Assertions.assertThat(tableRowAssert).isSameAs(tableRowAssert2);
  }

  /**
   * This method should fail because the number of columns is greater.
   */
  @Test
  @NeedReload
  public void should_fail_because_number_of_columns_is_greater() {
    Request request = assertDbConnection.request("select * from actor").build();
    Changes changes = assertDbConnection.changes().request(request).build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    try {
      assertThat(changes).change().hasNumberOfColumnsGreaterThanOrEqualTo(6);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Change at index 0 of Changes on 'select * from actor' request of 'sa/jdbc:h2:mem:test'] %n"
        + "Expecting size (number of columns) to be greater than or equal to :%n"
        + "   <6>%n"
        + "but was:%n"
        + "   <5>"));
    }
    try {
      assertThat(changes).change().rowAtEndPoint().hasNumberOfColumnsGreaterThanOrEqualTo(6);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Row at end point of Change at index 0 of Changes on 'select * from actor' request of 'sa/jdbc:h2:mem:test'] %n"
        + "Expecting size (number of columns) to be greater than or equal to :%n"
        + "   <6>%n"
        + "but was:%n"
        + "   <5>"));
    }
    try {
      assertThat(request).hasNumberOfColumnsGreaterThanOrEqualTo(6);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("['select * from actor' request] %n"
        + "Expecting size (number of columns) to be greater than or equal to :%n"
        + "   <6>%n"
        + "but was:%n"
        + "   <5>"));
    }
    try {
      assertThat(request).row().hasNumberOfColumnsGreaterThanOrEqualTo(6);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Row at index 0 of 'select * from actor' request] %n"
        + "Expecting size (number of columns) to be greater than or equal to :%n"
        + "   <6>%n"
        + "but was:%n"
        + "   <5>"));
    }
  }
}
