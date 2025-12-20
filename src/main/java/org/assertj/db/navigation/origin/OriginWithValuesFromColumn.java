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
package org.assertj.db.navigation.origin;

import org.assertj.db.navigation.ToValueFromColumn;
import org.assertj.db.navigation.element.ChangeElement;
import org.assertj.db.navigation.element.ChangesElement;
import org.assertj.db.navigation.element.ColumnElement;
import org.assertj.db.navigation.element.RowElement;
import org.assertj.db.navigation.element.ValueElement;

/**
 * Defines a class which is the {@link Origin} of another
 * and have values from a {@link org.assertj.db.type.Column} of a {@link org.assertj.db.type.Change}.
 *
 * @param <CHS> The class of a element of navigation on changes (an sub-class of {@link org.assertj.db.navigation.element.ChangesElement}).
 * @param <CH>  The class of a element of navigation on a change (an sub-class of {@link org.assertj.db.navigation.element.ChangeElement}).
 * @param <C>   The class of a element of navigation on column (an sub-class of {@link org.assertj.db.navigation.element.ColumnElement}).
 * @param <R>   The class of a element of navigation on a row (an sub-class of {@link org.assertj.db.navigation.element.RowElement}).
 * @param <V>   The class of a element of navigation on a value (an sub-class of {@link org.assertj.db.navigation.element.ValueElement}).
 * @author Régis Pouiller
 */
public interface OriginWithValuesFromColumn<CHS extends ChangesElement, CH extends ChangeElement, C extends ColumnElement, R extends RowElement, V extends ValueElement>
  extends OriginWithColumnsAndRowsFromChange<CHS, CH, C, R>,
  ToValueFromColumn<V> {
}
