package com.jubox70.springai.adapter.out.ai;

import com.jubox70.springai.application.port.out.ChatPort;
import com.jubox70.springai.domain.model.ChatProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
@Slf4j
public class SpringAiChatAdapter implements ChatPort {

    private final Map<ChatProvider, ChatClient> clients = new EnumMap<>(ChatProvider.class);

    public SpringAiChatAdapter(
            @Qualifier("ollamaChatClient") ChatClient ollamaChatClient,
            @Qualifier("openAiChatClient") ChatClient openAiChatClient,
            @Qualifier("anthropicChatClient") ChatClient anthropicChatClient
    ) {
        clients.put(ChatProvider.OLLAMA, ollamaChatClient);
        clients.put(ChatProvider.OPENAI, openAiChatClient);
        clients.put(ChatProvider.ANTHROPIC, anthropicChatClient);
    }

    @Override
    public String ask(ChatProvider provider, String userMessage, String systemContext) {
        var promptSpec = resolve(provider).prompt();

        if (systemContext != null && !systemContext.isBlank()) {
            promptSpec = promptSpec.system(systemContext);
        }

        var response = promptSpec
                .user(userMessage)
                .call();

        var answer = response.chatResponse().getResult().getOutput().getText();
        log.info("provider={} response={}", provider.externalName(), answer);
        return answer;
    }

    private ChatClient resolve(ChatProvider provider) {
        var chatClient = clients.get(provider);
        if (chatClient == null) {
            throw new IllegalArgumentException("Unsupported provider: " + provider.externalName());
        }
        return chatClient;
    }
}

