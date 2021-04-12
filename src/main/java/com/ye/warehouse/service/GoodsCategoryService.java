package com.ye.warehouse.service;

import com.ye.warehouse.dao.GoodsMapper;
import com.ye.warehouse.dao.GoodsTypeMapper;
import com.ye.warehouse.entity.Goods;
import com.ye.warehouse.entity.GoodsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackageName:com.ye.warehouse.service
 * @ClassName:GoodsCategoryService
 * @Description:
 * @author:何进业
 * @date:2021/3/25 12:28
 */
@Service
public class GoodsCategoryService {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 分页获取商品类别
     * @param offset 起始位置
     * @param limit 数量
     * @return
     */
    public List<GoodsCategory> getGoodsTypes(int offset, int limit){
        return goodsTypeMapper.getGoodsTypes(offset, limit);
    }

    /**
     * 获取所有商品类别数量
     * @return
     */
    public int getGoodsTypesCount(){
        return goodsTypeMapper.getGoodsTypesCount();
    }

    /**
     * 根据商品类别码获取商品类别
     * @param type 商品类别码
     * @return
     */
    public GoodsCategory selectGoodsType(int type){
        return goodsTypeMapper.selectGoodsType(type);
    }

    /**
     * 添加商品类别
     * @param goodsCategory
     * @return
     */
    public int addGoodsType(GoodsCategory goodsCategory){
        return goodsTypeMapper.insertGoodsType(goodsCategory);
    }

    public int updateGoodsTypeStatus(int id, int status){
        return goodsTypeMapper.updateStatus(id, status);
    }

    /**
     * 更新商品类别图片
     * @param id 商品类别码
     * @param url 图片获取路径
     * @return
     */
    public int updateGoodsTypePicture(int id, String url){
        return goodsTypeMapper.updatePicture(id, url);
    }

    /**
     * 更新商品类别名称
     * @param id 商品类别码
     * @param name 商品类别新名称
     * @return
     */
    public int updateGoodsTypeName(int id, String name){
        return goodsTypeMapper.updateName(id, name);
    }
}
