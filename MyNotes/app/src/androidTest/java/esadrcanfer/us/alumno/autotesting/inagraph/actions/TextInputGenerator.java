package esadrcanfer.us.alumno.autotesting.inagraph.actions;

import android.os.Environment;
import android.util.Log;

import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;

import net.sf.extjwnl.JWNLException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import esadrcanfer.us.alumno.autotesting.generators.DictionaryBasedValueGenerator;
import esadrcanfer.us.alumno.autotesting.generators.GivenValueGenerator;
import esadrcanfer.us.alumno.autotesting.generators.IncrementDoubleGenerator;
import esadrcanfer.us.alumno.autotesting.generators.IntegerListGenerator;
import esadrcanfer.us.alumno.autotesting.generators.ProbabilityGenerator;
import esadrcanfer.us.alumno.autotesting.generators.RandomIntegerGenerator;
import esadrcanfer.us.alumno.autotesting.generators.RandomRegexGenerator;
import esadrcanfer.us.alumno.autotesting.util.ReadUtil;

public class TextInputGenerator extends InputGenerator {

    private Long seed;
    private String defaultValue;
    private Integer iterator = -1;

    public TextInputGenerator(Long seed, String defaultValue){
        this.seed = seed;
        this.setDefaultValue(defaultValue);
    }

    public String generateInput(UiObject object) throws UiObjectNotFoundException {
        String res = getDefaultValue();
        String type = "";

        ReadUtil ru = new ReadUtil("Download/configu.txt");
        String configFile = ru.readText();
        String[] lines = configFile.split("\n");
        Integer iteration = iterate();
        String line = lines[iteration];
        String[] splitLine = line.split("-");
        type = splitLine[1];

        String conditions = splitLine[2];
        String[] splitConditions = conditions.split("/");

        String cond1 = splitConditions[0];
        String cond2 = "";

        if(!conditions.endsWith("/")) {
            cond2 = splitConditions[1];
        }

        List<Integer> integerList = new ArrayList<>();

        if(type.equals("numberFromList") || type.equals("numberFromProbabilityList")){
            String[] numbers = cond1.split(",");
            for(String number: numbers){
                integerList.add(Integer.parseInt(number));
            }
        }

        try {
            if (getSeed() > 0 || defaultValue == null) {
                switch (type) {
                    case "numberFromList":
                        IntegerListGenerator integerRes = new IntegerListGenerator(integerList);
                        res = integerRes.generate().toString();
                        break;
                    case "numberFromProbabilityList":
                        ProbabilityGenerator integerProbabilityRes = new ProbabilityGenerator(integerList);
                        res = integerProbabilityRes.generate().toString();
                        break;
                    case "number":
                        int min=Integer.parseInt(cond1);
                        int max=Integer.parseInt(cond2);
                        RandomIntegerGenerator numberRes = new RandomIntegerGenerator(min, max);
                        res = numberRes.generate().toString();
                        break;
                    case "regex":
                        RandomRegexGenerator regexRes = new RandomRegexGenerator(cond1);
                        res = regexRes.generate();
                        break;
                    case "word":
                        int numberOfWords = Integer.parseInt(cond1);
                        DictionaryBasedValueGenerator dictionaryRes = new DictionaryBasedValueGenerator(numberOfWords, Math.abs(new Random().nextLong()));
                        res = dictionaryRes.generate();
                        break;
                    case "given":
                        String givenWord = cond1;
                        GivenValueGenerator givenRes = new GivenValueGenerator(givenWord);
                        res = givenRes.generate();
                        break;
                    case "increment":
                        int givenNumber = Integer.parseInt(cond1);
                        IncrementDoubleGenerator incrementRes = new IncrementDoubleGenerator(givenNumber);
                        res = incrementRes.generate().toString();
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

    private Integer iterate(){
    return iterator+1;
    }
}
