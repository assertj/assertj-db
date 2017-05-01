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
package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.DefaultConnection;
import org.junit.Test;

/**
 * Tests on the exceptions of {@code AbstractDbElement}
 * 
 * @author Régis Pouiller
 * 
 */
public class AbstractDbElement_Exception_Test extends AbstractTest {

  /**
   * This method should fail because {@code getCatalog} throws an SQL Exception.
   */
  @Test
  public void should_fail_because_getCatalog_throws_an_SQLException() {
    try {
      AbstractDbElement.getCatalog(new DefaultConnection(null) {

        @Override
        public String getCatalog() throws SQLException {
          throw new SQLException("test");
        }        
      });
      fail("An exception must be raised");
    } catch (SQLException exception) {
      assertThat(exception.getMessage()).isEqualTo("test");
    }
  }

  /**
   * Tests if returns {@code null} because {@code getCatalog} throws an Exception.
   * @throws SQLException 
   */
  @Test
  public void test_if_returns_null_because_getCatalog_throws_an_Exception() throws SQLException {
    String catalog = AbstractDbElement.getCatalog(new DefaultConnection(null) {

        @Override
        public String getCatalog() throws SQLException {
          throw new RuntimeException("test");
        }        
      });
    assertThat(catalog).isNull();
  }

  /**
   * This method should fail because {@code getSchema} throws an SQL Exception.
   */
  @Test
  public void should_fail_because_getSchema_throws_an_SQLException() {
    try {
      AbstractDbElement.getSchema(new DefaultConnection(null) {

        @Override
        public String getSchema() throws SQLException {
          throw new SQLException("test");
        }        
      });
      fail("An exception must be raised");
    } catch (SQLException exception) {
      assertThat(exception.getMessage()).isEqualTo("test");
    }
  }

  /**
   * Tests if returns {@code null} because {@code getSchema} throws an Exception.
   * @throws SQLException 
   */
  @Test
  public void test_if_returns_null_because_getSchema_throws_an_Exception() throws SQLException {
    String schema = AbstractDbElement.getSchema(new DefaultConnection(null) {

        @Override
        public String getCatalog() throws SQLException {
          throw new RuntimeException("test");
        }        
      });
    assertThat(schema).isNull();
  }
}
