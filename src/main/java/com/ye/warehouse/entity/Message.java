package com.ye.warehouse.entity;

import java.util.Date;

/**
 * @PackageName:com.ye.warehouse.entity
 * @ClassName:Message
 * @Description:消息类
 * @author:何进业
 * @date:2021/3/23 20:36
 */
public class Message {
    /**
     * 消息编号
     */
    private int messageId;
    /**
     * 消息发送者id,0表示系统
     */
    private int fromId;
    /**
     * 消息发送者角色类型
     */
    private int fromRole;
    /**
     * 消息接收者id
     */
    private int toId;
    /**
     * 消息接收者角色类型
     */
    private int toRole;
    /**
     * 会话id.消息发送者id和消息接收者id的组合，便于查询，形式：小的role_大的role_小的id_大的id
     */
    private String conversationId;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 状态：0表示未处理，1表示已处理，2表示删除
     */
    private int status;
    /**
     * 消息发送时间
     */
    private Date createTime;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getFromRole() {
        return fromRole;
    }

    public void setFromRole(int fromRole) {
        this.fromRole = fromRole;
    }

    public int getToRole() {
        return toRole;
    }

    public void setToRole(int toRole) {
        this.toRole = toRole;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", fromId=" + fromId +
                ", fromRole=" + fromRole +
                ", toId=" + toId +
                ", toRole=" + toRole +
                ", conversationId='" + conversationId + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
