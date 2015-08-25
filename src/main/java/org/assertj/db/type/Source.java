/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p>
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.type;

/**
 * A source to indicates the informations to connect to the database. It contains the url, user and password to the
 * database. A source is used by a {@link Table} or a {@link Request}.
 * <p>Example of instantiation :</p>
 * <pre><code class='java'>
 * Source source = new Source("jdbc:h2:mem:test", "sa", "");
 * </code></pre>
 * <p>That creates a source to a H2 database in memory.</p>
 *
 * @author Régis Pouiller
 *
 */
public class Source {

    /**
     * URL to the database.
     */
    private final String url;
    /**
     * User to connect.
     */
    private final String user;
    /**
     * Password to connect.
     */
    private final String password;

    /**
     * Constructor with the informations.
     *
     * @param url URL to the database
     * @param user User to connect
     * @param password Password to connect
     */
    public Source(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /**
     * Returns the URL to the database.
     *
     * @return The URL to the database
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the user to connect.
     *
     * @return The user to connect
     */
    public String getUser() {
        return user;
    }

    /**
     * Returns the password to connect.
     *
     * @return The password to connect
     */
    public String getPassword() {
        return password;
    }
}
