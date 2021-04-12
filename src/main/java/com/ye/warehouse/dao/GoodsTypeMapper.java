package com.ye.warehouse.dao;

import com.ye.warehouse.entity.GoodsCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @PackageName:com.ye.warehouse.dao
 * @ClassName:GoodsTypeMapper
 * @Description:
 * @author:何进业
 * @date:2021/3/25 10:11
 */
@Mapper
public interface GoodsTypeMapper {
    List<GoodsCategory> getGoodsTypes(int offset, int limit);

    int getGoodsTypesCount();

    GoodsCategory selectGoodsType(int type);
    int insertGoodsType(GoodsCategory goodsCategory);
    int updateStatus(int id, int status);
    int updatePicture(int id, String url);
    int updateName(int id, String name);
}
