package com.ye.warehouse.dao;

import com.ye.warehouse.entity.EquipmentCategory;
import com.ye.warehouse.entity.EquipmentRecord;
import com.ye.warehouse.entity.GoodsCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @PackageName:com.ye.warehouse.dao
 * @ClassName:EquipmenType
 * @Description:
 * @author:何进业
 * @date:2021/3/30 18:43
 */
@Mapper
public interface EquipmentTypeMapper {

    int insertEquipmentType(EquipmentCategory equipmentCategory);

    EquipmentCategory selectEquipmentTypeById(int id);

    int updatePicture(int id, String url);
    int updateName(int id, String name);

    int updateStatus(int id, int status);

    List<EquipmentCategory> getEquipmentTypes(int offset, int limit);

    List<EquipmentCategory> getAllEquipmentsTypes();

}
