package org.assertj.db.api.assertions;

/**
 * Interface containing assertion method on the number of columns.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnNumberOfColumns<T extends AssertOnNumberOfColumns<T>> {

  /**
   * Verifies that the number of columns is equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that the table has 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).hasNumberOfColumns(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the first row of the table has 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(table).row().hasNumberOfColumns(8);
   * </code></pre>
   * <p>
   * Example where the assertion verifies that the row at end point of the first change has 8 columns :
   * </p>
   *
   * <pre><code class='java'>
   * assertThat(changes).change().rowAtEndPoint().hasNumberOfColumns(8);
   * </code></pre>
   *
   * @param expected The number to compare to the number of columns.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of columns is different to the number in parameter.
   */
  public T hasNumberOfColumns(int expected);
}
