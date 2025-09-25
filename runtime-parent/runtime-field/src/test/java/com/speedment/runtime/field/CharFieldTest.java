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
package com.speedment.runtime.field;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.Column;
import com.speedment.runtime.field.comparator.CharFieldComparator;
import com.speedment.runtime.field.comparator.NullOrder;
import com.speedment.runtime.field.predicate.Inclusion;
import com.speedment.runtime.typemapper.TypeMapper;
import org.junit.jupiter.api.BeforeEach;
import com.yegor256.AggregateRepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

/**
 * JUnit tests for the primitive {@code char} field class.
 * 
 * @author Emil Forslund
 * @since  3.0.3
 * 
 * @see CharField
 */
@GeneratedCode(value = "com.speedment.sources.pattern.FieldTestPattern")
@ExtendWith(value = org.mockito.junit.jupiter.MockitoExtension.class)
final class CharFieldTest {
    
    private static final Function<BasicEntity, String> FORMATTER = entity -> "" + entity.getVarChar();
    private CharField<BasicEntity, Character> field;
    private CharField<BasicEntity, Character> fkField;
    private @Mock Column column;
    private List<BasicEntity> entities;
    private BasicEntity a;
    private BasicEntity b;
    private BasicEntity c;
    private BasicEntity d;
    private BasicEntity e;
    private BasicEntity f;
    private BasicEntity g;
    private BasicEntity h;
    private BasicEntity i;
    private BasicEntity j;
    private BasicEntity k;
    private BasicEntity l;
    
    @BeforeEach
    void setUp() {
        fkField = null;
        field = CharField.create(
            BasicEntity.Identifier.VAR_CHAR,
            BasicEntity::getVarChar,
            BasicEntity::setVarChar,
            TypeMapper.primitive(),
            false
        );
        
        a = new BasicEntity().setVarChar('0');
        b = new BasicEntity().setVarChar('/');
        c = new BasicEntity().setVarChar('1');
        d = new BasicEntity().setVarChar('1');
        e = new BasicEntity().setVarChar('2');
        f = new BasicEntity().setVarChar('2');
        g = new BasicEntity().setVarChar('3');
        h = new BasicEntity().setVarChar('+');
        i = new BasicEntity().setVarChar('1');
        j = new BasicEntity().setVarChar(' ');
        k = new BasicEntity().setVarChar('}');
        l = new BasicEntity().setVarChar('0');
        
        entities = asList(a, b, c, d, e, f, g, h, i, j, k, l);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void testBetween() {
        // Create a number of predicates
        final Predicate<BasicEntity> t0 = field.between('0', '2');
        final Predicate<BasicEntity> t1 = field.between('.', '2');
        final Predicate<BasicEntity> t2 = field.between('0', '2', Inclusion.START_EXCLUSIVE_END_EXCLUSIVE);
        final Predicate<BasicEntity> t3 = field.between('0', '2', Inclusion.START_INCLUSIVE_END_EXCLUSIVE);
        final Predicate<BasicEntity> t4 = field.between('0', '2', Inclusion.START_EXCLUSIVE_END_INCLUSIVE);
        final Predicate<BasicEntity> t5 = field.between('0', '2', Inclusion.START_INCLUSIVE_END_INCLUSIVE);
        
        // Create a number of expected results
        final List<BasicEntity> e0 = asList(a, c, d, i, l);
        final List<BasicEntity> e1 = asList(a, b, c, d, i, l);
        final List<BasicEntity> e2 = asList(c, d, i);
        final List<BasicEntity> e3 = asList(a, c, d, i, l);
        final List<BasicEntity> e4 = asList(c, d, e, f, i);
        final List<BasicEntity> e5 = asList(a, c, d, e, f, i, l);
        
        // Create a number of actual results
        final List<BasicEntity> a0 = entities.stream().filter(t0).collect(toList());
        final List<BasicEntity> a1 = entities.stream().filter(t1).collect(toList());
        final List<BasicEntity> a2 = entities.stream().filter(t2).collect(toList());
        final List<BasicEntity> a3 = entities.stream().filter(t3).collect(toList());
        final List<BasicEntity> a4 = entities.stream().filter(t4).collect(toList());
        final List<BasicEntity> a5 = entities.stream().filter(t5).collect(toList());
        
        // Test the results
        TestUtil.assertListEqual("Test 0: between(0, 2):",                                a0, e0, FORMATTER);
        TestUtil.assertListEqual("Test 1: between(., 2):",                               a1, e1, FORMATTER);
        TestUtil.assertListEqual("Test 2: between(0, 2, START_EXCLUSIVE_END_EXCLUSIVE):", a2, e2, FORMATTER);
        TestUtil.assertListEqual("Test 3: between(0, 2, START_INCLUSIVE_END_EXCLUSIVE):", a3, e3, FORMATTER);
        TestUtil.assertListEqual("Test 4: between(0, 2, START_EXCLUSIVE_END_INCLUSIVE):", a4, e4, FORMATTER);
        TestUtil.assertListEqual("Test 5: between(0, 2, START_INCLUSIVE_END_INCLUSIVE):", a5, e5, FORMATTER);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void testEqual() {
        // Create a number of predicates
        final Predicate<BasicEntity> t0 = field.equal('/');
        final Predicate<BasicEntity> t1 = field.equal('0');
        final Predicate<BasicEntity> t2 = field.equal('1');
        final Predicate<BasicEntity> t3 = field.equal('2');
        final Predicate<BasicEntity> t4 = field.equal('3');
        final Predicate<BasicEntity> t5 = field.equal('+');
        final Predicate<BasicEntity> t6 = field.equal(' ');
        final Predicate<BasicEntity> t7 = field.equal('}');
        final Predicate<BasicEntity> t8 = field.equal('m');
        
        // Create a number of expected results
        final List<BasicEntity> e0 = asList(b);
        final List<BasicEntity> e1 = asList(a, l);
        final List<BasicEntity> e2 = asList(c, d, i);
        final List<BasicEntity> e3 = asList(e, f);
        final List<BasicEntity> e4 = asList(g);
        final List<BasicEntity> e5 = asList(h);
        final List<BasicEntity> e6 = asList(j);
        final List<BasicEntity> e7 = asList(k);
        final List<BasicEntity> e8 = asList();
        
        // Create a number of actual results
        final List<BasicEntity> a0 = entities.stream().filter(t0).collect(toList());
        final List<BasicEntity> a1 = entities.stream().filter(t1).collect(toList());
        final List<BasicEntity> a2 = entities.stream().filter(t2).collect(toList());
        final List<BasicEntity> a3 = entities.stream().filter(t3).collect(toList());
        final List<BasicEntity> a4 = entities.stream().filter(t4).collect(toList());
        final List<BasicEntity> a5 = entities.stream().filter(t5).collect(toList());
        final List<BasicEntity> a6 = entities.stream().filter(t6).collect(toList());
        final List<BasicEntity> a7 = entities.stream().filter(t7).collect(toList());
        final List<BasicEntity> a8 = entities.stream().filter(t8).collect(toList());
        
        // Test the results
        TestUtil.assertListEqual("Test 0: equal(/):",        a0, e0, FORMATTER);
        TestUtil.assertListEqual("Test 1: equal(0):",         a1, e1, FORMATTER);
        TestUtil.assertListEqual("Test 2: equal(1):",         a2, e2, FORMATTER);
        TestUtil.assertListEqual("Test 3: equal(2):",         a3, e3, FORMATTER);
        TestUtil.assertListEqual("Test 4: equal(3):",         a4, e4, FORMATTER);
        TestUtil.assertListEqual("Test 5: equal(+):",        a5, e5, FORMATTER);
        TestUtil.assertListEqual("Test 6: equal(MIN_VALUE):", a6, e6, FORMATTER);
        TestUtil.assertListEqual("Test 7: equal(MAX_VALUE):", a7, e7, FORMATTER);
        TestUtil.assertListEqual("Test 8: equal(m):",       a8, e8, FORMATTER);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void testGreaterOrEqual() {
        // Create a number of predicates
        final Predicate<BasicEntity> t0 = field.greaterOrEqual('/');
        final Predicate<BasicEntity> t1 = field.greaterOrEqual('0');
        final Predicate<BasicEntity> t2 = field.greaterOrEqual('1');
        final Predicate<BasicEntity> t3 = field.greaterOrEqual('2');
        final Predicate<BasicEntity> t4 = field.greaterOrEqual('3');
        final Predicate<BasicEntity> t5 = field.greaterOrEqual('+');
        final Predicate<BasicEntity> t6 = field.greaterOrEqual(' ');
        final Predicate<BasicEntity> t7 = field.greaterOrEqual('}');
        final Predicate<BasicEntity> t8 = field.greaterOrEqual('m');
        
        // Create a number of expected results
        final List<BasicEntity> e0 = asList(a, b, c, d, e, f, g, i, k, l);
        final List<BasicEntity> e1 = asList(a, c, d, e, f, g, i, k, l);
        final List<BasicEntity> e2 = asList(c, d, e, f, g, i, k);
        final List<BasicEntity> e3 = asList(e, f, g, k);
        final List<BasicEntity> e4 = asList(g, k);
        final List<BasicEntity> e5 = asList(a, b, c, d, e, f, g, h, i, k, l);
        final List<BasicEntity> e6 = asList(a, b, c, d, e, f, g, h, i, j, k, l);
        final List<BasicEntity> e7 = asList(k);
        final List<BasicEntity> e8 = asList(k);
        
        // Create a number of actual results
        final List<BasicEntity> a0 = entities.stream().filter(t0).collect(toList());
        final List<BasicEntity> a1 = entities.stream().filter(t1).collect(toList());
        final List<BasicEntity> a2 = entities.stream().filter(t2).collect(toList());
        final List<BasicEntity> a3 = entities.stream().filter(t3).collect(toList());
        final List<BasicEntity> a4 = entities.stream().filter(t4).collect(toList());
        final List<BasicEntity> a5 = entities.stream().filter(t5).collect(toList());
        final List<BasicEntity> a6 = entities.stream().filter(t6).collect(toList());
        final List<BasicEntity> a7 = entities.stream().filter(t7).collect(toList());
        final List<BasicEntity> a8 = entities.stream().filter(t8).collect(toList());
        
        // Test the results
        TestUtil.assertListEqual("Test 0: greaterOrEqual(/):",        a0, e0, FORMATTER);
        TestUtil.assertListEqual("Test 1: greaterOrEqual(0):",         a1, e1, FORMATTER);
        TestUtil.assertListEqual("Test 2: greaterOrEqual(1):",         a2, e2, FORMATTER);
        TestUtil.assertListEqual("Test 3: greaterOrEqual(2):",         a3, e3, FORMATTER);
        TestUtil.assertListEqual("Test 4: greaterOrEqual(3):",         a4, e4, FORMATTER);
        TestUtil.assertListEqual("Test 5: greaterOrEqual(+):",        a5, e5, FORMATTER);
        TestUtil.assertListEqual("Test 6: greaterOrEqual(MIN_VALUE):", a6, e6, FORMATTER);
        TestUtil.assertListEqual("Test 7: greaterOrEqual(MAX_VALUE):", a7, e7, FORMATTER);
        TestUtil.assertListEqual("Test 8: greaterOrEqual(m):",       a8, e8, FORMATTER);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void testGreaterThan() {
        // Create a number of predicates
        final Predicate<BasicEntity> t0 = field.greaterThan('/');
        final Predicate<BasicEntity> t1 = field.greaterThan('0');
        final Predicate<BasicEntity> t2 = field.greaterThan('1');
        final Predicate<BasicEntity> t3 = field.greaterThan('2');
        final Predicate<BasicEntity> t4 = field.greaterThan('3');
        final Predicate<BasicEntity> t5 = field.greaterThan('+');
        final Predicate<BasicEntity> t6 = field.greaterThan(' ');
        final Predicate<BasicEntity> t7 = field.greaterThan('}');
        final Predicate<BasicEntity> t8 = field.greaterThan('m');
        
        // Create a number of expected results
        final List<BasicEntity> e0 = asList(a, c, d, e, f, g, i, k, l);
        final List<BasicEntity> e1 = asList(c, d, e, f, g, i, k);
        final List<BasicEntity> e2 = asList(e, f, g, k);
        final List<BasicEntity> e3 = asList(g, k);
        final List<BasicEntity> e4 = asList(k);
        final List<BasicEntity> e5 = asList(a, b, c, d, e, f, g, i, k, l);
        final List<BasicEntity> e6 = asList(a, b, c, d, e, f, g, h, i, k, l);
        final List<BasicEntity> e7 = asList();
        final List<BasicEntity> e8 = asList(k);
        
        // Create a number of actual results
        final List<BasicEntity> a0 = entities.stream().filter(t0).collect(toList());
        final List<BasicEntity> a1 = entities.stream().filter(t1).collect(toList());
        final List<BasicEntity> a2 = entities.stream().filter(t2).collect(toList());
        final List<BasicEntity> a3 = entities.stream().filter(t3).collect(toList());
        final List<BasicEntity> a4 = entities.stream().filter(t4).collect(toList());
        final List<BasicEntity> a5 = entities.stream().filter(t5).collect(toList());
        final List<BasicEntity> a6 = entities.stream().filter(t6).collect(toList());
        final List<BasicEntity> a7 = entities.stream().filter(t7).collect(toList());
        final List<BasicEntity> a8 = entities.stream().filter(t8).collect(toList());
        
        // Test the results
        TestUtil.assertListEqual("Test 0: greaterThan(/):",        a0, e0, FORMATTER);
        TestUtil.assertListEqual("Test 1: greaterThan(0):",         a1, e1, FORMATTER);
        TestUtil.assertListEqual("Test 2: greaterThan(1):",         a2, e2, FORMATTER);
        TestUtil.assertListEqual("Test 3: greaterThan(2):",         a3, e3, FORMATTER);
        TestUtil.assertListEqual("Test 4: greaterThan(3):",         a4, e4, FORMATTER);
        TestUtil.assertListEqual("Test 5: greaterThan(+):",        a5, e5, FORMATTER);
        TestUtil.assertListEqual("Test 6: greaterThan(MIN_VALUE):", a6, e6, FORMATTER);
        TestUtil.assertListEqual("Test 7: greaterThan(MAX_VALUE):", a7, e7, FORMATTER);
        TestUtil.assertListEqual("Test 8: greaterThan(m):",       a8, e8, FORMATTER);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void testIn() {
        // Create a number of predicates
        final Predicate<BasicEntity> t0 = field.in();
        final Predicate<BasicEntity> t1 = field.in('0');
        final Predicate<BasicEntity> t2 = field.in('0', '1');
        final Predicate<BasicEntity> t3 = field.in('0', '1', '1');
        final Predicate<BasicEntity> t4 = field.in('/', '1', '2', '3');
        final Predicate<BasicEntity> t5 = field.in(' ', '}');
        final Predicate<BasicEntity> t6 = field.in('1', '2', '3', '4', '5');
        final Predicate<BasicEntity> t7 = field.in('m', 'n', 'o', 'p', 'q');
        final Predicate<BasicEntity> t8 = field.in('k');
        
        // Create a number of expected results
        final List<BasicEntity> e0 = asList();
        final List<BasicEntity> e1 = asList(a, l);
        final List<BasicEntity> e2 = asList(a, c, d, i, l);
        final List<BasicEntity> e3 = asList(a, c, d, i, l);
        final List<BasicEntity> e4 = asList(b, c, d, e, f, g, i);
        final List<BasicEntity> e5 = asList(j, k);
        final List<BasicEntity> e6 = asList(c, d, e, f, g, i);
        final List<BasicEntity> e7 = asList();
        final List<BasicEntity> e8 = asList();
        
        // Create a number of actual results
        final List<BasicEntity> a0 = entities.stream().filter(t0).collect(toList());
        final List<BasicEntity> a1 = entities.stream().filter(t1).collect(toList());
        final List<BasicEntity> a2 = entities.stream().filter(t2).collect(toList());
        final List<BasicEntity> a3 = entities.stream().filter(t3).collect(toList());
        final List<BasicEntity> a4 = entities.stream().filter(t4).collect(toList());
        final List<BasicEntity> a5 = entities.stream().filter(t5).collect(toList());
        final List<BasicEntity> a6 = entities.stream().filter(t6).collect(toList());
        final List<BasicEntity> a7 = entities.stream().filter(t7).collect(toList());
        final List<BasicEntity> a8 = entities.stream().filter(t8).collect(toList());
        
        // Test the results
        TestUtil.assertListEqual("Test 0: in():",                        a0, e0, FORMATTER);
        TestUtil.assertListEqual("Test 1: in(0):",                       a1, e1, FORMATTER);
        TestUtil.assertListEqual("Test 2: in(0, 1):",                    a2, e2, FORMATTER);
        TestUtil.assertListEqual("Test 3: in(0, 1, 1):",                 a3, e3, FORMATTER);
        TestUtil.assertListEqual("Test 4: in(/, 1, 2, 3):",             a4, e4, FORMATTER);
        TestUtil.assertListEqual("Test 5: in(MIN_VALUE, MAX_VALUE):",    a5, e5, FORMATTER);
        TestUtil.assertListEqual("Test 6: in(1, 2, 3, 4, 5):",           a6, e6, FORMATTER);
        TestUtil.assertListEqual("Test 7: in(100, 101, 102, 103, 104):", a7, e7, FORMATTER);
        TestUtil.assertListEqual("Test 8: in(-100):",                    a8, e8, FORMATTER);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void testInSet() {
        // Create a number of predicates
        final Predicate<BasicEntity> t0 = field.in(Collections.emptySet());
        final Predicate<BasicEntity> t1 = field.in(Collections.singleton('0'));
        final Predicate<BasicEntity> t2 = field.in(Stream.of('0', '1').collect(toSet()));
        final Predicate<BasicEntity> t3 = field.in(Stream.of('0', '1', '1').collect(toSet()));
        final Predicate<BasicEntity> t4 = field.in(Stream.of('/', '1', '2', '3').collect(toSet()));
        final Predicate<BasicEntity> t5 = field.in(Stream.of(' ', '}').collect(toSet()));
        final Predicate<BasicEntity> t6 = field.in(Stream.of('1', '2', '3', '4', '5').collect(toSet()));
        final Predicate<BasicEntity> t7 = field.in(Stream.of('m', 'n', 'o', 'p', 'q').collect(toSet()));
        final Predicate<BasicEntity> t8 = field.in(Collections.singleton('k'));
        
        // Create a number of expected results
        final List<BasicEntity> e0 = asList();
        final List<BasicEntity> e1 = asList(a, l);
        final List<BasicEntity> e2 = asList(a, c, d, i, l);
        final List<BasicEntity> e3 = asList(a, c, d, i, l);
        final List<BasicEntity> e4 = asList(b, c, d, e, f, g, i);
        final List<BasicEntity> e5 = asList(j, k);
        final List<BasicEntity> e6 = asList(c, d, e, f, g, i);
        final List<BasicEntity> e7 = asList();
        final List<BasicEntity> e8 = asList();
        
        // Create a number of actual results
        final List<BasicEntity> a0 = entities.stream().filter(t0).collect(toList());
        final List<BasicEntity> a1 = entities.stream().filter(t1).collect(toList());
        final List<BasicEntity> a2 = entities.stream().filter(t2).collect(toList());
        final List<BasicEntity> a3 = entities.stream().filter(t3).collect(toList());
        final List<BasicEntity> a4 = entities.stream().filter(t4).collect(toList());
        final List<BasicEntity> a5 = entities.stream().filter(t5).collect(toList());
        final List<BasicEntity> a6 = entities.stream().filter(t6).collect(toList());
        final List<BasicEntity> a7 = entities.stream().filter(t7).collect(toList());
        final List<BasicEntity> a8 = entities.stream().filter(t8).collect(toList());
        
        // Test the results
        TestUtil.assertListEqual("Test 0: inSet():",                        a0, e0, FORMATTER);
        TestUtil.assertListEqual("Test 1: inSet(0):",                       a1, e1, FORMATTER);
        TestUtil.assertListEqual("Test 2: inSet(0, 1):",                    a2, e2, FORMATTER);
        TestUtil.assertListEqual("Test 3: inSet(0, 1, 1):",                 a3, e3, FORMATTER);
        TestUtil.assertListEqual("Test 4: inSet(/, 1, 2, 3):",             a4, e4, FORMATTER);
        TestUtil.assertListEqual("Test 5: inSet(MIN_VALUE, MAX_VALUE):",    a5, e5, FORMATTER);
        TestUtil.assertListEqual("Test 6: inSet(1, 2, 3, 4, 5):",           a6, e6, FORMATTER);
        TestUtil.assertListEqual("Test 7: inSet(100, 101, 102, 103, 104):", a7, e7, FORMATTER);
        TestUtil.assertListEqual("Test 8: inSet(-100):",                    a8, e8, FORMATTER);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void testLessThan() {
        // Create a number of predicates
        final Predicate<BasicEntity> t0 = field.lessThan('/');
        final Predicate<BasicEntity> t1 = field.lessThan('0');
        final Predicate<BasicEntity> t2 = field.lessThan('1');
        final Predicate<BasicEntity> t3 = field.lessThan('2');
        final Predicate<BasicEntity> t4 = field.lessThan('3');
        final Predicate<BasicEntity> t5 = field.lessThan('+');
        final Predicate<BasicEntity> t6 = field.lessThan(' ');
        final Predicate<BasicEntity> t7 = field.lessThan('}');
        final Predicate<BasicEntity> t8 = field.lessThan('m');
        
        // Create a number of expected results
        final List<BasicEntity> e0 = asList(h, j);
        final List<BasicEntity> e1 = asList(b, h, j);
        final List<BasicEntity> e2 = asList(a, b, h, j, l);
        final List<BasicEntity> e3 = asList(a, b, c, d, h, i, j, l);
        final List<BasicEntity> e4 = asList(a, b, c, d, e, f, h, i, j, l);
        final List<BasicEntity> e5 = asList(j);
        final List<BasicEntity> e6 = asList();
        final List<BasicEntity> e7 = asList(a, b, c, d, e, f, g, h, i, j, l);
        final List<BasicEntity> e8 = asList(a, b, c, d, e, f, g, h, i, j, l);
        
        // Create a number of actual results
        final List<BasicEntity> a0 = entities.stream().filter(t0).collect(toList());
        final List<BasicEntity> a1 = entities.stream().filter(t1).collect(toList());
        final List<BasicEntity> a2 = entities.stream().filter(t2).collect(toList());
        final List<BasicEntity> a3 = entities.stream().filter(t3).collect(toList());
        final List<BasicEntity> a4 = entities.stream().filter(t4).collect(toList());
        final List<BasicEntity> a5 = entities.stream().filter(t5).collect(toList());
        final List<BasicEntity> a6 = entities.stream().filter(t6).collect(toList());
        final List<BasicEntity> a7 = entities.stream().filter(t7).collect(toList());
        final List<BasicEntity> a8 = entities.stream().filter(t8).collect(toList());
        
        // Test the results
        TestUtil.assertListEqual("Test 0: lessThan(/):",        a0, e0, FORMATTER);
        TestUtil.assertListEqual("Test 1: lessThan(0):",         a1, e1, FORMATTER);
        TestUtil.assertListEqual("Test 2: lessThan(1):",         a2, e2, FORMATTER);
        TestUtil.assertListEqual("Test 3: lessThan(2):",         a3, e3, FORMATTER);
        TestUtil.assertListEqual("Test 4: lessThan(3):",         a4, e4, FORMATTER);
        TestUtil.assertListEqual("Test 5: lessThan(+):",        a5, e5, FORMATTER);
        TestUtil.assertListEqual("Test 6: lessThan(MIN_VALUE):", a6, e6, FORMATTER);
        TestUtil.assertListEqual("Test 7: lessThan(MAX_VALUE):", a7, e7, FORMATTER);
        TestUtil.assertListEqual("Test 8: lessThan(m):",       a8, e8, FORMATTER);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void testLessOrEqual() {
        // Create a number of predicates
        final Predicate<BasicEntity> t0 = field.lessOrEqual('/');
        final Predicate<BasicEntity> t1 = field.lessOrEqual('0');
        final Predicate<BasicEntity> t2 = field.lessOrEqual('1');
        final Predicate<BasicEntity> t3 = field.lessOrEqual('2');
        final Predicate<BasicEntity> t4 = field.lessOrEqual('3');
        final Predicate<BasicEntity> t5 = field.lessOrEqual('+');
        final Predicate<BasicEntity> t6 = field.lessOrEqual(' ');
        final Predicate<BasicEntity> t7 = field.lessOrEqual('}');
        final Predicate<BasicEntity> t8 = field.lessOrEqual('m');
        
        // Create a number of expected results
        final List<BasicEntity> e0 = asList(b, h, j);
        final List<BasicEntity> e1 = asList(a, b, h, j, l);
        final List<BasicEntity> e2 = asList(a, b, c, d, h, i, j, l);
        final List<BasicEntity> e3 = asList(a, b, c, d, e, f, h, i, j, l);
        final List<BasicEntity> e4 = asList(a, b, c, d, e, f, g, h, i, j, l);
        final List<BasicEntity> e5 = asList(h, j);
        final List<BasicEntity> e6 = asList(j);
        final List<BasicEntity> e7 = asList(a, b, c, d, e, f, g, h, i, j, k, l);
        final List<BasicEntity> e8 = asList(a, b, c, d, e, f, g, h, i, j, l);
        
        // Create a number of actual results
        final List<BasicEntity> a0 = entities.stream().filter(t0).collect(toList());
        final List<BasicEntity> a1 = entities.stream().filter(t1).collect(toList());
        final List<BasicEntity> a2 = entities.stream().filter(t2).collect(toList());
        final List<BasicEntity> a3 = entities.stream().filter(t3).collect(toList());
        final List<BasicEntity> a4 = entities.stream().filter(t4).collect(toList());
        final List<BasicEntity> a5 = entities.stream().filter(t5).collect(toList());
        final List<BasicEntity> a6 = entities.stream().filter(t6).collect(toList());
        final List<BasicEntity> a7 = entities.stream().filter(t7).collect(toList());
        final List<BasicEntity> a8 = entities.stream().filter(t8).collect(toList());
        
        // Test the results
        TestUtil.assertListEqual("Test 0: lessOrEqual(/):",        a0, e0, FORMATTER);
        TestUtil.assertListEqual("Test 1: lessOrEqual(0):",         a1, e1, FORMATTER);
        TestUtil.assertListEqual("Test 2: lessOrEqual(1):",         a2, e2, FORMATTER);
        TestUtil.assertListEqual("Test 3: lessOrEqual(2):",         a3, e3, FORMATTER);
        TestUtil.assertListEqual("Test 4: lessOrEqual(3):",         a4, e4, FORMATTER);
        TestUtil.assertListEqual("Test 5: lessOrEqual(+):",        a5, e5, FORMATTER);
        TestUtil.assertListEqual("Test 6: lessOrEqual(MIN_VALUE):", a6, e6, FORMATTER);
        TestUtil.assertListEqual("Test 7: lessOrEqual(MAX_VALUE):", a7, e7, FORMATTER);
        TestUtil.assertListEqual("Test 8: lessOrEqual(m):",       a8, e8, FORMATTER);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void testNotBetween() {
        // Create a number of predicates
        final Predicate<BasicEntity> t0 = field.notBetween('0', '2');
        final Predicate<BasicEntity> t1 = field.notBetween('.', '2');
        final Predicate<BasicEntity> t2 = field.notBetween('0', '2', Inclusion.START_EXCLUSIVE_END_EXCLUSIVE);
        final Predicate<BasicEntity> t3 = field.notBetween('0', '2', Inclusion.START_INCLUSIVE_END_EXCLUSIVE);
        final Predicate<BasicEntity> t4 = field.notBetween('0', '2', Inclusion.START_EXCLUSIVE_END_INCLUSIVE);
        final Predicate<BasicEntity> t5 = field.notBetween('0', '2', Inclusion.START_INCLUSIVE_END_INCLUSIVE);
        
        // Create a number of expected results
        final List<BasicEntity> e0 = asList(b, e, f, g, h, j, k);
        final List<BasicEntity> e1 = asList(e, f, g, h, j, k);
        final List<BasicEntity> e2 = asList(a, b, e, f, g, h, j, k, l);
        final List<BasicEntity> e3 = asList(b, e, f, g, h, j, k);
        final List<BasicEntity> e4 = asList(a, b, g, h, j, k, l);
        final List<BasicEntity> e5 = asList(b, g, h, j, k);
        
        // Create a number of actual results
        final List<BasicEntity> a0 = entities.stream().filter(t0).collect(toList());
        final List<BasicEntity> a1 = entities.stream().filter(t1).collect(toList());
        final List<BasicEntity> a2 = entities.stream().filter(t2).collect(toList());
        final List<BasicEntity> a3 = entities.stream().filter(t3).collect(toList());
        final List<BasicEntity> a4 = entities.stream().filter(t4).collect(toList());
        final List<BasicEntity> a5 = entities.stream().filter(t5).collect(toList());
        
        // Test the results
        TestUtil.assertListEqual("Test 0: notBetween(0, 2):",                                a0, e0, FORMATTER);
        TestUtil.assertListEqual("Test 1: notBetween(., 2):",                               a1, e1, FORMATTER);
        TestUtil.assertListEqual("Test 2: notBetween(0, 2, START_EXCLUSIVE_END_EXCLUSIVE):", a2, e2, FORMATTER);
        TestUtil.assertListEqual("Test 3: notBetween(0, 2, START_INCLUSIVE_END_EXCLUSIVE):", a3, e3, FORMATTER);
        TestUtil.assertListEqual("Test 4: notBetween(0, 2, START_EXCLUSIVE_END_INCLUSIVE):", a4, e4, FORMATTER);
        TestUtil.assertListEqual("Test 5: notBetween(0, 2, START_INCLUSIVE_END_INCLUSIVE):", a5, e5, FORMATTER);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void testNotEqual() {
        // Create a number of predicates
        final Predicate<BasicEntity> t0 = field.notEqual('/');
        final Predicate<BasicEntity> t1 = field.notEqual('0');
        final Predicate<BasicEntity> t2 = field.notEqual('1');
        final Predicate<BasicEntity> t3 = field.notEqual('2');
        final Predicate<BasicEntity> t4 = field.notEqual('3');
        final Predicate<BasicEntity> t5 = field.notEqual('+');
        final Predicate<BasicEntity> t6 = field.notEqual(' ');
        final Predicate<BasicEntity> t7 = field.notEqual('}');
        final Predicate<BasicEntity> t8 = field.notEqual('m');
        
        // Create a number of expected results
        final List<BasicEntity> e0 = asList(a, c, d, e, f, g, h, i, j, k, l);
        final List<BasicEntity> e1 = asList(b, c, d, e, f, g, h, i, j, k);
        final List<BasicEntity> e2 = asList(a, b, e, f, g, h, j, k, l);
        final List<BasicEntity> e3 = asList(a, b, c, d, g, h, i, j, k, l);
        final List<BasicEntity> e4 = asList(a, b, c, d, e, f, h, i, j, k, l);
        final List<BasicEntity> e5 = asList(a, b, c, d, e, f, g, i, j, k, l);
        final List<BasicEntity> e6 = asList(a, b, c, d, e, f, g, h, i, k, l);
        final List<BasicEntity> e7 = asList(a, b, c, d, e, f, g, h, i, j, l);
        final List<BasicEntity> e8 = asList(a, b, c, d, e, f, g, h, i, j, k, l);
        
        // Create a number of actual results
        final List<BasicEntity> a0 = entities.stream().filter(t0).collect(toList());
        final List<BasicEntity> a1 = entities.stream().filter(t1).collect(toList());
        final List<BasicEntity> a2 = entities.stream().filter(t2).collect(toList());
        final List<BasicEntity> a3 = entities.stream().filter(t3).collect(toList());
        final List<BasicEntity> a4 = entities.stream().filter(t4).collect(toList());
        final List<BasicEntity> a5 = entities.stream().filter(t5).collect(toList());
        final List<BasicEntity> a6 = entities.stream().filter(t6).collect(toList());
        final List<BasicEntity> a7 = entities.stream().filter(t7).collect(toList());
        final List<BasicEntity> a8 = entities.stream().filter(t8).collect(toList());
        
        // Test the results
        TestUtil.assertListEqual("Test 0: notEqual(/):",        a0, e0, FORMATTER);
        TestUtil.assertListEqual("Test 1: notEqual(0):",         a1, e1, FORMATTER);
        TestUtil.assertListEqual("Test 2: notEqual(1):",         a2, e2, FORMATTER);
        TestUtil.assertListEqual("Test 3: notEqual(2):",         a3, e3, FORMATTER);
        TestUtil.assertListEqual("Test 4: notEqual(3):",         a4, e4, FORMATTER);
        TestUtil.assertListEqual("Test 5: notEqual(+):",        a5, e5, FORMATTER);
        TestUtil.assertListEqual("Test 6: notEqual(MIN_VALUE):", a6, e6, FORMATTER);
        TestUtil.assertListEqual("Test 7: notEqual(MAX_VALUE):", a7, e7, FORMATTER);
        TestUtil.assertListEqual("Test 8: notEqual(m):",       a8, e8, FORMATTER);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void testNotIn() {
        // Create a number of predicates
        final Predicate<BasicEntity> t0 = field.notIn();
        final Predicate<BasicEntity> t1 = field.notIn('0');
        final Predicate<BasicEntity> t2 = field.notIn('0', '1');
        final Predicate<BasicEntity> t3 = field.notIn('0', '1', '1');
        final Predicate<BasicEntity> t4 = field.notIn('/', '1', '2', '3');
        final Predicate<BasicEntity> t5 = field.notIn(' ', '}');
        final Predicate<BasicEntity> t6 = field.notIn('1', '2', '3', '4', '5');
        final Predicate<BasicEntity> t7 = field.notIn('m', 'n', 'o', 'p', 'q');
        final Predicate<BasicEntity> t8 = field.notIn('k');
        
        // Create a number of expected results
        final List<BasicEntity> e0 = asList(a, b, c, d, e, f, g, h, i, j, k, l);
        final List<BasicEntity> e1 = asList(b, c, d, e, f, g, h, i, j, k);
        final List<BasicEntity> e2 = asList(b, e, f, g, h, j, k);
        final List<BasicEntity> e3 = asList(b, e, f, g, h, j, k);
        final List<BasicEntity> e4 = asList(a, h, j, k, l);
        final List<BasicEntity> e5 = asList(a, b, c, d, e, f, g, h, i, l);
        final List<BasicEntity> e6 = asList(a, b, h, j, k, l);
        final List<BasicEntity> e7 = asList(a, b, c, d, e, f, g, h, i, j, k, l);
        final List<BasicEntity> e8 = asList(a, b, c, d, e, f, g, h, i, j, k, l);
        
        // Create a number of actual results
        final List<BasicEntity> a0 = entities.stream().filter(t0).collect(toList());
        final List<BasicEntity> a1 = entities.stream().filter(t1).collect(toList());
        final List<BasicEntity> a2 = entities.stream().filter(t2).collect(toList());
        final List<BasicEntity> a3 = entities.stream().filter(t3).collect(toList());
        final List<BasicEntity> a4 = entities.stream().filter(t4).collect(toList());
        final List<BasicEntity> a5 = entities.stream().filter(t5).collect(toList());
        final List<BasicEntity> a6 = entities.stream().filter(t6).collect(toList());
        final List<BasicEntity> a7 = entities.stream().filter(t7).collect(toList());
        final List<BasicEntity> a8 = entities.stream().filter(t8).collect(toList());
        
        // Test the results
        TestUtil.assertListEqual("Test 0: notIn():",                        a0, e0, FORMATTER);
        TestUtil.assertListEqual("Test 1: notIn(0):",                       a1, e1, FORMATTER);
        TestUtil.assertListEqual("Test 2: notIn(0, 1):",                    a2, e2, FORMATTER);
        TestUtil.assertListEqual("Test 3: notIn(0, 1, 1):",                 a3, e3, FORMATTER);
        TestUtil.assertListEqual("Test 4: notIn(/, 1, 2, 3):",             a4, e4, FORMATTER);
        TestUtil.assertListEqual("Test 5: notIn(MIN_VALUE, MAX_VALUE):",    a5, e5, FORMATTER);
        TestUtil.assertListEqual("Test 6: notIn(1, 2, 3, 4, 5):",           a6, e6, FORMATTER);
        TestUtil.assertListEqual("Test 7: notIn(100, 101, 102, 103, 104):", a7, e7, FORMATTER);
        TestUtil.assertListEqual("Test 8: notIn(-100):",                    a8, e8, FORMATTER);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void testNotInSet() {
        // Create a number of predicates
        final Predicate<BasicEntity> t0 = field.notIn(Collections.emptySet());
        final Predicate<BasicEntity> t1 = field.notIn(Collections.singleton('0'));
        final Predicate<BasicEntity> t2 = field.notIn(Stream.of('0', '1').collect(toSet()));
        final Predicate<BasicEntity> t3 = field.notIn(Stream.of('0', '1', '1').collect(toSet()));
        final Predicate<BasicEntity> t4 = field.notIn(Stream.of('/', '1', '2', '3').collect(toSet()));
        final Predicate<BasicEntity> t5 = field.notIn(Stream.of(' ', '}').collect(toSet()));
        final Predicate<BasicEntity> t6 = field.notIn(Stream.of('1', '2', '3', '4', '5').collect(toSet()));
        final Predicate<BasicEntity> t7 = field.notIn(Stream.of('m', 'n', 'o', 'p', 'q').collect(toSet()));
        final Predicate<BasicEntity> t8 = field.notIn(Collections.singleton('k'));
        
        // Create a number of expected results
        final List<BasicEntity> e0 = asList(a, b, c, d, e, f, g, h, i, j, k, l);
        final List<BasicEntity> e1 = asList(b, c, d, e, f, g, h, i, j, k);
        final List<BasicEntity> e2 = asList(b, e, f, g, h, j, k);
        final List<BasicEntity> e3 = asList(b, e, f, g, h, j, k);
        final List<BasicEntity> e4 = asList(a, h, j, k, l);
        final List<BasicEntity> e5 = asList(a, b, c, d, e, f, g, h, i, l);
        final List<BasicEntity> e6 = asList(a, b, h, j, k, l);
        final List<BasicEntity> e7 = asList(a, b, c, d, e, f, g, h, i, j, k, l);
        final List<BasicEntity> e8 = asList(a, b, c, d, e, f, g, h, i, j, k, l);
        
        // Create a number of actual results
        final List<BasicEntity> a0 = entities.stream().filter(t0).collect(toList());
        final List<BasicEntity> a1 = entities.stream().filter(t1).collect(toList());
        final List<BasicEntity> a2 = entities.stream().filter(t2).collect(toList());
        final List<BasicEntity> a3 = entities.stream().filter(t3).collect(toList());
        final List<BasicEntity> a4 = entities.stream().filter(t4).collect(toList());
        final List<BasicEntity> a5 = entities.stream().filter(t5).collect(toList());
        final List<BasicEntity> a6 = entities.stream().filter(t6).collect(toList());
        final List<BasicEntity> a7 = entities.stream().filter(t7).collect(toList());
        final List<BasicEntity> a8 = entities.stream().filter(t8).collect(toList());
        
        // Test the results
        TestUtil.assertListEqual("Test 0: notInSet():",                        a0, e0, FORMATTER);
        TestUtil.assertListEqual("Test 1: notInSet(0):",                       a1, e1, FORMATTER);
        TestUtil.assertListEqual("Test 2: notInSet(0, 1):",                    a2, e2, FORMATTER);
        TestUtil.assertListEqual("Test 3: notInSet(0, 1, 1):",                 a3, e3, FORMATTER);
        TestUtil.assertListEqual("Test 4: notInSet(/, 1, 2, 3):",             a4, e4, FORMATTER);
        TestUtil.assertListEqual("Test 5: notInSet(MIN_VALUE, MAX_VALUE):",    a5, e5, FORMATTER);
        TestUtil.assertListEqual("Test 6: notInSet(1, 2, 3, 4, 5):",           a6, e6, FORMATTER);
        TestUtil.assertListEqual("Test 7: notInSet(100, 101, 102, 103, 104):", a7, e7, FORMATTER);
        TestUtil.assertListEqual("Test 8: notInSet(-100):",                    a8, e8, FORMATTER);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void getField() {
        final CharField<BasicEntity, Character> other = field.getField();
        assertNotNull(other);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void comparator() {
        comparator(false);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void comparatorReversed() {
        comparator(true);
    }
    
    void comparator(final boolean reversed) {
        final Comparator<BasicEntity> comparatorExpected = Comparator.comparing(BasicEntity::getVarChar);
        final List<BasicEntity> expected = new ArrayList<>(entities);
        expected.sort(reversed ? comparatorExpected.reversed() : comparatorExpected);
        final CharFieldComparator<BasicEntity, Character> comparator = reversed ? field.reversed() : field.comparator();
        final List<BasicEntity> actual = new ArrayList<>(entities);
        actual.sort(comparator);
        assertEquals(expected, actual);
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void typemapper() {
        when(column.getDatabaseType()).thenReturn(Character.class.getName());
        final Type fieldType = field.typeMapper().getJavaType(column);
        assertEquals(char.class.getSimpleName(), fieldType.getTypeName());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void isUnique() {
        assertFalse(field.isUnique());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void tableAlias() {
        assertNotNull(field.tableAlias());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void setTableAlias() {
        final String name = "tryggve";
        assertEquals(name, field.tableAlias(name).tableAlias());
        assertEquals(field.identifier().getColumnId(), field.tableAlias(name).identifier().getColumnId());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void getNullOrder() {
        assertEquals(NullOrder.LAST, field.getNullOrder());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void isReversed() {
        assertFalse(field.isReversed());
    }
    
    @com.yegor256.AggregateRepeatedTest(100)
    void setter() {
        final char expected = (char) 1;
        final BasicEntity entity = new BasicEntity();
        field.setter().set(entity, expected);
        assertEquals(expected, entity.getVarChar());
    }
}