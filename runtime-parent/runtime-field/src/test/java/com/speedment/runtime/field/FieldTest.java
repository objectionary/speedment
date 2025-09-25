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
package com.speedment.runtime.field;

import com.yegor256.AggregateRepeatedTest;

import static com.speedment.runtime.field.TestEntity.ID;
import static com.speedment.runtime.field.TestEntity.NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author pemi
 */
final class FieldTest extends BaseFieldTest {

    @com.yegor256.AggregateRepeatedTest(100)
    void testField() {
        assertEquals("id", ID.identifier().getColumnId());
        assertEquals("name", NAME.identifier().getColumnId());
    }

}
