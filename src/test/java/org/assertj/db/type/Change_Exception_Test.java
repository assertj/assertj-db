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

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests on the exceptions of Change
 *
 * @author Régis Pouiller
 *
 */
public class Change_Exception_Test extends AbstractTest {

  /**
   * This method should fail because the data type must be not null.
   */
  @Test
  public void should_fail_because_datatype_must_be_not_null() throws Exception {

    Row start = getRow(singletonList(""), singletonList(""), singletonList(getValue(null, null)));
    Row end = getRow(singletonList(""), singletonList(""), singletonList(getValue(null, null)));

    assertThatThrownBy(() -> getChange(null, "name", ChangeType.CREATION, start, end))
        .isInstanceOf(Exception.class)
        .getCause()
        .hasMessage("The type of the data must be not null");
  }

  /**
   * This method should fail because the data name must be not null.
   */
  @Test
  public void should_fail_because_dataname_must_be_not_null() throws Exception {

    Row start = getRow(singletonList(""), singletonList(""), singletonList(getValue(null, null)));
    Row end = getRow(singletonList(""), singletonList(""), singletonList(getValue(null, null)));

    assertThatThrownBy(() -> getChange(DataType.TABLE, null, ChangeType.CREATION, start, end))
        .isInstanceOf(Exception.class)
        .getCause()
        .hasMessage("The name of the data must be not null");
  }
}
