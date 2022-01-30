package esadrcanfer.us.alumno.autotesting.generators;

import net.sf.extjwnl.JWNLException;

import nl.flotsam.xeger.Xeger;

public class RandomRegexGenerator {


    public String generate() throws JWNLException {

        String regex = "[CJPTFVNDL][aeiou][cjptfvndl][aeiou]( el )(destruye|pisa|empuña|tuerce|troncha|acaricia|pasea|devuelve|vuela|insulta)( gatos| peces| puentes| soles| guantes| zapatos| chicles| mares| pelos| uñas)";
        Xeger generator = new Xeger(regex);
        String result = generator.generate();
        assert result.matches(regex);

        return result;
    }

}
