package com.rapiddweller.codechallenges.translator.services;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.rapiddweller.codechallenges.translator.io.TranslationRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {
    @Value("${myapp.secret-key}")
    private String secretKey;
   public String getTranslatedText(TranslationRequest io) throws Exception {

        // Replace "YOUR_API_KEY" with your actual Google Cloud API key
        Translate translate = TranslateOptions.newBuilder().setApiKey(getSecretKey()).build().getService();

        Translation translation = translate.translate(io.getText(), Translate.TranslateOption.sourceLanguage(io.getSourceLanguage()), Translate.TranslateOption.targetLanguage(io.getTargetLanguage()));

       return translation.getTranslatedText();
   }

    public String getSecretKey() {
        return secretKey;
    }
}
