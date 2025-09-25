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
package com.speedment.common.logger.internal;

import com.speedment.common.logger.Level;
import com.speedment.common.logger.LoggerEvent;
import com.speedment.common.logger.LoggerEventListener;
import com.speedment.common.logger.LoggerFormatter;
import org.junit.jupiter.api.BeforeEach;
import com.yegor256.AggregateRepeatedTest;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

final class AbstractLoggerTest {

    private static final String LOGGER_NAME = "c.s.Foo";
    private static final LoggerFormatter LOGGER_FORMATTER = (level, name, message) -> level.toText() + "|" + name + "|" + message;
    //
    private static final Level LEVEL = Level.TRACE;
    private static final String TEXT = "Bar";
    private static final String MESSAGE = "Bazz";
    private static final String MESSAGE2 = "Two";
    private static final String MESSAGE3 = "Three";
    private static final String MESSAGE4 = "Four";
    private static final String FORMATTING_MESSAGE_PREFIX = "aGye#2";
    private static final String FORMATTING_MESSAGE = FORMATTING_MESSAGE_PREFIX + "|%s";
    private static final String FORMATTING_MESSAGE2 = FORMATTING_MESSAGE_PREFIX + "|%s|%s";
    private static final String FORMATTING_MESSAGE3 = FORMATTING_MESSAGE_PREFIX + "|%s|%s|%s";
    private static final String FORMATTING_MESSAGE4 = FORMATTING_MESSAGE_PREFIX + "|%s|%s|%s|%s";
    //
    private static final Exception EXCEPTION = new RuntimeException("SomeError");

    private AbstractLogger instance;
    private String output;

    @BeforeEach
    void setup() {
        instance = new AbstractLogger(LOGGER_NAME, LOGGER_FORMATTER) {
            @Override
            protected void output(String message) {
                output = message;
            }
        };
        instance.setLevel(LEVEL);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void output() {
        instance.output(TEXT);
        assertEquals(TEXT, output);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getLevel() {
        instance.setLevel(LEVEL);
        assertEquals(LEVEL, instance.getLevel());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setLevel() {
        instance.setLevel(Level.INFO);
        assertEquals(Level.INFO, instance.getLevel());
        instance.setLevel(LEVEL);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setLevelAndCheckNoOutput() {
        instance.setLevel(Level.FATAL);
        instance.warn("SomeText");
        assertNull(output);
        instance.setLevel(LEVEL);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getName() {
        assertEquals(LOGGER_NAME, instance.getName());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setFormatter() {
        final LoggerFormatter newFormatter = (level, name, message) -> null;
        instance.setFormatter(newFormatter);
        assertSame(newFormatter, instance.getFormatter());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getFormatter() {
        assertEquals(LOGGER_FORMATTER, instance.getFormatter());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void addListener() {
        final AtomicReference<LoggerEvent> event = new AtomicReference<>();
        final LoggerEventListener listener = event::set;
        instance.addListener(listener);
        instance.fatal("Olle");
        assertNotNull(event.get());
        // Add again
        instance.addListener(listener);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void removeListener() {
        final AtomicReference<LoggerEvent> event = new AtomicReference<>();
        final LoggerEventListener listener = event::set;
        instance.addListener(listener);
        instance.removeListener(listener);
        instance.fatal("Olle");
        assertNull(event.get());
        // Remove again
        instance.removeListener(listener);
    }

    // Trace

    @com.yegor256.AggregateRepeatedTest(100)
    void trace() {
        instance.trace(MESSAGE);
        assertEquals(LOGGER_FORMATTER.apply(Level.TRACE, LOGGER_NAME, MESSAGE), output);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testTrace() {
        instance.trace(EXCEPTION);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(Level.TRACE.toText()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testTraceThrowableNull() {
        instance.trace((Throwable) null);
        assertTrue(output.contains(Level.TRACE.toText()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testTrace1() {
        instance.trace(EXCEPTION, MESSAGE);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testTrace2() {
        instance.trace(EXCEPTION, FORMATTING_MESSAGE, MESSAGE);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testTrace3() {
        instance.trace(FORMATTING_MESSAGE, MESSAGE);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testTrace4() {
        instance.trace(FORMATTING_MESSAGE2, MESSAGE, MESSAGE2);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testTrace5() {
        instance.trace(FORMATTING_MESSAGE3, MESSAGE, MESSAGE2, MESSAGE3);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testTrace6() {
        instance.trace(FORMATTING_MESSAGE4, MESSAGE, MESSAGE2, MESSAGE3, MESSAGE4);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3 + "|" + MESSAGE4));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testTrace7() {
        instance.trace(EXCEPTION, FORMATTING_MESSAGE2, MESSAGE, MESSAGE2);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testTrace8() {
        instance.trace(EXCEPTION, FORMATTING_MESSAGE3, MESSAGE, MESSAGE2, MESSAGE3);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testTrace9() {
        instance.trace(EXCEPTION, FORMATTING_MESSAGE4, MESSAGE, MESSAGE2, MESSAGE3, MESSAGE4);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3 + "|" + MESSAGE4));
    }

    // Debug

    @com.yegor256.AggregateRepeatedTest(100)
    void debug() {
        instance.debug(MESSAGE);
        assertEquals(LOGGER_FORMATTER.apply(Level.DEBUG, LOGGER_NAME, MESSAGE), output);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDebug() {
        instance.debug(EXCEPTION);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(Level.DEBUG.toText()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDebugThrowableNull() {
        instance.debug((Throwable) null);
        assertTrue(output.contains(Level.DEBUG.toText()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDebug1() {
        instance.debug(EXCEPTION, MESSAGE);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDebug2() {
        instance.debug(EXCEPTION, FORMATTING_MESSAGE, MESSAGE);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDebug3() {
        instance.debug(FORMATTING_MESSAGE, MESSAGE);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDebug4() {
        instance.debug(FORMATTING_MESSAGE2, MESSAGE, MESSAGE2);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDebug5() {
        instance.debug(FORMATTING_MESSAGE3, MESSAGE, MESSAGE2, MESSAGE3);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDebug6() {
        instance.debug(FORMATTING_MESSAGE4, MESSAGE, MESSAGE2, MESSAGE3, MESSAGE4);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3 + "|" + MESSAGE4));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDebug7() {
        instance.debug(EXCEPTION, FORMATTING_MESSAGE2, MESSAGE, MESSAGE2);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDebug8() {
        instance.debug(EXCEPTION, FORMATTING_MESSAGE3, MESSAGE, MESSAGE2, MESSAGE3);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testDebug9() {
        instance.debug(EXCEPTION, FORMATTING_MESSAGE4, MESSAGE, MESSAGE2, MESSAGE3, MESSAGE4);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3 + "|" + MESSAGE4));
    }

    // Info

    @com.yegor256.AggregateRepeatedTest(100)
    void info() {
        instance.info(MESSAGE);
        assertEquals(LOGGER_FORMATTER.apply(Level.INFO, LOGGER_NAME, MESSAGE), output);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testInfo() {
        instance.info(EXCEPTION);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(Level.INFO.toText()), output);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testInfoThrowableNull() {
        instance.info((Throwable) null);
        assertTrue(output.contains(Level.INFO.toText()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testInfo1() {
        instance.info(EXCEPTION, MESSAGE);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testInfo2() {
        instance.info(EXCEPTION, FORMATTING_MESSAGE, MESSAGE);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testInfo3() {
        instance.info(FORMATTING_MESSAGE, MESSAGE);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testInfo4() {
        instance.info(FORMATTING_MESSAGE2, MESSAGE, MESSAGE2);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testInfo5() {
        instance.info(FORMATTING_MESSAGE3, MESSAGE, MESSAGE2, MESSAGE3);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testInfo6() {
        instance.info(FORMATTING_MESSAGE4, MESSAGE, MESSAGE2, MESSAGE3, MESSAGE4);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3 + "|" + MESSAGE4));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testInfo7() {
        instance.info(EXCEPTION, FORMATTING_MESSAGE2, MESSAGE, MESSAGE2);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testInfo8() {
        instance.info(EXCEPTION, FORMATTING_MESSAGE3, MESSAGE, MESSAGE2, MESSAGE3);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testInfo9() {
        instance.info(EXCEPTION, FORMATTING_MESSAGE4, MESSAGE, MESSAGE2, MESSAGE3, MESSAGE4);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3 + "|" + MESSAGE4));
    }

    // Warn

    @com.yegor256.AggregateRepeatedTest(100)
    void warn() {
        instance.warn(MESSAGE);
        assertEquals(LOGGER_FORMATTER.apply(Level.WARN, LOGGER_NAME, MESSAGE), output);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testWarn() {
        instance.warn(EXCEPTION);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(Level.WARN.toText()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testWarnThrowableNull() {
        instance.warn((Throwable) null);
        assertTrue(output.contains(Level.WARN.toText()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testWarn1() {
        instance.warn(EXCEPTION, MESSAGE);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testWarn2() {
        instance.warn(EXCEPTION, FORMATTING_MESSAGE, MESSAGE);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testWarn3() {
        instance.warn(FORMATTING_MESSAGE, MESSAGE);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testWarn4() {
        instance.warn(FORMATTING_MESSAGE2, MESSAGE, MESSAGE2);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testWarn5() {
        instance.warn(FORMATTING_MESSAGE3, MESSAGE, MESSAGE2, MESSAGE3);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testWarn6() {
        instance.warn(FORMATTING_MESSAGE4, MESSAGE, MESSAGE2, MESSAGE3, MESSAGE4);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3 + "|" + MESSAGE4));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testWarn7() {
        instance.warn(EXCEPTION, FORMATTING_MESSAGE2, MESSAGE, MESSAGE2);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testWarn8() {
        instance.warn(EXCEPTION, FORMATTING_MESSAGE3, MESSAGE, MESSAGE2, MESSAGE3);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testWarn9() {
        instance.warn(EXCEPTION, FORMATTING_MESSAGE4, MESSAGE, MESSAGE2, MESSAGE3, MESSAGE4);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3 + "|" + MESSAGE4));
    }

    // Error

    @com.yegor256.AggregateRepeatedTest(100)
    void error() {
        instance.error(MESSAGE);
        assertEquals(LOGGER_FORMATTER.apply(Level.ERROR, LOGGER_NAME, MESSAGE), output);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testError() {
        instance.error(EXCEPTION);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(Level.ERROR.toText()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testErrorThrowableNull() {
        instance.error((Throwable) null);
        assertTrue(output.contains(Level.ERROR.toText()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testError1() {
        instance.error(EXCEPTION, MESSAGE);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testError2() {
        instance.error(EXCEPTION, FORMATTING_MESSAGE, MESSAGE);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testError3() {
        instance.error(FORMATTING_MESSAGE, MESSAGE);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testError4() {
        instance.error(FORMATTING_MESSAGE2, MESSAGE, MESSAGE2);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testError5() {
        instance.error(FORMATTING_MESSAGE3, MESSAGE, MESSAGE2, MESSAGE3);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testError6() {
        instance.error(FORMATTING_MESSAGE4, MESSAGE, MESSAGE2, MESSAGE3, MESSAGE4);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3 + "|" + MESSAGE4));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testError7() {
        instance.error(EXCEPTION, FORMATTING_MESSAGE2, MESSAGE, MESSAGE2);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testError8() {
        instance.error(EXCEPTION, FORMATTING_MESSAGE3, MESSAGE, MESSAGE2, MESSAGE3);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testError9() {
        instance.error(EXCEPTION, FORMATTING_MESSAGE4, MESSAGE, MESSAGE2, MESSAGE3, MESSAGE4);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3 + "|" + MESSAGE4));
    }
    
    // Fatal

    @com.yegor256.AggregateRepeatedTest(100)
    void fatal() {
        instance.fatal(MESSAGE);
        assertEquals(LOGGER_FORMATTER.apply(Level.FATAL, LOGGER_NAME, MESSAGE), output);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testFatal() {
        instance.fatal(EXCEPTION);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(Level.FATAL.toText()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testFatalThrowableNull() {
        instance.fatal((Throwable) null);
        assertTrue(output.contains(Level.FATAL.toText()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testFatal1() {
        instance.fatal(EXCEPTION, MESSAGE);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testFatal2() {
        instance.fatal(EXCEPTION, FORMATTING_MESSAGE, MESSAGE);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testFatal3() {
        instance.fatal(FORMATTING_MESSAGE, MESSAGE);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testFatal4() {
        instance.fatal(FORMATTING_MESSAGE2, MESSAGE, MESSAGE2);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testFatal5() {
        instance.fatal(FORMATTING_MESSAGE3, MESSAGE, MESSAGE2, MESSAGE3);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testFatal6() {
        instance.fatal(FORMATTING_MESSAGE4, MESSAGE, MESSAGE2, MESSAGE3, MESSAGE4);
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3 + "|" + MESSAGE4));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testFatal7() {
        instance.fatal(EXCEPTION, FORMATTING_MESSAGE2, MESSAGE, MESSAGE2);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testFatal8() {
        instance.fatal(EXCEPTION, FORMATTING_MESSAGE3, MESSAGE, MESSAGE2, MESSAGE3);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testFatal9() {
        instance.fatal(EXCEPTION, FORMATTING_MESSAGE4, MESSAGE, MESSAGE2, MESSAGE3, MESSAGE4);
        assertTrue(output.contains(RuntimeException.class.getName()));
        assertTrue(output.contains(FORMATTING_MESSAGE_PREFIX + "|" + MESSAGE + "|" + MESSAGE2 + "|" + MESSAGE3 + "|" + MESSAGE4));
    }
}