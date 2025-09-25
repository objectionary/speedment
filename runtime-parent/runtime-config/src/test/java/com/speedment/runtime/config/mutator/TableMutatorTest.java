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

package com.speedment.runtime.config.mutator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.speedment.runtime.config.Table;
import com.speedment.runtime.config.mutator.trait.HasAliasMutatorMixin;
import com.speedment.runtime.config.mutator.trait.HasEnabledMutatorMixin;
import com.speedment.runtime.config.mutator.trait.HasIdMutatorMixin;
import com.speedment.runtime.config.mutator.trait.HasNameMutatorMixin;
import com.yegor256.AggregateRepeatedTest;

import java.util.HashMap;

final class TableMutatorTest implements
        HasEnabledMutatorMixin<Table, TableMutator<Table>>,
        HasIdMutatorMixin<Table, TableMutator<Table>>,
        HasNameMutatorMixin<Table, TableMutator<Table>>,
        HasAliasMutatorMixin<Table, TableMutator<Table>> {

    @Override
    @SuppressWarnings("unchecked")
    public TableMutator<Table> getMutatorInstance() {
        return (TableMutator<Table>) Table.create(null, new HashMap<>()).mutator();
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void setView() {
        assertDoesNotThrow(() -> getMutatorInstance().setView(true));
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void addNewColumn() {
        assertNotNull(getMutatorInstance().addNewColumn());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void addNewIndex() {
        assertNotNull(getMutatorInstance().addNewIndex());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void addNewForeignKey() {
        assertNotNull(getMutatorInstance().addNewForeignKey());
    }

    @com.yegor256.AggregateRepeatedTest(100)
    void addNewPrimaryKey() {
        assertNotNull(getMutatorInstance().addNewPrimaryKeyColumn());
    }
}
