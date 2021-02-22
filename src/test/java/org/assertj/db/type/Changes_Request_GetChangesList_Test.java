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
 * Copyright 2015-2021 the original author or authors.
 */
package org.assertj.db.type;

import org.assertj.db.api.Assertions;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on the list of changes on request.
 * 
 * @author Régis Pouiller.
 * 
 */
public class Changes_Request_GetChangesList_Test extends AbstractTest {

  /**
   * This method test when there is not change.
   */
  @Test
  public void test_when_there_is_no_change() {
    Changes changes = new Changes(new Request(dataSource, "select * from test"));
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
    Changes changes = new Changes(new Request(dataSource, "select * from test"));
    changes.setStartPointNow();
    update("delete from test2 where VAR1 is null");
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
    Changes changes = new Changes(new Request(dataSource, "select * from test2"));
    changes.setStartPointNow();
    update("delete from test2 where VAR1 is null");
    changes.setEndPointNow();

    assertThat(changes.getChangesList()).hasSize(1);
    Change change = changes.getChangesList().get(0);
    assertThat(change.getDataName()).isEqualTo("select * from test2");
    assertThat(change.getChangeType()).isEqualTo(ChangeType.DELETION);
    assertThat(change.getColumnsNameList()).containsExactly("VAR1", "VAR2", "VAR3", "VAR4", "VAR5", "VAR6", "VAR7",
            "VAR8", "VAR9", "VAR10", "VAR11", "VAR12", "VAR13", "VAR14", "VAR15", "VAR16", "VAR17");
    assertThat(change.getRowAtStartPoint().getValuesList().get(0).getValue()).isNull();
    assertThat(change.getRowAtStartPoint().getValuesList().get(1).getValue()).isNull();
    assertThat(change.getRowAtStartPoint().getValuesList().get(2).getValue()).isNull();
    assertThat(change.getRowAtStartPoint().getValuesList().get(3).getValue()).isNull();
    assertThat(change.getRowAtStartPoint().getValuesList().get(4).getValue()).isNull();
    assertThat(change.getRowAtStartPoint().getValuesList().get(5).getValue()).isNull();
    assertThat(change.getRowAtStartPoint().getValuesList().get(6).getValue()).isNull();
    assertThat(change.getRowAtStartPoint().getValuesList().get(7).getValue()).isNull();
    assertThat(change.getRowAtStartPoint().getValuesList().get(8).getValue()).isNull();
    assertThat(change.getRowAtStartPoint().getValuesList().get(9).getValue()).isNull();
    assertThat(change.getRowAtStartPoint().getValuesList().get(10).getValue()).isNull();
    assertThat(change.getRowAtStartPoint().getValuesList().get(11).getValue()).isNull();
    assertThat(change.getRowAtStartPoint().getValuesList().get(12).getValue()).isNull();
    assertThat(change.getRowAtStartPoint().getValuesList().get(13).getValue()).isNull();
    assertThat(change.getRowAtStartPoint().getValuesList().get(14).getValue()).isNull();
    assertThat(change.getRowAtStartPoint().getValuesList().get(15).getValue()).isNull();
    assertThat(change.getRowAtStartPoint().getValuesList().get(16).getValue()).isNull();
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
    Changes changes = new Changes(new Request(dataSource, "select * from test2"));
    changes.setStartPointNow();
    update("insert into test2(VAR1) values(200)");
    changes.setEndPointNow();

    assertThat(changes.getChangesList()).hasSize(1);
    Change change = changes.getChangesList().get(0);
    assertThat(change.getDataName()).isEqualTo("select * from test2");
    assertThat(change.getChangeType()).isEqualTo(ChangeType.CREATION);
    assertThat(change.getColumnsNameList()).containsExactly("VAR1", "VAR2", "VAR3", "VAR4", "VAR5", "VAR6", "VAR7",
        "VAR8", "VAR9", "VAR10", "VAR11", "VAR12", "VAR13", "VAR14", "VAR15", "VAR16", "VAR17");
    assertThat(change.getRowAtStartPoint()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(200);
    assertThat(change.getRowAtEndPoint().getValuesList().get(1).getValue()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList().get(2).getValue()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList().get(3).getValue()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList().get(4).getValue()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList().get(5).getValue()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList().get(6).getValue()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList().get(7).getValue()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList().get(8).getValue()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList().get(9).getValue()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList().get(10).getValue()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList().get(11).getValue()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList().get(12).getValue()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList().get(13).getValue()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList().get(14).getValue()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList().get(15).getValue()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList().get(16).getValue()).isNull();
  }

  /**
   * This method test when there is a modification change without primary key.
   * 
   * @throws SQLException
   */
  @Test
  @NeedReload
  public void test_when_there_is_modification_change_without_primary_key() throws SQLException {
    Changes changes = new Changes(new Request(dataSource, "select * from test2"));
    changes.setStartPointNow();
    update("update test2 set VAR12 = 'modification' where VAR1 = 1");
    changes.setEndPointNow();

    assertThat(changes.getChangesList()).hasSize(2);
    Change change = changes.getChangesList().get(0);
    assertThat(change.getDataName()).isEqualTo("select * from test2");
    assertThat(change.getChangeType()).isEqualTo(ChangeType.CREATION);
    assertThat(change.getColumnsNameList()).containsExactly("VAR1", "VAR2", "VAR3", "VAR4", "VAR5", "VAR6", "VAR7",
            "VAR8", "VAR9", "VAR10", "VAR11", "VAR12", "VAR13", "VAR14", "VAR15", "VAR16", "VAR17");
    assertThat(change.getRowAtStartPoint()).isNull();
    assertThat(change.getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(1);
    assertThat(change.getRowAtEndPoint().getValuesList().get(1).getValue()).isEqualTo(true);
    assertThat(change.getRowAtEndPoint().getValuesList().get(2).getValue()).isEqualTo((byte) 2);
    assertThat(change.getRowAtEndPoint().getValuesList().get(3).getValue()).isEqualTo((short) 3);
    assertThat(change.getRowAtEndPoint().getValuesList().get(4).getValue()).isEqualTo(4L);
    assertThat(change.getRowAtEndPoint().getValuesList().get(5).getValue()).isEqualTo(new BigDecimal("5.60"));
    assertThat(change.getRowAtEndPoint().getValuesList().get(6).getValue()).isEqualTo(7.8f);
    assertThat(change.getRowAtEndPoint().getValuesList().get(7).getValue()).isEqualTo(Time.valueOf("09:46:30"));
    assertThat(change.getRowAtEndPoint().getValuesList().get(8).getValue()).isEqualTo(Date.valueOf("2014-05-24"));
    assertThat(change.getRowAtEndPoint().getValuesList().get(9).getValue()).isEqualTo(Timestamp.valueOf("2014-05-24 09:46:30"));
    assertThat(change.getRowAtEndPoint().getValuesList().get(10).getValue()).isEqualTo(Assertions
                                                                                              .bytesContentFromClassPathOf(
                                                                                                      "h2-logo-2.png"));
    assertThat(change.getRowAtEndPoint().getValuesList().get(11).getValue()).isEqualTo("modification");
    assertThat(change.getRowAtEndPoint().getValuesList().get(12).getValue()).isEqualTo(new BigDecimal("5.00"));
    assertThat(change.getRowAtEndPoint().getValuesList().get(13).getValue()).isEqualTo(7f);
    assertThat(change.getRowAtEndPoint().getValuesList().get(14).getValue()).isEqualTo(null);
    assertThat(change.getRowAtEndPoint().getValuesList().get(15).getValue()).isEqualTo(UUID.fromString(
                                                                                              "30b443ae-c0c9-4790-9bec-ce1380808435"));
    assertThat(change.getRowAtEndPoint().getValuesList().get(16).getValue()).isEqualTo("T");
    Change change1 = changes.getChangesList().get(1);
    assertThat(change1.getDataName()).isEqualTo("select * from test2");
    assertThat(change1.getChangeType()).isEqualTo(ChangeType.DELETION);
    assertThat(change1.getColumnsNameList()).containsExactly("VAR1", "VAR2", "VAR3", "VAR4", "VAR5", "VAR6", "VAR7",
            "VAR8", "VAR9", "VAR10", "VAR11", "VAR12", "VAR13", "VAR14", "VAR15", "VAR16", "VAR17");
    assertThat(change1.getRowAtStartPoint().getValuesList().get(0).getValue()).isEqualTo(1);
    assertThat(change1.getRowAtStartPoint().getValuesList().get(1).getValue()).isEqualTo(true);
    assertThat(change1.getRowAtStartPoint().getValuesList().get(2).getValue()).isEqualTo((byte) 2);
    assertThat(change1.getRowAtStartPoint().getValuesList().get(3).getValue()).isEqualTo((short) 3);
    assertThat(change1.getRowAtStartPoint().getValuesList().get(4).getValue()).isEqualTo(4L);
    assertThat(change1.getRowAtStartPoint().getValuesList().get(5).getValue()).isEqualTo(new BigDecimal("5.60"));
    assertThat(change1.getRowAtStartPoint().getValuesList().get(6).getValue()).isEqualTo(7.8f);
    assertThat(change1.getRowAtStartPoint().getValuesList().get(7).getValue()).isEqualTo(Time.valueOf("09:46:30"));
    assertThat(change1.getRowAtStartPoint().getValuesList().get(8).getValue()).isEqualTo(Date.valueOf("2014-05-24"));
    assertThat(change1.getRowAtStartPoint().getValuesList().get(9).getValue()).isEqualTo(Timestamp.valueOf("2014-05-24 09:46:30"));
    assertThat(change1.getRowAtStartPoint().getValuesList().get(10).getValue()).isEqualTo(Assertions.bytesContentFromClassPathOf(
                                                                                                 "h2-logo-2.png"));
    assertThat(change1.getRowAtStartPoint().getValuesList().get(11).getValue()).isEqualTo("text");
    assertThat(change1.getRowAtStartPoint().getValuesList().get(12).getValue()).isEqualTo(new BigDecimal("5.00"));
    assertThat(change1.getRowAtStartPoint().getValuesList().get(13).getValue()).isEqualTo(7f);
    assertThat(change1.getRowAtStartPoint().getValuesList().get(14).getValue()).isEqualTo(null);
    assertThat(change1.getRowAtStartPoint().getValuesList().get(15).getValue()).isEqualTo(UUID.fromString(
                                                                                                 "30b443ae-c0c9-4790-9bec-ce1380808435"));
    assertThat(change1.getRowAtStartPoint().getValuesList().get(16).getValue()).isEqualTo("T");
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
    Changes changes = new Changes(new Request(dataSource, "select * from interpretation").setPksName("id"));
    changes.setStartPointNow();
    update("update interpretation set character = 'Doctor Grace Augustine' where id = 3");
    changes.setEndPointNow();

    assertThat(changes.getChangesList()).hasSize(1);
    Change change = changes.getChangesList().get(0);
    assertThat(change.getDataName()).isEqualTo("select * from interpretation");
    assertThat(change.getChangeType()).isEqualTo(ChangeType.MODIFICATION);
    assertThat(change.getColumnsNameList()).containsExactly("ID", "ID_MOVIE", "ID_ACTOR", "CHARACTER");
    assertThat(change.getRowAtStartPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(change.getRowAtStartPoint().getValuesList().get(1).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(change.getRowAtStartPoint().getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(1));
    assertThat(change.getRowAtStartPoint().getValuesList().get(3).getValue()).isEqualTo("Dr Grace Augustine");
    assertThat(change.getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(change.getRowAtEndPoint().getValuesList().get(1).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(change.getRowAtEndPoint().getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(1));
    assertThat(change.getRowAtEndPoint().getValuesList().get(3).getValue()).isEqualTo("Doctor Grace Augustine");
  }

  /**
   * This method test when there is a creation change with primary key.
   * 
   * @throws SQLException
   */
  @Test
  @NeedReload
  public void test_when_there_is_creation_change_with_primary_key() throws SQLException {
    Changes changes = new Changes(new Request(dataSource, "select * from movie").setPksName("id"));
    changes.setStartPointNow();
    update("insert into movie values(4, 'Ghostbusters', 1984, '16319617-AE95-4087-9264-D3D21BF611B6')");
    changes.setEndPointNow();

    assertThat(changes.getChangesList()).hasSize(1);
    Change change = changes.getChangesList().get(0);
    assertThat(change.getDataName()).isEqualTo("select * from movie");
    assertThat(change.getChangeType()).isEqualTo(ChangeType.CREATION);
    assertThat(change.getColumnsNameList()).containsExactly("ID", "TITLE", "YEAR", "MOVIE_IMDB");
    assertThat(change.getRowAtStartPoint()).isNull();

    assertThat(change.getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(4));
    assertThat(change.getRowAtEndPoint().getValuesList().get(1).getValue()).isEqualTo("Ghostbusters");
    assertThat(change.getRowAtEndPoint().getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(1984));
    assertThat(change.getRowAtEndPoint().getValuesList().get(3).getValue()).isEqualTo(UUID.fromString(
                    "16319617-AE95-4087-9264-D3D21BF611B6"));
  }

  /**
   * This method test when there is a deletion change with primary key.
   * 
   * @throws SQLException
   */
  @Test
  @NeedReload
  public void test_when_there_is_deletion_change_with_primary_key() throws SQLException {
    Changes changes = new Changes(new Request(dataSource, "select * from interpretation").setPksName("id"));
    changes.setStartPointNow();
    update("delete interpretation where id = 3");
    changes.setEndPointNow();

    assertThat(changes.getChangesList()).hasSize(1);
    Change change = changes.getChangesList().get(0);
    assertThat(change.getDataName()).isEqualTo("select * from interpretation");
    assertThat(change.getChangeType()).isEqualTo(ChangeType.DELETION);
    assertThat(change.getColumnsNameList()).containsExactly("ID", "ID_MOVIE", "ID_ACTOR", "CHARACTER");
    assertThat(change.getRowAtStartPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(change.getRowAtStartPoint().getValuesList().get(1).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(change.getRowAtStartPoint().getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(1));
    assertThat(change.getRowAtStartPoint().getValuesList().get(3).getValue()).isEqualTo("Dr Grace Augustine");
    assertThat(change.getRowAtEndPoint()).isNull();
  }

}
