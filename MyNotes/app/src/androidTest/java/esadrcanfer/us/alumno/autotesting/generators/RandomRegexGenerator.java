package esadrcanfer.us.alumno.autotesting.generators;

import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;

import net.sf.extjwnl.JWNLException;

import nl.flotsam.xeger.Xeger;

public class RandomRegexGenerator extends AbstractGenerator{


    public String generate() throws JWNLException, UiObjectNotFoundException {

        String regex = "[CJPTFVNDL][aeiou][cjptfvndl][aeiou]( el )(destruye|pisa|empuña|tuerce|troncha|acaricia|pasea|devuelve|vuela|insulta)( gatos| peces| puentes| soles| guantes| zapatos| chicles| mares| pelos| uñas)";
        Xeger generator = new Xeger(regex);
        String randomValue = generator.generate();
        assert randomValue.matches(regex);

        return randomValue;
    }

}
