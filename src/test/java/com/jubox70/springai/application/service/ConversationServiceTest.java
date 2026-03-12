package com.jubox70.springai.application.service;

import com.jubox70.springai.application.port.out.ChatPort;
import com.jubox70.springai.application.port.out.KnowledgeBasePort;
import com.jubox70.springai.domain.model.ChatProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConversationServiceTest {

    @Mock
    private ChatPort chatPort;

    @Mock
    private KnowledgeBasePort knowledgeBasePort;

    private ConversationService conversationService;

    @BeforeEach
    void setUp() {
        conversationService = new ConversationService(chatPort, knowledgeBasePort);
    }

    @Test
    void askDelegatesToChatPortWithoutSystemContext() {
        when(chatPort.ask(eq(ChatProvider.OPENAI), anyString(), eq(""))).thenReturn("answer");

        var response = conversationService.ask(ChatProvider.OPENAI, "Tell me a joke");

        assertThat(response).isEqualTo("answer");
        ArgumentCaptor<String> userMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(chatPort).ask(eq(ChatProvider.OPENAI), userMessageCaptor.capture(), eq(""));
        assertThat(userMessageCaptor.getValue())
                .isEqualTo("Tell me a joke. Today is " + LocalDate.now());
    }

    @Test
    void askWithKnowledgeBuildsSystemContextFromKnowledgeBase() {
        when(knowledgeBasePort.findRelevantContext("What is the cancellation policy?", 5))
                .thenReturn(List.of("policy 1", "policy 2"));
        when(chatPort.ask(eq(ChatProvider.OLLAMA), anyString(), anyString())).thenReturn("rag-answer");

        var response = conversationService.askWithKnowledge(ChatProvider.OLLAMA, "What is the cancellation policy?");

        assertThat(response).isEqualTo("rag-answer");
        verify(knowledgeBasePort).findRelevantContext("What is the cancellation policy?", 5);
        verify(chatPort).ask(
                eq(ChatProvider.OLLAMA),
                eq("What is the cancellation policy?. Today is " + LocalDate.now()),
                eq("policy 1" + System.lineSeparator() + "policy 2")
        );
    }

    @Test
    void askRejectsBlankQuestions() {
        assertThatThrownBy(() -> conversationService.ask(ChatProvider.ANTHROPIC, "   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Question must not be blank");
    }
}

