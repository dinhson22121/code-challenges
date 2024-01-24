package com.rapiddweller.codechallenges.interpreter.service;

import com.rapiddweller.codechallenges.interpreter.Interpreter;
import org.springframework.stereotype.Service;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@Service
public class InterpreterService {


    private final ScriptEngine scriptEngine;

    public InterpreterService() {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        this.scriptEngine = scriptEngineManager.getEngineByName("Nashorn");
    }

    public Object submitFunction(String functionCode) throws ScriptException {
       return scriptEngine.eval(functionCode);
    }

    public Object executeScript(String scriptCode) throws ScriptException, NoSuchMethodException {
        scriptEngine.eval(scriptCode);
        Invocable invocable = (Invocable) scriptEngine;
        return invocable.invokeFunction("execute");
    }
    public String performArithmeticOperation(Interpreter code) throws Exception {
        try {
            var result = submitFunction(code.getBody());
            return result.toString();
        } catch (ScriptException e) {
            throw new ArithmeticException("Error in arithmetic expression: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error in arithmetic expression: " + e.getMessage());
        }
    }

    public String contextSubmissionAndExecution(Interpreter code) throws Exception {
        Object result = null;
        try {
            submitFunction(code.getMethod());
            result = executeScript(code.getBody());
        } catch (ScriptException e) {
            throw new ArithmeticException("Error in arithmetic expression: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error in arithmetic expression: " + e.getMessage());
        }
        return result.toString();
    }
}
