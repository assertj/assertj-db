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
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.api.origin;

import org.assertj.db.api.*;
import org.assertj.db.api.navigation.WithColumnsAndRows;
import org.assertj.db.type.AbstractDbData;

/**
 * Interface that represents a assert which is the origin assert of another assert and have columns and rows.
 *
 * @author Régis Pouiller
 *
 * @param <D> The class of the actual value (an sub-class of {@link org.assertj.db.type.AbstractDbData}).
 * @param <A> The class of the original assert (an sub-class of {@link org.assertj.db.api.AbstractDbAssert}).
 * @param <C> The class of this assert (an sub-class of {@link org.assertj.db.api.AbstractColumnAssert}).
 * @param <CV> The class of this assertion on the value (an sub-class of {@link org.assertj.db.api.AbstractColumnValueAssert}).
 * @param <R> The class of the equivalent row assert (an sub-class of {@link org.assertj.db.api.AbstractRowAssert}).
 * @param <RV> The class of the equivalent row assertion on the value (an sub-class of {@link org.assertj.db.api.AbstractRowValueAssert}).
 */
public interface OriginWithColumnsAndRows<D extends AbstractDbData<D>, A extends AbstractDbAssert<D, A, C, CV, R, RV>, C extends AbstractColumnAssert<D, A, C, CV, R, RV>, CV extends AbstractColumnValueAssert<D, A, C, CV, R, RV>, R extends AbstractRowAssert<D, A, C, CV, R, RV>, RV extends AbstractRowValueAssert<D, A, C, CV, R, RV>>
  extends WithColumnsAndRows<D, A, C, CV, R, RV>, Origin {
}
