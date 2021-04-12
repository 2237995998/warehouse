package com.ye.warehouse.service;

import com.ye.warehouse.dao.WarehouseMapper;
import com.ye.warehouse.entity.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @PackageName:com.ye.warehouse.service
 * @ClassName:WareHouseService
 * @Description:
 * @author:何进业
 * @date:2021/3/30 12:31
 */
@Service
public class WareHouseService {
    @Autowired
    private WarehouseMapper warehouseMapper;

    /**
     * 修改仓库可用容量
     * @param wid 仓库id
     * @param sid 系统管理员id
     * @param usableCapacity 可用容量
     * @return
     */
    public int updateCapacity(int wid, int sid, int usableCapacity){
        return warehouseMapper.updateUsableCapacity(wid, sid, usableCapacity);
    }

    /**
     * 根据id获取仓库实体
     * @param wid 仓库id
     * @return
     */
    public Warehouse getWarehouse(int wid){
        return warehouseMapper.selectWarehouse(wid);
    }
}
