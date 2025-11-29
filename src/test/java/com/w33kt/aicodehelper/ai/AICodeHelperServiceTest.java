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
        String response = aiCodeHelperService.chat("1", message);
        System.out.println(response);
        message = "Who am I?";
        response = aiCodeHelperService.chat("1", message);
        System.out.println(response);
        message = "How to study Java? Can you give me some common interview questions?";
        response = aiCodeHelperService.chat("1", message);
        System.out.println(response);
    }

    @Test
    void testRag() {
        String message = "How to study Java? Also, please use your tool to search for interview questions specifically about 'Java concurrency'";
        String response = aiCodeHelperService.chat("1", message);
        System.out.println(response);
    }

    @Test
    void testMcp() {
        String message = "Please search the web for who is the Overwatch Player Leave";
        String response = aiCodeHelperService.chat("1", message);
        System.out.println(response);
    }

    @Test
    void testMonitor() {
        // This is a func to test my another project, don't care :)
    }
}