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

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.junit.Test;

/**
 * Tests on the {@code getChangesOfTable} method.
 * 
 * @author Régis Pouiller.
 * 
 */
public class Changes_Table_GetChangesOfTable_Test extends AbstractTest {

  /**
   * This method test when getting changes of table.
   */
  @Test
  @NeedReload
  public void test_getChangesOfTable() {
    Changes changesSource = new Changes(source);
    Changes changesRequest = new Changes(new Request(source,
        "select interpretation.id, character, movie.title, actor.name "
            + " from interpretation, movie, actor " + " where interpretation.id_movie = movie.id "
            + " and interpretation.id_actor = actor.id ").setPksName("id"));

    changesRequest.setStartPointNow();
    changesSource.setStartPointNow();

    update("insert into movie values(4, 'Ghostbusters', 1984)");
    update("insert into actor values(4, 'Murray', 'Bill', PARSEDATETIME('21/09/1950', 'dd/MM/yyyy'))");
    update("insert into interpretation values(6, 4, 4, 'Dr Peter Venkman')");

    update("delete from interpretation where id = 5");
    update("delete from actor where id = 3");

    update("update movie set title = 'The Avatar' where id = 3");
    update("update actor set firstname = 'Susan Alexandra' where id = 1");
    update("update interpretation set character = 'Doctor Grace Augustine' where id = 3");

    changesRequest.setEndPointNow();
    changesSource.setEndPointNow();

    assertThat(changesSource.getChangesList()).hasSize(8);

    Changes changesMovie = changesSource.getChangesOfTable("movie");

    assertThat(changesMovie.getChangesList()).hasSize(2);
    assertThat(changesMovie.getChangesList().get(0).getDataName()).isEqualTo("MOVIE");
    assertThat(changesMovie.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.CREATION);
    assertThat(changesMovie.getChangesList().get(0).getColumnsNameList()).containsExactly("ID", "TITLE", "YEAR");
    assertThat(changesMovie.getChangesList().get(0).getRowAtStartPoint()).isNull();
    assertThat(changesMovie.getChangesList().get(0).getRowAtEndPoint().getValuesList()).containsExactly(
        new BigDecimal(4), "Ghostbusters", new BigDecimal(1984));
    assertThat(changesMovie.getChangesList().get(1).getDataName()).isEqualTo("MOVIE");
    assertThat(changesMovie.getChangesList().get(1).getChangeType()).isEqualTo(ChangeType.MODIFICATION);
    assertThat(changesMovie.getChangesList().get(1).getColumnsNameList()).containsExactly("ID", "TITLE", "YEAR");
    assertThat(changesMovie.getChangesList().get(1).getRowAtStartPoint().getValuesList()).containsExactly(
        new BigDecimal(3), "Avatar", new BigDecimal(2009));
    assertThat(changesMovie.getChangesList().get(1).getRowAtEndPoint().getValuesList()).containsExactly(
        new BigDecimal(3), "The Avatar", new BigDecimal(2009));

    Changes changesActor = changesSource.getChangesOfTable("actor");
    assertThat(changesActor.getChangesList()).hasSize(3);
    assertThat(changesActor.getChangesList().get(0).getDataName()).isEqualTo("ACTOR");
    assertThat(changesActor.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.CREATION);
    assertThat(changesActor.getChangesList().get(0).getColumnsNameList()).containsExactly("ID", "NAME", "FIRSTNAME",
        "BIRTH");
    assertThat(changesActor.getChangesList().get(0).getRowAtStartPoint()).isNull();
    assertThat(changesActor.getChangesList().get(0).getRowAtEndPoint().getValuesList()).containsExactly(
        new BigDecimal(4), "Murray", "Bill", Date.valueOf("1950-09-21"));
    assertThat(changesActor.getChangesList().get(1).getDataName()).isEqualTo("ACTOR");
    assertThat(changesActor.getChangesList().get(1).getChangeType()).isEqualTo(ChangeType.MODIFICATION);
    assertThat(changesActor.getChangesList().get(1).getColumnsNameList()).containsExactly("ID", "NAME", "FIRSTNAME",
        "BIRTH");
    assertThat(changesActor.getChangesList().get(1).getRowAtStartPoint().getValuesList()).containsExactly(
        new BigDecimal(1), "Weaver", "Sigourney", Date.valueOf("1949-10-08"));
    assertThat(changesActor.getChangesList().get(1).getRowAtEndPoint().getValuesList()).containsExactly(
        new BigDecimal(1), "Weaver", "Susan Alexandra", Date.valueOf("1949-10-08"));
    assertThat(changesActor.getChangesList().get(2).getDataName()).isEqualTo("ACTOR");
    assertThat(changesActor.getChangesList().get(2).getChangeType()).isEqualTo(ChangeType.DELETION);
    assertThat(changesActor.getChangesList().get(2).getColumnsNameList()).containsExactly("ID", "NAME", "FIRSTNAME",
        "BIRTH");
    assertThat(changesActor.getChangesList().get(2).getRowAtStartPoint().getValuesList()).containsExactly(
        new BigDecimal(3), "Worthington", "Sam", Date.valueOf("1976-08-02"));
    assertThat(changesActor.getChangesList().get(2).getRowAtEndPoint()).isNull();

     Changes changesInterpretation = changesSource.getChangesOfTable("interpretation");
     assertThat(changesInterpretation.getChangesList()).hasSize(3);
     assertThat(changesInterpretation.getChangesList().get(0).getDataName()).isEqualTo("INTERPRETATION");
     assertThat(changesInterpretation.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.CREATION);
     assertThat(changesInterpretation.getChangesList().get(0).getColumnsNameList()).containsExactly("ID", "ID_MOVIE", "ID_ACTOR", "CHARACTER");
     assertThat(changesInterpretation.getChangesList().get(0).getRowAtStartPoint()).isNull();
     assertThat(changesInterpretation.getChangesList().get(0).getRowAtEndPoint().getValuesList()).containsExactly(
         new BigDecimal(6), new BigDecimal(4), new BigDecimal(4), "Dr Peter Venkman");
     assertThat(changesInterpretation.getChangesList().get(1).getDataName()).isEqualTo("INTERPRETATION");
     assertThat(changesInterpretation.getChangesList().get(1).getChangeType()).isEqualTo(ChangeType.MODIFICATION);
     assertThat(changesInterpretation.getChangesList().get(1).getColumnsNameList()).containsExactly("ID", "ID_MOVIE", "ID_ACTOR", "CHARACTER");
     assertThat(changesInterpretation.getChangesList().get(1).getRowAtStartPoint().getValuesList()).containsExactly(
         new BigDecimal(3), new BigDecimal(3), new BigDecimal(1), "Dr Grace Augustine");
     assertThat(changesInterpretation.getChangesList().get(1).getRowAtEndPoint().getValuesList()).containsExactly(
         new BigDecimal(3), new BigDecimal(3), new BigDecimal(1), "Doctor Grace Augustine");
     assertThat(changesInterpretation.getChangesList().get(2).getDataName()).isEqualTo("INTERPRETATION");
     assertThat(changesInterpretation.getChangesList().get(2).getChangeType()).isEqualTo(ChangeType.DELETION);
     assertThat(changesInterpretation.getChangesList().get(2).getColumnsNameList()).containsExactly("ID", "ID_MOVIE", "ID_ACTOR", "CHARACTER");
     assertThat(changesInterpretation.getChangesList().get(2).getRowAtStartPoint().getValuesList()).containsExactly(
         new BigDecimal(5), new BigDecimal(3), new BigDecimal(3), "Jake Sully");
     assertThat(changesInterpretation.getChangesList().get(2).getRowAtEndPoint()).isNull();

    assertThat(changesRequest.getChangesOfTable("movie").getChangesList()).hasSize(0);
  }

}
