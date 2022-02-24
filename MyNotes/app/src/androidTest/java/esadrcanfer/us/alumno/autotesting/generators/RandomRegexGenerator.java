package esadrcanfer.us.alumno.autotesting.generators;

import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;

import net.sf.extjwnl.JWNLException;

import nl.flotsam.xeger.Xeger;

public class RandomRegexGenerator extends AbstractGenerator{

    protected String type;
    public RandomRegexGenerator(String type){
        this.type=type;
    }

    public String generate() throws JWNLException, UiObjectNotFoundException {

        String randomValue = null;

        if(type.equals("email")) {
            String regex = "[bcdfghjklmnpqrstvwxy][aeiou][bcdfghjklmnpqrstvwxy][aeiou][bcdfghjklmnpqrstvwxy][aeiou][bcdfghjklmnpqrstvwxy][aeiou][123456789][123456789](ARROBA)(gmail)(PUNTO)(com)";
            Xeger generator = new Xeger(regex);
            randomValue = generator.generate();
            assert randomValue.matches(regex);
        }else if(type.equals("viking")){
            String regex = "[CJPTFVNDL][aeiou][cjptfvndl][aeiou]( el )(destruye|pisa|empuña|tuerce|troncha|acaricia|pasea|devuelve|vuela|insulta|abraza|consume|desestabiliza|respira|regaña)( gatos| peces| puentes| soles| guantes| zapatos| chicles| mares| pelos| uñas| mundos| conceptos| palomitas)";
            Xeger generator = new Xeger(regex);
            randomValue = generator.generate();
            assert randomValue.matches(regex);
        }
        return randomValue;
    }

}
