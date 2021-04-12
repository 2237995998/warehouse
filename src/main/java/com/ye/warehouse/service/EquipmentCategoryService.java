package com.ye.warehouse.service;

import com.ye.warehouse.dao.EquipmentTypeMapper;
import com.ye.warehouse.entity.EquipmentCategory;
import com.ye.warehouse.entity.EquipmentRecord;
import com.ye.warehouse.entity.GoodsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackageName:com.ye.warehouse.service
 * @ClassName:EquipmentCategoryService
 * @Description:
 * @author:何进业
 * @date:2021/3/30 18:58
 */
@Service
public class EquipmentCategoryService {

    @Autowired
    private EquipmentTypeMapper equipmentTypeMapper;

    /**
     * 分页获取所有器材种别列表
     * @param offset 起始位置
     * @param limit 数量
     * @return
     */
    public List<EquipmentCategory> getEquipmentTypes(int offset ,int limit){
        return equipmentTypeMapper.getEquipmentTypes(offset, limit);
    }

    /**
     *获取所有器材种别列表
     * @return
     */
    public List<EquipmentCategory> getAllEquipmentTypes(){
        return equipmentTypeMapper.getAllEquipmentsTypes();
    }

    /**
     * 获取所有器材种别数量
     * @return
     */
    public int getEquipmentTypesCount() {
        List<EquipmentCategory> equipmentCategories = getAllEquipmentTypes();
        return equipmentCategories.size();
    }

    /**
     * 根据器材种别码获取器材种别对象
     * @param id 器材种别码
     * @return
     */
    public EquipmentCategory getEquipmentTypeById(int id){
        return equipmentTypeMapper.selectEquipmentTypeById(id);
    }

    /**
     * 添加器材种别
     * @param equipmentCategory
     * @return
     */
    public int addEquipmentType(EquipmentCategory equipmentCategory){
        return equipmentTypeMapper.insertEquipmentType(equipmentCategory);
    }

    /**
     * 修改器材种别状态
     * @param id 器材种别码
     * @param status 状态
     * @return
     */
    public int updateEquipmentTypeStatus(int id, int status){

        return equipmentTypeMapper.updateStatus(id, status);
    }

    /**
     * 修改器材种别图片
     * @param id 器材种别码
     * @param picture 图片获取地址
     * @return
     */
    public int updatePicture(int id, String picture){
        return equipmentTypeMapper.updatePicture(id, picture);
    }

    /**
     * 修改器材种别名称
     * @param id 器材种别码
     * @param name 名称
     * @return
     */
    public int updateName(int id, String name){
        return equipmentTypeMapper.updateName(id, name);
    }


}
