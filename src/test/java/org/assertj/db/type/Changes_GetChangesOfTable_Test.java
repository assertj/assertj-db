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

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on the {@code getChangesOfTable} method.
 * 
 * @author Régis Pouiller.
 * 
 */
public class Changes_GetChangesOfTable_Test extends AbstractTest {

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

    Changes changesMovie = changesSource.getChangesOfTable("movie");

    assertThat(changesMovie.getChangesList()).hasSize(2);
    assertThat(changesMovie.getChangesList().get(0).getDataName()).isEqualTo("MOVIE");
    assertThat(changesMovie.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.CREATION);
    assertThat(changesMovie.getChangesList().get(0).getColumnsNameList()).containsExactly("ID", "TITLE", "YEAR", "MOVIE_IMDB");
    assertThat(changesMovie.getChangesList().get(0).getRowAtStartPoint()).isNull();
    assertThat(changesMovie.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(4));
    assertThat(changesMovie.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(1).getValue()).isEqualTo("Ghostbusters");
    assertThat(changesMovie.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(1984));
    assertThat(changesMovie.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(3).getValue()).isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
    assertThat(changesMovie.getChangesList().get(1).getDataName()).isEqualTo("MOVIE");
    assertThat(changesMovie.getChangesList().get(1).getChangeType()).isEqualTo(ChangeType.MODIFICATION);
    assertThat(changesMovie.getChangesList().get(1).getColumnsNameList()).containsExactly("ID", "TITLE", "YEAR", "MOVIE_IMDB");
    assertThat(changesMovie.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesMovie.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(1).getValue()).isEqualTo("Avatar");
    assertThat(changesMovie.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(2009));
    assertThat(changesMovie.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(3).getValue()).isEqualTo(UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));
    assertThat(changesMovie.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesMovie.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(1).getValue()).isEqualTo("The Avatar");
    assertThat(changesMovie.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(2009));
    assertThat(changesMovie.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(3).getValue()).isEqualTo(UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));

    Changes changesActor = changesSource.getChangesOfTable("actor");
    assertThat(changesActor.getChangesList()).hasSize(3);
    assertThat(changesActor.getChangesList().get(0).getDataName()).isEqualTo("ACTOR");
    assertThat(changesActor.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.CREATION);
    assertThat(changesActor.getChangesList().get(0).getColumnsNameList()).containsExactly("ID", "NAME", "FIRSTNAME",
            "BIRTH", "ACTOR_IMDB");
    assertThat(changesActor.getChangesList().get(0).getRowAtStartPoint()).isNull();
    assertThat(changesActor.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(4));
    assertThat(changesActor.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(1).getValue()).isEqualTo("Murray");
    assertThat(changesActor.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(2).getValue()).isEqualTo("Bill");
    assertThat(changesActor.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(3).getValue()).isEqualTo(Date.valueOf("1950-09-21"));
    assertThat(changesActor.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(4).getValue()).isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
    assertThat(changesActor.getChangesList().get(1).getDataName()).isEqualTo("ACTOR");
    assertThat(changesActor.getChangesList().get(1).getChangeType()).isEqualTo(ChangeType.MODIFICATION);
    assertThat(changesActor.getChangesList().get(1).getColumnsNameList()).containsExactly("ID", "NAME", "FIRSTNAME",
            "BIRTH", "ACTOR_IMDB");
    assertThat(changesActor.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(1));
    assertThat(changesActor.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(1).getValue()).isEqualTo("Weaver");
    assertThat(changesActor.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(2).getValue()).isEqualTo("Sigourney");
    assertThat(changesActor.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(3).getValue()).isEqualTo(Date.valueOf("1949-10-08"));
    assertThat(changesActor.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(4).getValue()).isEqualTo(UUID.fromString("30b443ae-c0c9-4790-9bec-ce1380808435"));
    assertThat(changesActor.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(1));
    assertThat(changesActor.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(1).getValue()).isEqualTo("Weaver");
    assertThat(changesActor.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(2).getValue()).isEqualTo("Susan Alexandra");
    assertThat(changesActor.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(3).getValue()).isEqualTo(Date.valueOf("1949-10-08"));
    assertThat(changesActor.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(4).getValue()).isEqualTo(UUID.fromString("30b443ae-c0c9-4790-9bec-ce1380808435"));
    assertThat(changesActor.getChangesList().get(2).getDataName()).isEqualTo("ACTOR");
    assertThat(changesActor.getChangesList().get(2).getChangeType()).isEqualTo(ChangeType.DELETION);
    assertThat(changesActor.getChangesList().get(2).getColumnsNameList()).containsExactly("ID", "NAME", "FIRSTNAME",
            "BIRTH", "ACTOR_IMDB");
    assertThat(changesActor.getChangesList().get(2).getRowAtStartPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesActor.getChangesList().get(2).getRowAtStartPoint().getValuesList().get(1).getValue()).isEqualTo("Worthington");
    assertThat(changesActor.getChangesList().get(2).getRowAtStartPoint().getValuesList().get(2).getValue()).isEqualTo("Sam");
    assertThat(changesActor.getChangesList().get(2).getRowAtStartPoint().getValuesList().get(3).getValue()).isEqualTo(Date.valueOf("1976-08-02"));
    assertThat(changesActor.getChangesList().get(2).getRowAtStartPoint().getValuesList().get(4).getValue()).isEqualTo(UUID.fromString("d735221b-5de5-4112-aa1e-49090cb75ada"));
    assertThat(changesActor.getChangesList().get(2).getRowAtEndPoint()).isNull();

     Changes changesInterpretation = changesSource.getChangesOfTable("interpretation");
     assertThat(changesInterpretation.getChangesList()).hasSize(3);
     assertThat(changesInterpretation.getChangesList().get(0).getDataName()).isEqualTo("INTERPRETATION");
     assertThat(changesInterpretation.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.CREATION);
     assertThat(changesInterpretation.getChangesList().get(0).getColumnsNameList()).containsExactly("ID", "ID_MOVIE", "ID_ACTOR", "CHARACTER");
     assertThat(changesInterpretation.getChangesList().get(0).getRowAtStartPoint()).isNull();
     assertThat(changesInterpretation.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(6));
    assertThat(changesInterpretation.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(1).getValue()).isEqualTo(new BigDecimal(4));
    assertThat(changesInterpretation.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(4));
    assertThat(changesInterpretation.getChangesList().get(0).getRowAtEndPoint().getValuesList().get(3).getValue()).isEqualTo("Dr Peter Venkman");
     assertThat(changesInterpretation.getChangesList().get(1).getDataName()).isEqualTo("INTERPRETATION");
     assertThat(changesInterpretation.getChangesList().get(1).getChangeType()).isEqualTo(ChangeType.MODIFICATION);
     assertThat(changesInterpretation.getChangesList().get(1).getColumnsNameList()).containsExactly("ID", "ID_MOVIE", "ID_ACTOR", "CHARACTER");
     assertThat(changesInterpretation.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesInterpretation.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(1).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesInterpretation.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(1));
    assertThat(changesInterpretation.getChangesList().get(1).getRowAtStartPoint().getValuesList().get(3).getValue()).isEqualTo("Dr Grace Augustine");
     assertThat(changesInterpretation.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesInterpretation.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(1).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesInterpretation.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(1));
    assertThat(changesInterpretation.getChangesList().get(1).getRowAtEndPoint().getValuesList().get(3).getValue()).isEqualTo("Doctor Grace Augustine");
     assertThat(changesInterpretation.getChangesList().get(2).getDataName()).isEqualTo("INTERPRETATION");
     assertThat(changesInterpretation.getChangesList().get(2).getChangeType()).isEqualTo(ChangeType.DELETION);
     assertThat(changesInterpretation.getChangesList().get(2).getColumnsNameList()).containsExactly("ID", "ID_MOVIE", "ID_ACTOR", "CHARACTER");
     assertThat(changesInterpretation.getChangesList().get(2).getRowAtStartPoint().getValuesList().get(0).getValue()).isEqualTo(new BigDecimal(5));
    assertThat(changesInterpretation.getChangesList().get(2).getRowAtStartPoint().getValuesList().get(1).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesInterpretation.getChangesList().get(2).getRowAtStartPoint().getValuesList().get(2).getValue()).isEqualTo(new BigDecimal(3));
    assertThat(changesInterpretation.getChangesList().get(2).getRowAtStartPoint().getValuesList().get(3).getValue()).isEqualTo("Jake Sully");
     assertThat(changesInterpretation.getChangesList().get(2).getRowAtEndPoint()).isNull();

    assertThat(changesRequest.getChangesOfTable("movie").getChangesList()).hasSize(0);
  }

}
