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

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.yegor256.AggregateRepeatedTest;

final class DefaultDatabaseNamingConventionTest {

    private final DefaultDatabaseNamingConvention instance = new DefaultDatabaseNamingConvention();

    @com.yegor256.AggregateRepeatedTest(100)
    void fullName() {
        assertNotNull(instance.fullNameOf("schema", "table"));
        assertNotNull(instance.fullNameOf("schema", "table", "table"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getSchemaExcludeSet() {
        assertNotNull(instance.getSchemaExcludeSet());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getFieldQuoteStart() {
        assertNotNull(instance.getFieldQuoteStart());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getFieldQuoteEnd() {
        assertNotNull(instance.getFieldQuoteEnd());
    }
}
