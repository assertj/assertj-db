/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p>
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.type;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on the {@code getChangesOfTable} and {@code getChangesOfType} methods together.
 *
 * @author Régis Pouiller.
 *
 */
public class Changes_GetChanges_Test extends AbstractTest {

    /**
     * This method test when getting changes of table and type.
     */
    @Test
    @NeedReload
    public void test_getChangesOfTableAndType() {
        Changes changesSource = new Changes(source);

        changesSource.setStartPointNow();
        updateChangesForTests();
        changesSource.setEndPointNow();

        assertThat(changesSource.getChangesList()).hasSize(8);

        Changes changesMovieCreation = changesSource.getChangesOfTable("movie").getChangesOfType(ChangeType.CREATION);

        assertThat(changesMovieCreation.getChangesList()).hasSize(1);
        assertThat(changesMovieCreation.getChangesList().get(0).getDataName()).isEqualTo("MOVIE");
        assertThat(changesMovieCreation.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.CREATION);
        assertThat(changesMovieCreation.getChangesList().get(0).getColumnsNameList())
            .containsExactly("ID", "TITLE", "YEAR", "MOVIE_IMDB");
        assertThat(changesMovieCreation.getChangesList().get(0).getRowAtStartPoint()).isNull();
        assertThat(changesMovieCreation.getChangesList().get(0).getRowAtEndPoint().getValuesList()).containsExactly(
            new BigDecimal(4), "Ghostbusters", new BigDecimal(1984),
            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
    }

    /**
     * This method test when getting changes of type and table.
     */
    @Test
    @NeedReload
    public void test_getChangesOfTypeAndTable() {
        Changes changesSource = new Changes(source);

        changesSource.setStartPointNow();
        updateChangesForTests();
        changesSource.setEndPointNow();

        assertThat(changesSource.getChangesList()).hasSize(8);

        Changes changesCreationMovie = changesSource.getChangesOfType(ChangeType.CREATION).getChangesOfTable("movie");

        assertThat(changesCreationMovie.getChangesList()).hasSize(1);
        assertThat(changesCreationMovie.getChangesList().get(0).getDataName()).isEqualTo("MOVIE");
        assertThat(changesCreationMovie.getChangesList().get(0).getChangeType()).isEqualTo(ChangeType.CREATION);
        assertThat(changesCreationMovie.getChangesList().get(0).getColumnsNameList())
            .containsExactly("ID", "TITLE", "YEAR", "MOVIE_IMDB");
        assertThat(changesCreationMovie.getChangesList().get(0).getRowAtStartPoint()).isNull();
        assertThat(changesCreationMovie.getChangesList().get(0).getRowAtEndPoint().getValuesList()).containsExactly(
            new BigDecimal(4), "Ghostbusters", new BigDecimal(1984),
            UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"));
    }
}
