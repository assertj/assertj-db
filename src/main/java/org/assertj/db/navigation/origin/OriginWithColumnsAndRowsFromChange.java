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
package org.assertj.db.navigation.origin;

import org.assertj.db.navigation.ToColumn;
import org.assertj.db.navigation.ToColumnFromChange;
import org.assertj.db.navigation.ToRowFromChange;
import org.assertj.db.navigation.element.ChangeElement;
import org.assertj.db.navigation.element.ChangesElement;
import org.assertj.db.navigation.element.ColumnElement;
import org.assertj.db.navigation.element.RowElement;

/**
 * Defines a class which is the {@link Origin} of another
 * and have {@link org.assertj.db.type.Column}s and {@link org.assertj.db.type.Row}s from a {@link org.assertj.db.type.Change}.
 *
 * @author Régis Pouiller
 *
 * @param <CHS> The class of a element of navigation on changes (an sub-class of {@link org.assertj.db.navigation.element.ChangesElement}).
 * @param <CH> The class of a element of navigation on a change (an sub-class of {@link org.assertj.db.navigation.element.ChangeElement}).
 * @param <C> The class of a element of navigation on column (an sub-class of {@link org.assertj.db.navigation.element.ColumnElement}).
 * @param <R> The class of a element of navigation on a row (an sub-class of {@link org.assertj.db.navigation.element.RowElement}).
 */
public interface OriginWithColumnsAndRowsFromChange<CHS extends ChangesElement, CH extends ChangeElement, C extends ColumnElement, R extends RowElement>
        extends OriginWithChanges<CHS, CH>,
                ToColumn<C>,
                ToColumnFromChange<C>,
                ToRowFromChange<R> {
}
