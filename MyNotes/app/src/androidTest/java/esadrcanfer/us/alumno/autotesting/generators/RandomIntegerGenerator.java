package esadrcanfer.us.alumno.autotesting.generators;

import net.sf.extjwnl.JWNLException;

public class RandomIntegerGenerator {


    public static Integer generate(Integer min, Integer max) throws JWNLException {
        Integer randomValue = (int)(Math.random()*(max-min+1)+min);
        return randomValue;
    }

}
