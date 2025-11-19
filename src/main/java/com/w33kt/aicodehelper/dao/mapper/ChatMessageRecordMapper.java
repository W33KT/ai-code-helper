package com.w33kt.aicodehelper.dao.mapper;

import com.w33kt.aicodehelper.dao.entity.ChatMessageRecord;
import com.w33kt.aicodehelper.dao.entity.ChatMessageRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatMessageRecordMapper {
    long countByExample(ChatMessageRecordExample example);

    int deleteByExample(ChatMessageRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ChatMessageRecord row);

    int insertSelective(ChatMessageRecord row);

    List<ChatMessageRecord> selectByExampleWithBLOBs(ChatMessageRecordExample example);

    List<ChatMessageRecord> selectByExample(ChatMessageRecordExample example);

    ChatMessageRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ChatMessageRecord row, @Param("example") ChatMessageRecordExample example);

    int updateByExampleWithBLOBs(@Param("row") ChatMessageRecord row, @Param("example") ChatMessageRecordExample example);

    int updateByExample(@Param("row") ChatMessageRecord row, @Param("example") ChatMessageRecordExample example);

    int updateByPrimaryKeySelective(ChatMessageRecord row);

    int updateByPrimaryKeyWithBLOBs(ChatMessageRecord row);

    int updateByPrimaryKey(ChatMessageRecord row);
}