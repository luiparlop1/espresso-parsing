package esadrcanfer.us.alumno.autotesting.generators;

import net.sf.extjwnl.JWNLException;

import java.util.Map;
import java.util.TreeMap;

public class ProbabilityGenerator extends IntegerListGenerator{

    Map<Integer, Integer> probabilityMap = new TreeMap<>();

    public ProbabilityGenerator(){
    }

    public Integer generate() throws JWNLException {

        probabilityMap.put(a,10);probabilityMap.put(b,20);probabilityMap.put(c,30);probabilityMap.put(d,40); //Creamos un mapa con los valores y un valor de probabilidad asociado a cada uno

        Integer min = 0;
        Integer max = 0;
        Integer randomValue = 0;

        for(int value: probabilityMap.values()) //Este for recorre el mapa, y va sumando los valores de probabilidad asociados a cada valor en el mapa.
            max = max+value;

        Integer chosenNumber = (int)(Math.random()*(max-min+1)+min); //Esto coge un número aleatorio entre 0 y el max;

        Integer acc = 0; //Inicializamos un valor acumulativo que será igual a la suma en cada iteración de los valores del mapa en el siguiente for.

        for (Map.Entry<Integer, Integer> entry : probabilityMap.entrySet()) {

            if(chosenNumber >= acc && chosenNumber < acc+entry.getValue()){
                randomValue = entry.getKey();
            }
            acc = acc+entry.getValue(); //Sumamos el valor actual a lo que ya llevamos

        }
        return randomValue;
    }

}
