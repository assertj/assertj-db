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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.navigation;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.AbstractDbAssert;
import org.assertj.db.api.AbstractSubAssert;
import org.assertj.db.api.TableAssert;
import org.assertj.db.api.TableColumnAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on instantiation errors.
 *
 * @author Régis Pouiller
 *
 */
public class InstantiationError_Test extends AbstractTest {

  /**
   * This method tests the error when using the {@code column} navigation method.
   */
  @Test
  public void should_fail_because_mistake_in_instantiation_of_column() throws Exception {
    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);

    Field field = AbstractDbAssert.class.getDeclaredField("columnPosition");
    Field field2 = Position.class.getDeclaredField("elementClass");
    field.setAccessible(true);
    field2.setAccessible(true);
    field2.set(field.get(tableAssert), InstantiationError_Test.class);

    try {
      tableAssert.column();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("There is an exception 'org.assertj.db.navigation.InstantiationError_Test.<init>(org.assertj.db.api.TableAssert, org.assertj.db.type.Column)'%n"
                                                      + "\t in the instantiation of the element org.assertj.db.navigation.InstantiationError_Test%n"
                                                      + "\t on class org.assertj.db.type.Column with class org.assertj.db.api.TableAssert.%n"
                                                      + " It is normally impossible.%n"
                                                      + " That means there is a big mistake in the development of AssertJDB.%n"
                                                      + " Please write an issue for that if you meet this problem."));
    }
  }

  /**
   * This method tests the error when using the {@code row} navigation method.
   */
  @Test
  public void should_fail_because_mistake_in_instantiation_of_row() throws Exception {
    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);

    Field field = AbstractDbAssert.class.getDeclaredField("rowPosition");
    Field field2 = Position.class.getDeclaredField("elementClass");
    field.setAccessible(true);
    field2.setAccessible(true);
    field2.set(field.get(tableAssert), InstantiationError_Test.class);

    try {
      tableAssert.row();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("There is an exception 'org.assertj.db.navigation.InstantiationError_Test.<init>(org.assertj.db.api.TableAssert, org.assertj.db.type.Row)'%n"
                                                      + "\t in the instantiation of the element org.assertj.db.navigation.InstantiationError_Test%n"
                                                      + "\t on class org.assertj.db.type.Row with class org.assertj.db.api.TableAssert.%n"
                                                      + " It is normally impossible.%n"
                                                      + " That means there is a big mistake in the development of AssertJDB.%n"
                                                      + " Please write an issue for that if you meet this problem."));
    }
  }

  /**
   * This method tests the error when using the {@code value} navigation method.
   */
  @Test
  public void should_fail_because_mistake_in_instantiation_of_value() throws Exception {
    Table table = new Table(source, "actor");
    TableColumnAssert tableColumnAssert = assertThat(table).column();

    Field field = AbstractSubAssert.class.getDeclaredField("valuePosition");
    Field field2 = Position.class.getDeclaredField("elementClass");
    field.setAccessible(true);
    field2.setAccessible(true);
    field2.set(field.get(tableColumnAssert), InstantiationError_Test.class);

    try {
      tableColumnAssert.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("There is an exception 'org.assertj.db.navigation.InstantiationError_Test.<init>(org.assertj.db.api.TableColumnAssert, org.assertj.db.type.Value)'\n"
                                                      + "\t in the instantiation of the assertion org.assertj.db.navigation.InstantiationError_Test\n"
                                                      + "\t on the value with class org.assertj.db.api.TableColumnAssert.\n"
                                                      + " It is normally impossible.\n"
                                                      + " That means there is a big mistake in the development of AssertJDB.\n"
                                                      + " Please write an issue for that if you meet this problem.");
    }
  }
}
