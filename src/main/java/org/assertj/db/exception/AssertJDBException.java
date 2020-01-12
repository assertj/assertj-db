/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.exception;

/**
 * Exception during the assertion (for example : when getting the data in the database, or accessing to file system).
 * 
 * @author Régis Pouiller
 * 
 */
public class AssertJDBException extends RuntimeException {

  /**
   * Serial version UID of the class.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor of the Exception.
   * 
   * @param exception Exception inside this one.
   */
  public AssertJDBException(Exception exception) {
    super(exception);
  }

  /**
   * Constructor of the Exception.
   * 
   * @param message Message of the exception
   * @param objects Parameters of the message
   */
  public AssertJDBException(String message, Object... objects) {
    super(String.format(message, objects));
  }
}
