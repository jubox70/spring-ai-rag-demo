package com.jubox70.springai.domain.model;

public record KnowledgeEntry(String id, String text) {

    public KnowledgeEntry {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Knowledge entry id must not be blank");
        }
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Knowledge entry text must not be blank");
        }
    }
}

