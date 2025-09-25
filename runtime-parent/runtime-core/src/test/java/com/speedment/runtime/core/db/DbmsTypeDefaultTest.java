/*
 *
 * Copyright (c) 2006-2020, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.speedment.runtime.core.db;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.yegor256.AggregateRepeatedTest;

final class DbmsTypeDefaultTest {

    private static final DbmsTypeDefault DBMS_TYPE_DEFAULT = DbmsTypeDefault.create();

    @com.yegor256.AggregateRepeatedTest(100)
    void getDefaultSchema() {
        assertNotNull(DBMS_TYPE_DEFAULT.getDefaultSchemaName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void hasSchemaNames() {
        assertTrue(DBMS_TYPE_DEFAULT.hasSchemaNames());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void hasDatabaseNames() {
        assertTrue(DBMS_TYPE_DEFAULT.hasDatabaseNames());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void hasDatabaseUsers() {
        assertTrue(DBMS_TYPE_DEFAULT.hasDatabaseUsers());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void hasServerNames() {
        assertFalse(DBMS_TYPE_DEFAULT.hasServerNames());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getConnectionType() {
        assertNotNull(DBMS_TYPE_DEFAULT.getConnectionType());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getDefaultServerName() {
        assertNotNull(DBMS_TYPE_DEFAULT.getDefaultServerName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getCollateFragment() {
        assertNotNull(DBMS_TYPE_DEFAULT.getCollateFragment());
    }
}
