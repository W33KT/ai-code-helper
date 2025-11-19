package com.w33kt.aicodehelper.ai.entity;
import com.w33kt.aicodehelper.dao.entity.ChatMessageRecord;
import com.w33kt.aicodehelper.dao.entity.ChatMessageRecordExample;
import com.w33kt.aicodehelper.dao.mapper.ChatMessageRecordMapper;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tankaiwen
 */
@Component
public class PersistentChatMemoryStore implements ChatMemoryStore {

    @Resource
    private ChatMessageRecordMapper chatMessageRecordMapper;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        String memIdStr = String.valueOf(memoryId);

        ChatMessageRecordExample example = new ChatMessageRecordExample();
        example.createCriteria().andMemoryIdEqualTo(memIdStr);
        example.setOrderByClause("id ASC");

        List<ChatMessageRecord> records = chatMessageRecordMapper.selectByExampleWithBLOBs(example);

        return records.stream()
                .map(record -> ChatMessageDeserializer.messageFromJson(record.getMessageJson()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        String memIdStr = String.valueOf(memoryId);

        deleteMessages(memoryId);

        for (ChatMessage message : messages) {
            ChatMessageRecord record = new ChatMessageRecord();
            record.setMemoryId(memIdStr);
            record.setRole(message.type().toString());
            record.setMessageJson(ChatMessageSerializer.messageToJson(message));

            chatMessageRecordMapper.insertSelective(record);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMessages(Object memoryId) {
        String memIdStr = String.valueOf(memoryId);
        ChatMessageRecordExample example = new ChatMessageRecordExample();
        example.createCriteria().andMemoryIdEqualTo(memIdStr);
        chatMessageRecordMapper.deleteByExample(example);
    }
}
