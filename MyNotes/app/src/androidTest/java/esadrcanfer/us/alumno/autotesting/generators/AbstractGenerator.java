package esadrcanfer.us.alumno.autotesting.generators;

import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;

import net.sf.extjwnl.JWNLException;

import esadrcanfer.us.alumno.autotesting.inagraph.actions.ButtonAction;

public abstract class AbstractGenerator<T> {

    public abstract T generate() throws UiObjectNotFoundException, JWNLException;

    public String generateSomething() throws JWNLException, UiObjectNotFoundException {
        String res = null;
        String type = "numberFromList";
        switch (type) {
            case "numberFromList":
                IntegerListGenerator integerRes = new IntegerListGenerator();
                res = integerRes.generate().toString();
                break;
            case "numberFromProbabilityList":
                ProbabilityGenerator integerProbabilityRes = new ProbabilityGenerator();
                res = integerProbabilityRes.generate().toString();
                break;
            case "number":
                RandomIntegerGenerator numberRes = new RandomIntegerGenerator();
                res = numberRes.generate().toString();
                break;
            case "regex":
                RandomRegexGenerator regexRes = new RandomRegexGenerator();
                res = regexRes.generate();
                break;
            case "word":
                DictionaryBasedValueGenerator dictionaryRes = new DictionaryBasedValueGenerator();
                res = dictionaryRes.generate();
                break;
            case "given":
                GivenValueGenerator givenRes = new GivenValueGenerator();
                res = givenRes.generate();
                break;
            case "lagrange":
                LagrangeDoubleGenerator lagrangeRes = new LagrangeDoubleGenerator();
                res = lagrangeRes.generate().toString();
                break;
//            case "reflection":
//                ReflectionGenerator reflectionRes = new ReflectionGenerator();
//                res = reflectionRes.generate();
//                break;
        }
        return res;
    }
}
