package com.rapiddweller.codechallenges.interpreter.controllers;

import com.rapiddweller.codechallenges.interpreter.io.Interpreter;
import com.rapiddweller.codechallenges.interpreter.io.Response;
import com.rapiddweller.codechallenges.interpreter.services.InterpreterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000",maxAge = 3600)
@RequestMapping("/api/v1")
public class InterpreterController {
    @Autowired
    private InterpreterService interpreterService;
    @PostMapping("/arithmetic")
    public ResponseEntity<Response> simpleArithmetic(@RequestBody Interpreter code) throws Exception {
        return ResponseEntity.ok(new Response(interpreterService.simpleArithmetic(code)));
    }

    @PostMapping("/context-execution")
    public ResponseEntity<Response> contextExecution(@RequestBody Interpreter code) throws Exception {
        return ResponseEntity.ok(new Response(interpreterService.contextExecution(code)));
    }

    @PostMapping("/context-submission")
    public ResponseEntity<?> contextSubmission(@RequestBody Interpreter code) throws Exception {
        interpreterService.contextSubmission(code);
        return ResponseEntity.ok(ResponseEntity.ok().body("Success"));
    }
    @CrossOrigin(origins = "http://localhost:3000",maxAge = 3600)
    @PostMapping("/complex-operation")
    public ResponseEntity<Response> complexOperation(@RequestBody Interpreter code) throws Exception {
        return ResponseEntity.ok(new Response(interpreterService.complexOperation(code)));
    }
}
