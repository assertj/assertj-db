package org.assertj.db.api.assertions;

/**
 * Interface that represents a assert on a primary key.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnPrimaryKey<T extends AssertOnPrimaryKey<T>> {

  /**
   * Verifies that primary of the rows of the change is the same as the parameters.
   * <p>
   * Example where the assertion verifies that primary key is the column called id :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).hasPksNames("id");
   * </code>
   * </pre>
   *
   * @param names The names of the primary key associated with the rows of the change.
   * @return {@code this} assertion object.
   * @throws AssertionError                 If the type is different to the type in parameter.
   * @throws java.lang.NullPointerException If one of the names in parameters is {@code null}.
   */
  public T hasPksNames(String... names);

  /**
   * Verifies that the values primary of the rows of the change are the same as the parameters.
   * <p>
   * Example where the assertion verifies that primary key have the value 1 :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).hasPksValues(1);
   * </code>
   * </pre>
   *
   * @param values The values of the primary key associated with the rows of the change.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public T hasPksValues(Object... values);
}
