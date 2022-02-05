package esadrcanfer.us.alumno.autotesting.generators;

import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;

public class GivenValueGenerator {

    private String defaultValue;

    public GivenValueGenerator(String defaultValue){
        this.setDefaultValue(defaultValue);
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String generateInput(UiObject object) throws UiObjectNotFoundException {
        return defaultValue;
    }
}
