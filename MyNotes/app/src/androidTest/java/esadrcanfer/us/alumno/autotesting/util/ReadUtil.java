package esadrcanfer.us.alumno.autotesting.util;

import android.os.Environment;
import android.util.Log;

import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import esadrcanfer.us.alumno.autotesting.TestCase;
import esadrcanfer.us.alumno.autotesting.inagraph.CloseAppAction;
import esadrcanfer.us.alumno.autotesting.inagraph.StartAppAction;
import esadrcanfer.us.alumno.autotesting.inagraph.actions.Action;
import esadrcanfer.us.alumno.autotesting.inagraph.actions.ButtonAction;
import esadrcanfer.us.alumno.autotesting.inagraph.actions.CheckBoxAction;
import esadrcanfer.us.alumno.autotesting.inagraph.actions.CheckedTextAction;
import esadrcanfer.us.alumno.autotesting.inagraph.actions.CountDownAction;
import esadrcanfer.us.alumno.autotesting.inagraph.actions.RadioButtonAction;
import esadrcanfer.us.alumno.autotesting.inagraph.actions.RadioButtonInputGenerator;
import esadrcanfer.us.alumno.autotesting.inagraph.actions.ScrollToAction;
import esadrcanfer.us.alumno.autotesting.inagraph.actions.SpinnerAction;
import esadrcanfer.us.alumno.autotesting.inagraph.actions.SwitchAction;
import esadrcanfer.us.alumno.autotesting.inagraph.actions.TextInputAction;
import esadrcanfer.us.alumno.autotesting.inagraph.actions.TextInputGenerator;

public class ReadUtil {
    String path;
    Boolean sameSeed;

    public ReadUtil(String path, Boolean sameSeed){
        this.path = path;
        this.sameSeed = sameSeed;
    }

    public String getPath(){
        return this.path;
    }

    public String readText(){
        StringBuilder text = new StringBuilder();
        try {
            String filename=Environment.getExternalStorageDirectory().getAbsolutePath().toString()
                    + "/" + getPath();
            BufferedReader br = new BufferedReader(new FileReader(filename
            ));
            String line;
            while ((line = br.readLine())!= null){
                text.append(line);
                text.append("\n");
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    public TestCase generateTestCase(){
        List<Action> beforeActions = new ArrayList<>();
        List<Action> afterActions = new ArrayList<>();
        List<Action> testActions = new ArrayList<>();
        String text = readText();
        String[] lines = text.split("\n");
        String appPackage = lines[0];
        Long seed;

        if(sameSeed) {
            seed = new Long(lines[1]);
        }else{
            seed = Math.abs(new Random().nextLong());
        }
        Integer actionsSize = new Integer(lines[2]);
        Random random = new Random(seed);
        String action = "";
        for(int i = 3; i<= actionsSize + 2; i++){
            action = lines[i];
            if (seed <  0)
                testActions.add(generateActionFromString(action,  seed));
            else
                testActions.add(generateActionFromString(action, random.nextLong()));
            if(i == actionsSize+2){
                actionsSize = i;
                break;
            }
        }
        String predicate = null; //Inicializo a null la aserción porque no tiene por qué haberlas siempre.
        long numberOfLines = Arrays.stream(lines).count(); //Cuento el número de líneas del archivo
        actionsSize = new Integer(lines[2]); //Vuelvo a setear actionsSize porque el método de arriba las cambia para acabar el for y las "arruina"
        if (numberOfLines != actionsSize+3) { //Esto cuenta las líneas y si no hay aserción, no habrá mas de actionsSize + 3
            predicate = lines[actionsSize + 3]; //Si hay aserción, se va a la línea donde debería estar. Cambié el +1 por un +3 porque tres líneas más arriba he cambiado el valor de actionsSize después del for
        }

        List<String> initialLabels = new ArrayList<>();
        /*
        String initialState = lines[actionsSize+2].replaceAll("\\[", "").replaceAll("\\]", "");
        String finalState = lines[actionsSize+3].replaceAll("\\[", "").replaceAll("\\]", "");
        for (String label: initialState.split(", ")) {
            initialLabels.add(label);
        }*/
        List<String> finalLabels = new ArrayList<>();
        /*for (String label: finalState.split(", ")) {
            finalLabels.add(label);
        }*/
        beforeActions.add(new StartAppAction(appPackage));
        afterActions.add(new CloseAppAction(appPackage));
        TestCase testCase = new TestCase(appPackage, Collections.EMPTY_SET,beforeActions,testActions,afterActions, initialLabels, finalLabels);
        testCase.setPredicate(predicate);
        return testCase;
    }

    public Action generateActionFromString(String action, Long seed){
        String[] splitAction = action.split(", "); // Dividir la cadena por comas
        String type = splitAction[0];       // Seleccionar el tipo de objeto (botón, cuadro de texto, radio button, etc.)
        String resourceId = splitAction[1]; // Selector del objeto sobre el que actuar
        String value = splitAction.length==2?"":splitAction[2];      // Valor a usar para realizar la acción
        String selectorType = resourceId.substring(resourceId.indexOf("[")+1,resourceId.indexOf("=")).trim();
        resourceId = resourceId.substring(resourceId.indexOf("=") + 1 ,resourceId.length()-1);
        Action res = null;
        UiObject object = null;
        if(selectorType.equals("RESOURCE_ID"))
            object = new UiObject(new UiSelector().resourceId(resourceId));
        else if(selectorType.equals("DESCRIPTION"))
            object = new UiObject(new UiSelector().descriptionContains(resourceId));
        else if(selectorType.equals("TEXT"))
            object = new UiObject(new UiSelector().textContains(resourceId));
        else if (selectorType.equals("SCROLLABLE"))
            object = new UiObject(new UiSelector().scrollable(!type.equals("SCROLL_DOWN")));
        else if (selectorType.equals("POSITION"))
            object = new UiObject(new UiSelector().className("android.widget.CheckedTextView").index(Integer.parseInt(resourceId)));
        switch (type) {
            case "BUTTON":
                res = new ButtonAction(object);
                break;
            case "TEXT":
                TextInputGenerator textInputGenerator = new TextInputGenerator(seed, value);
                res = new TextInputAction(object, textInputGenerator);
                break;
            case "CHECKBOX":
                res = new CheckBoxAction(object);
                break;
            case "RADIO_BUTTON":
                RadioButtonInputGenerator radioButtonInputGenerator = new RadioButtonInputGenerator(seed);
                res = new RadioButtonAction(object, radioButtonInputGenerator);
                break;
            case "SCROLL_TO":
                res = new ScrollToAction(object);
                break;
            case "COUNT_DOWN":
                res = new CountDownAction(object);
                break;
            case "SPINNER":
                res = new SpinnerAction(object);
                break;
            case "CHECKED_TEXT":
                res = new CheckedTextAction(object);
                break;
            case "SWITCH":
                res = new SwitchAction(object);
        }
        Log.d("ISA", "Action: " + action);
        Log.d("ISA", "Value: " + value);
        res.setValue(value.trim());
        return res;
    }

    public static Action generateActionFromSimpleString(String action, Long seed){
        Log.d("ISA", action);
        String value = null;
        String[] splitAction = action.split(", ");
        String type = splitAction[0];
        String resourceId = splitAction[1];
        resourceId = splitAction[1].substring(resourceId.indexOf("=") + 1 ,resourceId.length()-1);
        Action res = null;
        UiObject object = new UiObject(new UiSelector().resourceId(resourceId));
        switch (type){
            case "BUTTON":
                res = new ButtonAction(object);
                break;
            case "TEXT":
                TextInputGenerator textInputGenerator = new TextInputGenerator(seed, value);
                res = new TextInputAction(object, textInputGenerator);
                break;
            case "CHECKBOX":
                res = new CheckBoxAction(object);
                break;
            case "RADIO_BUTTON":
                RadioButtonInputGenerator radioButtonInputGenerator = new RadioButtonInputGenerator(seed);
                res = new RadioButtonAction(object, radioButtonInputGenerator);
                break;
            case "SCROLL_TO":
                res = new ScrollToAction(object);
                break;
            case "COUNT_DOWN":
                res = new CountDownAction(object);
                break;
            case "SPINNER":
                res = new SpinnerAction(object);
                break;
            case "CHECKED_TEXT":
                res = new CheckedTextAction(object);
                break;
            case "SWITCH":
                res = new SwitchAction(object);
        }
        return res;
    }
}