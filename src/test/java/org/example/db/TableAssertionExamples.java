package org.example.db;

// tag::user_guide[]
import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.type.AssertDbConnection;
import org.assertj.db.type.AssertDbConnectionFactory;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;

// end::user_guide[]

import org.junit.Test;

public class TableAssertionExamples {
// tag::user_guide[]
  private final AssertDbConnection assertDbConnection = AssertDbConnectionFactory.of("jdbc:h2:mem:test", "sa", "").create();

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
