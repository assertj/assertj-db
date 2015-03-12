package org.assertj.db.api.assertions;

/**
 * Interface that represents a assert on a column name.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnColumnName<T extends AssertOnColumnName<T>> {

  /**
   * Verifies that the name of a column is equal to parameter.
   * <p>
   * Example where the assertion verifies that the column name of the first {@code Column} of the {@code Table} is equal to
   * "title" :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasColumnName("title");
   * </code></pre>
   *
   * @param columnName The expected column name.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column name is not equal to the parameter.
   */
  public T hasColumnName(String columnName);
}
