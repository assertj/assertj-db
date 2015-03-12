package org.assertj.db.api.assertions;

import org.assertj.db.type.ChangeType;

/**
 * Interface that represents a assert on a change.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnChangeType<T extends AssertOnChangeType<T>> {

  /**
   * Verifies that the type of the change is equal to the type in parameter.
   * <p>
   * Example where the assertion verifies that the change is of type {@code CREATION} :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isOfType(ChangeType.CREATION);
   * </code>
   * </pre>
   *
   * @param expected The expected type to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public T isOfType(ChangeType expected);

  /**
   * Verifies that the type of the change is a creation.
   * <p>
   * Example where the assertion verifies that the change is a creation :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isCreation();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public T isCreation();

  /**
   * Verifies that the type of the change is a modification.
   * <p>
   * Example where the assertion verifies that the change is a modification :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isModification();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public T isModification();

  /**
   * Verifies that the type of the change is a deletion.
   * <p>
   * Example where the assertion verifies that the change is a deletion :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isDeletion();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public T isDeletion();
}
