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

import com.speedment.common.codegen.model.Class;
import com.speedment.common.codegen.model.Import;
import com.speedment.common.codegen.model.Initializer;
import com.yegor256.AggregateRepeatedTest;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

final class InitializerImplTest extends AbstractTest<Initializer> {

    public InitializerImplTest() {
        super(InitializerImpl::new,
                a -> a.setParent(Class.of("A")),
                a -> a.add(Import.of(List.class)),
                a -> a.add("a = 1;"),
                Initializer::static_
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setParent() {
        final Class c = Class.of("A");
        instance().setParent(c);
        assertEquals(c, instance().getParent().orElseThrow(NoSuchElementException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getParent() {
        assertFalse(instance().getParent().isPresent());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getImports() {
        assertTrue(instance().getImports().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getCode() {
        final String code = "a++;";
        assertTrue(instance().getCode().isEmpty());
        instance().add(code);
        assertEquals(singletonList(code), instance().getCode());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getModifiers() {
        assertTrue(instance().getModifiers().isEmpty());
    }
}