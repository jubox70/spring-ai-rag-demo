package com.jubox70.springai.application.port.out;

import com.jubox70.springai.domain.model.ChatProvider;

public interface ChatPort {

    String ask(ChatProvider provider, String userMessage, String systemContext);
}

