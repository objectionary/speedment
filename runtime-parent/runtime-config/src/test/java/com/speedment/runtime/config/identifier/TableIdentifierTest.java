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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.runtime.config.identifier;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.yegor256.AggregateRepeatedTest;

final class TableIdentifierTest {

    private final TableIdentifier<String> tableIdentifier = TableIdentifier.of("dbms", "schema", "table");

    @com.yegor256.AggregateRepeatedTest(100)
    void asSchemaIdentifier() {
        assertNotNull(tableIdentifier.asSchemaIdentifier());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void asColumnIdentifier() {
        assertNotNull(tableIdentifier.asColumnIdentifier("column"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getDbmsId() {
        assertEquals("dbms", tableIdentifier.getDbmsId());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getSchemaId() {
        assertEquals("schema", tableIdentifier.getSchemaId());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getTableId() {
        assertEquals("table", tableIdentifier.getTableId());
    }

}
