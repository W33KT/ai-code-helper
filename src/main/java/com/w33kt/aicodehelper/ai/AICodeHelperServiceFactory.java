package com.w33kt.aicodehelper.ai;

import com.w33kt.aicodehelper.ai.tools.InterviewQuestionTool;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.WebSearchContentRetriever;
import dev.langchain4j.rag.query.router.DefaultQueryRouter;
import dev.langchain4j.rag.query.router.QueryRouter;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import dev.langchain4j.web.search.WebSearchEngine;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author tankaiwen
 */
@Configuration
public class AICodeHelperServiceFactory {

    @Resource
    private ChatModel qwenChatModel;

    @Resource
    private ChatMemoryStore persistentChatMemoryStore;

    @Resource
    private ContentRetriever contentRetriever;

    @Resource
    private InterviewQuestionTool interviewQuestionTool;

    @Resource
    private WebSearchEngine webSearchEngine;

    @Resource
    private StreamingChatModel qwenStreamingChatModel;

    @Bean
    public AICodeHelperService aiCodeHelperService() {
        return AiServices.builder(AICodeHelperService.class)
                .chatModel(qwenChatModel)
                .streamingChatModel(qwenStreamingChatModel)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.builder()
                        .maxMessages(20)
                        .id(memoryId)
                        .chatMemoryStore(persistentChatMemoryStore)
                        .build())
                .retrievalAugmentor(buildRetrievalAugmentor())
                .tools(interviewQuestionTool)
                .build();
    }

    private RetrievalAugmentor buildRetrievalAugmentor() {
        ContentRetriever webSearchContentRetriever = WebSearchContentRetriever.builder()
                .webSearchEngine(webSearchEngine)
                .maxResults(3)
                .build();
        QueryRouter queryRouter = new DefaultQueryRouter(contentRetriever, webSearchContentRetriever);
        return DefaultRetrievalAugmentor.builder()
                .queryRouter(queryRouter)
                .build();
    }
}
