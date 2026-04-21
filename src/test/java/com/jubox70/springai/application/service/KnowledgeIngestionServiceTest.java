package com.jubox70.springai.application.service;

import com.jubox70.springai.application.port.out.KnowledgeBasePort;
import com.jubox70.springai.domain.model.DefaultKnowledgeEntries;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KnowledgeIngestionServiceTest {

    @Mock
    private KnowledgeBasePort knowledgeBasePort;

    @Test
    void ingestDefaultKnowledgeSavesAllReservationPolicies() {
        var service = new KnowledgeIngestionService(knowledgeBasePort);

        service.ingestDefaultKnowledge();

        verify(knowledgeBasePort).saveAll(DefaultKnowledgeEntries.RESERVATION_POLICIES);
    }
}

