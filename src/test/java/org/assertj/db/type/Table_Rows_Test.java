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
import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on the {@code Row} of {@code Table}.
 * <p>
 * These tests are on the values in the {@code Row} got from a {@code Table}.
 * </p>
 *
 * @author Régis Pouiller
 *
 */
public class Table_Rows_Test extends AbstractTest {

    /**
     * This method test the rows got from a {@code Source}.
     */
    @Test
    public void test_rows_with_source_set() {
        Table table = new Table(source, "movie");

        assertThat(table.getRowsList()).as("Values of MOVIE table").hasSize(3);

        assertThat(table.getRow(0).getColumnsNameList()).containsExactly("ID", "TITLE", "YEAR", "MOVIE_IMDB");
        assertThat(table.getRow(0).getColumnValue(0)).isEqualTo(new BigDecimal(1));
        assertThat(table.getRow(0).getColumnValue("title")).isEqualTo("Alien");

        assertThat(table.getRow(0).getValuesList()).as("Row 1 of MOVIE table")
                                                   .containsExactly(new BigDecimal(1), "Alien", new BigDecimal(1979),
                                                                    UUID.fromString(
                                                                        "30B443AE-C0C9-4790-9BEC-CE1380808435"));
        assertThat(table.getRow(1).getValuesList()).as("Row 2 of MOVIE table")
                                                   .containsExactly(new BigDecimal(2), "The Village",
                                                                    new BigDecimal(2004), UUID.fromString(
                                                       "16319617-AE95-4087-9264-D3D21BF611B6"));
        assertThat(table.getRow(2).getValuesList()).as("Row 3 of MOVIE table")
                                                   .containsExactly(new BigDecimal(3), "Avatar", new BigDecimal(2009),
                                                                    UUID.fromString(
                                                                        "D735221B-5DE5-4112-AA1E-49090CB75ADA"));
    }

    /**
     * This method test the rows got from a {@code DataSource}.
     */
    @Test
    public void test_rows_with_datasource_set() {
        Table table = new Table(dataSource, "movie");

        assertThat(table.getRowsList()).as("Values of MOVIE table").hasSize(3);

        assertThat(table.getRow(0).getValuesList()).as("Row 1 of MOVIE table")
                                                   .containsExactly(new BigDecimal(1), "Alien", new BigDecimal(1979),
                                                                    UUID.fromString(
                                                                        "30B443AE-C0C9-4790-9BEC-CE1380808435"));
        assertThat(table.getRow(1).getValuesList()).as("Row 2 of MOVIE table")
                                                   .containsExactly(new BigDecimal(2), "The Village",
                                                                    new BigDecimal(2004), UUID.fromString(
                                                       "16319617-AE95-4087-9264-D3D21BF611B6"));
        assertThat(table.getRow(2).getValuesList()).as("Row 3 of MOVIE table")
                                                   .containsExactly(new BigDecimal(3), "Avatar", new BigDecimal(2009),
                                                                    UUID.fromString(
                                                                        "D735221B-5DE5-4112-AA1E-49090CB75ADA"));
    }

    /**
     * This method should throw a {@code NullPointerException} because of calling {@code getColumnValue} with null in parameter.
     */
    @Test(expected = NullPointerException.class)
    public void should_throw_NullPointerException_because_column_name_parameter_is_null_when_calling_getColumnValue() {
        Table table = new Table(source, "movie");
        table.getRow(0).getColumnValue(null);
    }

    /**
     * This method test that the call to {@code getColumnValue} return null when the column name in parameter don't exist.
     */
    @Test
    public void test_that_we_get_null_when_calling_getColumnValue_and_the_column_name_dont_exist() {
        Table table = new Table(source, "movie");
        assertThat(table.getRow(0).getColumnValue("not_exist")).isNull();
    }

}
