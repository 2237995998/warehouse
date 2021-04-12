package com.ye.warehouse.dao;

import com.ye.warehouse.entity.Equipment;
import com.ye.warehouse.entity.EquipmentCategory;
import com.ye.warehouse.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @PackageName:com.ye.warehouse.dao
 * @ClassName:EquipmentMapper
 * @Description:
 * @author:何进业
 * @date:2021/3/30 17:42
 */
@Mapper
public interface EquipmentMapper {
    int insertEquipment(Equipment equipment);
    Equipment selectEquipment(int id);
    int updateEquipmentStatus(int id, int status);


    List<Equipment> selectAllByEquipmentTypeId(int id, int status, int offset, int limit);

    int selectEquipmentsCountByTypeId(int id, int status);
    List<Equipment> selectAllByEquipmentStatus(int status);


}
