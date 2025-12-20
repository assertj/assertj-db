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
 * Copyright 2015-2025 the original author or authors.
 */
package org.assertj.db.navigation.element;

/**
 * Interface that represents a element of navigation about row.
 * <p>It can be a row of a table, of the result of a request or a change.</p>
 * <p>This interface is used by the interfaces {@link org.assertj.db.navigation.ToRow}
 * and {@link org.assertj.db.navigation.ToRowFromChange}.</p>
 *
 * @author Régis Pouiller
 */
public interface RowElement extends Element {
}
