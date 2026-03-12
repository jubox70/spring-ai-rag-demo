package com.jubox70.springai.application.port.in;

import com.jubox70.springai.domain.model.ChatProvider;

public interface ConversationUseCase {

    String ask(ChatProvider provider, String question);

    String askWithKnowledge(ChatProvider provider, String question);
}

