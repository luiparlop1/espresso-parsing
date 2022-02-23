package esadrcanfer.us.alumno.autotesting.generators;

import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;

import net.sf.extjwnl.JWNLException;

public class GivenValueGenerator extends AbstractGenerator{

    private String defaultValue;

    public GivenValueGenerator(){
        this.setDefaultValue(defaultValue);
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String generate() throws UiObjectNotFoundException {
        return defaultValue;
    }
}
