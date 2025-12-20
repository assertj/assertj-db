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

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.junit.Test;

/**
 * Tests on the {@code getDataType} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy.
 */
public class Change_GetDataType_Test extends AbstractTest {

  /**
   * This method test when getting the data type of changes on a table.
   */
  @Test
  @NeedReload
  public void test_getDataTypeOfTable() {
    Changes changes = assertDbConnection.changes().build().setStartPointNow();
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
    Changes changes = assertDbConnection.changes().request(assertDbConnection.request("select * from movie").build()).build().setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes.getChangesList().get(0).getDataType()).isEqualTo(DataType.REQUEST);
  }
}
