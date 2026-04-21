package com.jubox70.springai.domain.model;

import java.util.Arrays;
import java.util.Locale;

public enum ChatProvider {
    OLLAMA,
    OPENAI,
    ANTHROPIC;

    public static ChatProvider from(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Provider must be provided");
        }

        return Arrays.stream(values())
                .filter(provider -> provider.name().equalsIgnoreCase(value.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported provider: " + value));
    }

    public String externalName() {
        return name().toLowerCase(Locale.ROOT);
    }
}

