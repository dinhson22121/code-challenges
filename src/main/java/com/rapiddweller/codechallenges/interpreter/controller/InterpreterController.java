package com.rapiddweller.codechallenges.interpreter.controller;

import com.rapiddweller.codechallenges.interpreter.Interpreter;
import com.rapiddweller.codechallenges.interpreter.response.Response;
import com.rapiddweller.codechallenges.interpreter.service.InterpreterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class InterpreterController {
    @Autowired
    private InterpreterService interpreterService;
    @PostMapping("/arithmetic")
    public ResponseEntity<Response> simpleArithmetic(@RequestBody Interpreter code) throws Exception {
        return ResponseEntity.ok(new Response(interpreterService.contextSubmissionAndExecution(code)));
    }

    @PostMapping("/context-submission")
    public ResponseEntity<Response> contextSubmissionAndExecution(@RequestBody Interpreter code) throws Exception {
        return ResponseEntity.ok(new Response(interpreterService.contextSubmissionAndExecution(code)));
    }
    @PostMapping("/complex-operation")
    public ResponseEntity<Response> complexOperation(@RequestBody Interpreter code) throws Exception {
        return ResponseEntity.ok(new Response(interpreterService.complexOperation(code)));
    }
}
