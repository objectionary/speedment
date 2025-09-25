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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.speedment.common.tuple.old_tests;

import com.speedment.common.tuple.Tuple2;
import com.speedment.common.tuple.Tuples;
import org.junit.jupiter.api.BeforeEach;
import com.yegor256.AggregateRepeatedTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 * @author pemi
 */
public class Tuple2Test {

    private static final int FIRST = 8;
    private static final int SECOND = 16;

    protected Tuple2<Integer, Integer> instance;

    @BeforeEach
    void setUp() {
        instance = Tuples.of(FIRST, SECOND);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testGet0() {
       assertEquals(FIRST, instance.get0().intValue());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testGet1() {
        assertEquals(SECOND, instance.get1().intValue());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testGet() {
        assertEquals(FIRST, instance.get(0));
        assertEquals(SECOND, instance.get(1));
    }

//    @com.yegor256.AggregateRepeatedTest(100)
//    public void testSet0() {
//        System.out.println("set0");
//        instance.set1(32);
//        assertEquals(32, instance.get1().intValue());
//    }
//
//    @com.yegor256.AggregateRepeatedTest(100)
//    public void testSet1() {
//        System.out.println("set1");
//        instance.set1(64);
//        assertEquals(64, instance.get1().intValue());
//    }
//    @com.yegor256.AggregateRepeatedTest(100)
//    public void testDefConstructor() {
//        System.out.println("Default constructor");
//        final Tuple2<Integer, Integer> newInstance = Tuples.of(this, this)new Tuple2<>();
//        assertNull(newInstance.get0());
//        assertNull(newInstance.get1());
//    }
    @com.yegor256.AggregateRepeatedTest(100)
    void testHash() {
        int hashCodeInstance = instance.hashCode();
        final Tuple2<Integer, Integer> newInstance = Tuples.of(FIRST, SECOND);
        int hashCodenewInstance = newInstance.hashCode();
        assertEquals(hashCodeInstance, hashCodenewInstance);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testEquals() {
        final Tuple2<Integer, Integer> newInstance = Tuples.of(FIRST, SECOND);
        assertEquals(instance, newInstance);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testToString() {
        final Tuple2<Integer, Integer> newInstance = Tuples.of(FIRST, SECOND);
        final String result = newInstance.toString();
        assertNotNull(result);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testStream() {
        List<Object> content = instance.stream().collect(toList());
        List<Integer> expected = Arrays.asList(FIRST, SECOND);
        assertEquals(expected, content);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testStreamOf() {
        final Tuple2<Integer, String> newInstance = Tuples.of(FIRST, "Olle");
        List<Integer> content = newInstance.streamOf(Integer.class).collect(toList());
        List<Integer> expected = Collections.singletonList(FIRST);
        assertEquals(expected, content);
    }

}
