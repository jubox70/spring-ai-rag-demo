package com.jubox70.springai.application.service;

import com.jubox70.springai.application.port.in.IngestKnowledgeUseCase;
import com.jubox70.springai.application.port.out.KnowledgeBasePort;
import com.jubox70.springai.domain.model.DefaultKnowledgeEntries;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeIngestionService implements IngestKnowledgeUseCase {

    private final KnowledgeBasePort knowledgeBasePort;

    public KnowledgeIngestionService(KnowledgeBasePort knowledgeBasePort) {
        this.knowledgeBasePort = knowledgeBasePort;
    }

    @Override
    public void ingestDefaultKnowledge() {
        knowledgeBasePort.saveAll(DefaultKnowledgeEntries.RESERVATION_POLICIES);
    }
}

