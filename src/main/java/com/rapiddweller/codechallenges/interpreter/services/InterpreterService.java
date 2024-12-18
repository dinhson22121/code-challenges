package com.rapiddweller.codechallenges.interpreter.services;

import com.rapiddweller.codechallenges.interpreter.io.Interpreter;
import jdk.jshell.JShell;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InterpreterService {

    private final JShell jShell;
    private static final String SPACE = " ";

    public InterpreterService() {
        jShell = JShell.create();
    }

    public void contextSubmission(Interpreter code) throws Exception {
        try {
            submitFunction(code.getMethod());
        } catch (ScriptException e) {
            throw new ArithmeticException("Error in arithmetic expression: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error in arithmetic expression: " + e.getMessage());
        }
    }
    public String contextExecution(Interpreter code) throws Exception {
        try {
            jShell.eval(code.getBody());
            return executeScript(code.getBody()).toString();
        } catch (ScriptException e) {
            throw new ArithmeticException("Error in arithmetic expression: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error in arithmetic expression: " + e.getMessage());
        }
    }
    public String complexOperation(Interpreter code) throws ScriptException {
        // Use regex to find segments containing JShell commands
        jShell.eval("import java.util.*;");
        String data= "";
        Pattern pattern = Pattern.compile("[^;]+;");
        Matcher matcher = pattern.matcher(code.getBody());
        while (matcher.find()) {
          String   command = matcher.group().trim();
          data =jShell.eval(command).iterator().next().value();
        }
        return data;
    }
    public String simpleArithmetic(Interpreter code) throws Exception {
        try {
            submitFunction(code.getBody());
            return contextExecution(code);
        } catch (ScriptException e) {
            throw new ArithmeticException("Error in arithmetic expression: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error in arithmetic expression: " + e.getMessage());
        }
    }

    public Object executeScript(String scriptCode) throws ScriptException {
        StringBuilder builder = new StringBuilder();
        String expression = scriptCode.substring(scriptCode.indexOf(SPACE) + 1, scriptCode.indexOf("=")).trim();

        jShell.variables().forEach(varSnippet -> {
            if (varSnippet.name().equals(expression)) {
                builder.append(jShell.varValue(varSnippet));
            }
        });

        return builder;
    }
    public void submitFunction(String functionCode) throws ScriptException {
        if (functionCode == null) {
            functionCode=SPACE;
        }
        jShell.eval(functionCode);
    }
}
