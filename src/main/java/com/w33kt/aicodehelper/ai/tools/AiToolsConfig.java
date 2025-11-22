package com.w33kt.aicodehelper.ai.tools;

import dev.langchain4j.web.search.WebSearchEngine;
import dev.langchain4j.web.search.tavily.TavilyWebSearchEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tankaiwen
 */
@Configuration
public class AiToolsConfig {

    @Bean
    public WebSearchEngine tavilyWebSearchEngine(@Value("${tavily.api-key}") String tavilyApiKey) {
        return TavilyWebSearchEngine.builder()
                .apiKey(tavilyApiKey)
                .build();
    }
}
