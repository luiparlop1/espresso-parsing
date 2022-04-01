package esadrcanfer.us.alumno.autotesting.tests;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static esadrcanfer.us.alumno.autotesting.tests.AssertionChecker.setLabels;
import static esadrcanfer.us.alumno.autotesting.tests.AutomaticRepairTests.labelsDetection;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObjectNotFoundException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import esadrcanfer.us.alumno.autotesting.TestCase;
import esadrcanfer.us.alumno.autotesting.util.ReadUtil;

@RunWith(AndroidJUnit4.class)
public class ReadTestCase {

    @Test
    public void read(String s, boolean b) throws UiObjectNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        UiDevice.getInstance(getInstrumentation());
        ReadUtil readUtil = new ReadUtil("Download/"+s+".txt", b);
        TestCase testCase = readUtil.generateTestCase();
        Log.d("TFG","Test case found: "+testCase);
        Log.d("TFG","Runnig it...");
        testCase.executeBefore();
        List<String> initialState = setLabels();
        testCase.setInitialState(initialState);
        testCase.executeTest();
        List<String> finalState = setLabels();
        testCase.setFinalState(finalState);
        Boolean eval = testCase.evaluateAssertion();
        Log.d("ISA", "Initial evaluation: " + eval.toString());
        testCase.executeAfter();
        if (eval.equals(false)) {
            Assert.fail("The assertion '" + testCase.getPredicate() + "' is not true");
        }
    }
}
