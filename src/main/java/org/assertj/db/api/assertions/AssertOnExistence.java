package org.assertj.db.api.assertions;

/**
 * Defines the assertion method on existence of something.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Avinash Ananth Narayan R
 */
public interface AssertOnExistence<T extends AssertOnExistence<T>> {

  /**
   * Verifies that the thing represented exist.
   *
   * @return {@code this} assertion object.
   */
  T exists();

  /**
   * Verifies that the thing represented doesn't exist.
   *
   * @return {@code this} assertion object.
   */
  T doesNotExist();
}
