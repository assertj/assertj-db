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
/**
 * This package contains interfaces that defines the different {@link org.assertj.db.api.origin.Origin}s used by the navigation.
 * <p>These {@link org.assertj.db.api.origin.Origin}s are the key to help the navigation.<br>
 * The {@link org.assertj.db.api.origin.Origin} is used by the {@link org.assertj.db.api.AbstractAssertWithOrigin#returnToOrigin()} method
 * to return to the previous instance that provides assertion methods :
 * for example to return to a object with assertion methods on a table ({@link org.assertj.db.api.TableAssert})
 * from a object with assertion methods on a column ({@link org.assertj.db.api.TableColumnAssert}).<br>
 * As shown in the javadoc of the navigation package, it is possible to navigate to a "smaller" thing (from a column to a value)
 * but also to a "bigger or equivalent" thing (from a column to another column or to a row).
 * The second possibility is possible thank to the {@link org.assertj.db.api.origin.Origin} because the classes with {@link org.assertj.db.api.origin.Origin}
 * use the navigation methods of their {@link org.assertj.db.api.origin.Origin}s.
 * </p>
 * <p>This diagram shows the part on the tables and requests (the origin interfaces are in blue) :</p>
 * <p>
 * <img src="https://raw.githubusercontent.com/joel-costigliola/assertj-db/master/doc/table_and_request/navigation/diagramOfPartOnTableAndRequestWithNavigation.png" alt="diagram with navigation on table and request" height="80%" width="80%" >
 * </p>
 * <p>This diagram shows the part on the changes (the origin interfaces are in blue) :</p>
 * <p>
 * <img src="https://raw.githubusercontent.com/joel-costigliola/assertj-db/master/doc/changes/navigation/diagramOfPartOnChangesWithNavigation.png" alt="diagram with navigation on changes" height="80%" width="80%" >
 * </p>
 *
 * @author Régis Pouiller
 */
package org.assertj.db.api.origin;