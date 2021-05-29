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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @PackageName:com.ye.warehouse.controller
 * @ClassName:WarehouseController
 * @Description:
 * @author:何进业
 * @date:2021/3/30 18:39
 */
@Controller
@RequestMapping("/WM")
public class WarehouseManagerController {

    @Autowired
    private EquipmentCategoryService equipmentCategoryService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @Autowired
    private UserService userService;


    @Value("${warehouse.path.upload.equipmentPicture}")
    private String equipmentTypePictureUpload;


    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * 分页获取器材类别，跳到器材类别管理界面
     * @param model
     * @param page
     * @return
     */
    @RequestMapping("/getEquipmentTypes")
    public String getEquipmentTypes(Model model, Page page){
        List<EquipmentCategory> equipmentCategories = equipmentCategoryService.getEquipmentTypes(page.getOffset(), page.getLimit());
        page.setPath("/WM/getEquipmentTypes");
        page.setRows(equipmentCategoryService.getEquipmentTypesCount());
        model.addAttribute("list", equipmentCategories);
        return "/equipment_type";
    }

    /**
     * 获取器材类别图片
     * @param fileName 图片文件名
     * @param response
     */
    @RequestMapping(path = "/equipmentPicture/{fileName}", method = RequestMethod.GET)
    public void getEquipmentTypePicture(@PathVariable("fileName")String fileName, HttpServletResponse response){
        fileName = equipmentTypePictureUpload + "/" + fileName;
        //文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //响应图片
        response.setContentType("image/" + fileName);
        try (
                OutputStream outputStream = response.getOutputStream();
                FileInputStream fileInputStream = new FileInputStream(fileName);
        ){
            byte[] buffer = new byte[1024];
            int i = 0;
            while ((i = fileInputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传器材类别图片至服务器
     * @param headerImage 图片文件
     * @param model
     * @param equipmentTypeId 器材种别码
     * @param page
     * @return
     */
    @RequestMapping(path = "/pictureUpload", method = RequestMethod.POST)
    public String uploadGoodsTypePicture(MultipartFile headerImage, Model model, int equipmentTypeId, Page page){
        if (headerImage.isEmpty()) {
            model.addAttribute("msg", "您还没有选择图片!");
            return getEquipmentTypes(model, page);
        }
        String fileName = headerImage.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //生成随机的文件名。
        fileName = StringUtil.generateUUID() + suffix;
        File dest = null;
        dest = new File(equipmentTypePictureUpload + "/" + fileName);
        try {
            //存储文件
            headerImage.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = contextPath + "/WM/equipmentPicture/" + fileName;
        equipmentCategoryService.updatePicture(equipmentTypeId, url);
        model.addAttribute("msg", "更换成功");
        return getEquipmentTypes(model, page);
    }

    /**
     * 添加器材类别
     * @param headerImage 图片文件
     * @param name 名称
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(path = "/addEquipmentType", method = RequestMethod.POST)
    public String addEquipmentType(MultipartFile headerImage, String name, Model model, Page page){
        if (headerImage.isEmpty()) {
            model.addAttribute("msg", "您还没有选择图片!");
            return getEquipmentTypes(model, page);
        }
        String fileName = headerImage.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //生成随机的文件名。
        fileName = StringUtil.generateUUID() + suffix;
        File dest = null;
        dest = new File(equipmentTypePictureUpload + "/" + fileName);
        try {
            //存储文件
            headerImage.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = contextPath + "/WM/equipmentPicture/" + fileName;
        EquipmentCategory equipmentCategory = new EquipmentCategory();
        equipmentCategory.setEquipmentCategoryPicture(url);
        equipmentCategory.setEquipmentCategoryName(name);
        equipmentCategory.setEquipmentCategoryStatus(1);
        equipmentCategoryService.addEquipmentType(equipmentCategory);
        model.addAttribute("msg", "添加成功！");
        return getEquipmentTypes(model, page);
    }

    /**
     * 删除器材类别
     * @param model
     * @param id 器材种别码
     * @param page
     * @return
     */
    @RequestMapping("/deleteEquipmentType")
    public String deleteEquipmentType(Model model, int id, Page page){
        int count  = equipmentService.getEquipmentsCountByTypeId(id, 1);
        if (count != 0){
            model.addAttribute("msg", "删除失败，该类别下存在器材在使用");
        }else {
            equipmentCategoryService.updateEquipmentTypeStatus(id, 0);
            model.addAttribute("msg", "删除成功");
        }
        return getEquipmentTypes(model, page);
    }

    /**
     * 修改器材种别名称
     * @param model
     * @param id 器材种别码
     * @param name 名称
     * @param page
     * @return
     */
    @RequestMapping(path = "/updateEquipmentTypeName", method = RequestMethod.GET)
    public String updateEquipmentTypeName(Model model, int id, String name, Page page){
        equipmentCategoryService.updateName(id, name);
        model.addAttribute("msg", "更新成功！");
        return getEquipmentTypes(model, page);
    }

//    @RequestMapping(path = "/getAllEquipmentsCount", method = RequestMethod.GET)
//    public String getAllEquipmentsCount(Model model, int status, Page page){
//        List<EquipmentCategory> equipmentCategories = equipmentCategoryService.getEquipmentTypes(page.getOffset(), page.getLimit());
//        page.setPath("/WM/getAllEquipmentsCount?status=" + status);
//        page.setRows(equipmentCategoryService.getEquipmentTypesCount());
//        List<Map<String, Object>> list = new ArrayList<>();
//        for (EquipmentCategory equipmentCategory : equipmentCategories) {
//            int count = equipmentService.getEquipmentsCountByTypeId(equipmentCategory.getEquipmentCategoryId(), status);
//            Map<String, Object> map = new HashMap<>();
//            map.put("category", equipmentCategory);
//            map.put("count", count);
//            list.add(map);
//        }
//        model.addAttribute("list", list);
//        model.addAttribute("status", status);
//        return "/look_equipments";
//    }

    /**
     * 分页获取不同状态的器材
     * @param model
     * @param status 状态
     * @param page
     * @return
     */
    @RequestMapping(path = "/getAllEquipments", method = RequestMethod.GET)
    public String getAllEquipments(Model model, int status, Page page){
        List<Equipment> equipmentList = equipmentService.getAllEquipmentsByTypeId(-1, status, page.getOffset(), page.getLimit());
        page.setPath("/WM/getAllEquipments?status=" + status);
        page.setRows(equipmentService.getEquipmentsCountByTypeId(-1, status));
        List<Map<String, Object>> list = new ArrayList<>();
        for (Equipment equipment : equipmentList) {
            EquipmentCategory equipmentCategory = equipmentCategoryService.getEquipmentTypeById(equipment.getEquipmentCategoryId());
            EquipmentRecord equipmentRecord = equipmentService.getEquipmentRecord(equipment.getEquipmentId());
            Map<String, Object> map = new HashMap<>();
            map.put("record", equipmentRecord);
            if (status == 0){
                WarehouseManager warehouseManager = userService.selectWM(equipmentRecord.getWarehouseManagerDestroyId());
                map.put("WM", warehouseManager);
            } else if (status == 1){
                WarehouseManager warehouseManager = userService.selectWM(equipmentRecord.getWarehouseManagerBuyId());
                map.put("WM", warehouseManager);
            }
            map.put("category", equipmentCategory);
            map.put("equipment", equipment);
            list.add(map);
        }
        model.addAttribute("list", list);
        model.addAttribute("status", status);
        return "/look_equipments";
    }

    /**
     * 前往添加器材界面
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(path = "/toAddEquipment", method = RequestMethod.GET)
    public String toAddEquipment(Model model, Page page){
        List<EquipmentCategory> equipmentCategories = equipmentCategoryService.getEquipmentTypes(page.getOffset(), page.getLimit());
        page.setPath("/WM/toAddEquipment");
        page.setRows(equipmentCategoryService.getEquipmentTypesCount());
        model.addAttribute("categories", equipmentCategories);
        return "/add_equipment";
    }

    /**
     * 添加器材
     * @param typeId 器材种别码
     * @param count 数量
     * @param model
     * @param session
     * @param page
     * @return
     */
    @RequestMapping(value = "/addEquipment", method = RequestMethod.POST)
    public String addEquipment(int typeId, int count, Model model, HttpSession session, Page page){
        WarehouseManager warehouseManager = (WarehouseManager) session.getAttribute("user");
        for (int i = 0; i < count; i++){
            Equipment equipment = new Equipment();
            equipment.setEquipmentStatus(1);
            equipment.setEquipmentCategoryId(typeId);
            equipmentService.addEquipment(equipment);
            EquipmentRecord equipmentRecord = new EquipmentRecord();
            equipmentRecord.setBuyTime(new Date());
            equipmentRecord.setEquipmentId(equipment.getEquipmentId());
            equipmentRecord.setWarehouseManagerBuyId(warehouseManager.getWarehouseManagerId());
            equipmentService.addEquipmentRecord(equipmentRecord);
        }
        model.addAttribute("msg", "添加成功！");
        return toAddEquipment(model, page);
    }

    /**
     * 销毁器材
     * @param model
     * @param equipmentId 器材编号
     * @param page
     * @param session
     * @return
     */
    @RequestMapping("/deleteEquipment")
    public String deleteEquipment(Model model, int equipmentId, Page page, HttpSession session){
        equipmentService.updateEquipmentStatus(equipmentId, 0);
        WarehouseManager warehouseManager = (WarehouseManager) session.getAttribute("user");
        equipmentService.updateEquipmentRecordStatus(equipmentId, warehouseManager.getWarehouseManagerId());
        model.addAttribute("msg", "销毁成功");
        return getAllEquipments(model, 1, page);
    }

    /**
     * 分页获取入库质检成功的所有商品，跳到入库管理界面
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(value = "/handleInHouse", method = RequestMethod.GET)
    public String handleInHouse(Model model, Page page){
        List<Goods> goodsList = goodsService.getAllGoods(-1, 2, page.getOffset(), page.getLimit());
        page.setPath("/WM/handleInHouse");
        page.setRows(goodsService.getAllGoodsCountByTypeId(-1, 2));
        List<Map<String, Object>> list = new ArrayList<>();
        for (Goods goods : goodsList) {
            Map<String, Object> map = new HashMap<>();
            map.put("goods", goods);
            GoodsCategory goodsCategory = goodsCategoryService.selectGoodsType(goods.getGoodsCategoryId());
            map.put("category", goodsCategory);
            GoodsApplyRecord goodsApplyRecord = goodsService.getGoodsApplyRecord(goods.getGoodsId());
            map.put("applyRecord", goodsApplyRecord);
            GoodsMan goodsMan = userService.selectGM(goodsApplyRecord.getGoodsManInId());
            map.put("goodsMan", goodsMan);
            GoodsQualityRecord qualityRecord = goodsService.getGoodsQualityRecord(goods.getGoodsId());
            map.put("qualityRecord", qualityRecord);
            QualityMan qualityMan = userService.selectQM(qualityRecord.getQualityManInId());
            map.put("qualityMan", qualityMan);
            list.add(map);
        }
        model.addAttribute("list", list);
        return "/handle_in_house";
    }

    /**
     * 分页获取出库质检成功的所有商品，跳到出库管理界面
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(value = "/handleOutHouse", method = RequestMethod.GET)
    public String handleOutHouse(Model model, Page page){
        List<Goods> goodsList = goodsService.getAllGoods(-1, 6, page.getOffset(), page.getLimit());
        page.setPath("/WM/handleOutHouse");
        page.setRows(goodsService.getAllGoodsCountByTypeId(-1, 6));
        List<Map<String, Object>> list = new ArrayList<>();
        for (Goods goods : goodsList) {
            Map<String, Object> map = new HashMap<>();
            map.put("goods", goods);
            GoodsCategory goodsCategory = goodsCategoryService.selectGoodsType(goods.getGoodsCategoryId());
            map.put("category", goodsCategory);
            GoodsApplyRecord goodsApplyRecord = goodsService.getGoodsApplyRecord(goods.getGoodsId());
            map.put("applyRecord", goodsApplyRecord);
            GoodsMan goodsMan = userService.selectGM(goodsApplyRecord.getGoodsManOutId());
            map.put("goodsMan", goodsMan);
            GoodsQualityRecord qualityRecord = goodsService.getGoodsQualityRecord(goods.getGoodsId());
            map.put("qualityRecord", qualityRecord);
            QualityMan qualityMan = userService.selectQM(qualityRecord.getQualityManOutId());
            map.put("qualityMan", qualityMan);
            list.add(map);
        }
        model.addAttribute("list", list);
        return "/handle_out_house";
    }

    /**
     * 入库
     * @param goodsId 商品编号
     * @param location 存放位置
     * @param model
     * @param session
     * @param page
     * @return
     */
    @RequestMapping(value = "/inHouse", method = RequestMethod.GET)
    public String inHouse(int goodsId, int location, Model model, HttpSession session, Page page){
        List<Goods> goodsList = goodsService.getAllGoods(-1, 4, page.getOffset(), page.getLimit());
        List<Goods> goodsList1 = goodsService.getAllGoods(-1, 5, page.getOffset(), page.getLimit());
        List<Goods> goodsList2 = goodsService.getAllGoods(-1, 6, page.getOffset(), page.getLimit());
        goodsList.addAll(goodsList1);
        goodsList.addAll(goodsList2);
        for (Goods goods : goodsList) {
            if (location == goods.getGoodsLocation()){
                model.addAttribute("msg", "入库失败,该位置已经存放商品！");
                return handleInHouse(model, page);
            }
        }
        WarehouseManager warehouseManager = (WarehouseManager) session.getAttribute("user");
        goodsService.updateGoodsStatus(goodsId, 4);
        goodsService.updateLocation(goodsId, location);
        goodsService.addGoodsHouseInRecord(goodsId, warehouseManager.getWarehouseManagerId());
        model.addAttribute("msg", "入库成功！");
        return handleInHouse(model, page);
    }

    /**
     * 出库
     * @param goodsId 商品编号
     * @param model
     * @param session
     * @param page
     * @return
     */
    @RequestMapping(value = "/outHouse", method = RequestMethod.GET)
    public String outHouse(int goodsId, Model model, HttpSession session, Page page){
        WarehouseManager warehouseManager = (WarehouseManager) session.getAttribute("user");
        goodsService.updateGoodsStatus(goodsId, 8);
        goodsService.updateLocation(goodsId, 0);
        goodsService.updateGoodsHouseOutRecord(goodsId, warehouseManager.getWarehouseManagerId());
        model.addAttribute("msg", "出库成功！");
        return handleOutHouse(model, page);
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
        page.setPath("/WM/getAllGoodsCount?status=" + status);
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
}
