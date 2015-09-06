package org.assertj.db.api.assertions;

/**
 * Defines the assertion method on the class of a value.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 * @since 1.1.0
 */
public interface AssertOnValueClass<T extends AssertOnValueClass<T>> {

  /**
   * Verifies that the class of the value is equal to the class in parameter.
   * <p>
   * Example where the assertion verifies that the value in the {@code Column} called "title" of the second {@code Row}
   * of the {@code Table} is of class {@code String} :
   * </p>
   * <p/>
   * <pre>
   * <code class='java'>
   * assertThat(table).row(1).value(&quot;title&quot;).isOfClass(String.class);
   * </code>
   * </pre>
   * <p>
   * Example where the assertion verifies that the value in the {@code Column} called "title" of the {@code Row} at end point
   * of the first {@code Change} is of class {@code String} :
   * </p>
   * <p/>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change().rowAtEndPoint().value(&quot;title&quot;).isOfClass(String.class);
   * </code>
   * </pre>
   *
   * @param expected The expected class to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the class of the value is different to the class in parameter.
   * @see org.assertj.db.api.AbstractValueAssert#isOfClass(Class)
   * @see org.assertj.db.api.AbstractAssertWithValues#isOfClass(Class)
   * @since 1.1.0
   */
  public T isOfClass(Class expected);
}
