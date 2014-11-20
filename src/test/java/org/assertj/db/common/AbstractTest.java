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
package org.assertj.db.common;

import java.lang.reflect.Constructor;
import java.util.List;

import javax.sql.DataSource;

import org.assertj.db.configuration.TestsConfiguration;
import org.assertj.db.type.Row;
import org.assertj.db.type.Source;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Parent for all the tests. It contains the variables like a {@code DataSource} and a {@code Source}.
 * 
 * @author Régis Pouiller
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestsConfiguration.class })
@Transactional
public abstract class AbstractTest {

  @Autowired(required = true)
  protected DataSource dataSource;

  protected Source source = new Source("jdbc:h2:mem:test", "sa", "");

  /**
   * Returns an instance of a Row.
   * 
   * @param pksNameList The list of the primary keys name.
   * @param columnsNameList The list of the columns name.
   * @param valuesList The values in the row.
   * @return An instance.
   * @throws Exception Exception
   */
  protected static Row getRow(List<String> pksNameList, List<String> columnsNameList, List<Object> valuesList) throws Exception {
    Constructor<Row> constructor = Row.class.getDeclaredConstructor(List.class, List.class, List.class);
    constructor.setAccessible(true);
    return constructor.newInstance(pksNameList, columnsNameList, valuesList);
  }
}
