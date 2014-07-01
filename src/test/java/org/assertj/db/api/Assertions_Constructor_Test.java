package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Test on the utility class {@code Assertions} : the private constructor.
 * 
 * @author Régis Pouiller
 * 
 */
public class Assertions_Constructor_Test extends AbstractTest {

  /**
   * This method tests the private constructor of {@code Assertions} for the tests coverage..
   * @throws NoSuchMethodException 
   * @throws SecurityException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws InstantiationException 
   * @throws IllegalArgumentException 
   */
  @Test
  public void test_private_constructor_for_the_tests_coverage() throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
    Constructor<Assertions> constructor = Assertions.class.getDeclaredConstructor();
    assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
    constructor.setAccessible(true);
    constructor.newInstance();
    constructor.setAccessible(false);
  }
}
