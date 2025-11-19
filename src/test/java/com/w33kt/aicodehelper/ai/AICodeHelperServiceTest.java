package com.w33kt.aicodehelper.ai;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AICodeHelperServiceTest {
    @Resource
    private AICodeHelperService aiCodeHelperService;

    @Test
    void testChat() {
        String message = "Hello, I am Kaiwen, a backend engineer at Meituan";
        String response = aiCodeHelperService.chat(1, message);
        System.out.println(response);
        message = "Who am I?";
        response = aiCodeHelperService.chat(1, message);
        System.out.println(response);
    }
}