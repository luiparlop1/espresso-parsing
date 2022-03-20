package esadrcanfer.us.alumno.autotesting;

import org.junit.Test;

import java.io.IOException;

public class TestConverter {

    @Test
    public void crearNotaTest() throws IOException {
        TextualUITestGenerator parse = new TextualUITestGenerator();
        parse.textualUITestGenerator("BackTest","src/androidTest/java/esadrcanfer/us/alumno/autotesting/", BuildConfig.APPLICATION_ID);
    }

}
