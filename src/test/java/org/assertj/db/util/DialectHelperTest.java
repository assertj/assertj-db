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