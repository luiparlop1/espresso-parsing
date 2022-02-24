package esadrcanfer.us.alumno.autotesting.generators;

import androidx.test.uiautomator.UiObjectNotFoundException;

import net.sf.extjwnl.JWNLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractGenerator<T> {

    public abstract T generate() throws UiObjectNotFoundException, JWNLException;

    public String generateSomething() throws JWNLException, UiObjectNotFoundException {
        String res = null;
        String type = "numberFromList";
        switch (type) {
            case "numberFromList":
                List<Integer> integerList = new ArrayList<Integer>();
                integerList.addAll(Arrays.asList(1, 2, 3, 4, 5));
                IntegerListGenerator integerRes = new IntegerListGenerator(integerList);
                res = integerRes.generate().toString();
                break;
            case "numberFromProbabilityList":
                List<Integer> integerProbList = new ArrayList<Integer>();
                integerProbList.addAll(Arrays.asList(1, 2, 3, 4, 5));
                ProbabilityGenerator integerProbabilityRes = new ProbabilityGenerator(integerProbList);
                res = integerProbabilityRes.generate().toString();
                break;
            case "number":
                RandomIntegerGenerator numberRes = new RandomIntegerGenerator(1,6);
                res = numberRes.generate().toString();
                break;
            case "regex":
                RandomRegexGenerator regexRes = new RandomRegexGenerator("vikingName");
                res = regexRes.generate();
                break;
            case "word":
                DictionaryBasedValueGenerator dictionaryRes = new DictionaryBasedValueGenerator();
                res = dictionaryRes.generate();
                break;
            case "given":
                GivenValueGenerator givenRes = new GivenValueGenerator("Word");
                res = givenRes.generate();
                break;
            case "increment":
                IncrementDoubleGenerator lagrangeRes = new IncrementDoubleGenerator(2);
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
