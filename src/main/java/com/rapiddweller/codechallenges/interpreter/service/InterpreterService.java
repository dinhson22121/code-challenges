package com.rapiddweller.codechallenges.interpreter.service;

import com.rapiddweller.codechallenges.interpreter.Interpreter;
import jdk.jshell.JShell;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;

@Service
public class InterpreterService {

    private final JShell jShell;
    private static final String SPACE = " ";

    public InterpreterService() {
        jShell = JShell.create();
    }

    public void submitFunction(Interpreter functionCode) throws ScriptException {
        if (functionCode.getMethod() == null){
            functionCode.setMethod(SPACE);
        }
        jShell.eval(functionCode.getMethod());
        jShell.eval(functionCode.getBody());
    }

    public Object executeScript(Interpreter scriptCode) throws ScriptException {
        StringBuilder builder = new StringBuilder();
        String expression = scriptCode.getBody().substring(scriptCode.getBody().indexOf(SPACE) + 1, scriptCode.getBody().indexOf("=")).trim();

        jShell.variables().forEach(varSnippet -> {
            if (varSnippet.name().equals(expression)) {
                builder.append(jShell.varValue(varSnippet));
            }
        });
        return builder;
    }

    public String contextSubmissionAndExecution(Interpreter code) throws Exception {
        try {
            submitFunction(code);
          return executeScript(code).toString();
        } catch (ScriptException e) {
            throw new ArithmeticException("Error in arithmetic expression: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error in arithmetic expression: " + e.getMessage());
        }
    }

    public String complexOperation(Interpreter code) {

        return null;
    }
}
