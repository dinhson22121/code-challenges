package com.rapiddweller.codechallenges.translator.io;

public class TranslationResponse {
    private String text;

    public TranslationResponse(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
