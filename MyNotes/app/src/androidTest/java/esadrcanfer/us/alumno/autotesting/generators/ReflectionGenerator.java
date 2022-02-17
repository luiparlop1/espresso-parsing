package esadrcanfer.us.alumno.autotesting.generators;


import java.lang.reflect.*;

import esadrcanfer.us.alumno.autotesting.classes.Person;

public class ReflectionGenerator {

    String attributeName;
    String className;
    Object value;
    Object target;
//    Generator generator;

    public ReflectionGenerator(String attributeName, String className, Object value, Object target){
        this.attributeName = attributeName;
        this.className = className;
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


    public static void main(String args[]) throws IllegalAccessException, NoSuchFieldException {

        Person p = new Person();
        p.setName("Francisco");

        ReflectionGenerator generator = new ReflectionGenerator("age", "esadrcanfer.us.alumno.autotesting.classes.Person",12 , p);
        generator.generate();
        System.out.println(p.getName());
        System.out.println(p.getAge());
    }

}
