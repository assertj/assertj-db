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
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.db.api.assertions;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnPrimaryKey} class :
 * {@link org.assertj.db.api.assertions.AssertOnPrimaryKey#hasPksNames(String...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnPrimaryKey_HasPksNames_Test extends AbstractTest {

  /**
   * This method tests the {@code hasPksNames} assertion method.
   */
  @Test
  @NeedReload
  public void test_has_pks_names() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeAssert changeAssert = assertThat(changes).change();
    ChangeAssert changeAssert2 = changeAssert.hasPksNames("ID");
    Assertions.assertThat(changeAssert).isSameAs(changeAssert2);
  }

  /**
   * This method should fail because the primary keys names are different.
   */
  @Test
  @NeedReload
  public void should_fail_because_pks_names_are_different() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    try {
      assertThat(changes).change().hasPksNames("ID2");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source] %n"
                                                      + "Expecting :%n"
                                                      + "  [\"ID2\"]%n"
                                                      + "to be the name of the columns of the primary keys but was:%n"
                                                      + "  [\"ID\"]"));
    }
  }

  /**
   * This method should fail because the number of primary keys names are different.
   */
  @Test
  @NeedReload
  public void should_fail_because_number_of_pks_names_are_different() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    try {
      assertThat(changes).change().hasPksNames("ID", "ID2");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source] %n"
                                                      + "Expecting :%n"
                                                      + "  [\"ID\", \"ID2\"]%n"
                                                      + "to be the name of the columns of the primary keys but was:%n"
                                                      + "  [\"ID\"]"));
    }
  }
}
