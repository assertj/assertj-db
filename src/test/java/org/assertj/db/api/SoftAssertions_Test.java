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
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.api;

import org.assertj.core.api.SoftAssertionError;
import org.assertj.core.api.ThrowableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Test on the utility class {@code SoftAssertions}.
 *
 * @author Julien Roy
 *
 */
public class SoftAssertions_Test extends AbstractTest {

  /**
   * This method tests the soft assertions of Table.
   */
  @Test
  @NeedReload
  public void test_soft_assert_table() {
    Table table = new Table(source, "test");
    final SoftAssertions softly = new SoftAssertions();
    softly.assertThat(table).as("Show be Zero").column("var1").value().isEqualTo(0);
    softly.assertThat(table).column("var2").value().isFalse();
    softly.assertThat(table).row(0).column("var2").value().isFalse();
    softly.assertThat(table).row().value("var2").isFalse();
    softly.assertThat(table).hasNumberOfRows(0);
    softly.assertThat(table).hasNumberOfColumns(0);
    softly.assertThat(table)
          .row(0).column("var2").value().isFalse()
          .returnToColumn()
          .column("var1").value().isEqualTo(0);

    assertThat(softly.wasSuccess()).isFalse();
    assertThat(softly.errorsCollected()).hasSize(8);

    assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
      @Override public void call() {
        softly.assertAll();
      }
    }).isInstanceOf(SoftAssertionError.class);
  }

  /**
   * This method tests the soft assertions of Request.
   */
  @Test
  @NeedReload
  public void test_soft_assert_request() {
    Request request = new Request(source, "select * from test");
    final SoftAssertions softly = new SoftAssertions();
    softly.assertThat(request).column("var1").value(0).isEqualTo(0);
    softly.assertThat(request).column("var2").value(0).isFalse();
    softly.assertThat(request).row(0).column("var2").value().isFalse();
    softly.assertThat(request).row().value("var2").isFalse();
    softly.assertThat(request).hasNumberOfRows(0);
    softly.assertThat(request).hasNumberOfColumns(0);
    softly.assertThat(request)
          .row(0).column("var2").value().isFalse()
          .returnToColumn()
          .column("var1").value().isEqualTo(0);

    assertThat(softly.wasSuccess()).isFalse();
    assertThat(softly.errorsCollected()).hasSize(8);

    assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
      @Override public void call() {
        softly.assertAll();
      }
    }).isInstanceOf(SoftAssertionError.class);
  }

  /**
   * This method tests the soft assertions of Changes.
   */
  @Test
  @NeedReload
  public void test_soft_assert_changes() {
    Changes changes = new Changes(source);
    changes.setStartPointNow();
    update("update test set var14 = 1 where var1 = 1");
    changes.setEndPointNow();

    final SoftAssertions softly = new SoftAssertions();
    softly.assertThat(changes).change().column("var1").hasValues(0);
    softly.assertThat(changes).change().rowAtStartPoint().changeOfModification().column("var1").hasValues(0);
    softly.assertThat(changes).ofModificationOnTable("test").hasNumberOfChanges(1);

    assertThat(softly.wasSuccess()).isFalse();
    assertThat(softly.errorsCollected()).hasSize(2);

    assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
      @Override public void call() {
        softly.assertAll();
      }
    }).isInstanceOf(SoftAssertionError.class);
  }
}
