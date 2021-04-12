package com.ye.warehouse.dao;

import com.ye.warehouse.entity.GoodsApplyRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * @PackageName:com.ye.warehouse.dao
 * @ClassName:GoodsApplyRecordMapper
 * @Description:
 * @author:何进业
 * @date:2021/3/27 16:16
 */
@Mapper
public interface GoodsApplyRecordMapper {
    GoodsApplyRecord getApplyRecord(int id);
    int insertApplyRecord(GoodsApplyRecord goodsApplyRecord);
    int updateOutApplyTime(int id, Date outApplyTime, int gmId);
}
