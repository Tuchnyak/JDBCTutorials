package ru.levelup.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class RandomIntegerConfigurationRunner {

    public static void configure(RandomIntegerExample example) {

//        Class classOfC = RandomIntegerConfigurationRunner.class.getClass();
//        Package packageOfT = classOfC.getPackage();

//        Class exampleClass = RandomIntegerExample.class.getClass();
        Class exampleClass = example.getClass();
        Field[] declaredFields = exampleClass.getDeclaredFields();

        for (Field f : declaredFields) {
            Annotation[] annotations = f.getAnnotations();

            for (Annotation a : annotations) {
                Class[] annotationClass = a.getClass().getInterfaces();
                for (Class ac : annotationClass) {
                    if (ac.getName().equals("ru.levelup.annotation.RandomInteger")) {
                        Method aMin;
                        Method aMax;
                        int minVal;
                        int maxVal;
                        int random;
                        try {
                            aMin = a.annotationType().getDeclaredMethod("min");
                            aMax = a.annotationType().getDeclaredMethod("max");
                            minVal = (int) aMin.invoke(a);
                            maxVal = (int) aMax.invoke(a);
                            random = (int) (minVal + Math.random() * (maxVal - minVal + 1));
                            f.setAccessible(true);
                            f.set(example, random);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }

        }

    }

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        RandomIntegerExample example = new RandomIntegerExample();

        configure(example);

        System.out.println(example.getValue() + " " + example.getValueWithoutMax() + " " + example.getValueWithoutMin());

//        Class exClass = example.getClass();
//        System.out.println(exClass.getName());
//
//        Field[] fields = exClass.getDeclaredFields();
//
//        for (Field f : fields) {
//            f.setAccessible(true);
//            System.out.println(f.get(example) + "***********************");
//        }
//
//        for (Field f : fields) {
//            System.out.println(f.getName());
//            Annotation[] annotations = f.getAnnotations();
//            for (Annotation a : annotations) {
//                Class aClass = a.getClass().getInterfaces()[0];
//                System.out.println(aClass.getName() + "*******");
//
//                if (aClass.getName().equals("ru.levelup.annotation.RandomInteger")) {
//                    Method aM = a.annotationType().getDeclaredMethod("min");
//                    System.out.println(aM.getName());
//                    int minVal = (int) aM.invoke(a);
//                    System.out.println(minVal);
//                    f.setAccessible(true);
//                    f.set(example, minVal);
//                }
//
//
//            }
//        }
//
//        for (Field f : fields) {
//            System.out.println(f.get(example) + "***********************");
//        }

    }


}
