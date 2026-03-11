package com.jubox70.springai.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AnthropicController {

    private final AnthropicChatModel anthropicChatModel;

    @GetMapping("/test/anthropic")
    public ResponseEntity<String> test() {
        var response = anthropicChatModel
                .call(Prompt
                        .builder()
                        .content("Tell me some joke about software engineers")
                        .build());
        log.info("response: {}", response.getResult().getOutput().getText());
        return ResponseEntity.ok("Hello World");
    }
}
