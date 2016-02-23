package org.assertj.db.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.usingDialect;
import static org.assertj.db.util.DialectHelper.ASSERTJ_DB_DIALECT_PROPERTY_NAME;

public class AssertionsTest {

    @Test
    public void should_pass_if_setting_property_works() throws Exception {
        String dialect = "H2";
        assertThat(usingDialect(dialect)).isNotEmpty();

        assertThat(System.getProperty(ASSERTJ_DB_DIALECT_PROPERTY_NAME)).isEqualTo(dialect);
    }
}
