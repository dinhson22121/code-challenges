package com.rapiddweller.codechallenges.translator;

import com.rapiddweller.codechallenges.translator.io.TranslationRequest;
import com.rapiddweller.codechallenges.translator.io.TranslationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000",maxAge = 3600)
@RequestMapping("/api/v1")
public class TranslationController {
    @Autowired
    private TranslationService service;

    @PostMapping("/translate")
    public ResponseEntity<TranslationResponse> translate(@RequestBody TranslationRequest request) throws Exception {
        return  ResponseEntity.ok(new TranslationResponse(service.getTranslatedText(request)));
    }

}
