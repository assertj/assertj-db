package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

import java.lang.reflect.Field;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the {@code getRow} methods for assertion on {@code Table}.
 * 
 * @author Régis Pouiller
 * 
 */
public class TableAssert_GetRow_Test extends AbstractTest {

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code index} parameter is less than the
   * minimum.
   * <p>
   * Message :
   * </p>
   * 
   * <pre>
   * Index -1 out of the limits [0, 3[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_index_is_less_than_the_minimum() {
    Table table = new Table(source, "movie");
    assertThat(table).getRow(-1);
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code index} parameter is more than the
   * maximum.
   * <p>
   * Message :
   * </p>
   * 
   * <pre>
   * Index 3 out of the limits [0, 3[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_index_is_more_than_the_maximum() {
    Table table = new Table(source, "movie");
    assertThat(table).getRow(3);
  }

  /**
   * This method tests the {@code getRow} method when using with {@code index} parameter.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_when_get_rows_with_parameter() throws IllegalArgumentException, IllegalAccessException,
      SecurityException, NoSuchFieldException {

    Table table = new Table(source, "movie");
    TableAssert assertion = assertThat(table);

    Field field = AbstractDbAssert.class.getDeclaredField("indexNextRow");
    field.setAccessible(true);

    assertThat(field.getInt(assertion)).isEqualTo(0);
    assertThat(assertion.getRow(2)).isSameAs(table.getRow(2));
    assertThat(field.getInt(assertion)).isEqualTo(3);
    assertThat(assertion.getRow(1)).isSameAs(table.getRow(1));
    assertThat(field.getInt(assertion)).isEqualTo(2);
    assertThat(assertion.getRow(0)).isSameAs(table.getRow(0));
    assertThat(field.getInt(assertion)).isEqualTo(1);
  }
}
