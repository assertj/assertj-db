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

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.description.Description;
import org.assertj.core.description.TextDescription;
import org.assertj.core.util.introspection.FieldSupport;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the description methods for assertion on {@code Table}.
 * 
 * @author Régis Pouiller
 * 
 */
public class TableAssert_Description_Test extends AbstractTest {

  /**
   * This method tests the result of description from text and parameters.
   */
  @Test
  public void test_description_by_text() {

    Table table = new Table(source, "movie");
    TableAssert assertion = assertThat(table).as("test %s", "param");

    FieldSupport.setAllowExtractingPrivateFields(true);
    WritableAssertionInfo info = FieldSupport.instance().fieldValue("info", WritableAssertionInfo.class, assertion);

    assertThat(info.descriptionText()).isEqualTo("test param");
  }

  /**
   * This method tests the result of description from description object.
   */
  @Test
  public void test_description_by_description() {

    Table table = new Table(source, "movie");
    Description description = new TextDescription("test %s %s", "param", "param2");
    TableAssert assertion = assertThat(table).as(description);

    FieldSupport.setAllowExtractingPrivateFields(true);
    WritableAssertionInfo info = FieldSupport.instance().fieldValue("info", WritableAssertionInfo.class, assertion);

    assertThat(info.descriptionText()).isEqualTo("test param param2");
  }
}
