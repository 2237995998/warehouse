package com.ye.warehouse.dao;

import com.ye.warehouse.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @PackageName:com.ye.warehouse.dao
 * @ClassName:MessageMapper
 * @Description:
 * @author:何进业
 * @date:2021/4/3 10:43
 */
@Mapper
public interface MessageMapper {
    /**
     * 查询当前用户的会话列表，针对每个会话只返回一条最新的私信。
     */
    List<Message> selectConversation(int userId, int role, int off, int lim);

    /**
     * 查询某个会话所包含的私信列表
     */
    List<Message> selectLetters(String conversationId, int offset, int limit);

    /**
     * 新增消息
     */
    int insertMessage(Message message);

    /**
     * 修改消息的状态
     */
    int updateStatus(List<Integer> ids, int status);

    /**
     * 查询当前用户的会话数量
     */
    int selectConversationCount(int userId, int role);

    /**
     * 查询某个会话所包含的私信数量
     */
    int selectLetterCount(String conversationId);

    /**
     * 查询未读私信的数量
     */
    int selectLetterUnreadCount(int userId, int role, String conversationId);


}
