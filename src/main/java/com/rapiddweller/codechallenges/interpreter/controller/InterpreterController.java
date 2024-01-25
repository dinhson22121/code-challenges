package com.rapiddweller.codechallenges.interpreter.controller;

import com.rapiddweller.codechallenges.interpreter.Interpreter;
import com.rapiddweller.codechallenges.interpreter.response.Response;
import com.rapiddweller.codechallenges.interpreter.service.InterpreterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.script.ScriptException;

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
    @PostMapping("/complex-operation")
    public ResponseEntity<Response> complexOperation(@RequestBody Interpreter code) throws Exception {
        return ResponseEntity.ok(new Response(interpreterService.complexOperation(code)));
    }
}
