package com.ye.warehouse.service;

import com.ye.warehouse.dao.UserMapper;
import com.ye.warehouse.entity.GoodsMan;
import com.ye.warehouse.entity.QualityMan;
import com.ye.warehouse.entity.SystemManager;
import com.ye.warehouse.entity.WarehouseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @PackageName:com.ye.warehouse.service
 * @ClassName:UserService
 * @Description:
 * @author:何进业
 * @date:2021/3/24 10:17
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    /**
     * 根据账号和身份类别获取map对象，包含账号、身份类别、昵称、密码、头像、状态、电话
     * @param id   账号
     * @param role 身份类别
     * @return
     */
    public Map<String, Object> selectUser(int id, int role) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("role", role);
        if (role == 0) {
            SystemManager manager = selectSM(id);
            if (manager == null) {
                return null;
            } else {
                map.put("roleName", "系统管理员");
                map.put("name", manager.getSystemManagerName());
                map.put("pwd", manager.getSystemManagerPwd());
                map.put("header", manager.getSystemManagerHeader());
                map.put("status", manager.getSystemManagerStatus());
                map.put("tel", manager.getSystemManagerTel());
            }
        } else if (role == 1) {
            GoodsMan goodsMan = selectGM(id);
            if (goodsMan == null) {
                return null;
            } else {
                map.put("roleName", "商品运输员");
                map.put("name", goodsMan.getGoodsManName());
                map.put("pwd", goodsMan.getGoodsManPwd());
                map.put("header", goodsMan.getGoodsManHeader());
                map.put("status", goodsMan.getGoodsManStatus());
                map.put("tel", goodsMan.getGoodsManTel());
            }
        } else if (role == 2) {
            QualityMan qualityMan = selectQM(id);
            if (qualityMan == null) {
                return null;
            } else {
                map.put("roleName", "质检员");
                map.put("name", qualityMan.getQualityManName());
                map.put("pwd", qualityMan.getQualityManPwd());
                map.put("header", qualityMan.getQualityManHeader());
                map.put("status", qualityMan.getQualityManStatus());
                map.put("tel", qualityMan.getQualityManTel());
            }
        } else if (role == 3) {
            WarehouseManager warehouseManager = selectWM(id);
            if (warehouseManager == null) {
                return null;
            } else {
                map.put("roleName", "仓库管理员");
                map.put("name", warehouseManager.getWarehouseManagerName());
                map.put("pwd", warehouseManager.getWarehouseManagerPwd());
                map.put("header", warehouseManager.getWarehouseManagerHeader());
                map.put("status", warehouseManager.getWarehouseManagerStatus());
                map.put("tel", warehouseManager.getWarehouseManagerTel());
            }
        }
        return map;
    }

    /**
     * 根据账号获取系统管理员对象
     * @param id 账号
     * @return
     */
    public SystemManager selectSM(int id) {
        return userMapper.selectSystemManagerById(id);
    }

    /**
     * 根据账号获取商品运输员对象
     * @param id 账号
     * @return
     */
    public GoodsMan selectGM(int id) {
        return userMapper.selectGoodsManById(id);
    }

    /**
     * 根据账号获取质检员对象
     * @param id 账号
     * @return
     */
    public QualityMan selectQM(int id) {
        return userMapper.selectQualityManById(id);
    }

    /**
     * 根据账号获取仓库管理员对象
     * @param id 账号
     * @return
     */
    public WarehouseManager selectWM(int id) {
        return userMapper.selectWarehouseManagerById(id);
    }

    /**
     * 系统管理员登录验证
     * @param userId  账号
     * @param userPwd 密码
     * @return
     */
    public SystemManager smLogin(int userId, String userPwd) {
        SystemManager systemManager = userMapper.selectSystemManagerById(userId);
        if ((systemManager != null) && userPwd.equals(systemManager.getSystemManagerPwd())) {
            return systemManager;
        }
        return null;
    }

    /**
     * 商品运输员登录验证
     * @param userId  账号
     * @param userPwd 密码
     * @return
     */
    public GoodsMan gmLogin(int userId, String userPwd) {
        GoodsMan goodsMan = userMapper.selectGoodsManById(userId);
        if ((goodsMan != null) && userPwd.equals(goodsMan.getGoodsManPwd())) {
            return goodsMan;
        }
        return null;
    }

    /**
     * 质检员登录验证
     * @param userId  账号
     * @param userPwd 密码
     * @return
     */
    public QualityMan qmLogin(int userId, String userPwd) {
        QualityMan qualityMan = userMapper.selectQualityManById(userId);
        if ((qualityMan != null) && userPwd.equals(qualityMan.getQualityManPwd())) {
            return qualityMan;
        }
        return null;
    }

    /**
     * 仓库管理员登录验证
     * @param userId  账号
     * @param userPwd 密码
     * @return
     */
    public WarehouseManager wmLogin(int userId, String userPwd) {
        WarehouseManager warehouseManager = userMapper.selectWarehouseManagerById(userId);
        if ((warehouseManager != null) && userPwd.equals(warehouseManager.getWarehouseManagerPwd())) {
            return warehouseManager;
        }
        return null;
    }


    /**
     * 更新头像
     * @param userId    账号
     * @param headerUrl 头像获取路径
     * @param role      身份类别
     * @return
     */
    public int updateHeader(int userId, String headerUrl, int role) {
        if (role == 0) {
            return userMapper.updateSystemManagerHeader(userId, headerUrl);
        } else if (role == 1) {
            return userMapper.updateGoodsManHeader(userId, headerUrl);
        } else if (role == 2) {
            return userMapper.updateQualityManHeader(userId, headerUrl);
        } else if (role == 3) {
            return userMapper.updateWarehouseManagerHeader(userId, headerUrl);
        }
        return 0;
    }

    /**
     * 更新密码
     * @param uerId    账号
     * @param password 新密码
     * @param role     身份类别
     * @return
     */
    public int updatePassword(int uerId, String password, int role) {
        if (role == 0) {
            return userMapper.updateSystemManagerPassword(uerId, password);
        } else if (role == 1) {
            return userMapper.updateGoodsManPassword(uerId, password);
        } else if (role == 2) {
            return userMapper.updateQualityManPassword(uerId, password);
        } else if (role == 3) {
            return userMapper.updateWarehouseManagerPassword(uerId, password);
        }
        return 0;
    }

    /**
     * 分页查找某一状态下的商品运输员
     * @param status 状态
     * @param offset
     * @param limit
     * @return
     */
    public List<GoodsMan> selectGoodsMen(int status, int offset, int limit) {
        return userMapper.selectGoodsMen(status, offset, limit);
    }

    /**
     * 查找某一状态下的商品运输员数量
     * @param status 状态
     * @return
     */
    public int selectGoodsMenCount(int status) {
        List<GoodsMan> goodsManList = userMapper.selectGoodsMen(status, 0, 0);
        return goodsManList.size();
    }

    /**
     * 分页查找某一状态下的质检员
     * @param status 状态
     * @param offset
     * @param limit
     * @return
     */
    public List<QualityMan> selectQualityMen(int status, int offset, int limit) {
        return userMapper.selectQualityMen(status, offset, limit);
    }

    /**
     * 查找某一状态下的质检员数量
     * @param status 状态
     * @return
     */
    public int selectQualityMenCount(int status) {
        List<QualityMan> qualityManList = userMapper.selectQualityMen(status, 0, 0);
        return qualityManList.size();
    }

    /**
     * 分页查找某一状态下的仓库管理员
     * @param status 状态
     * @param offset
     * @param limit
     * @return
     */
    public List<WarehouseManager> selectWarehouseManagers(int status, int offset, int limit) {
        return userMapper.selectWarehouseManagers(status, offset, limit);
    }
    /**
     * 查找某一状态下的仓库管理员数量
     * @param status 状态
     * @return
     */
    public int selectWarehouseManagersCount(int status) {
        List<WarehouseManager> warehouseManagerList = userMapper.selectWarehouseManagers(status, 0, 0);
        return warehouseManagerList.size();
    }
    /**
     * 添加人员
     * @param userName  人员名称
     * @param password  人员密码
     * @param role      人员身份类型
     * @param tel  电话
     * @return
     */
    public Map<String, Object> addPeople(String userName, String password, int role, String tel) {
        Map<String, Object> map = new HashMap<>();
        if (role == 0) {
            SystemManager systemManager = userMapper.selectSystemManagerByName(userName);
            if (systemManager != null) {
                map.put("usernameMsg", "该昵称已被使用!");
                return map;
            }
            systemManager = userMapper.selectSystemManagerByTel(tel);
            if (systemManager != null) {
                map.put("telMsg", "改手机号已经被注册！");
                return map;
            }
            systemManager = new SystemManager();
            systemManager.setSystemManagerName(userName);
            systemManager.setSystemManagerPwd(password);
            systemManager.setSystemManagerStatus(1);
            systemManager.setSystemManagerTel(tel);
            systemManager.setSystemManagerHeader(String.format("http://localhost:8080/warehouse/header/%d.png", new Random().nextInt(6) + 1));
            userMapper.insertSystemManager(systemManager);
            map.put("id", systemManager.getSystemManagerId());
        } else if (role == 1) {
            GoodsMan goodsMan = userMapper.selectGoodsManByName(userName);
            if (goodsMan != null) {
                map.put("usernameMsg", "该账号已存在");
                return map;
            }
            goodsMan = userMapper.selectGoodsManByTel(tel);
            if (goodsMan != null){
                map.put("telMsg", "改手机号已经被注册！");
                return map;
            }
            goodsMan = new GoodsMan();
            goodsMan.setGoodsManName(userName);
            goodsMan.setGoodsManPwd(password);
            goodsMan.setGoodsManStatus(1);
            goodsMan.setGoodsManTel(tel);
            goodsMan.setGoodsManHeader(String.format("http://localhost:8080/warehouse/header/%d.png", new Random().nextInt(6) + 1));

            userMapper.insertGoodsMan(goodsMan);
            map.put("id", goodsMan.getGoodsManId());
        } else if (role == 2) {
            QualityMan qualityMan = userMapper.selectQualityManByName(userName);
            if (qualityMan != null) {
                map.put("usernameMsg", "该账号已存在");
                return map;
            }
            qualityMan = userMapper.selectQualityManByTel(tel);
            if (qualityMan != null) {
                map.put("telMsg", "改手机号已经被注册！");
                return map;
            }
            qualityMan = new QualityMan();
            qualityMan.setQualityManName(userName);
            qualityMan.setQualityManPwd(password);
            qualityMan.setQualityManStatus(1);
            qualityMan.setQualityManTel(tel);
            qualityMan.setQualityManHeader(String.format("http://localhost:8080/warehouse/header/%d.png", new Random().nextInt(6) + 1));

            userMapper.insertQualityMan(qualityMan);
            map.put("id", qualityMan.getQualityManId());
        } else if (role == 3) {
            WarehouseManager warehouseManager = userMapper.selectWarehouseManagerByName(userName);
            if (warehouseManager != null) {
                map.put("usernameMsg", "该账号已存在");
                return map;
            }
            warehouseManager = userMapper.selectWarehouseManagerByTel(tel);
            if (warehouseManager != null) {
                map.put("telMsg", "改手机号已经被注册！");
                return map;
            }
            warehouseManager = new WarehouseManager();
            warehouseManager.setWarehouseManagerName(userName);
            warehouseManager.setWarehouseManagerPwd(password);
            warehouseManager.setWarehouseManagerStatus(1);
            warehouseManager.setWarehouseManagerTel(tel);
            warehouseManager.setWarehouseManagerHeader(String.format("http://localhost:8080/warehouse/header/%d.png", new Random().nextInt(6) + 1));
            userMapper.insertWarehouseManager(warehouseManager);
            map.put("id", warehouseManager.getWarehouseManagerId());
        }
        return map;
    }

    /**
     * 更新人员状态
     * @param id     人员账号
     * @param role   人员身份类别
     * @param status 状态
     * @return
     */
    public int deletePeople(int id, int status, int role) {
        if (role == 1) {
            return userMapper.updateGoodsManStatus(id, status);
        } else if (role == 2) {
            return userMapper.updateQualityManStatus(id, status);
        } else if (role == 3) {
            return userMapper.updateWarehouseManagerStatus(id, status);
        }
        return 0;
    }
}
