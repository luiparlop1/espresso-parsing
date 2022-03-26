package esadrcanfer.us.alumno.autotesting.tests;


import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;

public abstract class AssertionChecker<T>{

    public abstract boolean assertionCheck();

    public static List<String> setInitialLabels() throws UiObjectNotFoundException {
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        List<String> initialState = new ArrayList<>();
        List<UiObject2> elements = device.findObjects(By.clazz(TextView.class));
        elements.addAll(device.findObjects(By.clazz(CheckBox.class)));
        elements.addAll(device.findObjects(By.clazz(Button.class)));
        elements.addAll(device.findObjects(By.clazz(RadioButton.class)));
        elements.addAll(device.findObjects(By.clazz(ScrollView.class)));
        elements.addAll(device.findObjects(By.clazz(Spinner.class)));
        elements.addAll(device.findObjects(By.clazz(CheckedTextView.class)));
        elements.addAll(device.findObjects(By.clazz(Switch.class)));
        for (UiObject2 label : elements) {
            String text = label.getText();
            //Solución básica, hay que mejorarla
            if (text!=null && (!(text.contains(":") || text.contains("%")))) {
               initialState.add(text);
            }
        }
        return initialState;
    }

    public static List<String> setFinalLabels() throws UiObjectNotFoundException {
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        List<String> finalState = new ArrayList<>();
        List<UiObject2> elements = device.findObjects(By.clazz(TextView.class));
        elements.addAll(device.findObjects(By.clazz(CheckBox.class)));
        elements.addAll(device.findObjects(By.clazz(Button.class)));
        elements.addAll(device.findObjects(By.clazz(RadioButton.class)));
        elements.addAll(device.findObjects(By.clazz(ScrollView.class)));
        elements.addAll(device.findObjects(By.clazz(Spinner.class)));
        elements.addAll(device.findObjects(By.clazz(CheckedTextView.class)));
        elements.addAll(device.findObjects(By.clazz(Switch.class)));
        for (UiObject2 label : elements) {
            String text = label.getText();
            //Solución básica, hay que mejorarla
            if (text!=null && (!(text.contains(":") || text.contains("%")))) {
                finalState.add(text);
            }
        }
        return finalState;
    }
}
