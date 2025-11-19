package com.w33kt.aicodehelper.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class ChatMessageRecord implements Serializable {
    private Long id;

    private String memoryId;

    private String role;

    private Date createTime;

    private String messageJson;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(String memoryId) {
        this.memoryId = memoryId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMessageJson() {
        return messageJson;
    }

    public void setMessageJson(String messageJson) {
        this.messageJson = messageJson;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memoryId=").append(memoryId);
        sb.append(", role=").append(role);
        sb.append(", createTime=").append(createTime);
        sb.append(", messageJson=").append(messageJson);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}