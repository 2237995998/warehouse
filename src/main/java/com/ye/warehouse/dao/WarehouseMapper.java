package com.ye.warehouse.dao;

import com.ye.warehouse.entity.Warehouse;
import org.apache.ibatis.annotations.Mapper;

/**
 * @PackageName:com.ye.warehouse.dao
 * @ClassName:WarehouseMapper
 * @Description:
 * @author:何进业
 * @date:2021/3/27 17:31
 */
@Mapper
public interface WarehouseMapper {
    int updateUsableCapacity(int wid, int sid, int usableCapacity);
    Warehouse selectWarehouse(int wid);
}
