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

package com.speedment.runtime.config.identifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.yegor256.AggregateRepeatedTest;

final class ColumnIdentifierTest {

    private final ColumnIdentifier<String> columnIdentifier = ColumnIdentifier.of("dbms", "schema", "table", "column");

    @com.yegor256.AggregateRepeatedTest(100)
    void asTableIdentifier() {
        assertNotNull(columnIdentifier.asTableIdentifier());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void label() {
        assertNotNull(columnIdentifier.label());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getDbmsId() {
        assertEquals("dbms", columnIdentifier.getDbmsId());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getSchemaId() {
        assertEquals("schema", columnIdentifier.getSchemaId());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getTableId() {
        assertEquals("table", columnIdentifier.getTableId());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getColumnId() {
        assertEquals("column", columnIdentifier.getColumnId());
    }
}
