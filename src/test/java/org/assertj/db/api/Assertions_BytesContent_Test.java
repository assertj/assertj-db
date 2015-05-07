/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.api;

import org.assertj.db.exception.AssertJDBException;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.bytesContentFromClassPathOf;
import static org.assertj.db.api.Assertions.bytesContentOf;

/**
 * Tests on {@code bytesContentOf} and {@code bytesContentFromClassPathOf} methods of {@code Assertions}.
 * 
 * @author Régis Pouiller
 * 
 */
public class Assertions_BytesContent_Test {

  /**
   * This method should throw a {@code NullPointerException}, because of call to {@code bytesContentOf} with
   * {@code null} in parameter.
   */
  @Test(expected = NullPointerException.class)
  public void should_throw_NullPointerException_if_call_of_bytesContentOf_with_null_in_parameter() {
    bytesContentOf(null);
  }

  /**
   * This method should throw a {@code NullPointerException}, because of call to {@code bytesContentFromClassPathOf}
   * with {@code null} in parameter.
   */
  @Test(expected = NullPointerException.class)
  public void should_throw_NullPointerException_if_call_of_bytesContentFromClassPathOf_with_null_in_parameter() {
    bytesContentFromClassPathOf(null);
  }

  /**
   * This method should throw a {@code NullPointerException}, because of call to {@code bytesContentOf} with a file
   * which don't exist in parameter.
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_if_call_of_bytesContentOf_with_file_which_dont_exist_in_parameter() {
    bytesContentOf(new File("notexist"));
  }

  /**
   * This method should throw a {@code NullPointerException}, because of call to {@code bytesContentFromClassPathOf}
   * with a resource which don't exist in parameter.
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_if_call_of_bytesContentFromClassPathOf_with_resource_which_dont_exist_in_parameter() {
    bytesContentFromClassPathOf("notexist");
  }

  /**
   * This method test that the {@code bytesContentOf} method returns the content of a {@code File}.
   */
  @Test
  public void test_bytesContentOf() {
    byte[] bytes = bytesContentOf(new File("target/test-classes/test.txt"));
    assertThat(bytes).isEqualTo(new byte[] { 'T', 'e', 'x', 't', ' ', 'f', 'o', 'r', ' ', 't', 'e', 's', 't', 's' });
  }

  /**
   * This method test that the {@code bytesContentOf} method returns the content of a resource in the classpath.
   */
  @Test
  public void test_bytesContentFromClassPathOf() {
    byte[] bytes = bytesContentFromClassPathOf("test.txt");
    assertThat(bytes).isEqualTo(new byte[] { 'T', 'e', 'x', 't', ' ', 'f', 'o', 'r', ' ', 't', 'e', 's', 't', 's' });
  }

  /**
   * This method should throw an {@code AssertJDBException} when the {@code InputStream} throw an {@code IOException}
   * during the reading.
   * 
   * @throws Throwable
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_an_AssertJDBException_when_IOException_during_reading_and_closing() throws Throwable {
    InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(new byte[0])) {

      @Override
      public synchronized int read() throws IOException {
        throw new IOException();
      }

      @Override
      public void close() throws IOException {
        throw new IOException();
      }
    };
    
    Class<?> assertionClass = Assertions.class;
    Method method = assertionClass.getDeclaredMethod("read", InputStream.class);
    method.setAccessible(true);
    try {
      method.invoke(null, inputStream);
    } catch (InvocationTargetException e) {
      throw e.getCause();
    }
  }

  /**
   * This method should throw an {@code AssertJDBException} when the {@code InputStream} throw an {@code IOException}
   * during the reading.
   * 
   * @throws Throwable
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_an_AssertJDBException_when_IOException_during_reading() throws Throwable {
    InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(new byte[0])) {

      @Override
      public synchronized int read() throws IOException {
        throw new IOException();
      }
    };
    
    Class<?> assertionClass = Assertions.class;
    Method method = assertionClass.getDeclaredMethod("read", InputStream.class);
    method.setAccessible(true);
    try {
      method.invoke(null, inputStream);
    } catch (InvocationTargetException e) {
      throw e.getCause();
    }
  }
}
