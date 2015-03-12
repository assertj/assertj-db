package org.assertj.db.api.assertions;

/**
 * Interface that represents a assert on the nullity of a column.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnColumnNullity<T extends AssertOnColumnNullity<T>> {

  /**
   * Verifies that all the values of the column are {@code null}.
   * <p>
   * Example where the assertion verifies that all the values in the first {@code Column} of the {@code Table} are
   * {@code null} :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasOnlyNullValues();
   * </code></pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If at least one of the values of the column are not {@code null}.
   */
  public T hasOnlyNullValues();

  /**
   * Verifies that all the values of the column are not {@code null}.
   * <p>
   * Example where the assertion verifies that all the values in the first {@code Column} of the {@code Table} are not
   * {@code null} :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).column().hasOnlyNotNullValues();
   * </code></pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If at least one of the values of the column are {@code null}.
   */
  public T hasOnlyNotNullValues();
}
