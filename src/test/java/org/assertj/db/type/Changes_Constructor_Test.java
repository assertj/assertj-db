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
package org.assertj.db.type;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on the constructors of Changes
 * 
 * @author Régis Pouiller
 * 
 */
public class Changes_Constructor_Test extends AbstractTest {

  /**
   * This method test the constructor with source.
   */
  @Test
  public void test_constructor_source() {
    Changes changes = new Changes(source);

    assertThat(changes.getRequestAtStartPoint()).isNull();
    assertThat(changes.getRequestAtEndPoint()).isNull();
    assertThat(changes.getTablesAtStartPointList()).isNull();
    assertThat(changes.getTablesAtEndPointList()).isNull();

    changes.setStartPointNow();

    assertThat(changes.getRequestAtStartPoint()).isNull();
    assertThat(changes.getRequestAtEndPoint()).isNull();
    assertThat(changes.getTablesAtStartPointList()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().size()).isEqualTo(5);
    assertThat(changes.getTablesAtStartPointList().get(0).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtStartPointList().get(0).getDataSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(0).getName()).isEqualTo("ACTOR");
    assertThat(changes.getTablesAtStartPointList().get(0).getRequest()).isEqualTo("SELECT * FROM ACTOR");
    assertThat(changes.getTablesAtStartPointList().get(1).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtStartPointList().get(1).getDataSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(1).getName()).isEqualTo("INTERPRETATION");
    assertThat(changes.getTablesAtStartPointList().get(1).getRequest()).isEqualTo("SELECT * FROM INTERPRETATION");
    assertThat(changes.getTablesAtStartPointList().get(2).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtStartPointList().get(2).getDataSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(2).getName()).isEqualTo("MOVIE");
    assertThat(changes.getTablesAtStartPointList().get(2).getRequest()).isEqualTo("SELECT * FROM MOVIE");
    assertThat(changes.getTablesAtStartPointList().get(3).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtStartPointList().get(3).getDataSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(3).getName()).isEqualTo("TEST");
    assertThat(changes.getTablesAtStartPointList().get(3).getRequest()).isEqualTo("SELECT * FROM TEST");
    assertThat(changes.getTablesAtStartPointList().get(4).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtStartPointList().get(4).getDataSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(4).getName()).isEqualTo("TEST2");
    assertThat(changes.getTablesAtStartPointList().get(4).getRequest()).isEqualTo("SELECT * FROM TEST2");
    assertThat(changes.getTablesAtEndPointList()).isNull();

    changes.setEndPointNow();

    assertThat(changes.getRequestAtStartPoint()).isNull();
    assertThat(changes.getRequestAtEndPoint()).isNull();
    assertThat(changes.getTablesAtStartPointList()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().size()).isEqualTo(5);
    assertThat(changes.getTablesAtStartPointList().get(0).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtStartPointList().get(0).getDataSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(0).getName()).isEqualTo("ACTOR");
    assertThat(changes.getTablesAtStartPointList().get(0).getRequest()).isEqualTo("SELECT * FROM ACTOR");
    assertThat(changes.getTablesAtStartPointList().get(1).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtStartPointList().get(1).getDataSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(1).getName()).isEqualTo("INTERPRETATION");
    assertThat(changes.getTablesAtStartPointList().get(1).getRequest()).isEqualTo("SELECT * FROM INTERPRETATION");
    assertThat(changes.getTablesAtStartPointList().get(2).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtStartPointList().get(2).getDataSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(2).getName()).isEqualTo("MOVIE");
    assertThat(changes.getTablesAtStartPointList().get(2).getRequest()).isEqualTo("SELECT * FROM MOVIE");
    assertThat(changes.getTablesAtStartPointList().get(3).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtStartPointList().get(3).getDataSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(3).getName()).isEqualTo("TEST");
    assertThat(changes.getTablesAtStartPointList().get(3).getRequest()).isEqualTo("SELECT * FROM TEST");
    assertThat(changes.getTablesAtStartPointList().get(4).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtStartPointList().get(4).getDataSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(4).getName()).isEqualTo("TEST2");
    assertThat(changes.getTablesAtStartPointList().get(4).getRequest()).isEqualTo("SELECT * FROM TEST2");
    assertThat(changes.getTablesAtEndPointList()).isNotNull();
    assertThat(changes.getTablesAtEndPointList().size()).isEqualTo(5);
    assertThat(changes.getTablesAtEndPointList().get(0).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtEndPointList().get(0).getDataSource()).isNull();
    assertThat(changes.getTablesAtEndPointList().get(0).getName()).isEqualTo("ACTOR");
    assertThat(changes.getTablesAtEndPointList().get(0).getRequest()).isEqualTo("SELECT * FROM ACTOR");
    assertThat(changes.getTablesAtEndPointList().get(1).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtEndPointList().get(1).getDataSource()).isNull();
    assertThat(changes.getTablesAtEndPointList().get(1).getName()).isEqualTo("INTERPRETATION");
    assertThat(changes.getTablesAtEndPointList().get(1).getRequest()).isEqualTo("SELECT * FROM INTERPRETATION");
    assertThat(changes.getTablesAtEndPointList().get(2).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtEndPointList().get(2).getDataSource()).isNull();
    assertThat(changes.getTablesAtEndPointList().get(2).getName()).isEqualTo("MOVIE");
    assertThat(changes.getTablesAtEndPointList().get(2).getRequest()).isEqualTo("SELECT * FROM MOVIE");
    assertThat(changes.getTablesAtEndPointList().get(3).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtEndPointList().get(3).getDataSource()).isNull();
    assertThat(changes.getTablesAtEndPointList().get(3).getName()).isEqualTo("TEST");
    assertThat(changes.getTablesAtEndPointList().get(3).getRequest()).isEqualTo("SELECT * FROM TEST");
    assertThat(changes.getTablesAtEndPointList().get(4).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtEndPointList().get(4).getDataSource()).isNull();
    assertThat(changes.getTablesAtEndPointList().get(4).getName()).isEqualTo("TEST2");
    assertThat(changes.getTablesAtEndPointList().get(4).getRequest()).isEqualTo("SELECT * FROM TEST2");
  }

  /**
   * This method test the constructor with datasource.
   */
  @Test
  public void test_constructor_datasource() {
    Changes changes = new Changes(dataSource);

    assertThat(changes.getRequestAtStartPoint()).isNull();
    assertThat(changes.getRequestAtEndPoint()).isNull();
    assertThat(changes.getTablesAtStartPointList()).isNull();
    assertThat(changes.getTablesAtEndPointList()).isNull();

    changes.setStartPointNow();

    assertThat(changes.getRequestAtStartPoint()).isNull();
    assertThat(changes.getRequestAtEndPoint()).isNull();
    assertThat(changes.getTablesAtStartPointList()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().size()).isEqualTo(5);
    assertThat(changes.getTablesAtStartPointList().get(0).getSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(0).getDataSource()).isSameAs(dataSource);
    assertThat(changes.getTablesAtStartPointList().get(0).getName()).isEqualTo("ACTOR");
    assertThat(changes.getTablesAtStartPointList().get(0).getRequest()).isEqualTo("SELECT * FROM ACTOR");
    assertThat(changes.getTablesAtStartPointList().get(1).getSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(1).getDataSource()).isSameAs(dataSource);
    assertThat(changes.getTablesAtStartPointList().get(1).getName()).isEqualTo("INTERPRETATION");
    assertThat(changes.getTablesAtStartPointList().get(1).getRequest()).isEqualTo("SELECT * FROM INTERPRETATION");
    assertThat(changes.getTablesAtStartPointList().get(2).getSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(2).getDataSource()).isSameAs(dataSource);
    assertThat(changes.getTablesAtStartPointList().get(2).getName()).isEqualTo("MOVIE");
    assertThat(changes.getTablesAtStartPointList().get(2).getRequest()).isEqualTo("SELECT * FROM MOVIE");
    assertThat(changes.getTablesAtStartPointList().get(3).getSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(3).getDataSource()).isSameAs(dataSource);
    assertThat(changes.getTablesAtStartPointList().get(3).getName()).isEqualTo("TEST");
    assertThat(changes.getTablesAtStartPointList().get(3).getRequest()).isEqualTo("SELECT * FROM TEST");
    assertThat(changes.getTablesAtStartPointList().get(4).getSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(4).getDataSource()).isSameAs(dataSource);
    assertThat(changes.getTablesAtStartPointList().get(4).getName()).isEqualTo("TEST2");
    assertThat(changes.getTablesAtStartPointList().get(4).getRequest()).isEqualTo("SELECT * FROM TEST2");
    assertThat(changes.getTablesAtEndPointList()).isNull();

    changes.setEndPointNow();

    assertThat(changes.getRequestAtStartPoint()).isNull();
    assertThat(changes.getRequestAtEndPoint()).isNull();
    assertThat(changes.getTablesAtStartPointList()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().size()).isEqualTo(5);
    assertThat(changes.getTablesAtStartPointList().get(0).getSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(0).getDataSource()).isSameAs(dataSource);
    assertThat(changes.getTablesAtStartPointList().get(0).getName()).isEqualTo("ACTOR");
    assertThat(changes.getTablesAtStartPointList().get(0).getRequest()).isEqualTo("SELECT * FROM ACTOR");
    assertThat(changes.getTablesAtStartPointList().get(1).getSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(1).getDataSource()).isSameAs(dataSource);
    assertThat(changes.getTablesAtStartPointList().get(1).getName()).isEqualTo("INTERPRETATION");
    assertThat(changes.getTablesAtStartPointList().get(1).getRequest()).isEqualTo("SELECT * FROM INTERPRETATION");
    assertThat(changes.getTablesAtStartPointList().get(2).getSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(2).getDataSource()).isSameAs(dataSource);
    assertThat(changes.getTablesAtStartPointList().get(2).getName()).isEqualTo("MOVIE");
    assertThat(changes.getTablesAtStartPointList().get(2).getRequest()).isEqualTo("SELECT * FROM MOVIE");
    assertThat(changes.getTablesAtStartPointList().get(3).getSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(3).getDataSource()).isSameAs(dataSource);
    assertThat(changes.getTablesAtStartPointList().get(3).getName()).isEqualTo("TEST");
    assertThat(changes.getTablesAtStartPointList().get(3).getRequest()).isEqualTo("SELECT * FROM TEST");
    assertThat(changes.getTablesAtStartPointList().get(4).getSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(4).getDataSource()).isSameAs(dataSource);
    assertThat(changes.getTablesAtStartPointList().get(4).getName()).isEqualTo("TEST2");
    assertThat(changes.getTablesAtStartPointList().get(4).getRequest()).isEqualTo("SELECT * FROM TEST2");
    assertThat(changes.getTablesAtEndPointList()).isNotNull();
    assertThat(changes.getTablesAtEndPointList().size()).isEqualTo(5);
    assertThat(changes.getTablesAtEndPointList().get(0).getSource()).isNull();
    assertThat(changes.getTablesAtEndPointList().get(0).getDataSource()).isSameAs(dataSource);
    assertThat(changes.getTablesAtEndPointList().get(0).getName()).isEqualTo("ACTOR");
    assertThat(changes.getTablesAtEndPointList().get(0).getRequest()).isEqualTo("SELECT * FROM ACTOR");
    assertThat(changes.getTablesAtEndPointList().get(1).getSource()).isNull();
    assertThat(changes.getTablesAtEndPointList().get(1).getDataSource()).isSameAs(dataSource);
    assertThat(changes.getTablesAtEndPointList().get(1).getName()).isEqualTo("INTERPRETATION");
    assertThat(changes.getTablesAtEndPointList().get(1).getRequest()).isEqualTo("SELECT * FROM INTERPRETATION");
    assertThat(changes.getTablesAtEndPointList().get(2).getSource()).isNull();
    assertThat(changes.getTablesAtEndPointList().get(2).getDataSource()).isSameAs(dataSource);
    assertThat(changes.getTablesAtEndPointList().get(2).getName()).isEqualTo("MOVIE");
    assertThat(changes.getTablesAtEndPointList().get(2).getRequest()).isEqualTo("SELECT * FROM MOVIE");
    assertThat(changes.getTablesAtEndPointList().get(3).getSource()).isNull();
    assertThat(changes.getTablesAtEndPointList().get(3).getDataSource()).isSameAs(dataSource);
    assertThat(changes.getTablesAtEndPointList().get(3).getName()).isEqualTo("TEST");
    assertThat(changes.getTablesAtEndPointList().get(3).getRequest()).isEqualTo("SELECT * FROM TEST");
    assertThat(changes.getTablesAtEndPointList().get(4).getSource()).isNull();
    assertThat(changes.getTablesAtEndPointList().get(4).getDataSource()).isSameAs(dataSource);
    assertThat(changes.getTablesAtEndPointList().get(4).getName()).isEqualTo("TEST2");
    assertThat(changes.getTablesAtEndPointList().get(4).getRequest()).isEqualTo("SELECT * FROM TEST2");
  }

  /**
   * This method test the constructor with one table.
   */
  @Test
  public void test_constructor_one_table() {
    Changes changes = new Changes(new Table(source, "test"));

    assertThat(changes.getRequestAtStartPoint()).isNull();
    assertThat(changes.getRequestAtEndPoint()).isNull();

    assertThat(changes.getTablesAtStartPointList()).isNull();
    changes.setStartPointNow();
    assertThat(changes.getTablesAtStartPointList()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().size()).isEqualTo(1);
    assertThat(changes.getTablesAtStartPointList().get(0).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtStartPointList().get(0).getDataSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(0).getName()).isEqualTo("TEST");
    assertThat(changes.getTablesAtStartPointList().get(0).getRequest()).isEqualTo("SELECT * FROM TEST");

    assertThat(changes.getTablesAtEndPointList()).isNull();
    changes.setEndPointNow();
    assertThat(changes.getTablesAtEndPointList()).isNotNull();
    assertThat(changes.getTablesAtEndPointList().size()).isEqualTo(1);
    assertThat(changes.getTablesAtEndPointList().get(0).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtEndPointList().get(0).getDataSource()).isNull();
    assertThat(changes.getTablesAtEndPointList().get(0).getName()).isEqualTo("TEST");
    assertThat(changes.getTablesAtEndPointList().get(0).getRequest()).isEqualTo("SELECT * FROM TEST");
  }

  /**
   * This method test the constructor with two tables.
   */
  @Test
  public void test_constructor_two_tables() {
    Changes changes = new Changes(new Table(source, "test"), new Table(dataSource, "test2"));

    assertThat(changes.getRequestAtStartPoint()).isNull();
    assertThat(changes.getRequestAtEndPoint()).isNull();

    assertThat(changes.getTablesAtStartPointList()).isNull();
    changes.setStartPointNow();
    assertThat(changes.getTablesAtStartPointList()).isNotNull();
    assertThat(changes.getTablesAtStartPointList().size()).isEqualTo(2);
    assertThat(changes.getTablesAtStartPointList().get(0).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtStartPointList().get(0).getDataSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(0).getName()).isEqualTo("TEST");
    assertThat(changes.getTablesAtStartPointList().get(0).getRequest()).isEqualTo("SELECT * FROM TEST");
    assertThat(changes.getTablesAtStartPointList().get(1).getSource()).isNull();
    assertThat(changes.getTablesAtStartPointList().get(1).getDataSource()).isSameAs(dataSource);
    assertThat(changes.getTablesAtStartPointList().get(1).getName()).isEqualTo("TEST2");
    assertThat(changes.getTablesAtStartPointList().get(1).getRequest()).isEqualTo("SELECT * FROM TEST2");

    assertThat(changes.getTablesAtEndPointList()).isNull();
    changes.setEndPointNow();
    assertThat(changes.getTablesAtEndPointList()).isNotNull();
    assertThat(changes.getTablesAtEndPointList().size()).isEqualTo(2);
    assertThat(changes.getTablesAtEndPointList().get(0).getSource()).isSameAs(source);
    assertThat(changes.getTablesAtEndPointList().get(0).getDataSource()).isNull();
    assertThat(changes.getTablesAtEndPointList().get(0).getName()).isEqualTo("TEST");
    assertThat(changes.getTablesAtEndPointList().get(0).getRequest()).isEqualTo("SELECT * FROM TEST");
    assertThat(changes.getTablesAtEndPointList().get(1).getSource()).isNull();
    assertThat(changes.getTablesAtEndPointList().get(1).getDataSource()).isSameAs(dataSource);
    assertThat(changes.getTablesAtEndPointList().get(1).getName()).isEqualTo("TEST2");
    assertThat(changes.getTablesAtEndPointList().get(1).getRequest()).isEqualTo("SELECT * FROM TEST2");
  }

  /**
   * This method test the constructor with a request.
   */
  @Test
  public void test_constructor_request() {
    Changes changes = new Changes(new Request(source, "select * from test"));

    assertThat(changes.getRequestAtStartPoint()).isNull();
    assertThat(changes.getRequestAtEndPoint()).isNull();
    assertThat(changes.getTablesAtStartPointList()).isNull();
    assertThat(changes.getTablesAtEndPointList()).isNull();

    changes.setStartPointNow();

    assertThat(changes.getRequestAtStartPoint()).isNotNull();
    assertThat(changes.getRequestAtStartPoint().getSource()).isSameAs(source);
    assertThat(changes.getRequestAtStartPoint().getRequest()).isEqualTo("select * from test");
    assertThat(changes.getRequestAtEndPoint()).isNull();
    assertThat(changes.getTablesAtStartPointList()).isNull();
    assertThat(changes.getTablesAtEndPointList()).isNull();

    changes.setEndPointNow();

    assertThat(changes.getRequestAtStartPoint()).isNotNull();
    assertThat(changes.getRequestAtEndPoint()).isNotNull();
    assertThat(changes.getRequestAtEndPoint().getSource()).isSameAs(source);
    assertThat(changes.getRequestAtEndPoint().getRequest()).isEqualTo("select * from test");
    assertThat(changes.getTablesAtStartPointList()).isNull();
    assertThat(changes.getTablesAtEndPointList()).isNull();
  }
}
