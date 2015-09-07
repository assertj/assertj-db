package org.assertj.db.api.assertions.impl.constructor;

import org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeClass;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeClass} class :
 * {@link org.assertj.db.api.assertions.impl.AssertionsOnColumnOfChangeClass#AssertionsOnColumnOfChangeClass()} private constructor.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnColumnOfChangeClass_Constructor_Test {

  /**
   * This method tests the private constructor of {@code Assertions} for the tests coverage..
   * @throws NoSuchMethodException
   * @throws SecurityException
   * @throws java.lang.reflect.InvocationTargetException
   * @throws IllegalAccessException
   * @throws InstantiationException
   * @throws IllegalArgumentException
   */
  @Test
  public void test_private_constructor_for_the_tests_coverage()
          throws SecurityException, NoSuchMethodException, IllegalArgumentException,
          InstantiationException, IllegalAccessException, InvocationTargetException {

    Constructor<AssertionsOnColumnOfChangeClass> constructor = AssertionsOnColumnOfChangeClass.class.getDeclaredConstructor();
    assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
    constructor.setAccessible(true);
    constructor.newInstance();
    constructor.setAccessible(false);
  }
}
