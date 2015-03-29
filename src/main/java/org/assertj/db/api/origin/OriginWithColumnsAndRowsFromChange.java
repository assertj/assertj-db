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

import org.assertj.db.api.ChangeColumnAssert;
import org.assertj.db.api.ChangeRowAssert;
import org.assertj.db.api.navigation.ToColumn;
import org.assertj.db.api.navigation.ToColumnFromChange;
import org.assertj.db.api.navigation.ToRowFromChange;

/**
 * Defines a class which is the {@link org.assertj.db.api.origin.Origin} of another
 * and have {@link org.assertj.db.type.Column}s and {@link org.assertj.db.type.Row}s from a {@link org.assertj.db.type.Change}.
 *
 * @author Régis Pouiller
 */
public interface OriginWithColumnsAndRowsFromChange
        extends OriginWithChanges,
                ToColumn<ChangeColumnAssert>,
                ToColumnFromChange<ChangeColumnAssert>,
                ToRowFromChange<ChangeRowAssert> {
}
