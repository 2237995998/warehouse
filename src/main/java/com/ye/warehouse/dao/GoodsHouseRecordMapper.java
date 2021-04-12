package com.ye.warehouse.dao;

import com.ye.warehouse.entity.GoodsHouseRecord;
import com.ye.warehouse.entity.GoodsQualityRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * @PackageName:com.ye.warehouse.dao
 * @ClassName:GoodsHouseRecordMapper
 * @Description:
 * @author:何进业
 * @date:2021/3/31 10:15
 */
@Mapper
public interface GoodsHouseRecordMapper {
    int insertRecord(GoodsHouseRecord goodsHouseRecord);
    int updateRecordOutResult(int id, Date time, int wmId);
    GoodsHouseRecord getRecordById(int id);
}
