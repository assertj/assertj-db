package org.assertj.db.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.usingDialect;
import static org.assertj.db.util.DialectHelper.getColumnName;

public class DialectHelperTest {
    @Test
    public void should_pass_if_DialectHelper_change_column_name_when_using_H2_dialect() throws Exception {
        usingDialect("H2");

        assertThat(getColumnName("firstName")).isEqualTo("FIRSTNAME");
    }

    @Test
    public void should_pass_if_DialectHelper_change_column_name_when_using_no_dialect() throws Exception {
        assertThat(getColumnName("firstName")).isEqualTo("FIRSTNAME");
    }

    @Test
    public void should_pass_if_DialectHelper_do_not_change_column_name_when_using_any_dialect_other_than_H2()
        throws Exception {
        usingDialect("MySQL");

        assertThat(getColumnName("firstName")).isEqualTo("firstName");
    }
}