package com.w33kt.aicodehelper.controller;

import com.w33kt.aicodehelper.ai.AICodeHelperService;
import jakarta.annotation.Resource;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.UUID;

/**
 * @author tankaiwen
 */
@RestController
@RequestMapping("/api/ai")
public class AiController {
    @Resource
    private AICodeHelperService aiCodeHelperService;

    @PostMapping("/chat/start")
    public Map<String, String> startChat() {
        String memoryId = UUID.randomUUID().toString();
        return Map.of("memoryId", memoryId);
    }

    @GetMapping("/chat")
    public Flux<ServerSentEvent<String>> chat(String memoryId, String userMessage) {
        return aiCodeHelperService.chatStream(memoryId, userMessage)
                .map(e -> ServerSentEvent.<String>builder().data(e).build());
    }
}
