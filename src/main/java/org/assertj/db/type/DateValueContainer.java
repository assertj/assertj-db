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
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.type;

/**
 * This interface indicates container of {@link DateValue}.
 * 
 * @author Régis Pouiller
 * 
 */
public interface DateValueContainer {

  /**
   * Returns the date.
   * 
   * @return The date.
   */
  DateValue getDate();

  /**
   * Returns if it is midnight.
   * @return {@code true} if it is midnight.
   */
  boolean isMidnight();

}
