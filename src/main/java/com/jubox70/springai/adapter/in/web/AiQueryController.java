package com.jubox70.springai.adapter.in.web;

import com.jubox70.springai.application.port.in.ConversationUseCase;
import com.jubox70.springai.domain.model.ChatProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class AiQueryController {

    private final ConversationUseCase conversationUseCase;

    @GetMapping("/{provider}")
    public ResponseEntity<String> ask(
            @PathVariable String provider,
            @RequestParam(name = "question") String question
    ) {
        var response = conversationUseCase.ask(ChatProvider.from(provider), question);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{provider}/knowledge")
    public ResponseEntity<String> askWithKnowledge(
            @PathVariable String provider,
            @RequestParam(name = "question") String question
    ) {
        var response = conversationUseCase.askWithKnowledge(ChatProvider.from(provider), question);
        return ResponseEntity.ok(response);
    }
}
