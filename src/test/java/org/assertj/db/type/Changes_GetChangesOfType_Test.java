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

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.junit.Test;

/**
 * Tests on the {@code getChangesOfType} method.
 *
 * @author Régis Pouiller
 * @author Julien Roy.
 */
public class Changes_GetChangesOfType_Test extends AbstractTest {

  /**
   * This method test when getting changes of table.
   */
  @Test
  @NeedReload
  public void test_getChangesOfTable() {
    Changes changesSource = assertDbConnection.changes().build();
    Changes changesRequest = assertDbConnection.changes().request(assertDbConnection.request("select interpretation.id, character, movie.title, actor.name "
      + " from interpretation, movie, actor " + " where interpretation.id_movie = movie.id "
      + " and interpretation.id_actor = actor.id ").pksName("id").build()).build();

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
    assertThat(changesCreation.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(4));
    assertThat(changesCreation.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(1).getValue()).isEqualTo("Murray");
    assertThat(changesCreation.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(2).getValue()).isEqualTo("Bill");
    assertThat(changesCreation.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(3).getValue()).isEqualTo(Date.valueOf("1950-09-21"));
    assertThat(changesCreation.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(4).getValue()).isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
    assertThat(changesCreation.getChangesList().get(1).getDataName()).isEqualTo("INTERPRETATION");
    assertThat(changesCreation.getChangesList().get(1).getChangeType()).isEqualTo(ChangeType.CREATION);
    assertThat(changesCreation.getChangesList().get(1).getColumnsNameList()).containsExactly("ID", "ID_MOVIE", "ID_ACTOR", "CHARACTER");
    assertThat(changesCreation.getChangesList().get(1).getRowAtStartPoint()).isNull();
    assertThat(changesCreation.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(6));
    assertThat(changesCreation.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(1).getValue()).isEqualTo(new BigDecimal(4));
    assertThat(changesCreation.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(4));
    assertThat(changesCreation.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(3).getValue()).isEqualTo("Dr Peter Venkman");
    assertThat(changesCreation.getChangesList().get(2).getDataName()).isEqualTo("MOVIE");
    assertThat(changesCreation.getChangesList().get(2).getChangeType()).isEqualTo(ChangeType.CREATION);
    assertThat(changesCreation.getChangesList().get(2).getColumnsNameList()).containsExactly("ID", "TITLE", "YEAR", "MOVIE_IMDB");
    assertThat(changesCreation.getChangesList().get(2).getRowAtStartPoint()).isNull();
    assertThat(changesCreation.getChangesList().get(2).getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(4));
    assertThat(changesCreation.getChangesList().get(2).getRowAtEndPoint().getValuesList().get(1).getValue()).isEqualTo("Ghostbusters");
    assertThat(changesCreation.getChangesList().get(2).getRowAtEndPoint().getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(1984));
    assertThat(changesCreation.getChangesList().get(2).getRowAtEndPoint().getValuesList().get(3).getValue()).isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));

    Changes changesModification = changesSource.getChangesOfType(ChangeType.MODIFICATION);
    assertThat(changesModification.getChangesList()).hasSize(3);
    assertThat(changesModification.getChangesList().get(0).getDataName()).isEqualTo("ACTOR");
    assertThat(changesModification.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.MODIFICATION);
    assertThat(changesModification.getChangesList().get(0).getColumnsNameList()).containsExactly("ID", "NAME", "FIRSTNAME",
      "BIRTH", "ACTOR_IMDB");
    assertThat(changesModification.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(1));
    assertThat(changesModification.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(1).getValue()).isEqualTo("Weaver");
    assertThat(changesModification.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(2).getValue()).isEqualTo("Sigourney");
    assertThat(changesModification.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(3).getValue()).isEqualTo(Date.valueOf("1949-10-08"));
    assertThat(changesModification.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(4).getValue()).isEqualTo(UUID.fromString("30b443ae-c0c9-4790-9bec-ce1380808435"));
    assertThat(changesModification.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(1));
    assertThat(changesModification.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(1).getValue()).isEqualTo("Weaver");
    assertThat(changesModification.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(2).getValue()).isEqualTo("Susan Alexandra");
    assertThat(changesModification.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(3).getValue()).isEqualTo(Date.valueOf("1949-10-08"));
    assertThat(changesModification.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(4).getValue()).isEqualTo(UUID.fromString("30b443ae-c0c9-4790-9bec-ce1380808435"));
    assertThat(changesModification.getChangesList().get(1).getDataName()).isEqualTo("INTERPRETATION");
    assertThat(changesModification.getChangesList().get(1).getChangeType()).isEqualTo(ChangeType.MODIFICATION);
    assertThat(changesModification.getChangesList().get(1).getColumnsNameList()).containsExactly("ID", "ID_MOVIE", "ID_ACTOR", "CHARACTER");
    assertThat(changesModification.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesModification.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(1).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesModification.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(1));
    assertThat(changesModification.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(3).getValue()).isEqualTo("Dr Grace Augustine");
    assertThat(changesModification.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesModification.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesModification.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(1));
    assertThat(changesModification.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(3).getValue()).isEqualTo("Doctor Grace Augustine");
    assertThat(changesModification.getChangesList().get(2).getDataName()).isEqualTo("MOVIE");
    assertThat(changesModification.getChangesList().get(2).getChangeType()).isEqualTo(ChangeType.MODIFICATION);
    assertThat(changesModification.getChangesList().get(2).getColumnsNameList()).containsExactly("ID", "TITLE", "YEAR", "MOVIE_IMDB");
    assertThat(changesModification.getChangesList().get(2).getRowAtStartPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesModification.getChangesList().get(2).getRowAtStartPoint().getValuesList().get(1).getValue()).isEqualTo("Avatar");
    assertThat(changesModification.getChangesList().get(2).getRowAtStartPoint().getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(2009));
    assertThat(changesModification.getChangesList().get(2).getRowAtStartPoint().getValuesList().get(3).getValue()).isEqualTo(UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));
    assertThat(changesModification.getChangesList().get(2).getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesModification.getChangesList().get(2).getRowAtEndPoint().getValuesList().get(1).getValue()).isEqualTo("The Avatar");
    assertThat(changesModification.getChangesList().get(2).getRowAtEndPoint().getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(2009));
    assertThat(changesModification.getChangesList().get(2).getRowAtEndPoint().getValuesList().get(3).getValue()).isEqualTo(UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));

    Changes changesDeletion = changesSource.getChangesOfType(ChangeType.DELETION);
    assertThat(changesDeletion.getChangesList()).hasSize(2);
    assertThat(changesDeletion.getChangesList().get(0).getDataName()).isEqualTo("ACTOR");
    assertThat(changesDeletion.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.DELETION);
    assertThat(changesDeletion.getChangesList().get(0).getColumnsNameList()).containsExactly("ID", "NAME", "FIRSTNAME",
      "BIRTH", "ACTOR_IMDB");
    assertThat(changesDeletion.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesDeletion.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(1).getValue()).isEqualTo("Worthington");
    assertThat(changesDeletion.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(2).getValue()).isEqualTo("Sam");
    assertThat(changesDeletion.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(3).getValue()).isEqualTo(Date.valueOf("1976-08-02"));
    assertThat(changesDeletion.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(4).getValue()).isEqualTo(UUID.fromString("d735221b-5de5-4112-aa1e-49090cb75ada"));
    assertThat(changesDeletion.getChangesList().get(0).getRowAtEndPoint()).isNull();
    assertThat(changesDeletion.getChangesList().get(1).getDataName()).isEqualTo("INTERPRETATION");
    assertThat(changesDeletion.getChangesList().get(1).getChangeType()).isEqualTo(ChangeType.DELETION);
    assertThat(changesDeletion.getChangesList().get(1).getColumnsNameList()).containsExactly("ID", "ID_MOVIE", "ID_ACTOR", "CHARACTER");
    assertThat(changesDeletion.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(0).getValue())
      .isEqualTo(new BigDecimal(5));
    assertThat(changesDeletion.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(1).getValue())
      .isEqualTo(new BigDecimal(3));
    assertThat(changesDeletion.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(2).getValue())
      .isEqualTo(new BigDecimal(3));
    assertThat(changesDeletion.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(3).getValue())
      .isEqualTo("Jake Sully");
    assertThat(changesDeletion.getChangesList().get(1).getRowAtEndPoint()).isNull();

    Changes changesRequestCreation = changesRequest.getChangesOfType(ChangeType.CREATION);
    assertThat(changesRequestCreation.getChangesList()).hasSize(1);
    assertThat(changesRequestCreation.getChangesList().get(0).getDataName()).isEqualTo("select interpretation.id, character, movie.title, actor.name  from interpretation, movie, actor  where interpretation.id_movie = movie.id  and interpretation.id_actor = actor.id ");
    assertThat(changesRequestCreation.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.CREATION);
    assertThat(changesRequestCreation.getChangesList().get(0).getColumnsNameList()).containsExactly("ID", "CHARACTER", "TITLE",
      "NAME");
    assertThat(changesRequestCreation.getChangesList().get(0).getRowAtStartPoint()).isNull();
    assertThat(changesRequestCreation.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(6));
    assertThat(changesRequestCreation.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(1).getValue()).isEqualTo("Dr Peter Venkman");
    assertThat(changesRequestCreation.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(2).getValue()).isEqualTo("Ghostbusters");
    assertThat(changesRequestCreation.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(3).getValue()).isEqualTo("Murray");

    Changes changesRequestModification = changesRequest.getChangesOfType(ChangeType.MODIFICATION);
    assertThat(changesRequestModification.getChangesList()).hasSize(1);
    assertThat(changesRequestModification.getChangesList().get(0).getDataName()).isEqualTo("select interpretation.id, character, movie.title, actor.name  from interpretation, movie, actor  where interpretation.id_movie = movie.id  and interpretation.id_actor = actor.id ");
    assertThat(changesRequestModification.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.MODIFICATION);
    assertThat(changesRequestModification.getChangesList().get(0).getColumnsNameList()).containsExactly("ID", "CHARACTER", "TITLE",
      "NAME");
    assertThat(changesRequestModification.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesRequestModification.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(1).getValue()).isEqualTo("Dr Grace Augustine");
    assertThat(changesRequestModification.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(2).getValue()).isEqualTo("Avatar");
    assertThat(changesRequestModification.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(3).getValue()).isEqualTo("Weaver");
    assertThat(changesRequestModification.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesRequestModification.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(1).getValue()).isEqualTo("Doctor Grace Augustine");
    assertThat(changesRequestModification.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(2).getValue()).isEqualTo("The Avatar");
    assertThat(changesRequestModification.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(3).getValue()).isEqualTo("Weaver");

    Changes changesRequestDeletion = changesRequest.getChangesOfType(ChangeType.DELETION);
    assertThat(changesRequestDeletion.getChangesList()).hasSize(1);
    assertThat(changesRequestDeletion.getChangesList().get(0).getDataName()).isEqualTo("select interpretation.id, character, movie.title, actor.name  from interpretation, movie, actor  where interpretation.id_movie = movie.id  and interpretation.id_actor = actor.id ");
    assertThat(changesRequestDeletion.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.DELETION);
    assertThat(changesRequestDeletion.getChangesList().get(0).getColumnsNameList()).containsExactly("ID", "CHARACTER", "TITLE",
      "NAME");
    assertThat(changesRequestDeletion.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(5));
    assertThat(changesRequestDeletion.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(1).getValue()).isEqualTo("Jake Sully");
    assertThat(changesRequestDeletion.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(2).getValue()).isEqualTo("Avatar");
    assertThat(changesRequestDeletion.getChangesList().get(0).getRowAtStartPoint().getValuesList().get(3).getValue()).isEqualTo("Worthington");
    assertThat(changesRequestDeletion.getChangesList().get(0).getRowAtEndPoint()).isNull();
  }

}
