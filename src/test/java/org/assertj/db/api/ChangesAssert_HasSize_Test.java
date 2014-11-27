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
package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the {@code hasSize} assertion method on {@code Changes}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ChangesAssert_HasSize_Test extends AbstractTest {

  /**
   * This method test the assertion on the size of {@code Changes} on source.
   */
  @Test
  @NeedReload
  public void test_size_of_changes_on_source_assertion() {
    Changes changes = new Changes(source);

    changes.setStartPointNow();

    update("insert into movie values(4, 'Ghostbusters', 1984)");
    update("insert into actor values(4, 'Murray', 'Bill', PARSEDATETIME('21/09/1950', 'dd/MM/yyyy'))");
    update("insert into interpretation values(6, 4, 4, 'Dr Peter Venkman')");

    update("delete from interpretation where id = 5");
    update("delete from actor where id = 3");

    update("update movie set title = 'The Avatar' where id = 3");
    update("update actor set firstname = 'Susan Alexandra' where id = 1");
    update("update interpretation set character = 'Doctor Grace Augustine' where id = 3");

    changes.setEndPointNow();

    assertThat(changes).hasSize(8);
  }

  /**
   * This method test the assertion on the size of {@code Changes} on data source.
   */
  @Test
  @NeedReload
  public void test_size_of_changes_on_datasource_assertion() {
    Changes changes = new Changes(dataSource);

    changes.setStartPointNow();

    update("insert into movie values(4, 'Ghostbusters', 1984)");
    update("insert into actor values(4, 'Murray', 'Bill', PARSEDATETIME('21/09/1950', 'dd/MM/yyyy'))");
    update("insert into interpretation values(6, 4, 4, 'Dr Peter Venkman')");

    update("delete from interpretation where id = 5");
    update("delete from actor where id = 3");

    update("update movie set title = 'The Avatar' where id = 3");
    update("update actor set firstname = 'Susan Alexandra' where id = 1");
    update("update interpretation set character = 'Doctor Grace Augustine' where id = 3");

    changes.setEndPointNow();

    assertThat(changes).hasSize(8);
  }

  /**
   * This method test the assertion on the size of {@code Changes} on a table.
   */
  @Test
  @NeedReload
  public void test_size_of_changes_on_table_assertion() {
    Changes changes = new Changes(new Table(source, "movie"));

    changes.setStartPointNow();

    update("insert into movie values(4, 'Ghostbusters', 1984)");
    update("insert into actor values(4, 'Murray', 'Bill', PARSEDATETIME('21/09/1950', 'dd/MM/yyyy'))");
    update("insert into interpretation values(6, 4, 4, 'Dr Peter Venkman')");

    update("delete from interpretation where id = 5");
    update("delete from actor where id = 3");

    update("update movie set title = 'The Avatar' where id = 3");
    update("update actor set firstname = 'Susan Alexandra' where id = 1");
    update("update interpretation set character = 'Doctor Grace Augustine' where id = 3");

    changes.setEndPointNow();

    assertThat(changes).hasSize(2);
  }

  /**
   * This method test the assertion on the size of {@code Changes} on many tables.
   */
  @Test
  @NeedReload
  public void test_size_of_changes_on_many_tables_assertion() {
    Changes changes = new Changes(new Table(source, "movie"), new Table(source, "actor"));

    changes.setStartPointNow();

    update("insert into movie values(4, 'Ghostbusters', 1984)");
    update("insert into actor values(4, 'Murray', 'Bill', PARSEDATETIME('21/09/1950', 'dd/MM/yyyy'))");
    update("insert into interpretation values(6, 4, 4, 'Dr Peter Venkman')");

    update("delete from interpretation where id = 5");
    update("delete from actor where id = 3");

    update("update movie set title = 'The Avatar' where id = 3");
    update("update actor set firstname = 'Susan Alexandra' where id = 1");
    update("update interpretation set character = 'Doctor Grace Augustine' where id = 3");

    changes.setEndPointNow();

    assertThat(changes).hasSize(5);
  }

  /**
   * This method test the assertion on the size of {@code Changes} on a request.
   */
  @Test
  @NeedReload
  public void test_size_of_changes_on_request_assertion() {
    Changes changes = new Changes(new Request(source,
        "select interpretation.id, character, movie.title, actor.name "
            + " from interpretation, movie, actor " + " where interpretation.id_movie = movie.id "
            + " and interpretation.id_actor = actor.id ").setPksName("id"));

    changes.setStartPointNow();

    update("insert into movie values(4, 'Ghostbusters', 1984)");
    update("insert into actor values(4, 'Murray', 'Bill', PARSEDATETIME('21/09/1950', 'dd/MM/yyyy'))");
    update("insert into interpretation values(6, 4, 4, 'Dr Peter Venkman')");

    update("delete from interpretation where id = 5");
    update("delete from actor where id = 3");

    update("update movie set title = 'The Avatar' where id = 3");
    update("update actor set firstname = 'Susan Alexandra' where id = 1");
    update("update interpretation set character = 'Doctor Grace Augustine' where id = 3");

    changes.setEndPointNow();

    assertThat(changes).hasSize(3);
  }

  /**
   * This test should fail because the size of the changes on source is different (8).
   */
  @Test
  @NeedReload
  public void should_fail_because_size_of_changes_on_source_is_different() {
    try {
      Changes changes = new Changes(source);

      changes.setStartPointNow();

      update("insert into movie values(4, 'Ghostbusters', 1984)");
      update("insert into actor values(4, 'Murray', 'Bill', PARSEDATETIME('21/09/1950', 'dd/MM/yyyy'))");
      update("insert into interpretation values(6, 4, 4, 'Dr Peter Venkman')");

      update("delete from interpretation where id = 5");
      update("delete from actor where id = 3");

      update("update movie set title = 'The Avatar' where id = 3");
      update("update actor set firstname = 'Susan Alexandra' where id = 1");
      update("update interpretation set character = 'Doctor Grace Augustine' where id = 3");

      changes.setEndPointNow();

      assertThat(changes).hasSize(3);

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
          "Expecting size (number of changes) to be equal to :\n" +
          "   <3>\n" +
          "but was:\n" +
          "   <8>");
    }
  }

  /**
   * This test should fail because the size if the changes on data source is different (8).
   */
  @Test
  @NeedReload
  public void should_fail_because_size_of_changes_on_datasource_is_different() {
    try {
      Changes changes = new Changes(dataSource);

      changes.setStartPointNow();

      update("insert into movie values(4, 'Ghostbusters', 1984)");
      update("insert into actor values(4, 'Murray', 'Bill', PARSEDATETIME('21/09/1950', 'dd/MM/yyyy'))");
      update("insert into interpretation values(6, 4, 4, 'Dr Peter Venkman')");

      update("delete from interpretation where id = 5");
      update("delete from actor where id = 3");

      update("update movie set title = 'The Avatar' where id = 3");
      update("update actor set firstname = 'Susan Alexandra' where id = 1");
      update("update interpretation set character = 'Doctor Grace Augustine' where id = 3");

      changes.setEndPointNow();

      assertThat(changes).hasSize(3);

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Changes on tables of a data source] \n" +
          "Expecting size (number of changes) to be equal to :\n" +
          "   <3>\n" +
          "but was:\n" +
          "   <8>");
    }
  }

  /**
   * This test should fail because the size of the changes on table is different (2).
   */
  @Test
  @NeedReload
  public void should_fail_because_size_of_changes_on_table_is_different() {
    try {
      Changes changes = new Changes(new Table(source, "movie"));

      changes.setStartPointNow();

      update("insert into movie values(4, 'Ghostbusters', 1984)");
      update("insert into actor values(4, 'Murray', 'Bill', PARSEDATETIME('21/09/1950', 'dd/MM/yyyy'))");
      update("insert into interpretation values(6, 4, 4, 'Dr Peter Venkman')");

      update("delete from interpretation where id = 5");
      update("delete from actor where id = 3");

      update("update movie set title = 'The Avatar' where id = 3");
      update("update actor set firstname = 'Susan Alexandra' where id = 1");
      update("update interpretation set character = 'Doctor Grace Augustine' where id = 3");

      changes.setEndPointNow();

      assertThat(changes).hasSize(3);

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Changes on movie table of 'sa/jdbc:h2:mem:test' source] \n" +
          "Expecting size (number of changes) to be equal to :\n" +
          "   <3>\n" +
          "but was:\n" +
          "   <2>");
    }
  }

  /**
   * This test should fail because the size of the changes on many tables is different (5).
   */
  @Test
  @NeedReload
  public void should_fail_because_size_of_changes_on_many_tables_is_different() {
    try {
      Changes changes = new Changes(new Table(source, "movie"), new Table(source, "actor"));

      changes.setStartPointNow();

      update("insert into movie values(4, 'Ghostbusters', 1984)");
      update("insert into actor values(4, 'Murray', 'Bill', PARSEDATETIME('21/09/1950', 'dd/MM/yyyy'))");
      update("insert into interpretation values(6, 4, 4, 'Dr Peter Venkman')");

      update("delete from interpretation where id = 5");
      update("delete from actor where id = 3");

      update("update movie set title = 'The Avatar' where id = 3");
      update("update actor set firstname = 'Susan Alexandra' where id = 1");
      update("update interpretation set character = 'Doctor Grace Augustine' where id = 3");

      changes.setEndPointNow();

      assertThat(changes).hasSize(3);

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Changes on tables of 'sa/jdbc:h2:mem:test' source] \n" +
          "Expecting size (number of changes) to be equal to :\n" +
          "   <3>\n" +
          "but was:\n" +
          "   <5>");
    }
  }

  /**
   * This test should fail because the size of the changes on many tables is different (3).
   */
  @Test
  @NeedReload
  public void should_fail_because_size_of_changes_on_request_is_different() {
    try {
      Changes changes = new Changes(new Request(source,
          "select interpretation.id, character, movie.title, actor.name "
              + " from interpretation, movie, actor " + " where interpretation.id_movie = movie.id "
              + " and interpretation.id_actor = actor.id ").setPksName("id"));

      changes.setStartPointNow();

      update("insert into movie values(4, 'Ghostbusters', 1984)");
      update("insert into actor values(4, 'Murray', 'Bill', PARSEDATETIME('21/09/1950', 'dd/MM/yyyy'))");
      update("insert into interpretation values(6, 4, 4, 'Dr Peter Venkman')");

      update("delete from interpretation where id = 5");
      update("delete from actor where id = 3");

      update("update movie set title = 'The Avatar' where id = 3");
      update("update actor set firstname = 'Susan Alexandra' where id = 1");
      update("update interpretation set character = 'Doctor Grace Augustine' where id = 3");

      changes.setEndPointNow();

      assertThat(changes).hasSize(2);

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Changes on 'select interpretation.id, char...' request of 'sa/jdbc:h2:mem:test' source] \n" +
          "Expecting size (number of changes) to be equal to :\n" +
          "   <2>\n" +
          "but was:\n" +
          "   <3>");
    }
  }

}
