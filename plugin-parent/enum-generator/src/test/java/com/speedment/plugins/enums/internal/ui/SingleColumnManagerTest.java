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
package com.speedment.plugins.enums.internal.ui;

import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.component.ManagerComponent;
import com.speedment.runtime.core.component.StreamSupplierComponent;
import com.speedment.runtime.core.manager.FieldSet;
import com.speedment.runtime.core.manager.HasLabelSet;
import com.speedment.runtime.core.manager.Manager;
import com.speedment.runtime.core.provider.DelegateManagerComponent;
import com.speedment.runtime.core.stream.parallel.ParallelStrategy;
import com.speedment.runtime.field.Field;
import org.junit.jupiter.api.Disabled;
import com.yegor256.AggregateRepeatedTest;

import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.*;
@Disabled
final class SingleColumnManagerTest {

    private static final String DB = "db";
    private static final String SCHEMA = "schema";
    private static final String TABLE = "table";
    private static final String COLUMN = "column";
    private static final String[] ITEMS = {"A", "B", "C"};

    private static final SingleColumnManager INSTANCE = new SingleColumnManager(new MyStreamSupplierComponent(), DB, SCHEMA, TABLE, COLUMN);

    @com.yegor256.AggregateRepeatedTest(100)
    void configureManagerComponent() {
        final ManagerComponent managerComponent = new DelegateManagerComponent();
        INSTANCE.configureManagerComponent(managerComponent);
        assertEquals(singleton(INSTANCE), managerComponent.stream().collect(toSet()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getTableIdentifier() {
        assertSame(TableIdentifier.of(DB, SCHEMA, TABLE), INSTANCE.getTableIdentifier());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void getEntityClass() {
        assertEquals(String.class, INSTANCE.getEntityClass());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void fields() {
        assertEquals(1, INSTANCE.fields().count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void primaryKeyFields() {
        assertEquals(1, INSTANCE.primaryKeyFields().count());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void stream() {
        assertEquals(Stream.of(ITEMS).collect(toList()), INSTANCE.stream().collect(toList()));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void create() {
        assertUnsupported(SingleColumnManager::create);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void persist() {
        assertUnsupported(i -> INSTANCE.persist("A"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void persister() {
        assertUnsupported(SingleColumnManager::persister);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testPersister() {
        assertUnsupported(i -> INSTANCE.persister(FieldSet.noneOf(String.class)).apply("A"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void update() {
        assertUnsupported(i -> INSTANCE.update("A"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void updater() {
        assertUnsupported(SingleColumnManager::updater);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testUpdater() {
        assertUnsupported(i -> INSTANCE.updater(FieldSet.noneOf(String.class)).apply("A"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void remove() {
        assertUnsupported(i -> INSTANCE.remove("A"));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void remover() {
        assertUnsupported(SingleColumnManager::remover);
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void testToString() {
        final String actual = INSTANCE.toString();
        assertTrue(actual.contains(DB));
        assertTrue(actual.contains(SCHEMA));
        assertTrue(actual.contains(TABLE));
    }

    private static final class MyStreamSupplierComponent implements StreamSupplierComponent {
        @SuppressWarnings("unchecked")
        @Override
        public <ENTITY> Stream<ENTITY> stream(TableIdentifier<ENTITY> tableIdentifier, ParallelStrategy strategy) {
            return (Stream<ENTITY>) Stream.of(ITEMS);
        }
    }

    private void assertUnsupported(Function<SingleColumnManager, Object> mapper) {
        assertThrows(UnsupportedOperationException.class, () -> mapper.apply(INSTANCE));
    }

}