package ru.levelup.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class RandomIntegerConfigurationRunner {

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        RandomIntegerExample example = new RandomIntegerExample();

        Class exClass = example.getClass();
        System.out.println(exClass.getName());

        Field[] fields = exClass.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f.getName());
            Annotation[] annotations = f.getAnnotations();
            for (Annotation a : annotations) {
                Class aClass = a.getClass().getInterfaces()[0];
                System.out.println(aClass.getName());

                if (aClass.getName().equals("ru.levelup.annotation.RandomInteger")) {
                    Method aM = a.annotationType().getDeclaredMethod("min");
                    System.out.println(aM.getName());
                    int minVal = (int) aM.invoke(a);
                    System.out.println(minVal);
                }


            }
        }


    }


    public static void configure() {

        Class exampleClass = RandomIntegerExample.class.getClass();
        Field[] declaredFields = exampleClass.getDeclaredFields();

        for (Field f : declaredFields) {
            Annotation[] annotations = f.getAnnotations();

            for (Annotation a : annotations) {
                Class annotationClass = a.getClass();

                if (annotationClass.getName().equals("ru.levelup.annotation.RandomInteger")) {
                    try {
                        Field fieldMin = annotationClass.getField("min");
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    try {
                        Field fieldMax = annotationClass.getField("max");
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }

            }

        }


    }

}
