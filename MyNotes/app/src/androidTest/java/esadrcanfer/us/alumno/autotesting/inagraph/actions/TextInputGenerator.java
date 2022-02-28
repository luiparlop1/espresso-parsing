package esadrcanfer.us.alumno.autotesting.inagraph.actions;

import android.util.Log;

import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;

import net.sf.extjwnl.JWNLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import esadrcanfer.us.alumno.autotesting.generators.DictionaryBasedValueGenerator;
import esadrcanfer.us.alumno.autotesting.generators.GivenValueGenerator;
import esadrcanfer.us.alumno.autotesting.generators.IncrementDoubleGenerator;
import esadrcanfer.us.alumno.autotesting.generators.IntegerListGenerator;
import esadrcanfer.us.alumno.autotesting.generators.ProbabilityGenerator;
import esadrcanfer.us.alumno.autotesting.generators.RandomIntegerGenerator;
import esadrcanfer.us.alumno.autotesting.generators.RandomRegexGenerator;

public class TextInputGenerator extends InputGenerator {

    private Long seed;
    private String defaultValue;

    public TextInputGenerator(Long seed, String defaultValue){
        this.seed = seed;
        this.setDefaultValue(defaultValue);
    }

    public String generateInput(UiObject object) throws UiObjectNotFoundException {
        String res = null;
        String type = "regex";
        try {
            if (getSeed() > 0 || defaultValue == null) {
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
                        RandomIntegerGenerator numberRes = new RandomIntegerGenerator(1, 6);
                        res = numberRes.generate().toString();
                        break;
                    case "regex":
                        RandomRegexGenerator regexRes = new RandomRegexGenerator("viking");
                        res = regexRes.generate();
                        break;
                    case "word":
                        DictionaryBasedValueGenerator dictionaryRes = new DictionaryBasedValueGenerator(1, Math.abs(new Random().nextLong()));
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
                object.setText(res);
            }
        }catch (JWNLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public Long getSeed() {
        return seed;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
