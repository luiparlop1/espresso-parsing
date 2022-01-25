package esadrcanfer.us.alumno.autotesting;

import androidx.test.uiautomator.UiObjectNotFoundException;

import org.junit.Test;

import java.io.IOException;

import esadrcanfer.us.alumno.autotesting.tests.ReadTestCase;

public class ParsedTestReader {

    @Test
    public void crearNotaUITest() throws UiObjectNotFoundException, IOException {
        ReadTestCase read = new ReadTestCase();
        read.read("TestCase-20220115_185154", true);
    }

}
