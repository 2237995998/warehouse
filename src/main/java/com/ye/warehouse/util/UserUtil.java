package com.ye.warehouse.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ye.warehouse.entity.GoodsMan;
import com.ye.warehouse.entity.QualityMan;
import com.ye.warehouse.entity.SystemManager;
import com.ye.warehouse.entity.WarehouseManager;

/**
 * @PackageName:com.ye.warehouse.util
 * @ClassName:UserUtil
 * @Description:
 * @author:何进业
 * @date:2021/4/2 21:13
 */
public class UserUtil {

    /**
     * 获取某一用户的账号
     * @param object 用户对象
     * @param role 身份类别
     * @return
     */
    public static int getId(Object object, int role){
        ObjectMapper objectMapper = new ObjectMapper();
        if (role == 0){
            SystemManager systemManager = objectMapper.convertValue(object, SystemManager.class);
            return systemManager.getSystemManagerId();
        } else if (role == 1){
            GoodsMan goodsMan = objectMapper.convertValue(object, GoodsMan.class);
            return goodsMan.getGoodsManId();
        } else if (role == 2){
            QualityMan qualityMan = objectMapper.convertValue(object, QualityMan.class);
            return qualityMan.getQualityManId();
        } else if (role == 3){
            WarehouseManager warehouseManager = objectMapper.convertValue(object, WarehouseManager.class);
            return warehouseManager.getWarehouseManagerId();
        }
        return 0;
    }

    public static String getName(Object object, int role){
        ObjectMapper objectMapper = new ObjectMapper();
        if (role == 0){
            SystemManager systemManager = objectMapper.convertValue(object, SystemManager.class);
            return systemManager.getSystemManagerName();
        } else if (role == 1){
            GoodsMan goodsMan = objectMapper.convertValue(object, GoodsMan.class);
            return goodsMan.getGoodsManName();
        } else if (role == 2){
            QualityMan qualityMan = objectMapper.convertValue(object, QualityMan.class);
            return qualityMan.getQualityManName();
        } else if (role == 3){
            WarehouseManager warehouseManager = objectMapper.convertValue(object, WarehouseManager.class);
            return warehouseManager.getWarehouseManagerName();
        }
        return null;
    }

    public static String getHeader(Object object, int role){
        ObjectMapper objectMapper = new ObjectMapper();
        if (role == 0){
            SystemManager systemManager = objectMapper.convertValue(object, SystemManager.class);
            return systemManager.getSystemManagerHeader();
        } else if (role == 1){
            GoodsMan goodsMan = objectMapper.convertValue(object, GoodsMan.class);
            return goodsMan.getGoodsManHeader();
        } else if (role == 2){
            QualityMan qualityMan = objectMapper.convertValue(object, QualityMan.class);
            return qualityMan.getQualityManHeader();
        } else if (role == 3){
            WarehouseManager warehouseManager = objectMapper.convertValue(object, WarehouseManager.class);
            return warehouseManager.getWarehouseManagerHeader();
        }
        return null;
    }

    public static String getPassword(Object object, int role) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (role == 0) {
            SystemManager systemManager = objectMapper.convertValue(object, SystemManager.class);
            return systemManager.getSystemManagerPwd();
        } else if (role == 1) {
            GoodsMan goodsMan = objectMapper.convertValue(object, GoodsMan.class);
            return goodsMan.getGoodsManPwd();
        } else if (role == 2) {
            QualityMan qualityMan = objectMapper.convertValue(object, QualityMan.class);
            return qualityMan.getQualityManPwd();
        } else if (role == 3) {
            WarehouseManager warehouseManager = objectMapper.convertValue(object, WarehouseManager.class);
            return warehouseManager.getWarehouseManagerPwd();
        }
        return null;
    }


}
