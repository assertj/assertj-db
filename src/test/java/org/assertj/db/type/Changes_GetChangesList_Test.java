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
package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import org.assertj.db.api.Assertions;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.junit.Test;

/**
 * Tests on the list of changes.
 * 
 * @author Régis Pouiller.
 * 
 */
public class Changes_GetChangesList_Test extends AbstractTest {

  /**
   * This method test when there is not change.
   */
  @Test
  public void test_when_there_is_no_change() {
    Changes changes = new Changes(source);
    changes.setStartPointNow();
    changes.setEndPointNow();
    assertThat(changes.getChangesList()).hasSize(0);
  }

  /**
   * This method test when there is no change found because it is another table.
   * 
   * @throws SQLException
   */
  @Test
  @NeedReload
  public void test_when_there_is_no_change_found() throws SQLException {
    Changes changes = new Changes(new Table(source, "test"));
    changes.setStartPointNow();
    update("delete from test2 where var1 is null");
    changes.setEndPointNow();
    assertThat(changes.getChangesList()).hasSize(0);
  }

  /**
   * This method test when there is a deletion change.
   * 
   * @throws SQLException
   */
  @Test
  @NeedReload
  public void test_when_there_is_deletion_change() throws SQLException {
    Changes changes = new Changes(source);
    changes.setStartPointNow();
    update("delete from test2 where var1 is null");
    changes.setEndPointNow();

    assertThat(changes.getChangesList()).hasSize(1);
    Change change = changes.getChangesList().get(0);
    assertThat(change.getDataName()).isEqualTo("TEST2");
    assertThat(change.getChangeType()).isEqualTo(ChangeType.DELETION);
    assertThat(change.getRowAtStartPoint().getValuesList()).containsExactly(null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null);
    assertThat(change.getRowAtEndPoint()).isNull();
  }

  /**
   * This method test when there is a creation change.
   * 
   * @throws SQLException
   */
  @Test
  @NeedReload
  public void test_when_there_is_creation_change() throws SQLException {
    Changes changes = new Changes(source);
    changes.setStartPointNow();
    update("insert into test2(var1) values(200)");
    changes.setEndPointNow();

    assertThat(changes.getChangesList()).hasSize(1);
    Change change = changes.getChangesList().get(0);
    assertThat(change.getDataName()).isEqualTo("TEST2");
    assertThat(change.getChangeType()).isEqualTo(ChangeType.CREATION);
    assertThat(change.getRowAtStartPoint()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList()).containsExactly(200, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null);
  }

  /**
   * This method test when there is a modification change without primary key.
   * 
   * @throws SQLException
   */
  @Test
  @NeedReload
  public void test_when_there_is_modification_change_without_primary_key() throws SQLException {
    Changes changes = new Changes(source);
    changes.setStartPointNow();
    update("update test2 set var12 = 'modification' where var1 = 1");
    changes.setEndPointNow();

    assertThat(changes.getChangesList()).hasSize(2);
    Change change = changes.getChangesList().get(0);
    assertThat(change.getDataName()).isEqualTo("TEST2");
    assertThat(change.getChangeType()).isEqualTo(ChangeType.CREATION);
    assertThat(change.getRowAtStartPoint()).isNull();

    assertThat(change.getRowAtEndPoint().getValuesList()).containsExactly(1, true, (byte) 2, (short) 3, 4L,
        new BigDecimal("5.60"), 7.8f, Time.valueOf("09:46:30"), Date.valueOf("2014-05-24"),
        Timestamp.valueOf("2014-05-24 09:46:30"), Assertions.bytesContentFromClassPathOf("h2-logo-2.png"),
        "modification", new BigDecimal("5.00"), 7f, null);
    Change change1 = changes.getChangesList().get(1);
    assertThat(change1.getDataName()).isEqualTo("TEST2");
    assertThat(change1.getChangeType()).isEqualTo(ChangeType.DELETION);
    assertThat(change1.getRowAtStartPoint().getValuesList()).containsExactly(1, true, (byte) 2, (short) 3, 4L,
        new BigDecimal("5.60"), 7.8f, Time.valueOf("09:46:30"), Date.valueOf("2014-05-24"),
        Timestamp.valueOf("2014-05-24 09:46:30"), Assertions.bytesContentFromClassPathOf("h2-logo-2.png"), "text",
        new BigDecimal("5.00"), 7f, null);
    assertThat(change1.getRowAtEndPoint()).isNull();
  }

  /**
   * This method test when there is a modification change with primary key.
   * 
   * @throws SQLException
   */
  @Test
  @NeedReload
  public void test_when_there_is_modification_change_with_primary_key() throws SQLException {
    Changes changes = new Changes(source);
    changes.setStartPointNow();
    update("update interpretation set character = 'Doctor Grace Augustine' where id = 3");
    changes.setEndPointNow();

    assertThat(changes.getChangesList()).hasSize(1);
    Change change = changes.getChangesList().get(0);
    assertThat(change.getDataName()).isEqualTo("INTERPRETATION");
    assertThat(change.getChangeType()).isEqualTo(ChangeType.MODIFICATION);
    assertThat(change.getRowAtStartPoint().getValuesList()).containsExactly(new BigDecimal(3), new BigDecimal(3),
        new BigDecimal(1), "Dr Grace Augustine");
    assertThat(change.getRowAtEndPoint().getValuesList()).containsExactly(new BigDecimal(3), new BigDecimal(3),
        new BigDecimal(1), "Doctor Grace Augustine");
  }
}
