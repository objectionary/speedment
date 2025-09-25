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
package com.speedment.runtime.core.internal.db;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.yegor256.AggregateRepeatedTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class DbmsTypeDefaultImplTest {

    private final DbmsTypeDefaultImpl instance = new DbmsTypeDefaultImpl();

    @com.yegor256.AggregateRepeatedTest(100)
    void getResultSetTableSchema() {
        assertNotNull(instance.getResultSetTableSchema());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getSchemaTableDelimiter() {
        assertNotNull(instance.getSchemaTableDelimiter());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getInitialQuery() {
        assertNotNull(instance.getInitialQuery());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getDataTypes() {
        assertNotNull(instance.getDataTypes());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getDefaultDbmsName() {
        assertNotNull(instance.getDefaultDbmsName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getColumnHandler() {
        assertNotNull(instance.getColumnHandler());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getSkipLimitSupport() {
        assertNotNull(instance.getSkipLimitSupport());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getSubSelectAlias() {
        assertNotNull(instance.getSubSelectAlias());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getSortByNullOrderInsertion() {
        assertNotNull(instance.getSortByNullOrderInsertion());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void applySkipLimit() {
        assertThrows(IllegalArgumentException.class, () -> instance.applySkipLimit("",
                Collections.emptyList(), -1, -1));
        assertThrows(IllegalArgumentException.class, () -> instance.applySkipLimit("",
                Collections.emptyList(), 1, -1));

        final String sql = "SELECT * FROM table";
        final List<Object> params = new ArrayList<>();

        assertDoesNotThrow(() -> instance.applySkipLimit(sql, params, 1, 1));

        assertEquals(sql, instance.applySkipLimit(sql, params, 0, Long.MAX_VALUE));
    }

}
