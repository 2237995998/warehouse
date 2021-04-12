package com.ye.warehouse.controller;

import com.ye.warehouse.entity.*;
import com.ye.warehouse.service.MessageService;
import com.ye.warehouse.service.UserService;
import com.ye.warehouse.util.StringUtil;
import com.ye.warehouse.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @PackageName:com.ye.warehouse.controller
 * @ClassName:MessageController
 * @Description:
 * @author:何进业
 * @date:2021/4/6 16:01
 */
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;


    /**
     * 分页获取会话列表
     * @param model
     * @param page
     * @param session
     * @return
     */
    @RequestMapping(path = "/letter/list", method = RequestMethod.GET)
    public String getLetterList(Model model, Page page, HttpSession session) {
        int role = (int)session.getAttribute("role");
        int userId = UserUtil.getId(session.getAttribute("user"), role);
        // 分页信息
        page.setLimit(5);
        page.setPath("/letter/list");
        page.setRows(messageService.findConversationCount(userId, role));

        // 会话列表
        List<Message> conversationList = messageService.findConversations(userId, role, page.getOffset(), page.getLimit());
        List<Map<String, Object>> conversations = new ArrayList<>();
        if (conversationList != null) {
            for (Message message : conversationList) {
                Map<String, Object> map = new HashMap<>();
                map.put("conversation", message);
                map.put("letterCount", messageService.findLetterCount(message.getConversationId()));
                map.put("unreadCount", messageService.findLetterUnreadCount(userId, role, message.getConversationId()));
                if (userId == message.getFromId() && role == message.getFromRole()){
                    Map<String, Object> toMap = userService.selectUser(message.getToId(), message.getToRole());
                    map.put("target", toMap);
                }else if (userId == message.getToId() && role == message.getToRole()){
                    Map<String, Object> toMap = userService.selectUser(message.getFromId(), message.getFromRole());
                    map.put("target", toMap);
                }
                conversations.add(map);
            }
        }
        model.addAttribute("conversations", conversations);

        // 查询未读消息数量
        int letterUnreadCount = messageService.findLetterUnreadCount(userId, role, null);
        model.addAttribute("letterUnreadCount", letterUnreadCount);
        //int noticeUnreadCount = messageService.findNoticeUnreadCount(userId, null);
        //model.addAttribute("noticeUnreadCount", noticeUnreadCount);

        return "/letter";
    }


    /**
     * 获取会话详情对话消息
     * @param conversationId 会话id
     * @param page
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(path = "/letter/detail/{conversationId}", method = RequestMethod.GET)
    public String getLetterDetail(@PathVariable("conversationId") String conversationId, Page page, Model model, HttpSession session) {
        // 分页信息
        page.setLimit(4);
        page.setPath("/letter/detail/" + conversationId);
        page.setRows(messageService.findLetterCount(conversationId));

        // 会话列表
        List<Message> letterList = messageService.findLetters(conversationId, page.getOffset(), page.getLimit());
        List<Map<String, Object>> letters = new ArrayList<>();
        if (letterList != null) {
            for (Message message : letterList) {
                Map<String, Object> map = new HashMap<>();
                map.put("letter", message);
                map.put("fromUser", userService.selectUser(message.getFromId(), message.getFromRole()));
                letters.add(map);
            }
        }
        model.addAttribute("letters", letters);

        // 私信目标
        model.addAttribute("target", getLetterTarget(conversationId, session));

        //本人账号
        model.addAttribute("meId", UserUtil.getId(session.getAttribute("user"), (int)session.getAttribute("role")));
        // 设置已读
        List<Integer> ids = getLetterIds(letterList, session);
        if (!ids.isEmpty()) {
            messageService.readMessage(ids);
        }

        return "/letter_detail";
    }


    /**
     * 根据会话id获取对方详细信息
     * @param conversationId 会话id
     * @param session
     * @return
     */
    private Map<String, Object> getLetterTarget(String conversationId, HttpSession session) {
        String[] ids = conversationId.split("_");
        int id0 = Integer.parseInt(ids[0]);
        int id1 = Integer.parseInt(ids[1]);
        int id2 = Integer.parseInt(ids[2]);
        int id3 = Integer.parseInt(ids[3]);
        int role = (int)session.getAttribute("role");
        int id = UserUtil.getId(session.getAttribute("user"), role);
        int targetManRole = role == id0 ? id1 : id0;
        int targetManId = id == id2 ? id3 : id2;
        return userService.selectUser(targetManId, targetManRole);
    }

    /**
     * 获取指定消息列表中本人为接收方的消息id
     * @param letterList 消息列表
     * @param session
     * @return
     */
    private List<Integer> getLetterIds(List<Message> letterList, HttpSession session) {
        List<Integer> ids = new ArrayList<>();
        if (letterList != null) {
            for (Message message : letterList) {
                if (UserUtil.getId(session.getAttribute("user"), (int)session.getAttribute("role")) == message.getToId()
                        && message.getStatus() == 0
                        && (int)session.getAttribute("role") == message.getToRole()) {
                    ids.add(message.getMessageId());
                }
            }
        }

        return ids;
    }


    /**
     * 发送消息
     * @param toId 接收消息者账号
     * @param toRole 接收消息者身份类别
     * @param content 消息内容
     * @param session
     * @return
     */
    @RequestMapping(path = "/letter/send", method = RequestMethod.POST)
    @ResponseBody
    public String sendLetter(int toId, int toRole, String content, HttpSession session) {

        if (StringUtils.isBlank(content)) {
            return StringUtil.getJSONString(1, "发送失败，内容不能为空!");
        }
        Map<String, Object> map = userService.selectUser(toId, toRole);
        if (map == null){
            return StringUtil.getJSONString(2, "发送失败，目标用户不存在!");
        }
        Message message = new Message();
        int fromRole = (int)session.getAttribute("role");
        int fromId = UserUtil.getId(session.getAttribute("user"), fromRole);
        if (fromId == toId && fromRole == toRole){
            return StringUtil.getJSONString(3, "发送失败，不能给自己发消息！");
        }
        message.setFromId(fromId);
        message.setFromRole(fromRole);
        message.setToId(toId);
        message.setToRole(toRole);
        String conversationId = "";
        if (message.getFromRole() < message.getToRole()){
            conversationId += message.getFromRole() + "_" + message.getToRole();
        } else {
            conversationId += message.getToRole()  + "_" + message.getFromRole();
        }
        if (message.getFromId() < message.getToId()) {
            conversationId += "_" + message.getFromId() + "_" + message.getToId();
        } else {
            conversationId += "_" + message.getToId() + "_" + message.getFromId();
        }
        message.setConversationId(conversationId);
        message.setContent(content);
        message.setCreateTime(new Date());
        messageService.addMessage(message);
        return StringUtil.getJSONString(0);
    }


}
