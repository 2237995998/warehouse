package com.ye.warehouse.dao;

import com.ye.warehouse.entity.GoodsQualityRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * @PackageName:com.ye.warehouse.dao
 * @ClassName:GoodsQualityRecordMapper
 * @Description:
 * @author:何进业
 * @date:2021/3/30 15:44
 */
@Mapper
public interface GoodsQualityRecordMapper {
    int insertRecord(GoodsQualityRecord goodsQualityRecord);
    int updateRecordOutResult(int id, Date time, int result, int qmId);
    GoodsQualityRecord selectRecord(int id);
}
