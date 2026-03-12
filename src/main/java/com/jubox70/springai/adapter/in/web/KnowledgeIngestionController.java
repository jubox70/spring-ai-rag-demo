package com.jubox70.springai.adapter.in.web;

import com.jubox70.springai.application.port.in.IngestKnowledgeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/knowledge")
@RequiredArgsConstructor
public class KnowledgeIngestionController {

    private final IngestKnowledgeUseCase ingestKnowledgeUseCase;

    @PostMapping("/ingest")
    public ResponseEntity<String> ingestDefaultKnowledge() {
        ingestKnowledgeUseCase.ingestDefaultKnowledge();
        return ResponseEntity.ok("Ingestion completed");
    }
}

