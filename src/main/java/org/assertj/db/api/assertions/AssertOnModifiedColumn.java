package org.assertj.db.api.assertions;

/**
 * Interface that represents a assert on a modified column.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnModifiedColumn<T extends AssertOnModifiedColumn<T>> {

  /**
   * Verifies that the column is modified between the start point and the end point.
   * <p>
   * Example where the assertion verifies that :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).column().isModified();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public T isModified();

  /**
   * Verifies that the column is not modified between the start point and the end point.
   * <p>
   * Example where the assertion verifies that :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).column().isNotModified();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public T isNotModified();
}
