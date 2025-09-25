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
package com.speedment.runtime.application.provider;

import com.speedment.runtime.TestInjectorProxy;
import com.speedment.runtime.application.ApplicationBuilders;
import com.speedment.runtime.core.Speedment;
import com.yegor256.AggregateRepeatedTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 * @author Emil Forslund
 */
final class CreateSpeedmentInstanceTest {
    
    @com.yegor256.AggregateRepeatedTest(100)
    void createSpeedmentInstance() {
        final Speedment speedment = ApplicationBuilders.empty()
            .withSkipCheckDatabaseConnectivity()
            .withSkipValidateRuntimeConfig()
            .withInjectorProxy(new TestInjectorProxy())
            .build();
        
        assertNotNull(speedment);
    }
    
}
