package com.ye.warehouse.dao;

import com.ye.warehouse.entity.GoodsMan;
import com.ye.warehouse.entity.QualityMan;
import com.ye.warehouse.entity.SystemManager;
import com.ye.warehouse.entity.WarehouseManager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @PackageName:com.ye.warehouse.dao
 * @ClassName:UserMapper
 * @Description:四类用户数据库表的操作
 * @author:何进业
 * @date:2021/3/24 10:22
 */
@Mapper
public interface UserMapper {
    /**
     * 系统管理员表的操作
     */
    SystemManager selectSystemManagerById(int id);

    SystemManager selectSystemManagerByName(String username);

    SystemManager selectSystemManagerByTel(String tel);

    int insertSystemManager(SystemManager systemManager);

    int updateSystemManagerStatus(int id, int status);

    int updateSystemManagerHeader(int id, String headerUrl);

    int updateSystemManagerPassword(int id, String password);

    /**
     * 商品运输员表的操作
     */
    GoodsMan selectGoodsManById(int id);

    GoodsMan selectGoodsManByName(String username);


    GoodsMan selectGoodsManByTel(String tel);

    int insertGoodsMan(GoodsMan goodsMan);

    int updateGoodsManStatus(int id, int status);

    int updateGoodsManHeader(int id, String headerUrl);

    int updateGoodsManPassword(int id, String password);

    List<GoodsMan> selectGoodsMen(int status, int offset, int limit);


    /**
     * 质检员表的操作
     */
    QualityMan selectQualityManById(int id);

    QualityMan selectQualityManByName(String username);

    QualityMan selectQualityManByTel(String tel);

    int insertQualityMan(QualityMan qualityMan);

    int updateQualityManStatus(int id, int status);

    int updateQualityManHeader(int id, String headerUrl);

    int updateQualityManPassword(int id, String password);

    List<QualityMan> selectQualityMen(int status, int offset, int limit);



    /**
     * 仓库管理员表的操作
     */
    WarehouseManager selectWarehouseManagerById(int id);

    WarehouseManager selectWarehouseManagerByName(String username);

    WarehouseManager selectWarehouseManagerByTel(String tel);

    int insertWarehouseManager(WarehouseManager warehouseManager);

    int updateWarehouseManagerStatus(int id, int status);

    int updateWarehouseManagerHeader(int id, String headerUrl);

    int updateWarehouseManagerPassword(int id, String password);

    List<WarehouseManager> selectWarehouseManagers(int status, int offset, int limit);
}
