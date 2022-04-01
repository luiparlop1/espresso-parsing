package esadrcanfer.us.alumno.autotesting;

import androidx.test.uiautomator.UiObjectNotFoundException;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import esadrcanfer.us.alumno.autotesting.tests.ReadTestCase;

public class ParsedTestReader {

    @Test
    public void crearNotaUITest() throws UiObjectNotFoundException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ReadTestCase read = new ReadTestCase();
        read.read("TestSimpleA", true);
    }
}
