package org.assertj.db.api;

/**
 * Interface that represents a assert on column of change.
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnColumnOfChange <T extends AssertOnColumnOfChange<T>> {

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

  /**
   * Verifies that the values at the start point and the end point are equal to the parameter.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * "Ellen Louise Ripley" :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValuesEqualTo("Ellen Louise Ripley");
   * </code></pre>
   *
   * @param expected The expected value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values are not equal to the parameter.
   */
  public T hasValuesEqualTo(Object expected);

  /**
   * Verifies that the values at the start point and the end point are equal to a parameter for start point and a parameter for end point.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * "Sigourney" at start point and "Susan Alexandra" at end point :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValuesEqualTo("Sigourney", "Susan Alexandra");
   * </code></pre>
   *
   * @param expectedAtStartPoint The expected value at start point.
   * @param expectedAtEndPoint   The expected value at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values are not equal to the parameters.
   */
  public T hasValuesEqualTo(Object expectedAtStartPoint, Object expectedAtEndPoint);

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
