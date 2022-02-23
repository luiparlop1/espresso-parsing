package esadrcanfer.us.alumno.autotesting.generators;

import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;

import net.sf.extjwnl.JWNLException;

import java.util.ArrayList;
import java.util.List;

public class IntegerListGenerator extends AbstractGenerator{

    List<Integer> valueList = new ArrayList<Integer>();
    Integer a = 15; Integer b = 34; Integer c = 68; Integer d = 90;
    Long seed;

    public IntegerListGenerator(){
        this.valueList=valueList;
    }

    public Integer generate() throws JWNLException{

        valueList.add(a);valueList.add(b);valueList.add(c);valueList.add(d);

        Integer min = 0;
        Integer max = valueList.size()-1;
        Integer chosenIndex = (int)(Math.random()*(max-min+1)+min);

        Integer randomValue = valueList.get(chosenIndex);

        return randomValue;
    }
}
