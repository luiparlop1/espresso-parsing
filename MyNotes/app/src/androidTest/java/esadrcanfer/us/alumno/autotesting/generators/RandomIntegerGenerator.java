package esadrcanfer.us.alumno.autotesting.generators;

import net.sf.extjwnl.JWNLException;

public class RandomIntegerGenerator {


    public static Integer generate() throws JWNLException {
        Integer min = 1;
        Integer max = 6;

        Integer randomValue = (int)(Math.random()*(max-min+1)+min);
        return randomValue;
    }

}
