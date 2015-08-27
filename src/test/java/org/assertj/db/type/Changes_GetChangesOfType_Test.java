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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.type;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on the {@code getChangesOfType} method.
 * 
 * @author Régis Pouiller.
 * 
 */
public class Changes_GetChangesOfType_Test extends AbstractTest {

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
      updateChangesForTests();
      changesRequest.setEndPointNow();
      changesSource.setEndPointNow();

      assertThat(changesSource.getChangesList()).hasSize(8);

      Changes changesCreation = changesSource.getChangesOfType(ChangeType.CREATION);
      assertThat(changesCreation.getChangesList()).hasSize(3);
      assertThat(changesCreation.getChangesList().get(0).getDataName()).isEqualTo("ACTOR");
      assertThat(changesCreation.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.CREATION);
      assertThat(changesCreation.getChangesList().get(0).getColumnsNameList()).containsExactly("ID", "NAME", "FIRSTNAME",
              "BIRTH", "ACTOR_IMDB");
      assertThat(changesCreation.getChangesList().get(0).getRowAtStartPoint()).isNull();
      assertThat(changesCreation.getChangesList().get(0).getRowAtEndPoint().getValuesList()).containsExactly(
              new BigDecimal(4), "Murray", "Bill", Date.valueOf("1950-09-21"), UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
      assertThat(changesCreation.getChangesList().get(1).getDataName()).isEqualTo("INTERPRETATION");
      assertThat(changesCreation.getChangesList().get(1).getChangeType()).isEqualTo(ChangeType.CREATION);
      assertThat(changesCreation.getChangesList().get(1).getColumnsNameList()).containsExactly("ID", "ID_MOVIE", "ID_ACTOR", "CHARACTER");
      assertThat(changesCreation.getChangesList().get(1).getRowAtStartPoint()).isNull();
      assertThat(changesCreation.getChangesList().get(1).getRowAtEndPoint().getValuesList()).containsExactly(
              new BigDecimal(6), new BigDecimal(4), new BigDecimal(4), "Dr Peter Venkman");
      assertThat(changesCreation.getChangesList().get(2).getDataName()).isEqualTo("MOVIE");
      assertThat(changesCreation.getChangesList().get(2).getChangeType()).isEqualTo(ChangeType.CREATION);
      assertThat(changesCreation.getChangesList().get(2).getColumnsNameList()).containsExactly("ID", "TITLE", "YEAR", "MOVIE_IMDB");
      assertThat(changesCreation.getChangesList().get(2).getRowAtStartPoint()).isNull();
      assertThat(changesCreation.getChangesList().get(2).getRowAtEndPoint().getValuesList()).containsExactly(
              new BigDecimal(4), "Ghostbusters", new BigDecimal(1984), UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));

      Changes changesModification = changesSource.getChangesOfType(ChangeType.MODIFICATION);
      assertThat(changesModification.getChangesList()).hasSize(3);
      assertThat(changesModification.getChangesList().get(0).getDataName()).isEqualTo("ACTOR");
      assertThat(changesModification.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.MODIFICATION);
      assertThat(changesModification.getChangesList().get(0).getColumnsNameList()).containsExactly("ID", "NAME", "FIRSTNAME",
              "BIRTH", "ACTOR_IMDB");
      assertThat(changesModification.getChangesList().get(0).getRowAtStartPoint().getValuesList()).containsExactly(
          new BigDecimal(1), "Weaver", "Sigourney", Date.valueOf("1949-10-08"), UUID.fromString("30b443ae-c0c9-4790-9bec-ce1380808435"));
      assertThat(changesModification.getChangesList().get(0).getRowAtEndPoint().getValuesList()).containsExactly(
          new BigDecimal(1), "Weaver", "Susan Alexandra", Date.valueOf("1949-10-08"), UUID.fromString("30b443ae-c0c9-4790-9bec-ce1380808435"));
      assertThat(changesModification.getChangesList().get(1).getDataName()).isEqualTo("INTERPRETATION");
      assertThat(changesModification.getChangesList().get(1).getChangeType()).isEqualTo(ChangeType.MODIFICATION);
      assertThat(changesModification.getChangesList().get(1).getColumnsNameList()).containsExactly("ID", "ID_MOVIE", "ID_ACTOR", "CHARACTER");
      assertThat(changesModification.getChangesList().get(1).getRowAtStartPoint().getValuesList()).containsExactly(
          new BigDecimal(3), new BigDecimal(3), new BigDecimal(1), "Dr Grace Augustine");
      assertThat(changesModification.getChangesList().get(1).getRowAtEndPoint().getValuesList()).containsExactly(
          new BigDecimal(3), new BigDecimal(3), new BigDecimal(1), "Doctor Grace Augustine");
      assertThat(changesModification.getChangesList().get(2).getDataName()).isEqualTo("MOVIE");
      assertThat(changesModification.getChangesList().get(2).getChangeType()).isEqualTo(ChangeType.MODIFICATION);
      assertThat(changesModification.getChangesList().get(2).getColumnsNameList()).containsExactly("ID", "TITLE", "YEAR", "MOVIE_IMDB");
      assertThat(changesModification.getChangesList().get(2).getRowAtStartPoint().getValuesList()).containsExactly(
          new BigDecimal(3), "Avatar", new BigDecimal(2009), UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));
      assertThat(changesModification.getChangesList().get(2).getRowAtEndPoint().getValuesList()).containsExactly(
          new BigDecimal(3), "The Avatar", new BigDecimal(2009), UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));

      Changes changesDeletion = changesSource.getChangesOfType(ChangeType.DELETION);
      assertThat(changesDeletion.getChangesList()).hasSize(2);
      assertThat(changesDeletion.getChangesList().get(0).getDataName()).isEqualTo("ACTOR");
      assertThat(changesDeletion.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.DELETION);
      assertThat(changesDeletion.getChangesList().get(0).getColumnsNameList()).containsExactly("ID", "NAME", "FIRSTNAME",
          "BIRTH", "ACTOR_IMDB");
      assertThat(changesDeletion.getChangesList().get(0).getRowAtStartPoint().getValuesList()).containsExactly(
          new BigDecimal(3), "Worthington", "Sam", Date.valueOf("1976-08-02"), UUID.fromString("d735221b-5de5-4112-aa1e-49090cb75ada"));
      assertThat(changesDeletion.getChangesList().get(0).getRowAtEndPoint()).isNull();
       assertThat(changesDeletion.getChangesList().get(1).getDataName()).isEqualTo("INTERPRETATION");
       assertThat(changesDeletion.getChangesList().get(1).getChangeType()).isEqualTo(ChangeType.DELETION);
       assertThat(changesDeletion.getChangesList().get(1).getColumnsNameList()).containsExactly("ID", "ID_MOVIE", "ID_ACTOR", "CHARACTER");
       assertThat(changesDeletion.getChangesList().get(1).getRowAtStartPoint().getValuesList()).containsExactly(
           new BigDecimal(5), new BigDecimal(3), new BigDecimal(3), "Jake Sully");
       assertThat(changesDeletion.getChangesList().get(1).getRowAtEndPoint()).isNull();

       Changes changesRequestCreation = changesRequest.getChangesOfType(ChangeType.CREATION);
       assertThat(changesRequestCreation.getChangesList()).hasSize(1);
       assertThat(changesRequestCreation.getChangesList().get(0).getDataName()).isEqualTo("select interpretation.id, character, movie.title, actor.name  from interpretation, movie, actor  where interpretation.id_movie = movie.id  and interpretation.id_actor = actor.id ");
       assertThat(changesRequestCreation.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.CREATION);
       assertThat(changesRequestCreation.getChangesList().get(0).getColumnsNameList()).containsExactly("ID", "CHARACTER", "TITLE",
           "NAME");
       assertThat(changesRequestCreation.getChangesList().get(0).getRowAtStartPoint()).isNull();
       assertThat(changesRequestCreation.getChangesList().get(0).getRowAtEndPoint().getValuesList()).containsExactly(
           new BigDecimal(6), "Dr Peter Venkman", "Ghostbusters", "Murray");

       Changes changesRequestModification = changesRequest.getChangesOfType(ChangeType.MODIFICATION);
       assertThat(changesRequestModification.getChangesList()).hasSize(1);
       assertThat(changesRequestModification.getChangesList().get(0).getDataName()).isEqualTo("select interpretation.id, character, movie.title, actor.name  from interpretation, movie, actor  where interpretation.id_movie = movie.id  and interpretation.id_actor = actor.id ");
       assertThat(changesRequestModification.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.MODIFICATION);
       assertThat(changesRequestModification.getChangesList().get(0).getColumnsNameList()).containsExactly("ID", "CHARACTER", "TITLE",
           "NAME");
       assertThat(changesRequestModification.getChangesList().get(0).getRowAtStartPoint().getValuesList()).containsExactly(
           new BigDecimal(3), "Dr Grace Augustine", "Avatar", "Weaver");
       assertThat(changesRequestModification.getChangesList().get(0).getRowAtEndPoint().getValuesList()).containsExactly(
           new BigDecimal(3), "Doctor Grace Augustine", "The Avatar", "Weaver");

       Changes changesRequestDeletion = changesRequest.getChangesOfType(ChangeType.DELETION);
       assertThat(changesRequestDeletion.getChangesList()).hasSize(1);
       assertThat(changesRequestDeletion.getChangesList().get(0).getDataName()).isEqualTo("select interpretation.id, character, movie.title, actor.name  from interpretation, movie, actor  where interpretation.id_movie = movie.id  and interpretation.id_actor = actor.id ");
       assertThat(changesRequestDeletion.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.DELETION);
       assertThat(changesRequestDeletion.getChangesList().get(0).getColumnsNameList()).containsExactly("ID", "CHARACTER", "TITLE",
           "NAME");
       assertThat(changesRequestDeletion.getChangesList().get(0).getRowAtStartPoint().getValuesList()).containsExactly(
           new BigDecimal(5), "Jake Sully", "Avatar", "Worthington");
       assertThat(changesRequestDeletion.getChangesList().get(0).getRowAtEndPoint()).isNull();
    }

}
