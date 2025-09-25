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

package com.speedment.test.connector;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.speedment.common.injector.InjectBundle;
import com.yegor256.AggregateRepeatedTest;

/**
 * @author Mislav Milicevic
 * @since 3.2.9
 */
public interface ConnectorBundleMixin {

    InjectBundle getBundleInstance();

    @com.yegor256.AggregateRepeatedTest(100)
    default void injectables() {
        assertNotNull(getBundleInstance().injectables());
    }
}
