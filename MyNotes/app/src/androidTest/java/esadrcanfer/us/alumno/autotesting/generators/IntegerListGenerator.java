package esadrcanfer.us.alumno.autotesting.generators;

import net.sf.extjwnl.JWNLException;

import java.util.ArrayList;
import java.util.List;

public class IntegerListGenerator {

    List<Integer> valueList = new ArrayList<Integer>();
    Integer a = 15; Integer b = 34; Integer c = 68; Integer d = 90;

    public Integer generate() throws JWNLException {

        valueList.add(a);valueList.add(b);valueList.add(c);valueList.add(d);

        int min = 0;
        int max = valueList.size()-1;
        int chosenIndex = (int)(Math.random()*(max-min+1)+min);

        Integer randomValue = valueList.get(chosenIndex);

    return randomValue;
    }
}
