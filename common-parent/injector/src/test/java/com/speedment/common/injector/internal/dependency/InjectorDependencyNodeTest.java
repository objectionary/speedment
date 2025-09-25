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
package com.speedment.common.injector.internal.dependency;

import com.speedment.common.injector.Injector;
import com.speedment.common.injector.State;
import org.junit.jupiter.api.BeforeEach;
import com.yegor256.AggregateRepeatedTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

final class InjectorDependencyNodeTest {

    private InjectorDependencyNode instance;

    @BeforeEach
    void setup() {
        instance = new InjectorDependencyNode();
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getRepresentedType() {
        assertEquals(Injector.class, instance.getRepresentedType());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getDependencies() {
        assertTrue(instance.getDependencies().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getExecutions() {
        assertTrue(instance.getExecutions().isEmpty());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getCurrentState() {
        assertEquals(State.STARTED, instance.getCurrentState());
        instance.setState(State.STOPPED);
        assertEquals(State.STOPPED, instance.getCurrentState());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setState() {
        Stream.of(State.values()).forEach(instance::setState);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void canBe() {
        assertFalse(instance.canBe(State.CREATED));
        assertFalse(instance.canBe(State.INITIALIZED));
        assertFalse(instance.canBe(State.RESOLVED));
        assertFalse(instance.canBe(State.STARTED));
        assertTrue(instance.canBe(State.STOPPED));
        instance.setState(State.STOPPED);
        assertFalse(instance.canBe(State.CREATED));
        assertFalse(instance.canBe(State.INITIALIZED));
        assertFalse(instance.canBe(State.RESOLVED));
        assertFalse(instance.canBe(State.STARTED));
        assertFalse(instance.canBe(State.STOPPED));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void is() {
        assertTrue(instance.is(State.CREATED));
        assertTrue(instance.is(State.INITIALIZED));
        assertTrue(instance.is(State.RESOLVED));
        assertTrue(instance.is(State.STARTED));
        assertFalse(instance.is(State.STOPPED));
        instance.setState(State.STOPPED);
        assertTrue(instance.is(State.STARTED));
        assertTrue(instance.is(State.STOPPED));
    }
}