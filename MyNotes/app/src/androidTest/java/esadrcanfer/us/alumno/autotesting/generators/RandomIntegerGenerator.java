package esadrcanfer.us.alumno.autotesting.generators;

import net.sf.extjwnl.JWNLException;

public class RandomIntegerGenerator {


    public Integer generate() throws JWNLException {
        int min = 1;
        int max = 6;

        int chosenNumber = (int)(Math.random()*(max-min+1)+min);
        return chosenNumber;
    }

}
