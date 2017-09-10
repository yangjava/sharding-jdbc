/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.rdb.sharding.api;

import com.dangdang.ddframe.rdb.sharding.rule.MasterSlaveRule;
import com.dangdang.ddframe.rdb.sharding.fixture.TestDataSource;
import com.dangdang.ddframe.rdb.sharding.jdbc.core.datasource.MasterSlaveDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public final class MasterSlaveDataSourceFactoryTest {
    
    @Test
    public void assertCreateDataSourceForSingleSlave() throws SQLException {
        Map<String, DataSource> slaveDataSourceMap = new HashMap<>(1, 1);
        slaveDataSourceMap.put("slave_ds", new TestDataSource("slave_ds"));
        assertThat(MasterSlaveDataSourceFactory.createDataSource(
                new MasterSlaveRule("logic_ds", "master_ds", new TestDataSource("master_ds"), slaveDataSourceMap)), instanceOf(MasterSlaveDataSource.class));
    }
    
    @Test
    public void assertCreateDataSourceForMultipleSlaves() throws SQLException {
        Map<String, DataSource> slaveDataSourceMap = new HashMap<>(2, 1);
        slaveDataSourceMap.put("slave_ds_0", new TestDataSource("slave_ds_0"));
        slaveDataSourceMap.put("slave_ds_1", new TestDataSource("slave_ds_1"));
        assertThat(MasterSlaveDataSourceFactory.createDataSource(
                new MasterSlaveRule("logic_ds", "master_ds", new TestDataSource("master_ds"), slaveDataSourceMap)), instanceOf(MasterSlaveDataSource.class));
    }
}
