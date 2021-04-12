package com.ye.warehouse.service;

import com.ye.warehouse.dao.EquipmentMapper;
import com.ye.warehouse.dao.EquipmentRecordMapper;
import com.ye.warehouse.entity.Equipment;
import com.ye.warehouse.entity.EquipmentCategory;
import com.ye.warehouse.entity.EquipmentRecord;
import com.ye.warehouse.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @PackageName:com.ye.warehouse.service
 * @ClassName:EquipmentService
 * @Description:
 * @author:何进业
 * @date:2021/3/30 22:09
 */
@Service
public class EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private EquipmentRecordMapper equipmentRecordMapper;

    /**
     * 获取某一种别下的某一状态的器材数量
     * @param typeId 器材种别码
     * @param status 状态
     * @return
     */
    public int getEquipmentsCountByTypeId(int typeId, int status){
        return equipmentMapper.selectEquipmentsCountByTypeId(typeId, status);
    }

    /**
     * 分页获取某一种别下的某一状态的所有器材
     * @param typeId 器材种别码
     * @param status 状态
     * @param offset 起始位置
     * @param limit 数量
     * @return
     */
    public List<Equipment> getAllEquipmentsByTypeId(int typeId, int status, int offset, int limit){
        return equipmentMapper.selectAllByEquipmentTypeId(typeId, status, offset, limit);
    }

    /**
     * 添加器材
     * @param equipment
     * @return
     */
    public int addEquipment(Equipment equipment){
        return equipmentMapper.insertEquipment(equipment);
    }

    /**
     * 添加器材记录表
     * @param equipmentRecord
     * @return
     */
    public int addEquipmentRecord(EquipmentRecord equipmentRecord){
        return equipmentRecordMapper.addRecord(equipmentRecord);
    }

    /**
     * 销毁器材时修改器材记录表中销毁器材相关信息
     * @param id 器材种别码
     * @param wmId 销毁时的仓库管理员账号
     * @return
     */
    public int updateEquipmentRecordStatus(int id, int wmId){
        return equipmentRecordMapper.updateRecord(id, new Date(), wmId);
    }

    /**
     * 根据器材编号获取器材记录
     * @param id
     * @return
     */
    public EquipmentRecord getEquipmentRecord(int id){
        return equipmentRecordMapper.selectRecord(id);
    }
    /**
     * 更新器材状态
     * @param id 器材种别码
     * @param status 状态
     * @return
     */
    public int updateEquipmentStatus(int id, int status){
        return equipmentMapper.updateEquipmentStatus(id, status);
    }
}
