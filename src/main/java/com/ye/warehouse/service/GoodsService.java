package com.ye.warehouse.service;

import com.ye.warehouse.dao.GoodsApplyRecordMapper;
import com.ye.warehouse.dao.GoodsHouseRecordMapper;
import com.ye.warehouse.dao.GoodsMapper;
import com.ye.warehouse.dao.GoodsQualityRecordMapper;
import com.ye.warehouse.entity.Goods;
import com.ye.warehouse.entity.GoodsApplyRecord;
import com.ye.warehouse.entity.GoodsHouseRecord;
import com.ye.warehouse.entity.GoodsQualityRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @PackageName:com.ye.warehouse.service
 * @ClassName:GoodsService
 * @Description:
 * @author:何进业
 * @date:2021/3/25 12:27
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsApplyRecordMapper goodsApplyRecordMapper;

    @Autowired
    private GoodsQualityRecordMapper goodsQualityRecordMapper;

    @Autowired
    private GoodsHouseRecordMapper goodsHouseRecordMapper;

    /**
     * 根据商品id获取商品
     * @param id
     * @return
     */
    public Goods getGoodsById(int id){
        return goodsMapper.selectGoodsById(id);
    }

    public List<Goods> getAllGoods(int typeId, int status, int offset, int limit){
        return goodsMapper.selectAllByGoodsTypeId(typeId, status ,offset, limit);
    }

    /**
     * 分页获取所有商品
     * @param offset 起始位置
     * @param limit 数量
     * @return
     */
    public List<Goods> getAllGoods(int offset, int limit){
        return goodsMapper.selectAllGoods(offset, limit);
    }

    /**
     * 获取所有商品数量
     * @return
     */
    public int getAllGoodsCount(){
        return goodsMapper.selectAllGoodsCount();
    }

    /**
     *获取某一类别下的某一个状态的所有商品数量
     * @param typeId 商品类别
     * @param status 状态
     * @return
     */
    public int getAllGoodsCountByTypeId(int typeId, int status){
        return goodsMapper.selectAllCountByGoodsTypeId(typeId, status);
    }

    /**
     * 获取某一类别下状态为1、2、4、5、6的商品数量（即占据仓库名义上容量的商品数量），若参数为-1则表示查询所有类别
     * @param type 商品类别码
     * @return
     */
    public int getOnGoodsCount(int type){
        return goodsMapper.getOnGoodsCount(type);
    }


    /**
     * 获取某一类别下的某一状态的商品数量
     * @param typeId 商品类别
     * @param status 状态
     * @return
     */
    public int getGoodsCountByTypeId(int typeId, int status){
        return goodsMapper.selectGoodsCountByTypeId(typeId, status);
    }

    /**
     * 获取一定数量的某一类别商品，按照申请入库时间倒序排序
     * @param type 商品类别
     * @param count 数量
     * @return
     */
    public List<Goods> getAllInGoodsByCount(int type, int count){
        return goodsMapper.selectALlInGoodsByCount(type, count);
    }

    /**
     *修改商品状态
     * @param id 商品编号
     * @param status 状态
     * @return
     */
    public int updateGoodsStatus(int id, int status){
        return goodsMapper.updateGoodsStatus(id, status);
    }

    public int updateLocation(int id, int location){
        return goodsMapper.updateLocation(id, location);
    }

    /**
     * 添加申请入库记录
     * @param goodsType 商品类别
     * @param goodsManId 申请入库的商品运输员账号
     * @return
     */
    @Transactional
    public int addApplyRecord(int goodsType, int goodsManId){
        Goods goods = new Goods();
        goods.setGoodsCategoryId(goodsType);
        goods.setGoodsStatus(1);
        int result = goodsMapper.insertGoods(goods);
        if (result == 1) {
//            int a = 1/0;
            GoodsApplyRecord goodsApplyRecord = new GoodsApplyRecord();
            goodsApplyRecord.setGoodsId(goods.getGoodsId());
            goodsApplyRecord.setGoodsManInId(goodsManId);
            goodsApplyRecord.setInApplyTime(new Date());
            int re = goodsApplyRecordMapper.insertApplyRecord(goodsApplyRecord);
            return re;
        }
        return 0;
    }

    /**
     * 修改商品入库出库申请记录表中的出库申请相关信息
     * @param id 商品编号
     * @param outApplyTime 出库申请时间
     * @param gmId 申请出库时的商品运输员
     * @return
     */
    public int updateOutApplyTime(int id, Date outApplyTime, int gmId){
        return goodsApplyRecordMapper.updateOutApplyTime(id, outApplyTime, gmId);
    }

    /**
     * 根据商品入库出库申请记录表id获取申请记录表
     * @param id
     * @return
     */
    public GoodsApplyRecord getGoodsApplyRecord(int id){
        return goodsApplyRecordMapper.getApplyRecord(id);
    }

    /**
     * 根据商品入库出库质检记录表id获取质检记录表
     * @param id
     * @return
     */
    public GoodsQualityRecord getGoodsQualityRecord(int id){
        return goodsQualityRecordMapper.selectRecord(id);
    }

    /**
     * 添加质检记录表
     * @param goodsId 商品编号
     * @param goodsManId 入库质检时的质检员
     * @param result 质检结果
     * @return
     */
    public int addQualityRecord(int goodsId, int goodsManId, int result){
        GoodsQualityRecord goodsQualityRecord = new GoodsQualityRecord();
        goodsQualityRecord.setGoodsId(goodsId);
        goodsQualityRecord.setGoodsQualityInTime(new Date());
        goodsQualityRecord.setQualityManInId(goodsManId);
        goodsQualityRecord.setGoodsQualityInResult(result);
        return goodsQualityRecordMapper.insertRecord(goodsQualityRecord);
    }

    /**
     * 出库质检时填写质检记录表出库质检相关信息
     * @param goodsId 商品编号
     * @param result 质检结果
     * @param qmId 出库质检时的质检员账号
     * @return
     */
    public int updateQualityOutRecordResult(int goodsId, int result, int qmId){
        return goodsQualityRecordMapper.updateRecordOutResult(goodsId, new Date(), result, qmId);
    }

    /**
     * 入库时添加入库记录表
     * @param goodsId 商品编号
     * @param wmId 入库时仓库管理员账号
     * @return
     */
    public int addGoodsHouseInRecord(int goodsId, int wmId){
        GoodsHouseRecord goodsHouseRecord = new GoodsHouseRecord();
        goodsHouseRecord.setGoodsId(goodsId);
        goodsHouseRecord.setInTime(new Date());
        goodsHouseRecord.setWarehouseManagerInId(wmId);
        return goodsHouseRecordMapper.insertRecord(goodsHouseRecord);
    }

    /**
     * 出库时添加出库入库记录表中出库相关信息
     * @param goodsId 商品编号
     * @param wmId 出库时仓库管理员账号
     * @return
     */
    public int updateGoodsHouseOutRecord(int goodsId, int wmId){
        return goodsHouseRecordMapper.updateRecordOutResult(goodsId, new Date(), wmId);
    }


    public GoodsHouseRecord getGoodsHouseRecord(int id){
        return goodsHouseRecordMapper.getRecordById(id);
    }

}
