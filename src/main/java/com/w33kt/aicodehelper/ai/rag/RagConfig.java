package com.w33kt.aicodehelper.ai.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author tankaiwen
 */
@Configuration
public class RagConfig {
    @Resource
    private EmbeddingModel qwenEmbeddingModel;

    @Resource
    private EmbeddingStore<TextSegment> embeddingStore; // recommend: use pgvector: https://docs.langchain4j.dev/integrations/embedding-stores/pgvector

    @Bean
    public ContentRetriever contentRetriever() {
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("src/main/resources/docs");

        DocumentByParagraphSplitter documentByParagraphSplitter =
                new DocumentByParagraphSplitter(2500, 500);

        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(documentByParagraphSplitter)
                .textSegmentTransformer(
                        textSegment ->
                                TextSegment.from(
                                        textSegment.metadata().getString("file_name") + "\n" + textSegment.text(),
                                        textSegment.metadata()))
                .embeddingModel(qwenEmbeddingModel)
                .embeddingStore(embeddingStore)
                .build();

        ingestor.ingest(documents);

        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(qwenEmbeddingModel)
                .maxResults(5)
                .minScore(0.7)
                .build();
    }
}
