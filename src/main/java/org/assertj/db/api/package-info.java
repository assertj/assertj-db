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
/**
 * This package contains the API of assertj-db (and especially the entry point class {@link org.assertj.db.api.Assertions}).
 * <p>This class contains three entry point methods :</p>
 * <ul>
 *   <li>{@link org.assertj.db.api.Assertions#assertThat(org.assertj.db.type.Table)}</li>
 *   <li>{@link org.assertj.db.api.Assertions#assertThat(org.assertj.db.type.Request)}</li>
 *   <li>{@link org.assertj.db.api.Assertions#assertThat(org.assertj.db.type.Changes)}</li>
 * </ul>
 * <p>This diagram shows the part on the tables and requests :</p>
 * <p>
 * <img src="https://raw.githubusercontent.com/joel-costigliola/assertj-db/master/doc/table_and_request/diagramOfPartOnTableAndRequest.png" alt="diagram with navigation on table and request" height="80%" width="80%" >
 * </p>
 * <p>This diagram shows the part on the changes :</p>
 * <p>
 * <img src="https://raw.githubusercontent.com/joel-costigliola/assertj-db/master/doc/changes/diagramOfPartOnChanges.png" alt="diagram with navigation on changes" height="80%" width="80%" >
 * </p>
 *
 * @author Régis Pouiller
 */
package org.assertj.db.api;