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
package org.assertj.db.navigation;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.AbstractColumnAssert;
import org.assertj.db.api.AbstractDbAssert;
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.api.ChangesAssert;
import org.assertj.db.api.TableAssert;
import org.assertj.db.api.TableColumnAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.junit.Test;

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

    Field field = AbstractColumnAssert.class.getDeclaredField("valuePosition");
    Field field2 = Position.class.getDeclaredField("elementClass");
    field.setAccessible(true);
    field2.setAccessible(true);
    field2.set(field.get(tableColumnAssert), InstantiationError_Test.class);

    try {
      tableColumnAssert.value();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("There is an exception 'org.assertj.db.navigation.InstantiationError_Test.<init>(org.assertj.db.api.TableColumnAssert, org.assertj.db.type.Value)'%n"
                                                      + "\t in the instantiation of the element org.assertj.db.navigation.InstantiationError_Test%n"
                                                      + "\t on class org.assertj.db.type.Value with class org.assertj.db.api.TableColumnAssert.%n"
                                                      + " It is normally impossible.%n"
                                                      + " That means there is a big mistake in the development of AssertJDB.%n"
                                                      + " Please write an issue for that if you meet this problem."));
    }
  }

  /**
   * This method tests the error when using the changes (in example : {@code ofCreation}) navigation method.
   */
  @Test
  @NeedReload
  public void should_fail_because_mistake_in_instantiation_of_changes() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangesAssert changesAssert = assertThat(changes);

    Field field = ChangesAssert.class.getDeclaredField("changesPosition");
    Field field2 = PositionWithChanges.class.getDeclaredField("actualElementClass");
    field.setAccessible(true);
    field2.setAccessible(true);
    field2.set(field.get(changesAssert), InstantiationError_Test.class);

    try {
      changesAssert.ofCreation();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("There is an exception 'org.assertj.db.navigation.InstantiationError_Test.<init>(org.assertj.db.api.ChangesAssert, org.assertj.db.type.Changes)'%n"
                                                      + "\t in the instantiation of the element org.assertj.db.navigation.InstantiationError_Test%n"
                                                      + "\t on class org.assertj.db.type.Changes with class org.assertj.db.api.ChangesAssert.%n"
                                                      + " It is normally impossible.%n"
                                                      + " That means there is a big mistake in the development of AssertJDB.%n"
                                                      + " Please write an issue for that if you meet this problem."));
    }
  }

  /**
   * This method tests the error when using the (in example : {@code change}) navigation method.
   */
  @Test
  @NeedReload
  public void should_fail_because_mistake_in_instantiation_of_change() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangesAssert changesAssert = assertThat(changes);

    Field field = ChangesAssert.class.getDeclaredField("changesPosition");
    Field field2 = PositionWithChanges.class.getDeclaredField("nextElementClass");
    field.setAccessible(true);
    field2.setAccessible(true);
    field2.set(field.get(changesAssert), InstantiationError_Test.class);

    try {
      changesAssert.change(1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("There is an exception 'org.assertj.db.navigation.InstantiationError_Test.<init>(org.assertj.db.api.ChangesAssert, org.assertj.db.type.Change)'%n"
                                                      + "\t in the instantiation of the element org.assertj.db.navigation.InstantiationError_Test%n"
                                                      + "\t on class org.assertj.db.type.Change with class org.assertj.db.api.ChangesAssert.%n"
                                                      + " It is normally impossible.%n"
                                                      + " That means there is a big mistake in the development of AssertJDB.%n"
                                                      + " Please write an issue for that if you meet this problem."));
    }
  }

  /**
   * This method tests the error when using the (in example : {@code column}) navigation method.
   */
  @Test
  @NeedReload
  public void should_fail_because_mistake_in_instantiation_of_columnchange() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeAssert changeAssert = assertThat(changes).change();

    Field field = ChangeAssert.class.getDeclaredField("columnPosition");
    Field field2 = PositionWithColumnsChange.class.getDeclaredField("elementClass");
    field.setAccessible(true);
    field2.setAccessible(true);
    field2.set(field.get(changeAssert), InstantiationError_Test.class);

    try {
      changeAssert.column(1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("There is an exception 'org.assertj.db.navigation.InstantiationError_Test.<init>(org.assertj.db.api.ChangeAssert, java.lang.String, org.assertj.db.type.Value, org.assertj.db.type.Value)'%n"
                                                      + "\t in the instantiation of the element org.assertj.db.navigation.InstantiationError_Test%n"
                                                      + "\t with class org.assertj.db.api.ChangeAssert.%n"
                                                      + " It is normally impossible.%n"
                                                      + " That means there is a big mistake in the development of AssertJDB.%n"
                                                      + " Please write an issue for that if you meet this problem."));
    }
  }

  /**
   * This method tests the error when using the (in example : {@code rowAtStartPoint}) navigation method.
   */
  @Test
  @NeedReload
  public void should_fail_because_mistake_in_instantiation_of_rowAtStartPoint() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeAssert changeAssert = assertThat(changes).change();

    Field field = ChangeAssert.class.getDeclaredField("rowPosition");
    Field field2 = PositionWithPoints.class.getDeclaredField("elementClass");
    field.setAccessible(true);
    field2.setAccessible(true);
    field2.set(field.get(changeAssert), InstantiationError_Test.class);

    try {
      changeAssert.rowAtStartPoint();
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format("There is an exception 'org.assertj.db.navigation.InstantiationError_Test.<init>(org.assertj.db.api.ChangeAssert, org.assertj.db.type.Row)'%n"
                                                      + "\t in the instantiation of the element org.assertj.db.navigation.InstantiationError_Test%n"
                                                      + "\t on class org.assertj.db.type.Row with class org.assertj.db.api.ChangeAssert.%n"
                                                      + " It is normally impossible.%n"
                                                      + " That means there is a big mistake in the development of AssertJDB.%n"
                                                      + " Please write an issue for that if you meet this problem."));
    }
  }
}
