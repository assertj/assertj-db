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

import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;
import org.assertj.core.api.ObjectAssert;
import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on the constructors of Changes
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class Changes_Constructor_Test extends AbstractTest {

  /**
   * This method test the constructor with jdbc connection provider.
   */
  @Test
  public void test_constructor_jdbc() {
    Changes changes = assertDbConnection.changes().build();

    assertThat(changes.getRequestAtStartPoint()).isNull();
    assertThat(changes.getRequestAtEndPoint()).isNull();
    assertThat(changes.getTablesAtStartPointList()).isNull();
    assertThat(changes.getTablesAtEndPointList()).isNull();

    changes.setStartPointNow();

    assertThat(changes.getRequestAtStartPoint()).isNull();
    assertThat(changes.getRequestAtEndPoint()).isNull();
    assertThat(changes.getTablesAtStartPointList()).isNotNull();
    assertThat(changes.getTablesAtStartPointList()).hasSize(5);
    assertThat(changes.getTablesAtStartPointList().get(0).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().get(0).getName()).isEqualTo("ACTOR");
    assertThat(changes.getTablesAtStartPointList().get(0).getRequest()).isEqualTo("SELECT * FROM ACTOR");
    assertThat(changes.getTablesAtStartPointList().get(1).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().get(1).getName()).isEqualTo("INTERPRETATION");
    assertThat(changes.getTablesAtStartPointList().get(1).getRequest()).isEqualTo("SELECT * FROM INTERPRETATION");
    assertThat(changes.getTablesAtStartPointList().get(2).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().get(2).getName()).isEqualTo("MOVIE");
    assertThat(changes.getTablesAtStartPointList().get(2).getRequest()).isEqualTo("SELECT * FROM MOVIE");
    assertThat(changes.getTablesAtStartPointList().get(3).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().get(3).getName()).isEqualTo("TEST");
    assertThat(changes.getTablesAtStartPointList().get(3).getRequest()).isEqualTo("SELECT * FROM TEST");
    assertThat(changes.getTablesAtStartPointList().get(4).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().get(4).getName()).isEqualTo("TEST2");
    assertThat(changes.getTablesAtStartPointList().get(4).getRequest()).isEqualTo("SELECT * FROM TEST2");
    assertThat(changes.getTablesAtEndPointList()).isNull();

    changes.setEndPointNow();

    assertThat(changes.getRequestAtStartPoint()).isNull();
    assertThat(changes.getRequestAtEndPoint()).isNull();
    assertThat(changes.getTablesAtStartPointList()).isNotNull();
    assertThat(changes.getTablesAtStartPointList()).hasSize(5);
    assertThat(changes.getTablesAtStartPointList().get(0).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().get(0).getName()).isEqualTo("ACTOR");
    assertThat(changes.getTablesAtStartPointList().get(0).getRequest()).isEqualTo("SELECT * FROM ACTOR");
    assertThat(changes.getTablesAtStartPointList().get(1).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().get(1).getName()).isEqualTo("INTERPRETATION");
    assertThat(changes.getTablesAtStartPointList().get(1).getRequest()).isEqualTo("SELECT * FROM INTERPRETATION");
    assertThat(changes.getTablesAtStartPointList().get(2).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().get(2).getName()).isEqualTo("MOVIE");
    assertThat(changes.getTablesAtStartPointList().get(2).getRequest()).isEqualTo("SELECT * FROM MOVIE");
    assertThat(changes.getTablesAtStartPointList().get(3).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().get(3).getName()).isEqualTo("TEST");
    assertThat(changes.getTablesAtStartPointList().get(3).getRequest()).isEqualTo("SELECT * FROM TEST");
    assertThat(changes.getTablesAtStartPointList().get(4).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().get(4).getName()).isEqualTo("TEST2");
    assertThat(changes.getTablesAtStartPointList().get(4).getRequest()).isEqualTo("SELECT * FROM TEST2");
    assertThat(changes.getTablesAtEndPointList()).isNotNull();
    assertThat(changes.getTablesAtEndPointList()).hasSize(5);
    assertThat(changes.getTablesAtEndPointList().get(0).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtEndPointList().get(0).getName()).isEqualTo("ACTOR");
    assertThat(changes.getTablesAtEndPointList().get(0).getRequest()).isEqualTo("SELECT * FROM ACTOR");
    assertThat(changes.getTablesAtEndPointList().get(1).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtEndPointList().get(1).getName()).isEqualTo("INTERPRETATION");
    assertThat(changes.getTablesAtEndPointList().get(1).getRequest()).isEqualTo("SELECT * FROM INTERPRETATION");
    assertThat(changes.getTablesAtEndPointList().get(2).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtEndPointList().get(2).getName()).isEqualTo("MOVIE");
    assertThat(changes.getTablesAtEndPointList().get(2).getRequest()).isEqualTo("SELECT * FROM MOVIE");
    assertThat(changes.getTablesAtEndPointList().get(3).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtEndPointList().get(3).getName()).isEqualTo("TEST");
    assertThat(changes.getTablesAtEndPointList().get(3).getRequest()).isEqualTo("SELECT * FROM TEST");
    assertThat(changes.getTablesAtEndPointList().get(4).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtEndPointList().get(4).getName()).isEqualTo("TEST2");
    assertThat(changes.getTablesAtEndPointList().get(4).getRequest()).isEqualTo("SELECT * FROM TEST2");
  }

  /**
   * This method test the constructor with one table.
   */
  @Test
  public void test_constructor_one_table() {
    Changes changes = assertDbConnection.changes().tables(assertDbConnection.table("test").build()).build();

    assertThat(changes.getRequestAtStartPoint()).isNull();
    assertThat(changes.getRequestAtEndPoint()).isNull();

    assertThat(changes.getTablesAtStartPointList()).isNull();
    changes.setStartPointNow();
    assertThat(changes.getTablesAtStartPointList()).isNotNull();
    assertThat(changes.getTablesAtStartPointList()).hasSize(1);
    assertThat(changes.getTablesAtStartPointList().get(0).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().get(0).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().get(0).getName()).isEqualTo("TEST");
    assertThat(changes.getTablesAtStartPointList().get(0).getRequest()).isEqualTo("SELECT * FROM TEST");

    assertThat(changes.getTablesAtEndPointList()).isNull();
    changes.setEndPointNow();
    assertThat(changes.getTablesAtEndPointList()).isNotNull();
    assertThat(changes.getTablesAtEndPointList()).hasSize(1);
    assertThat(changes.getTablesAtEndPointList().get(0).getConnectionProvider()).isNotNull();
    assertThat(changes.getTablesAtEndPointList().get(0).getName()).isEqualTo("TEST");
    assertThat(changes.getTablesAtEndPointList().get(0).getRequest()).isEqualTo("SELECT * FROM TEST");
  }

  /**
   * This method test the constructor with two tables.
   */
  @Test
  public void test_constructor_two_tables() {
    Changes changes = assertDbConnection.changes()
      .tables(assertDbConnection.table("test").build(), assertDbConnection.table("test2").build())
      .build();

    assertThat(changes.getRequestAtStartPoint()).isNull();
    assertThat(changes.getRequestAtEndPoint()).isNull();

    assertThat(changes.getTablesAtStartPointList()).isNull();
    changes.setStartPointNow();
    assertThat(changes.getTablesAtStartPointList()).isNotNull();
    assertThat(changes.getTablesAtStartPointList()).hasSize(2);
    assertThat(changes.getTablesAtStartPointList().get(0)).isNotNull();
    assertThat(changes.getTablesAtStartPointList().get(0).getName()).isEqualTo("TEST");
    assertThat(changes.getTablesAtStartPointList().get(0).getRequest()).isEqualTo("SELECT * FROM TEST");
    assertThat(changes.getTablesAtStartPointList().get(1).getName()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().get(1).getName()).isEqualTo("TEST2");
    assertThat(changes.getTablesAtStartPointList().get(1).getRequest()).isEqualTo("SELECT * FROM TEST2");

    assertThat(changes.getTablesAtEndPointList()).isNull();
    changes.setEndPointNow();
    assertThat(changes.getTablesAtEndPointList()).isNotNull();
    assertThat(changes.getTablesAtEndPointList()).hasSize(2);
    assertThat(changes.getTablesAtEndPointList().get(0).getName()).isNotNull();
    assertThat(changes.getTablesAtEndPointList().get(0).getName()).isEqualTo("TEST");
    assertThat(changes.getTablesAtEndPointList().get(0).getRequest()).isEqualTo("SELECT * FROM TEST");
    assertThat(changes.getTablesAtEndPointList().get(1).getName()).isNotNull();
    assertThat(changes.getTablesAtEndPointList().get(1).getName()).isEqualTo("TEST2");
    assertThat(changes.getTablesAtEndPointList().get(1).getRequest()).isEqualTo("SELECT * FROM TEST2");
  }

  /**
   * This method test the constructor with a request.
   */
  @Test
  public void test_constructor_request() {
    Changes changes = assertDbConnection.changes().request(assertDbConnection.request("select * from test").build()).build();

    assertThat(changes.getRequestAtStartPoint()).isNull();
    assertThat(changes.getRequestAtEndPoint()).isNull();
    assertThat(changes.getTablesAtStartPointList()).isNull();
    assertThat(changes.getTablesAtEndPointList()).isNull();

    changes.setStartPointNow();

    assertThat(changes.getRequestAtStartPoint()).isNotNull();
    assertThat(changes.getRequestAtStartPoint().getConnectionProvider()).isNotNull();
    assertThat(changes.getRequestAtStartPoint().getRequest()).isEqualTo("select * from test");
    assertThat(changes.getRequestAtEndPoint()).isNull();
    assertThat(changes.getTablesAtStartPointList()).isNull();
    assertThat(changes.getTablesAtEndPointList()).isNull();

    changes.setEndPointNow();

    assertThat(changes.getRequestAtStartPoint()).isNotNull();
    assertThat(changes.getRequestAtEndPoint()).isNotNull();
    assertThat(changes.getRequestAtEndPoint().getConnectionProvider()).isNotNull();
    assertThat(changes.getRequestAtEndPoint().getRequest()).isEqualTo("select * from test");
    assertThat(changes.getTablesAtStartPointList()).isNull();
    assertThat(changes.getTablesAtEndPointList()).isNull();
  }

  private static ObjectAssert<Object> assertThat(Object o) {
    return Assertions.assertThat(o);
  }

  private static <T> ListAssert<Object> assertThat(List<T> list) {
    return Assertions.assertThat(list);
  }
}
