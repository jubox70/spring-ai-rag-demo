package com.jubox70.springai.application.port.out;

import com.jubox70.springai.domain.model.KnowledgeEntry;

import java.util.List;

public interface KnowledgeBasePort {

    List<String> findRelevantContext(String query, int topK);

    void saveAll(List<KnowledgeEntry> entries);
}

