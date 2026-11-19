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
package example.db;

// tag::user_guide[]
// From v3.0.2
import static org.assertj.core.api.Assertions.assertThat;

// For versions <= 3.0.1 :
// import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.type.AssertDbConnection;
import org.assertj.db.type.AssertDbConnectionFactory;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;

// end::user_guide[]

import org.junit.Test;

public class TableAssertionExamples {
// tag::user_guide[]
private AssertDbConnection assertDbConnection = AssertDbConnectionFactory.of("jdbc:h2:mem:test", "sa", "").create();

// end::user_guide[]
    /**
     * This example shows a simple case of test.
     */
    @Test
    public void basic_table_assertion_examples() {
// tag::user_guide[]
Table table = assertDbConnection.table("members").build();

// Check column "name" values
assertThat(table).column("name")
        .value().isEqualTo("Hewson")
        .value().isEqualTo("Evans")
        .value().isEqualTo("Clayton")
        .value().isEqualTo("Mullen");

// Check row at index 1 (the second row) values
assertThat(table).row(1)
        .value().isEqualTo(2)
        .value().isEqualTo("Evans")
        .value().isEqualTo("David Howell")
        .value().isEqualTo("The Edge")
        .value().isEqualTo(DateValue.of(1961, 8, 8))
        .value().isEqualTo(1.77);
// end::user_guide[]
    }
}
