package esadrcanfer.us.alumno.autotesting.generators;

import net.sf.extjwnl.JWNLException;

import java.lang.reflect.*;

import esadrcanfer.us.alumno.autotesting.classes.Person;

public class ReflectionGenerator {

    public static void main(String[] args) throws JWNLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class personClass = Person.class;
        DictionaryBasedValueGenerator dictionaryGenerator = new DictionaryBasedValueGenerator(1, 1L);
        RandomIntegerGenerator integerGenerator = new RandomIntegerGenerator();
        Object[] fields = new Object[]{dictionaryGenerator.generate().toString(), integerGenerator.generate(), dictionaryGenerator.generate()};
        Class[] parameterTypes = new Class[]{String.class, Integer.class, String.class};

        Person person = new Person();
        final Class instanceClass = person.getClass();
        Method[] methods = instanceClass.getMethods();
        int index = 0;
        for(Method method : methods){
            String name = method.getName();
            if(name.startsWith("set")){
                instanceClass.getMethod(name, parameterTypes[index]).invoke(person, fields[index]);
                index++;
            }
        }
    }
    public ReflectionGenerator() throws JWNLException {
    }
}
