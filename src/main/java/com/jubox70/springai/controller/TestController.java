package com.jubox70.springai.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final ChatClient chatClient;

    private final VectorStore vectorStore;


    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestParam(name = "question") String query) {

        query = query + ". Today is " + java.time.LocalDate.now();

        var searchRequest = SearchRequest
                .builder()
                .query("What is the cancellation policy?")
                .topK(5)
                .build();
        var ragResult = vectorStore.similaritySearch(searchRequest);

        var stringRag = ragResult.stream()
                .map(Document::getText)
                .reduce("", (a, b) -> a + b + "\n");


        var response = chatClient
                /*.prompt(Prompt
                        .builder()

                        .content("Tell me some joke about software engineers")
                        .build())*/
                .prompt()
                .system(stringRag)
                .user(query)
                .call();

        var chatResponse = response.chatResponse();
        //var responseOutput = response.content();
        var responseOutput = chatResponse.getResult().getOutput().getText();
        log.info("response: {}", responseOutput);
        return ResponseEntity.ok(responseOutput);
    }
}
