package com.jubox70.springai.application.service;

import com.jubox70.springai.application.port.in.ConversationUseCase;
import com.jubox70.springai.application.port.out.ChatPort;
import com.jubox70.springai.application.port.out.KnowledgeBasePort;
import com.jubox70.springai.domain.model.ChatProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConversationService implements ConversationUseCase {

    private static final int DEFAULT_TOP_K = 5;

    private final ChatPort chatPort;
    private final KnowledgeBasePort knowledgeBasePort;

    public ConversationService(ChatPort chatPort, KnowledgeBasePort knowledgeBasePort) {
        this.chatPort = chatPort;
        this.knowledgeBasePort = knowledgeBasePort;
    }

    @Override
    public String ask(ChatProvider provider, String question) {
        var normalizedQuestion = normalize(question);
        return chatPort.ask(provider, appendCurrentDate(normalizedQuestion), "");
    }

    @Override
    public String askWithKnowledge(ChatProvider provider, String question) {
        var normalizedQuestion = normalize(question);
        List<String> relevantContext = knowledgeBasePort.findRelevantContext(normalizedQuestion, DEFAULT_TOP_K);
        var systemContext = String.join(System.lineSeparator(), relevantContext);

        return chatPort.ask(provider, appendCurrentDate(normalizedQuestion), systemContext);
    }

    private String normalize(String question) {
        if (question == null || question.isBlank()) {
            throw new IllegalArgumentException("Question must not be blank");
        }
        return question.trim();
    }

    private String appendCurrentDate(String question) {
        return question + ". Today is " + LocalDate.now();
    }
}
