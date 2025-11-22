package com.w33kt.aicodehelper.controller;

import com.w33kt.aicodehelper.ai.AICodeHelperService;
import jakarta.annotation.Resource;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author tankaiwen
 */
@RestController
@RequestMapping("/ai")
public class AiController {
    @Resource
    private AICodeHelperService aiCodeHelperService;

    @GetMapping("/chat")
    public Flux<ServerSentEvent<String>> chat(int memoryId, String userMessage) {
        return aiCodeHelperService.chatStream(memoryId, userMessage)
                .map(e -> ServerSentEvent.<String>builder().data(e).build());
    }
}
