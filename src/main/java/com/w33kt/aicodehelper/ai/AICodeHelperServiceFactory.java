package com.w33kt.aicodehelper.ai;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tankaiwen
 */
@Configuration
public class AICodeHelperServiceFactory {

    @Resource
    private ChatModel qwenChatModel;

    @Resource
    private ChatMemoryStore persistentChatMemoryStore;

    @Bean
    public AICodeHelperService aiCodeHelperService() {
        return AiServices.builder(AICodeHelperService.class)
                .chatModel(qwenChatModel)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.builder()
                        .maxMessages(20)
                        .id(memoryId)
                        .chatMemoryStore(persistentChatMemoryStore)
                        .build())
                .build();
    }
}