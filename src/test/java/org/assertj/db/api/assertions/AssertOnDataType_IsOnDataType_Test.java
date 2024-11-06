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

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.DataType;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnDataType} class :
 * {@link org.assertj.db.api.assertions.AssertOnDataType#isOnDataType(org.assertj.db.type.DataType)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnDataType_IsOnDataType_Test extends AbstractTest {

  /**
   * This method tests the {@code isOnDataType} assertion method.
   */
  @Test
  @NeedReload
  public void test_is_on_data_type() throws Exception {
    Table table = new Table(source, "actor");
    Changes changes = new Changes(table).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeAssert changeAssert = assertThat(changes).change();
    ChangeAssert changeAssert2 = changeAssert.isOnDataType(DataType.TABLE);
    Assertions.assertThat(changeAssert).isSameAs(changeAssert2);
  }

  /**
   * This method should fail because the data type is different.
   */
  @Test
  @NeedReload
  public void should_fail_because_data_type_is_different() throws Exception {
    Request request = new Request(source, "select * from actor");
    Changes changes = new Changes(request).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    try {
      assertThat(changes).change().isOnDataType(DataType.TABLE);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Change at index 0 of Changes on 'select * from actor' request of 'sa/jdbc:h2:mem:test' source] %n"
                                                      + "Expecting:%n"
                                                      + "to be on data type%n"
                                                      + "  <TABLE>%n"
                                                      + "but was on data type%n"
                                                      + "  <REQUEST>"));
    }
  }
}
