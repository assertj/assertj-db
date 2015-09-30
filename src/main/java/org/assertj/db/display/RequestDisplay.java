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
package org.assertj.db.display;

import org.assertj.db.display.impl.RepresentationType;
import org.assertj.db.type.Request;

/**
 * Assertion methods for a {@link org.assertj.db.type.Request}.
 *
 * @author Régis Pouiller
 *
 */
public class RequestDisplay extends AbstractDisplay<Request, RequestDisplay> {

  /**
   * Request on which the display is.
   */
  private Request request;

  /**
   * Constructor.
   *
   * @param request Request on which the display is.
   */
  RequestDisplay(Request request) {
    super(RequestDisplay.class, RepresentationType.PLAIN);
    this.request = request;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getRepresentation(RepresentationType displayType) {
    return displayType.getRequestRepresentation(info, request);
  }
}
