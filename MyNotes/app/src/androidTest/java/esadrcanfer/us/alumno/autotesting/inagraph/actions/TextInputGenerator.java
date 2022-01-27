package esadrcanfer.us.alumno.autotesting.inagraph.actions;

import android.util.Log;

import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;

import net.sf.extjwnl.JWNLException;

import esadrcanfer.us.alumno.autotesting.generators.DictionaryBasedValueGenerator;
import esadrcanfer.us.alumno.autotesting.generators.RandomIntegerGenerator;

public class TextInputGenerator extends InputGenerator {

    private Long seed;
    private String defaultValue;
    private String value;

    public TextInputGenerator(Long seed, String defaultValue){
        this.seed = seed;
        this.setDefaultValue(defaultValue);
    }

    public String generateInput(UiObject object) throws UiObjectNotFoundException {
        String value = getDefaultValue();

        DictionaryBasedValueGenerator dictionary = new DictionaryBasedValueGenerator(1, getSeed());
        String dictionaryValue = defaultValue;
//        String intValue = defaultValue;
        RandomIntegerGenerator gInteger = new RandomIntegerGenerator();
        try {
            if(getSeed() > 0 || defaultValue == null) {
               dictionaryValue = dictionary.generate().toString();
//               intValue = gInteger.generate().toString();
            }
                Log.d("TFG", dictionaryValue);
            object.setText(dictionaryValue);
        } catch (JWNLException e) {
            e.printStackTrace();
        }
        return value;
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
