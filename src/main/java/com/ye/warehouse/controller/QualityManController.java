package com.ye.warehouse.controller;

import com.ye.warehouse.entity.*;
import com.ye.warehouse.service.GoodsCategoryService;
import com.ye.warehouse.service.GoodsService;
import com.ye.warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackageName:com.ye.warehouse.controller
 * @ClassName:QualityManController
 * @Description:
 * @author:何进业
 * @date:2021/3/30 11:16
 */
@Controller
@RequestMapping("/QM")
public class QualityManController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @Autowired
    private UserService userService;

    /**
     * 分页获取质检列表，跳到质检界面
     * @param model
     * @param type 1为入库质检   其他为出库质检
     * @param page
     * @return
     */
    @RequestMapping(path = "/qualityCheck", method = RequestMethod.GET)
    public String qualityCheck(Model model, int type, Page page){
        List<Goods> goodsList = new ArrayList<>();
        if (type == 1){
            goodsList = goodsService.getAllGoods(-1, 1, page.getOffset(), page.getLimit());
            page.setRows(goodsService.getAllGoodsCountByTypeId(-1, 1));
        } else {
            goodsList = goodsService.getAllGoods(-1, 5, page.getOffset(), page.getLimit());
            page.setRows(goodsService.getAllGoodsCountByTypeId(-1, 5));
        }
        page.setPath("/QM/qualityCheck?type=" + type);
        List<Map<String, Object>> list = new ArrayList<>();
        if (goodsList != null) {
            for (Goods goods : goodsList) {
                Map<String, Object> map = new HashMap<>();
                map.put("goods", goods);
                GoodsCategory goodsCategory = goodsCategoryService.selectGoodsType(goods.getGoodsCategoryId());
                map.put("goodsCategory", goodsCategory);
                GoodsApplyRecord goodsApplyRecord = goodsService.getGoodsApplyRecord(goods.getGoodsId());
                map.put("record", goodsApplyRecord);
                if (type == 1){
                    GoodsMan inGoodsMan = userService.selectGM(goodsApplyRecord.getGoodsManInId());
                    map.put("goodsMan", inGoodsMan);
                } else if (type == 2){
                    GoodsMan outGoodsMan = userService.selectGM(goodsApplyRecord.getGoodsManOutId());
                    map.put("goodsMan", outGoodsMan);
                }
                list.add(map);
            }
        }
        model.addAttribute("type", type);
        model.addAttribute("list", list);
        return "/quality";
    }

    /**
     * 通过质检
     * @param model
     * @param goodsId 商品编号
     * @param type 1为入库质检   其他为出库质检
     * @param session
     * @param page
     * @return
     */
    @RequestMapping(path = "/qualityPass", method = RequestMethod.GET)
    public String qualityPass(Model model, int goodsId, int type, HttpSession session, Page page){
        QualityMan qualityMan = (QualityMan) session.getAttribute("user");
        if (type == 1){
            goodsService.updateGoodsStatus(goodsId, 2);
            goodsService.addQualityRecord(goodsId, qualityMan.getQualityManId(), 1);
        } else if (type == 2){
            goodsService.updateGoodsStatus(goodsId, 6);
            goodsService.updateQualityOutRecordResult(goodsId, 1, qualityMan.getQualityManId());
        }
        model.addAttribute("msg", "已通过!");
        return qualityCheck(model, type, page);
    }

    /**
     * 不通过质检
     * @param model
     * @param goodsId 商品编号
     * @param type 1为入库质检   其他为出库质检
     * @param session
     * @param page
     * @return
     */
    @RequestMapping(path = "/qualityFail", method = RequestMethod.GET)
    public String qualityFail(Model model, int goodsId, int type, HttpSession session, Page page){
        QualityMan qualityMan = (QualityMan) session.getAttribute("user");
        if (type == 1){
            goodsService.updateGoodsStatus(goodsId, 3);
            goodsService.addQualityRecord(goodsId, qualityMan.getQualityManId(), 1);
        } else if (type == 2){
            goodsService.updateGoodsStatus(goodsId, 7);
            goodsService.updateQualityOutRecordResult(goodsId, 2, qualityMan.getQualityManId());
        }
        model.addAttribute("msg", "已不通过!");
        return qualityCheck(model, type, page);
    }
}
