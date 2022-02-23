package esadrcanfer.us.alumno.autotesting.generators;

import androidx.test.uiautomator.UiObject;

import net.sf.extjwnl.JWNLException;

import java.util.concurrent.ThreadLocalRandom;

public class LagrangeDoubleGenerator{

    Integer givenNumber;
    Long seed;

    public LagrangeDoubleGenerator(){
     this.seed = seed;
     this.givenNumber = givenNumber;
    }

    public Double generate() throws JWNLException {

        Double min = 0.0;
        Double max = 1.0;
        Double increment = ThreadLocalRandom.current().nextDouble(min, max);
        Double randomValue = givenNumber + increment;
        return randomValue;
    }

}
