package com.ye.warehouse.controller;

import com.ye.warehouse.entity.*;
import com.ye.warehouse.service.*;
import com.ye.warehouse.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackageName:com.ye.warehouse.controller
 * @ClassName:SystemManagerController
 * @Description:
 * @author:何进业
 * @date:2021/3/25 10:08
 */
@Controller
@RequestMapping("/SM")
public class SystemManagerController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @Autowired
    private WareHouseService wareHouseService;

    @Autowired
    private UserService userService;

    @Value("${warehouse.path.upload.goodsPicture}")
    private String goodsTypePictureUpload;


    @Value("${warehouse.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * 获取所有商品的类别，跳到商品类别管理页面
     *
     * @param model
     * @param page
     * @return
     */
    @RequestMapping("/getGoodsTypes")
    public String getGoodsTypes(Model model, Page page) {
        List<GoodsCategory> goodsCategories = goodsCategoryService.getGoodsTypes(page.getOffset(), page.getLimit());
        page.setPath("/SM/getGoodsTypes");
        page.setRows(goodsCategoryService.getGoodsTypesCount());
        model.addAttribute("list", goodsCategories);
        return "goods_type";
    }

    /**
     * 添加商品类别
     *
     * @param headerImage 文件
     * @param name 文件名
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(path = "/addGoodsTypes", method = RequestMethod.POST)
    public String addGoodsTypes(MultipartFile headerImage, String name, Model model, Page page) {
        if (headerImage == null) {
            model.addAttribute("error", "您还没有选择图片!");
            return getGoodsTypes(model, page);
        }
        String fileName = headerImage.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //生成随机的文件名。
        fileName = StringUtil.generateUUID() + suffix;
        File dest = null;
        dest = new File(goodsTypePictureUpload + "/" + fileName);
        try {
            //存储文件
            headerImage.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = domain + contextPath + "/SM/goodsPicture/" + fileName;
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setGoodsCategoryPicture(url);
        goodsCategory.setGoodsCategoryName(name);
        goodsCategory.setGoodsCategoryStatus(1);
        goodsCategoryService.addGoodsType(goodsCategory);
        model.addAttribute("msg", "添加成功！");
        return getGoodsTypes(model, page);
    }

    /**
     * 删除商品类别，将商品种别状态改为0
     * @param model
     * @param id
     * @param page
     * @return
     */
    @RequestMapping("/deleteGoodsTypes")
    public String deleteGoodsTypes(Model model, int id, Page page) {
        int count = goodsService.getOnGoodsCount(id);
        if (count == 0) {
            goodsCategoryService.updateGoodsTypeStatus(id, 0);
            model.addAttribute("msg", "删除成功");
        } else {
            model.addAttribute("msg", "删除失败，该类别下存在商品正在处理中");
        }
        return getGoodsTypes(model, page);
    }

    /**
     * 获取商品类别图片
     *
     * @param fileName
     * @param response
     */
    @RequestMapping(path = "/goodsPicture/{fileName}", method = RequestMethod.GET)
    public void getGoodsTypePicture(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        fileName = goodsTypePictureUpload + "/" + fileName;
        //文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //响应图片
        response.setContentType("image/" + fileName);
        try (
                OutputStream outputStream = response.getOutputStream();
                FileInputStream fileInputStream = new FileInputStream(fileName);
        ) {
            byte[] buffer = new byte[1024];
            int i = 0;
            while ((i = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传商品类别图片至服务器，路径"e:/warehouse/upload/gp"
     *
     * @param headerImage
     * @param model
     * @param goodsTypeId
     * @param page
     * @return
     */
    @RequestMapping(path = "/pictureUpload", method = RequestMethod.POST)
    public String uploadGoodsTypePicture(MultipartFile headerImage, Model model, int goodsTypeId, Page page) {
        if (headerImage == null) {
            model.addAttribute("error", "您还没有选择图片!");
            return getGoodsTypes(model, page);
        }
        String fileName = headerImage.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //生成随机的文件名。
        fileName = StringUtil.generateUUID() + suffix;
        File dest = null;
        dest = new File(goodsTypePictureUpload + "/" + fileName);
        try {
            //存储文件
            headerImage.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = domain + contextPath+ "/SM/goodsPicture/" + fileName;
        goodsCategoryService.updateGoodsTypePicture(goodsTypeId, url);
        model.addAttribute("msg", "更换成功");
        return getGoodsTypes(model, page);
    }


    /**
     * 更新商品类别名称
     *
     * @param model
     * @param id
     * @param name
     * @param page
     * @return
     */
    @RequestMapping(path = "/updateGoodsTypeName", method = RequestMethod.GET)
    public String updateGoodsTypeName(Model model, int id, String name, Page page) {
        goodsCategoryService.updateGoodsTypeName(id, name);
        return getGoodsTypes(model, page);
    }

    /**
     * 获取不同身份的人员，跳到人员管理界面
     *
     * @param model
     * @param type 人员身份类别
     * @param page
     * @return
     */
    @RequestMapping(path = "/getPeople", method = RequestMethod.GET)
    public String getPeople(Model model, int type, Page page) {
        page.setPath("/SM/getPeople");
        if (type == 1) {
            List<GoodsMan> goodsManList = userService.selectGoodsMen(1);
            page.setRows(goodsManList.size());
            model.addAttribute("list", goodsManList);
        } else if (type == 2) {
            List<QualityMan> qualityManList = userService.selectQualityMen(1);
            page.setRows(qualityManList.size());
            model.addAttribute("list", qualityManList);
        } else if (type == 3) {
            List<WarehouseManager> warehouseManagerList = userService.selectWarehouseManagers(1);
            page.setRows(warehouseManagerList.size());
            model.addAttribute("list", warehouseManagerList);
        }
        model.addAttribute("type", type);
        return "/people";
    }

    /**
     * 添加人员
     *
     * @param model
     * @param name 人员名称
     * @param password 人员密码
     * @param type 人员身份类别
     * @param page
     * @return
     */
    @RequestMapping(path = "/addPeople", method = RequestMethod.POST)
    public String addPeople(Model model, String name, String password, int type, String tel, Page page) {
        Map<String, Object> map = userService.addPeople(name, password, type, tel);
        if (map.get("usernameMsg") != null) {
            model.addAttribute("msg", map.get("usernameMsg"));
        } else if (map.get("telMsg") != null){
            model.addAttribute("msg", map.get("telMsg"));
        } else {
            model.addAttribute("msg", "恭喜添加成功，该人员的账号为" + map.get("id") + "    请牢记，该账号将用于登录使用");
        }
        return getPeople(model, type, page);
    }

    /**
     * 删除人员， 将状态置为0
     *
     * @param model
     * @param userId 人员账号
     * @param type 人员身份类别
     * @param page
     * @return
     */
    @RequestMapping(path = "/deletePeople", method = RequestMethod.GET)
    public String deletePeople(Model model, int userId, int type, Page page) {
        userService.deletePeople(userId, 0, type);
        return getPeople(model, type, page);
    }

    /**
     * 查看每类商品在各种状态下的数量，跳到查看库存界面
     *
     * @param model
     * @param status 状态
     * @param page
     * @return
     */
    @RequestMapping(path = "/getAllGoodsCount", method = RequestMethod.GET)
    public String getAllGoodsCount(Model model, int status, Page page) {
        List<GoodsCategory> goodsCategories = goodsCategoryService.getGoodsTypes(page.getOffset(), page.getLimit());
        page.setPath("/SM/getAllGoodsCount?status=" + status);
        page.setRows(goodsCategoryService.getGoodsTypesCount());
        List<Map<String, Object>> list = new ArrayList<>();
        for (GoodsCategory category : goodsCategories) {
            int count = goodsService.getAllGoodsCountByTypeId(category.getGoodsCategoryId(), status);
            Map<String, Object> map = new HashMap<>();
            map.put("category", category);
            map.put("count", count);
            list.add(map);
        }
        model.addAttribute("list", list);
        model.addAttribute("status", status);
        return "/look_inventory";
    }

    /**
     * 获取仓库容量，跳到容量管理界面
     *
     * @param model
     * @return
     */
    @RequestMapping(path = "/getCapacity", method = RequestMethod.GET)
    public String getCapacity(Model model) {
        int count = goodsService.getOnGoodsCount(-1);
        Warehouse warehouse = wareHouseService.getWarehouse(1);
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("type", "总容量");
        map.put("number", warehouse.getTotalCapacity());
        list.add(0, map);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("type", "可用容量");
        map2.put("number", warehouse.getUsableCapacity());
        list.add(1, map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("type", "已用容量");
        map3.put("number", count);
        list.add(2, map3);
        Map<String, Object> map4 = new HashMap<>();
        map4.put("type", "剩余容量");
        map4.put("number", warehouse.getUsableCapacity() - count);
        list.add(3, map4);
        model.addAttribute("list", list);
        return "/capacity";
    }

    /**
     * 修改仓库可用容量
     *
     * @param model
     * @param capacity
     * @param session
     * @return
     */
    @RequestMapping(path = "/updateCapacity", method = RequestMethod.GET)
    public String updateCapacity(Model model, int capacity, HttpSession session) {
        SystemManager systemManager = (SystemManager) session.getAttribute("user");
        wareHouseService.updateCapacity(1, systemManager.getSystemManagerId(), capacity);
        model.addAttribute("msg", "修改成功！");
        return getCapacity(model);
    }

//    @RequestMapping(value = "/lookTotalRecord", method = RequestMethod.GET)
//    public String lookTotalRecord(Model model, int type){
//        List<Goods> goodsList = goodsService.getAllGoods();
//        List<Map<String, Object>> list = new ArrayList<>();
//        for (Goods goods : goodsList) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("goods", goods);
//            GoodsCategory goodsCategory = goodsCategoryService.selectGoodsType(goods.getGoodsCategoryId());
//            map.put("category", goodsCategory);
//            GoodsApplyRecord goodsApplyRecord = goodsService.getGoodsApplyRecord(goods.getGoodsId());
//            map.put("applyRecord", goodsApplyRecord);
//            GoodsMan inGoodsMan = userService.selectGM(goodsApplyRecord.getGoodsManInId());
//            map.put("inGoodsMan", inGoodsMan);
//            map.put("outGoodsMan", null);
//            map.put("qualityRecord", null);
//            map.put("inQualityMan", null);
//            map.put("outQualityMan", null);
//            map.put("houseRecord", null);
//            map.put("outWarehouseManager", null);
//            map.put("inWarehouseManager", null);
//            GoodsMan outGoodsMan = userService.selectGM(goodsApplyRecord.getGoodsManOutId());
//            if (outGoodsMan != null){
//                map.put("outGoodsMan", outGoodsMan);
//                GoodsQualityRecord qualityRecord = goodsService.getGoodsQualityRecord(goods.getGoodsId());
//                if (qualityRecord != null) {
//                    map.put("qualityRecord", qualityRecord);
//                    QualityMan inQualityMan = userService.selectQM(qualityRecord.getQualityManInId());
//                    if (inQualityMan != null) {
//                        map.put("inQualityMan", inQualityMan);
//                        QualityMan outQualityMan = userService.selectQM(qualityRecord.getQualityManOutId());
//                        if (outQualityMan != null) {
//                            map.put("outQualityMan", outQualityMan);
//                            GoodsHouseRecord goodsHouseRecord = goodsService.getGoodsHouseRecord(goods.getGoodsId());
//                            if (goodsHouseRecord != null) {
//                                map.put("houseRecord", goodsHouseRecord);
//                                WarehouseManager outWarehouseManager = userService.selectWM(goodsHouseRecord.getWarehouseManagerOutId());
//                                if (outWarehouseManager != null) {
//                                    map.put("outWarehouseManager", outWarehouseManager);
//                                    WarehouseManager inWarehouseManager = userService.selectWM(goodsHouseRecord.getWarehouseManagerInId());
//                                    if (inWarehouseManager != null) {
//                                        map.put("inWarehouseManager", inWarehouseManager);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            list.add(map);
//        }
//        model.addAttribute("type", type);
//        model.addAttribute("list", list);
//        return "/look_total_record";
//    }

    /**
     * 查看每件商品当前所在状态，跳到查看进度界面
     * @param model
     * @param page
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/lookProcess", method = RequestMethod.GET)
    public String lookProcess(Model model, Page page, @RequestParam(required = false) Integer goodsId) {
        if (goodsId == null) {
            List<Goods> goodsList = goodsService.getAllGoods(page.getOffset(), page.getLimit());
            page.setPath("/SM/lookProcess");
            page.setRows(goodsService.getAllGoodsCount());
            List<Map<String, Object>> list = new ArrayList<>();
            for (Goods goods : goodsList) {
                Map<String, Object> map = new HashMap<>();
                map.put("goods", goods);
                GoodsCategory goodsCategory = goodsCategoryService.selectGoodsType(goods.getGoodsCategoryId());
                map.put("category", goodsCategory);
                map.put("process", judgeStatus(goods.getGoodsStatus()));
                list.add(map);
            }
            model.addAttribute("list", list);
        } else {
            Map<String, Object> map = new HashMap<>();
            Goods goods = goodsService.getGoodsById((int) goodsId);
            page.setRows(0);
            map.put("goods", goods);
            GoodsCategory goodsCategory = goodsCategoryService.selectGoodsType(goods.getGoodsCategoryId());
            map.put("category", goodsCategory);
            map.put("process", judgeStatus(goods.getGoodsStatus()));
            model.addAttribute("map", map);
        }
        model.addAttribute("goodsId", goodsId);
        return "/look_process";

    }

    /**
     * 根据商品状态码返回状态名称
     * @param status 商品状态码
     * @return
     */
    private String judgeStatus(int status) {
        if (status == 1) {
            return "已申请入库，待质检员质检";
        } else if (status == 2) {
            return "入库质检通过，等待仓库管理员入库";
        } else if (status == 3) {
            return "入库质检失败，已通知商品运输员取回商品";
        } else if (status == 4) {
            return "入库成功, 当前商品在库中";
        } else if (status == 5) {
            return "已申请出库，待质检员质检";
        } else if (status == 6) {
            return "出库质检通过，等待仓库管理员出库";
        } else if (status == 7) {
            return "出库质检失败，已通知商品运输员取回商品";
        } else if (status == 8) {
            return "出库成功, 当前商品不在库中";
        } else {
            return "错误！";
        }
    }
}
