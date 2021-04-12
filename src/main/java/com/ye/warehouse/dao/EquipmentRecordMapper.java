package com.ye.warehouse.dao;

import com.ye.warehouse.entity.Equipment;
import com.ye.warehouse.entity.EquipmentRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * @PackageName:com.ye.warehouse.dao
 * @ClassName:equipmentRecordMapper
 * @Description:
 * @author:何进业
 * @date:2021/4/2 12:38
 */
@Mapper
public interface EquipmentRecordMapper {

    int addRecord(EquipmentRecord equipmentRecord);

    int updateRecord(int id, Date destroyTime, int wmId);

    EquipmentRecord selectRecord(int id);
}
