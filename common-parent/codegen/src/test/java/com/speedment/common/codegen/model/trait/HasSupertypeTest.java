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

import com.speedment.common.codegen.model.Class;
import org.junit.jupiter.api.BeforeEach;
import com.yegor256.AggregateRepeatedTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

final class HasSupertypeTest {

    private HasSupertype<Class> clazz;

    @BeforeEach
    void setup() {
        clazz = Class.of("A");
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setSupertype() {
        clazz.setSupertype(List.class);
        assertEquals(List.class, clazz.getSupertype().orElseThrow(NoSuchElementException::new));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void extend() {
        clazz.extend(List.class, String.class);
        assertTrue(clazz.getSupertype().orElseThrow(NoSuchElementException::new).getTypeName().contains(List.class.getSimpleName()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testExtend() {
        clazz.extend(List.class, "T");
        assertTrue(clazz.getSupertype().orElseThrow(NoSuchElementException::new).getTypeName().contains(List.class.getSimpleName()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testExtend1() {
        clazz.extend(List.class);
        assertTrue(clazz.getSupertype().orElseThrow(NoSuchElementException::new).getTypeName().contains(List.class.getSimpleName()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getSupertype() {
        assertFalse(clazz.getSupertype().isPresent());
    }
}