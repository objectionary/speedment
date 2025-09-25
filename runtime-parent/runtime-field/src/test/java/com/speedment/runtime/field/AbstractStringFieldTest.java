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

import com.speedment.runtime.config.Column;
import com.speedment.runtime.field.comparator.FieldComparator;
import com.speedment.runtime.field.predicate.Inclusion;
import com.yegor256.AggregateRepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

/**
 *
 * @author pemi
 */
@ExtendWith(MockitoExtension.class)
abstract class AbstractStringFieldTest extends BaseFieldTest {

    private final Set<String> SAMPLES = unmodifiableSet(Stream.of("a", "b", "AbCd", "aQhAj!wgW")
        .collect(toSet()));

    protected StringField<TestEntity, String> field;
    private final RerferenceFieldTestSupport<String> support;
    private @Mock Column column;

    public AbstractStringFieldTest(StringField<TestEntity, String> field) {
        this.field = requireNonNull(field);
        this.support = new RerferenceFieldTestSupport<>(field, Function.identity(), TestEntity::getName, TestEntity::setName, RerferenceFieldTestSupport.NAME, "Sven");
/*        this.support = new RerferenceFieldTestSupport<>(field);*/
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testSupportMethods() {
        support.testAll();
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void equalIgnoreCase() {
        final List<TestEntity> expected = collect(e -> e.getName() != null && e.getName().equalsIgnoreCase("abcdef"));
        final List<TestEntity> result = collect(field.equalIgnoreCase("abcdef"));

        assertEquals(expected, result);
        assertEquals(4, collect(field.isNull()).size());
        assertThrows(NullPointerException.class, () ->
            collect(field.equalIgnoreCase(null))
        );

    }

    @com.yegor256.AggregateRepeatedTest(100)
    void notEqualIgnoreCase() {
        final List<TestEntity> expected = collect(e -> e.getName() != null && !e.getName().equalsIgnoreCase("abcdef"));
        final List<TestEntity> result = collect(field.notEqualIgnoreCase("abcdef"));

        assertEquals(expected, result);
        assertEquals(25, collect(field.isNotNull()).size());
        assertThrows(NullPointerException.class, () ->
            collect(field.notEqualIgnoreCase(null))
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void startsWith() {

        //assertTrue(STARTS_WITH_PREDICATE.test("ab", "abc"));
        final List<TestEntity> expected = collect(e -> e.getName() != null && e.getName().startsWith("abc"));
        //final List<Entity> result = collect(e -> e.getName() != null && STARTS_WITH_PREDICATE.test(e.getName(), "abc"));
        final List<TestEntity> result = collect(field.startsWith("abc"));

        printList("startswith expected", expected);
        printList("startswith result", result);

        assertEquals(expected, result);
        assertThrows(NullPointerException.class, () ->
                collect(field.startsWith(null))
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void startsWithIgnoreCase() {
        final List<TestEntity> expected = collect(e -> e.getName() != null && e.getName().toLowerCase().startsWith("abc"));
        final List<TestEntity> result = collect(field.startsWithIgnoreCase("abc"));
        assertEquals(expected, result);
        assertThrows(NullPointerException.class, () ->
            collect(field.startsWithIgnoreCase(null))
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void endWithIgnoreCase() {
        final List<TestEntity> expected = collect(e -> e.getName() != null && e.getName().toLowerCase().endsWith("c"));
        final List<TestEntity> result = collect(field.endsWithIgnoreCase("c"));
        assertEquals(expected, result);
        assertThrows(NullPointerException.class, () ->
            collect(field.endsWithIgnoreCase(null))
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void endsWith() {
        
        final List<TestEntity> expected = collect(e -> e.getName() != null && e.getName().endsWith("f"));
        final List<TestEntity> result = collect(field.endsWith("f"));

        printList("endswith expected", expected);
        printList("endswith result", result);

        assertEquals(expected, result);
        assertThrows(NullPointerException.class, () ->
            collect(field.endsWith(null))
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void contains()  {

        final List<TestEntity> expected = collect(e -> e.getName() != null && e.getName().contains("a"));
        final List<TestEntity> result = collect(field.contains("a"));

        printList("contains expected", expected);
        printList("contains result", result);

        assertEquals(expected, result);
        assertThrows(NullPointerException.class, () ->
                collect(field.contains(null))
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void isEmpty() {
        final List<TestEntity> expected = collect(e -> e.getName() == null || e.getName().isEmpty());
        final List<TestEntity> result = collect(field.isEmpty());
        assertEquals(expected, result);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void isNotEmpty()  {
        final List<TestEntity> expected = collect(e -> e.getName() != null && !e.getName().isEmpty());
        final List<TestEntity> result = collect(field.isNotEmpty());
        assertEquals(expected, result);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void in() {
        final List<TestEntity> expected = collect(e -> SAMPLES.contains(e.getName()));
        final List<TestEntity> result = collect(field.in(SAMPLES));

        assertEquals(expected, result);
        assertThrows(NullPointerException.class, () ->
            collect(field.in((Collection<String>) null))
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void notIn() {
        final List<TestEntity> expected = collect(e -> e.getName() != null && !SAMPLES.contains(e.getName()));
        final List<TestEntity> result = collect(field.notIn(SAMPLES));

        assertEquals(expected, result);
        assertThrows(NullPointerException.class, () ->
            collect(field.notIn((Collection<String>) null))
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void betweenIE() {
        final List<TestEntity> expected = collect(e -> e.getName() != null && "b".compareTo(e.getName()) <= 0 && "f".compareTo(e.getName()) > 0);
        final List<TestEntity> result = collect(field.between("b", "f"));
        assertEquals(expected, result);
        assertThrows(NullPointerException.class, () ->
            collect(field.between(null, "A", Inclusion.START_EXCLUSIVE_END_EXCLUSIVE))
        );        assertThrows(NullPointerException.class, () ->
            collect(field.between("A", null, Inclusion.START_EXCLUSIVE_END_EXCLUSIVE))
        );        assertThrows(NullPointerException.class, () ->
            collect(field.between("A", "A", null))
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void betweenII() {
        final List<TestEntity> expected = collect(e -> e.getName() != null && "b".compareTo(e.getName()) <= 0 && "f".compareTo(e.getName()) >= 0);
        final List<TestEntity> result = collect(field.between("b", "f", Inclusion.START_INCLUSIVE_END_INCLUSIVE));
        assertEquals(expected, result);
        assertThrows(NullPointerException.class, () ->
            collect(field.in((Collection<String>) null))
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void betweenEI() {
        final List<TestEntity> expected = collect(e -> e.getName() != null && "b".compareTo(e.getName()) < 0 && "f".compareTo(e.getName()) >= 0);
        final List<TestEntity> result = collect(field.between("b", "f", Inclusion.START_EXCLUSIVE_END_INCLUSIVE));
        assertEquals(expected, result);
        assertThrows(NullPointerException.class, () ->
            collect(field.in((Collection<String>) null))
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void betweenEE() {
        final List<TestEntity> expected = collect(e -> e.getName() != null && "b".compareTo(e.getName()) < 0 && "f".compareTo(e.getName()) > 0);
        final List<TestEntity> result = collect(field.between("b", "f", Inclusion.START_EXCLUSIVE_END_EXCLUSIVE));
        assertEquals(expected, result);
        assertThrows(NullPointerException.class, () ->
            collect(field.in((Collection<String>) null))
        );
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void toStringTest() {
        assertNotNull(field.toString());
    }



    /*
    @com.yegor256.AggregateRepeatedTest(100)
    void getField() {
        final StringField<TestEntity, String> other = field.getField();
        assertNotNull(other);
    }
*/
    @com.yegor256.AggregateRepeatedTest(100)
    void comparator() {
        comparator(false);
    }

    /*
    @com.yegor256.AggregateRepeatedTest(100)
    void comparatorReversed() {
        comparator(true);
    }
    */


    void comparator(final boolean reversed) {
        final List<TestEntity> list = Arrays.asList(new TestEntityImpl(3, "C", null), new TestEntityImpl(2, "A", null), new TestEntityImpl(2, "B", null));

        final Comparator<TestEntity> comparatorExpected = Comparator.comparing(TestEntity::getName);
        final List<TestEntity> expected = new ArrayList<>(list);
        expected.sort(reversed ? comparatorExpected.reversed() : comparatorExpected);

        // final FieldComparator<TestEntity> comparator = reversed ? field.reversed() : field.comparator();
        final FieldComparator<TestEntity> comparator =  field.comparator();

        final List<TestEntity> actual =  new ArrayList<>(list);
        actual.sort(comparator);

        assertEquals(expected, actual);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    @SuppressWarnings({"raw", "unchecked"})
    void typemapper() {
        when(column.findDatabaseType()).thenReturn((Class) String.class);
        final Type fieldType = field.typeMapper().getJavaType(column);
        assertEquals(String.class, fieldType);
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
/*
    @com.yegor256.AggregateRepeatedTest(100)
    void getNullOrder() {
        assertEquals(NullOrder.LAST, field.getNullOrder());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void isReversed() {
        assertFalse(field.isReversed());
    }
*/
    @com.yegor256.AggregateRepeatedTest(100)
    void setter() {
        final String expected = "Olle";
        final TestEntity entity = new TestEntityImpl(1, "A", null);
        field.setter().set(entity, expected);
        assertEquals(expected, entity.getName());
    }

}
