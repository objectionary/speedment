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
package com.speedment.common.codegen.model.modifier;

import com.speedment.common.codegen.model.Class;
import com.speedment.common.codegen.model.Field;
import com.speedment.common.codegen.model.Method;
import com.speedment.common.codegen.model.trait.HasModifiers;
import org.junit.jupiter.api.BeforeEach;
import com.yegor256.AggregateRepeatedTest;

import java.util.function.UnaryOperator;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class KeywordTest {

    private Field field;

    @BeforeEach
    void setup() {
        field = Field.of("x", int.class);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void public_() {
        assertOnOff(Field::public_, Field::notPublic, Modifier.PUBLIC);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void protected_() {
        assertOnOff(Field::protected_, Field::notProtected, Modifier.PROTECTED);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void private_() {
        assertOnOff(Field::private_, Field::notPrivate, Modifier.PRIVATE);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void static_() {
        assertOnOff(Field::static_, Field::notStatic, Modifier.STATIC);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void final_() {
        assertOnOff(Field::final_, Field::notFinal, Modifier.FINAL);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void abstract_() {
        assertOnOff(Class.of("A"), Class::abstract_, Class::notAbstract, Modifier.ABSTRACT);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void strictFp() {
        assertOnOff(Class.of("A"), Class::strictfp_, Class::notStrictfp, Modifier.STRICTFP);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void synchronized_() {
        assertOnOff(Field::synchronized_, Field::notSynchronized, Modifier.SYNCHRONIZED);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void transient_() {
        assertOnOff(Field::transient_, Field::notTransient, Modifier.TRANSIENT);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void volatile_() {
        assertOnOff(Field::volatile_, Field::notVolatile, Modifier.VOLATILE);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void native_() {
        assertOnOff(Method.of("A", int.class), Method::native_, Method::notNative, Modifier.NATIVE);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void default_() {
        assertOnOff(Method.of("A", int.class), Method::default_, Method::notDefault, Modifier.DEFAULT);
    }

    private  void assertOnOff(UnaryOperator<Field> on, UnaryOperator<Field> off, Modifier modifier) {
        assertOnOff(field, on, off, modifier);
    }

    private <T extends HasModifiers<T>> void assertOnOff(T t, UnaryOperator<T> on, UnaryOperator<T> off, Modifier modifier) {
        assertEquals(emptySet(), t.getModifiers());
        on.apply(t);
        assertEquals(singleton(modifier), t.getModifiers());
        off.apply(t);
        assertEquals(emptySet(), t.getModifiers());
    }

}