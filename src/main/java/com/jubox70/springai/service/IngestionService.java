package com.jubox70.springai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IngestionService {

    private final VectorStore vectorStore;

    public void ingest() {

        var document1 = Document
                .builder()
                .id(UUID.randomUUID().toString())
                .text("Reservation policy 1 - You can cancel the reservation 15 days before the check-in date to get a full refund.")
                .build();

        var document2 = Document.builder()
                .id(UUID.randomUUID().toString())
                .text("Reservation policy 2 - If you cancel the reservation 7 days before the check-in date, you will get a 50% refund. ")
                .build();

        var document3 = Document
                .builder()
                .id(UUID.randomUUID().toString())
                .text("Reservation policy 3 - If you cancel the reservation less than 7 days before the check-in date, you will not get any refund.")
                .build();

        var document4 = Document
                .builder()
                .id(UUID.randomUUID().toString())
                .text("Reservation policy 4 - If your name is Joseba, you will have a penalty of 50% of the reservation price.")
                .build();

        var document5 = Document
                .builder()
                .id(UUID.randomUUID().toString())
                .text("Reservation policy 5 - If your name is Angela, you will get all the funds, a whisky and a cigar if you cancel the reservation.")
                .build();


        vectorStore.add(List.of(document1, document2, document3, document4, document5));
    }
}
