package com.ye.warehouse.controller;

import com.ye.warehouse.entity.*;
import com.ye.warehouse.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @PackageName:com.ye.warehouse.controller
 * @ClassName:GoodsManController
 * @Description:
 * @author:何进业
 * @date:2021/3/28 9:29
 */
@Controller
@RequestMapping("/GM")
public class GoodsManController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @Autowired
    private WareHouseService wareHouseService;

    /**
     * 前往申请入库界面
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(path = "/toInApply", method = RequestMethod.GET)
    public String toInApply(Model model, Page page){
        List<GoodsCategory> goodsCategories = goodsCategoryService.getGoodsTypes(page.getOffset(), page.getLimit());
        page.setRows(goodsCategoryService.getGoodsTypesCount());
        page.setPath("/GM/toInApply");
        model.addAttribute("list", goodsCategories);
        return "/in_apply";
    }

    /**
     * 前往申请出库界面
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(path = "/toOutApply", method = RequestMethod.GET)
    public String toOutApply(Model model, Page page){
        List<GoodsCategory> goodsCategories = goodsCategoryService.getGoodsTypes(page.getOffset(), page.getLimit());
        page.setRows(goodsCategoryService.getGoodsTypesCount());
        page.setPath("/GM/toOutApply");
        List<Map<String, Object>> list = new ArrayList<>();
        for (GoodsCategory category : goodsCategories) {
            int count = goodsService.getGoodsCountByTypeId(category.getGoodsCategoryId(), 4, page.getOffset(), page.getLimit());
            Map<String, Object> map = new HashMap<>();
            map.put("category", category);
            map.put("count", count);
            list.add(map);
        }
        model.addAttribute("list", list);
        return "/out_apply";
    }

    /**
     * 申请入库
     * @param count 数量
     * @param goodsType 商品类别
     * @param model
     * @param session
     * @param page
     * @return
     */
    @RequestMapping(path = "/inApply", method = RequestMethod.POST)
    public String inApply(int count, int goodsType, Model model, HttpSession session, Page page){
        GoodsMan goodsMan = (GoodsMan)session.getAttribute("user");
        boolean flag = true;
        int use = goodsService.getOnGoodsCount(-1);
        int usable = wareHouseService.getWarehouse(1).getUsableCapacity();
        if (usable < (count - use)){
            model.addAttribute("msg", "库存容量不足！申请失败");
            return toInApply(model, page);
        }
        for(int i = 0; i < count; i++){
            int result = goodsService.addApplyRecord(goodsType, goodsMan.getGoodsManId());
            if (result != 1) {
                flag = false;
            }
        }
        if (flag){
            model.addAttribute("msg", "申请成功！");
        } else{
            model.addAttribute("msg", "申请失败！");
        }
        return toInApply(model, page);
    }

    /**
     * 申请出库
     * @param model
     * @param goodsType 商品类别码
     * @param count 数量
     * @param session
     * @param page
     * @return
     */
    @RequestMapping(path = "/outApply", method = RequestMethod.POST)
    public String outApply(Model model, int goodsType, int count, HttpSession session, Page page){
        List<Goods> goodsList = goodsService.getAllInGoodsByCount(goodsType, count);
        if (goodsList.size() < count){
            model.addAttribute("msg", "申请失败，申请该类商品出库的数量大于在库存中的数量！");
        } else {
            for (Goods goods : goodsList) {
                goodsService.updateGoodsStatus(goods.getGoodsId(), 5);
                GoodsMan goodsMan = (GoodsMan)session.getAttribute("user");
                goodsService.updateOutApplyTime(goods.getGoodsId(), new Date(), goodsMan.getGoodsManId());
            }
            model.addAttribute("msg", "申请成功！");
        }
        return toOutApply(model, page);
    }
}
