package com.ye.warehouse.service;

import com.ye.warehouse.dao.MessageMapper;
import com.ye.warehouse.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import java.util.List;

/**
 * @PackageName:com.ye.warehouse.service
 * @ClassName:MessageService
 * @Description:
 * @author:何进业
 * @date:2021/4/6 15:56
 */
@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 分页获取某用户的会话列表
     * @param userId 账号
     * @param role 身份类别
     * @param offset 起始位置
     * @param limit 数量
     * @return
     */
    public List<Message> findConversations(int userId, int role, int offset, int limit){
        return messageMapper.selectConversation(userId, role, offset, limit);
    }

    /**
     * 获取某用户的会话总数
     * @param userId 账号
     * @param role 身份类别
     * @return
     */
    public int findConversationCount(int userId, int role){
        return messageMapper.selectConversationCount(userId, role);
    }

    /**
     * 根据会话id获取消息总数
     * @param conversationId 会话id
     * @return
     */
    public int findLetterCount(String conversationId){
        return messageMapper.selectLetterCount(conversationId);
    }

    /**
     * 根据会话id获取某用户的未读消息
     * @param userId 账号
     * @param role 身份类别
     * @param conversationId 会话id
     * @return
     */
    public int findLetterUnreadCount(int userId, int role, String conversationId){
        return messageMapper.selectLetterUnreadCount(userId, role, conversationId);
    }

    /**
     * 分页获取某一会话id的所有消息
     * @param conversationId 会话id
     * @param offset 起始位置
     * @param limit 数量
     * @return
     */
    public List<Message> findLetters(String conversationId, int offset, int limit){
        return messageMapper.selectLetters(conversationId, offset, limit);
    }

    /**
     * 将消息列表标为已读
     * @param ids 消息id列表
     * @return
     */
    public int readMessage(List<Integer> ids){
        return messageMapper.updateStatus(ids, 1);
    }


    /**
     * 添加消息
     * @param message
     * @return
     */
    public int addMessage(Message message){
        message.setContent(HtmlUtils.htmlEscape(message.getContent()));
        return messageMapper.insertMessage(message);
    }
}
