package com.w33kt.aicodehelper;

import com.w33kt.aicodehelper.ai.AICodeHelper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiCodeHelperApplicationTests {
    @Resource
    private AICodeHelper aiCodeHelper;

	@Test
	void contextLoads() {
	}

    @Test
    void chat() {
        aiCodeHelper.chat("Hello, I am Kaiwen, a backend engineer at Meituan");
    }
}
