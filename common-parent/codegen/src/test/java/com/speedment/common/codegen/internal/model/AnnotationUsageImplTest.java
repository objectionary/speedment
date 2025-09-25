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
package com.speedment.common.codegen.internal.model;

import com.speedment.common.codegen.model.AnnotationUsage;
import com.speedment.common.codegen.model.Value;
import com.yegor256.AggregateRepeatedTest;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class AnnotationUsageImplTest extends AbstractTest<AnnotationUsage> {

    private static final Type TYPE = Integer.class;

    public AnnotationUsageImplTest() {
        super(() -> new AnnotationUsageImpl(TYPE),
                au -> au.set(Long.class),
                au -> au.set( 1),
                au -> au.put("a", 1)
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void copyExtra() {
        instance().put("A", Value.ofNumber(1));
        final AnnotationUsage copy = instance().copy();
        assertEquals(instance(), copy);
    }

}