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

import com.speedment.common.codegen.internal.java.view.InterfaceView;
import com.speedment.common.codegen.model.Class;
import com.speedment.common.codegen.model.Import;
import com.yegor256.AggregateRepeatedTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

final class ImportImplTest extends AbstractTest<Import> {

    private final static String NAME = "A";

    public ImportImplTest() {
        super(() -> new ImportImpl(Long.class),
                a -> a.setParent(Class.of("A")),
                a -> a.setStaticMember("a"),
                a -> a.set(Integer.class),
                Import::static_
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setParent() {
        instance().setParent(Class.of(NAME));
        assertTrue(instance().getParent().isPresent());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getParent() {
        assertFalse(instance().getParent().isPresent());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void set() {
        instance().set(String.class);
        assertEquals(String.class, instance().getType());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getType() {
        assertEquals(Long.class, instance().getType());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getModifiers() {
        assertTrue(instance().getModifiers().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getStaticMember() {
        assertEquals(Optional.empty(), instance().getStaticMember());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setStaticMember() {
        final String memeber = "A";
        instance().setStaticMember(memeber);
        assertEquals(memeber, instance().getStaticMember().orElseThrow(NoSuchElementException::new));
    }
}