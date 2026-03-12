package com.jubox70.springai.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChatProviderTest {

    @Test
    void fromParsesProviderIgnoringCase() {
        assertThat(ChatProvider.from("OpenAI")).isEqualTo(ChatProvider.OPENAI);
    }

    @Test
    void fromRejectsUnsupportedProvider() {
        assertThatThrownBy(() -> ChatProvider.from("vertex"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unsupported provider: vertex");
    }
}

