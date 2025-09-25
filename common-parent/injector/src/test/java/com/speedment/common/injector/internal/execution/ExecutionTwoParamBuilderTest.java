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
package com.speedment.common.injector.internal.execution;

import com.speedment.common.injector.State;
import com.speedment.common.injector.execution.ExecutionOneParamBuilder;
import com.speedment.common.injector.execution.ExecutionTwoParamBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import com.yegor256.AggregateRepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Disabled
final class ExecutionTwoParamBuilderTest {

    @Mock
    private ExecutionTwoParamBuilder<String, Integer, Integer> builder;
    private AtomicInteger cnt;

    @BeforeEach
    void setup() {
        cnt = new AtomicInteger();
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void withStateInitialized() {
        prepareMock(State.INITIALIZED);
        when(builder.withStateInitialized(Long.class)).thenCallRealMethod();
        builder.withStateInitialized(Long.class);
        assertEquals(1, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void withStateResolved() {
        prepareMock(State.RESOLVED);
        when(builder.withStateResolved(Long.class)).thenCallRealMethod();
        builder.withStateResolved(Long.class);
        assertEquals(1, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void withStateStarted() {
        prepareMock(State.STARTED);
        when(builder.withStateStarted(Long.class)).thenCallRealMethod();
        builder.withStateStarted(Long.class);
        assertEquals(1, cnt.get());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void withStateStopped() {
        prepareMock(State.STOPPED);
        when(builder.withStateStopped(Long.class)).thenCallRealMethod();
        builder.withStateStopped(Long.class);
        assertEquals(1, cnt.get());
    }

    private void prepareMock(State state) {
        when(builder.withState(state, Long.class)).thenAnswer(invocationOnMock -> {
            cnt.incrementAndGet();
            return null;
        });
    }

}