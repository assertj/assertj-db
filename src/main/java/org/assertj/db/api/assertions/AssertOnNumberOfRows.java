package org.assertj.db.api.assertions;

/**
 * Interface containing assertion method on the number of rows.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnNumberOfRows<T extends AssertOnNumberOfRows<T>> {

  /**
   * Verifies that the number of rows is equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that the table has 2 rows :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).hasNumberOfRows(2);
   * </code></pre>
   *
   * @param expected The number to compare to the number of rows.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of rows is different to the number in parameter.
   */
  public T hasNumberOfRows(int expected);
}
