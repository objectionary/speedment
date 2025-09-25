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
package com.speedment.common.logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import com.yegor256.AggregateRepeatedTest;
import org.mockito.Mock;

import java.util.logging.LogManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

final class LoggerManagerTest {

    private static LoggerFactory defaultLoggerFactory;

    @Mock
    private LoggerFactory loggerFactory;
    private LogManager instance;

    @BeforeAll
    static void save() {
        defaultLoggerFactory = LoggerManager.getFactory();
    }

    @AfterEach
    void restore() {
        LoggerManager.setFactory(defaultLoggerFactory);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setFactory() {
        LoggerManager.setFactory(loggerFactory);
        assertSame(loggerFactory, LoggerManager.getFactory());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getFactory() {
        assertSame(defaultLoggerFactory, LoggerManager.getFactory());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getLogger() {
        assertSame(defaultLoggerFactory.create(LoggerManagerTest.class), LoggerManager.getLogger(LoggerManagerTest.class));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testGetLogger() {
        assertSame(defaultLoggerFactory.create(LoggerManagerTest.class.getName() + "A"), LoggerManager.getLogger(LoggerManagerTest.class.getName() + "A"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setLevel() {
        final Logger logger = LoggerManager.getLogger(LoggerManagerTest.class);
        LoggerManager.setLevel(LoggerManagerTest.class, Level.FATAL);
        assertEquals(Level.FATAL, logger.getLevel());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testSetLevel() {
        final Logger logger = LoggerManager.getLogger(LoggerManagerTest.class.getName() + "A");
        LoggerManager.setLevel(LoggerManagerTest.class.getName() + "A", Level.FATAL);
        assertEquals(Level.FATAL, logger.getLevel());

    }
}