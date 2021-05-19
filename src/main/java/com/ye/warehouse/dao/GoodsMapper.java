package com.ye.warehouse.dao;

import com.ye.warehouse.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @PackageName:com.ye.warehouse.dao
 * @ClassName:GoodsMapper
 * @Description:
 * @author:何进业
 * @date:2021/3/25 16:26
 */
@Mapper
public interface GoodsMapper {
    Goods selectGoodsById(int id);
    List<Goods> selectAllByGoodsTypeId(int id, int status, int offset, int limit);
    int selectAllCountByGoodsTypeId(int id, int status);
    int getOnGoodsCount(int type);
    int insertGoods(Goods goods);
    int updateGoodsStatus(int id, int status);
    List<Goods> selectALlInGoodsByCount(int type, int count);

    int updateLocation(int id, int location);

    List<Goods> selectAllGoods(int offset, int limit);

    int selectAllGoodsCount();

    int selectGoodsCountByTypeId(int typeId, int status);
}
