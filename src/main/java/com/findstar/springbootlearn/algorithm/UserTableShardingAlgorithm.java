package com.findstar.springbootlearn.algorithm;

// import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
// import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
// import org.springframework.stereotype.Component;
//
// import java.util.Collection;

/**
 * @author : findStar
 * @date :  2021/10/10 2:23 下午
 */
// @Component
// public class UserTableShardingAlgorithm implements PreciseShardingAlgorithm<String> {
//
// 	@Override
// 	public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
// 		// 分片键的值 11111
// 		String value = shardingValue.getValue();
// 		// 分片列名称 id
// 		String columnName = shardingValue.getColumnName();
// 		// 逻辑表名称 tb_order
// 		String logicTableName = shardingValue.getLogicTableName();
//
// 		return  logicTableName + '_' + value.hashCode() % 2;
// 	}
//
// }


public class UserTableShardingAlgorithm{

}
