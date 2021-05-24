package com.ye.warehouse.controller;


import com.ye.warehouse.entity.GoodsMan;
import com.ye.warehouse.entity.QualityMan;
import com.ye.warehouse.entity.SystemManager;
import com.ye.warehouse.entity.WarehouseManager;
import com.ye.warehouse.service.UserService;
import com.ye.warehouse.util.StringUtil;
import com.ye.warehouse.util.UserUtil;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

@Controller
public class CommonController {


    @Autowired
    private UserService userService;

    @Value("${warehouse.path.upload.header}")
    private String headerUpload;


    @Value("${server.servlet.context-path}")
    private String contextPath;


    @RequestMapping(path = "/exception", method = RequestMethod.GET)
    public String getErrorPage(Model model){
        model.addAttribute("msg", "操作失败,服务器异常,请稍后重试!!!");
        return "/error";
    }


    /**
     * 前往登录界面
     * @return
     */
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getLoginPage() {
        return "/login";
    }

    /**
     * 登录
     * @param model
     * @param userId 账号
     * @param password 密码
     * @param role 身份类别
     * @param session
     * @return
     */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(Model model, int userId, String password, int role, HttpSession session) {
        session.setAttribute("role", role);
        if (role == 0) {
            SystemManager systemManager = userService.smLogin(userId, password);
            if (systemManager != null) {
                session.setAttribute("user", systemManager);
                model.addAttribute("msg", "登录成功!欢迎系统管理员——\"" + systemManager.getSystemManagerName()+"\"");
                return "/sm_index";
            } else {
                // 用户名或密码错误
                model.addAttribute("msg", "账号不存在或密码错误!");
            }
        } else if (role == 1) {
            GoodsMan goodsMan = userService.gmLogin(userId, password);
            if (goodsMan != null) {
                session.setAttribute("user", goodsMan);
                model.addAttribute("msg", "登录成功!欢迎商品运输员——\"" + goodsMan.getGoodsManName()+"\"");
                return "/gm_index";
            } else {
                // 用户名或密码错误
                model.addAttribute("msg", "账号不存在或密码错误!");
            }
        } else if (role == 2) {
            QualityMan qualityMan = userService.qmLogin(userId, password);
            if (qualityMan != null) {
                session.setAttribute("user", qualityMan);
                model.addAttribute("msg", "登录成功!欢迎质检员——\"" + qualityMan.getQualityManName()+"\"");
                return "/qm_index";
            } else {
                // 用户名或密码错误
                model.addAttribute("msg", "账号不存在或密码错误!");
            }
        } else if (role == 3) {
            WarehouseManager warehouseManager = userService.wmLogin(userId, password);
            if (warehouseManager != null) {
                session.setAttribute("user", warehouseManager);
                model.addAttribute("msg", "登录成功!欢迎仓库管理员——\"" + warehouseManager.getWarehouseManagerName()+"\"");
                return "/wm_index";
            } else {
                // 用户名或密码错误
                model.addAttribute("msg", "账号不存在或密码错误!");
            }
        }
        return "/login";
    }

    /**
     * 退出登录。清空session里面的数据
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(Model model, HttpSession session) {
        Enumeration<String> em = session.getAttributeNames();
        while (em.hasMoreElements()) {
            session.removeAttribute(em.nextElement());
        }
        model.addAttribute("msg", "已成功退出登录! ");
        model.addAttribute("target", "/");
        return "/result";
    }

    /**
     * 前往设置界面
     * @return
     */
    @RequestMapping(path = "/setting", method = RequestMethod.GET)
    public String getSettingPage(){
        return "/setting";
    }


    /**
     * 上传头像至服务器
     * @param headerImage 头像文件
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String uploadHeader(MultipartFile headerImage, Model model, HttpSession session){
        if (headerImage == null){
            model.addAttribute("error","您还没有选择图片!");
            return "/setting";
        }
        String fileName = headerImage.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //生成随机的文件名。
        fileName = StringUtil.generateUUID() + suffix;
        //确定文件存放的路径
        File dest = new File(headerUpload + "/" + fileName);
        try {
            //存储文件
            headerImage.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传失败", e);
        }
        String headerUrl =  contextPath + "/header/" + fileName;
        int role = (int) session.getAttribute("role");
        int id = UserUtil.getId(session.getAttribute("user"), role);
        userService.updateHeader(id, headerUrl, role);
        model.addAttribute("msg", "更换成功");
        if (role == 0){
            SystemManager systemManager = userService.selectSM(id);
            session.setAttribute("user", systemManager);
        } else if (role == 1){
            GoodsMan goodsMan = userService.selectGM(id);
            session.setAttribute("user", goodsMan);
        } else if (role == 2){
            QualityMan qualityMan = userService.selectQM(id);
            session.setAttribute("user", qualityMan);
        } else if (role == 3){
            WarehouseManager warehouseManager = userService.selectWM(id);
            session.setAttribute("user", warehouseManager);
        }
        return "/setting";
    }

    /**
     * 获取头像
     * @param fileName 头像名称
     * @param response
     */
    @RequestMapping(path = "/header/{fileName}", method = RequestMethod.GET)
    public void getHeader(@PathVariable("fileName")String fileName, HttpServletResponse response){
        //服务器存放路径
        fileName = headerUpload + "/" + fileName;
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
     * 更新密码
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(path = "/updatePassword", method = RequestMethod.POST)
    public String updatePassword(String oldPassword, String newPassword, Model model, HttpSession session){
        int role = (int) session.getAttribute("role");
        String password = UserUtil.getPassword(session.getAttribute("user"), role);
        int id = UserUtil.getId(session.getAttribute("user"), role);
        if (!oldPassword.equals(password)){
            model.addAttribute("msg", "原密码错误！");
        }else if (oldPassword.equals(newPassword)){
            model.addAttribute("msg", "密码未作任何修改！");
        }else {
            userService.updatePassword(id, newPassword, role);
            model.addAttribute("msg", "更改密码成功！");
        }
        if (role == 0){
            SystemManager systemManager = userService.selectSM(id);
            session.setAttribute("user", systemManager);
        } else if (role == 1){
            GoodsMan goodsMan = userService.selectGM(id);
            session.setAttribute("user", goodsMan);
        } else if (role == 2){
            QualityMan qualityMan = userService.selectQM(id);
            session.setAttribute("user", qualityMan);
        } else if (role == 3){
            WarehouseManager warehouseManager = userService.selectWM(id);
            session.setAttribute("user", warehouseManager);
        }
        return "/setting";
    }

}
