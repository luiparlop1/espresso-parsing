package esadrcanfer.us.alumno.autotesting.generators;


import androidx.test.uiautomator.UiObjectNotFoundException;

import net.sf.extjwnl.JWNLException;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

import esadrcanfer.us.alumno.autotesting.classes.Person;

public class ReflectionGenerator{

    String attributeName;
    static Map<String, Object> attributeMap = new HashMap<String, Object>();
    static Object value;
    Object target;
//  Generator generator;

    public ReflectionGenerator(String attributeName, Object value, Object target){
        this.attributeName = attributeName;
        this.value = value;
        this.target = target;
    }

    public void generate() throws IllegalAccessException, NoSuchFieldException {

        final Class instanceClass = target.getClass();
       Field attribute = instanceClass.getDeclaredField(attributeName);
            if(attribute!=null){
                attribute.setAccessible(true);
                attribute.set(target, value);
            }
        }


    public static void main(String args[]) throws IllegalAccessException, NoSuchFieldException, JWNLException, UiObjectNotFoundException {

        Person p = new Person();

        Field[] attributes = p.getClass().getDeclaredFields();
        for(Field a: attributes){
            String fieldName = a.getName();

            if(fieldName.contains("name") || fieldName.contains("Name")){
                Long seed = 0L + RandomIntegerGenerator.generate(1,6);
                DictionaryBasedValueGenerator dict = new DictionaryBasedValueGenerator(1, seed);
                value = dict.generate();

            }else if(fieldName.contains("mail")){
                RandomRegexGenerator regex = new RandomRegexGenerator();
                value = regex.generate();
            }else{
                value = RandomIntegerGenerator.generate(1,6);
            }
            attributeMap.put(fieldName, value);
            ReflectionGenerator generator = new ReflectionGenerator(fieldName, value , p);
            generator.generate();
        }

        System.out.println(p.getName());
        System.out.println(p.getLastName());
        System.out.println(p.getAge());
        System.out.println(p.getEmail());
    }

}
