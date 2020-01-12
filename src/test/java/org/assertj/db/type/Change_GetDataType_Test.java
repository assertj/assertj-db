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
import org.assertj.db.common.NeedReload;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on the {@code getDataType} method.
 *
 * @author Régis Pouiller.
 *
 */
public class Change_GetDataType_Test extends AbstractTest {

  /**
   * This method test when getting the data type of changes on a table.
   */
  @Test
  @NeedReload
  public void test_getDataTypeOfTable() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes.getChangesList().get(0).getDataType()).isEqualTo(DataType.TABLE);
  }

  /**
   * This method test when getting the data type of changes on a request.
   */
  @Test
  @NeedReload
  public void test_getDataTypeOfRequest() {
    Changes changes = new Changes(new Request(source, "select * from movie")).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes.getChangesList().get(0).getDataType()).isEqualTo(DataType.REQUEST);
  }
}
