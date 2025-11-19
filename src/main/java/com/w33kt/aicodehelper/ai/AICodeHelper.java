package com.w33kt.aicodehelper.ai;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tankaiwen
 */
@Service
@Slf4j
public class AICodeHelper {
    @Resource
    private ChatModel qwenChatModel;

    public String chat(String message) {
        UserMessage userMessage = UserMessage.from(message);

        ChatResponse response = qwenChatModel.chat(userMessage);
        AiMessage aiMessage = response.aiMessage();
        log.info("User Message: {}, AI Message: {}", message, aiMessage);

        return aiMessage.text();
    }
}
