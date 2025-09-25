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
package com.speedment.common.codegen.model.trait;

import com.speedment.common.codegen.model.Field;
import org.junit.jupiter.api.BeforeEach;
import com.yegor256.AggregateRepeatedTest;

import static com.speedment.common.codegen.constant.DefaultAnnotationUsage.DEPRECATED;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class HasAnnotationUsageTest {

    private HasAnnotationUsage<Field> field;

    @BeforeEach
    void setup() {
        field = Field.of("x", int.class);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void add() {
        field.add(DEPRECATED);
        assertEquals(singletonList(DEPRECATED), field.getAnnotations());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void annotate() {
        field.annotate(DEPRECATED);
        assertEquals(singletonList(DEPRECATED), field.getAnnotations());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testAnnotate() {
        field.annotate(Deprecated.class);
        assertEquals(1, field.getAnnotations().size());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testAnnotate1() {
        field.annotate(Deprecated.class, "Never used");
        assertEquals(1, field.getAnnotations().size());

    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getAnnotations() {
        assertTrue(field.getAnnotations().isEmpty());
    }
}