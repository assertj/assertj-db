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
 * Copyright 2015-2021 the original author or authors.
 */
package org.assertj.db.output;

import org.assertj.db.output.impl.Output;
import org.assertj.db.type.Request;

/**
 * Output methods for a {@link org.assertj.db.type.Request}.
 *
 * @author Régis Pouiller
 *
 */
public class RequestOutputter extends
        AbstractDbOutputter<Request, RequestOutputter, RequestColumnOutputter, RequestColumnValueOutputter, RequestRowOutputter, RequestRowValueOutputter> {

  /**
   * Constructor.
   *
   * @param request Request on which the output is.
   */
  RequestOutputter(Request request) {
    super(request, RequestOutputter.class, RequestColumnOutputter.class, RequestRowOutputter.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getOutput(Output outputType) {
    return outputType.getRequestOutput(info, actual);
  }
}
