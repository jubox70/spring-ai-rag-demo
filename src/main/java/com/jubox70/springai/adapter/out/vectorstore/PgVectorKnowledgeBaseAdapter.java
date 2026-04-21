package com.jubox70.springai.adapter.out.vectorstore;

import com.jubox70.springai.application.port.out.KnowledgeBasePort;
import com.jubox70.springai.domain.model.KnowledgeEntry;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PgVectorKnowledgeBaseAdapter implements KnowledgeBasePort {

    private final VectorStore vectorStore;

    public PgVectorKnowledgeBaseAdapter(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public List<String> findRelevantContext(String query, int topK) {
        return vectorStore.similaritySearch(SearchRequest.builder()
                        .query(query)
                        .topK(topK)
                        .build())
                .stream()
                .map(Document::getText)
                .toList();
    }

    @Override
    public void saveAll(List<KnowledgeEntry> entries) {
        var documents = entries.stream()
                .map(entry -> Document.builder()
                        .id(entry.id())
                        .text(entry.text())
                        .build())
                .toList();

        vectorStore.add(documents);
    }
}

