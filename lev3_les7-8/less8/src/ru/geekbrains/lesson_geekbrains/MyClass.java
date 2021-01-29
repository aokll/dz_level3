package ru.geekbrains.lesson_geekbrains;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyClass  {
    public static void start (Class c){
        List<Method> methods = new ArrayList<>();
        Method[] classMethods = c.getDeclaredMethods();

        for (Method m : classMethods) {
            if (m.isAnnotationPresent(Test.class)){
                methods.add(m);
            }
        }
        methods.sort(Comparator.comparingInt((Method i) -> {
            return i.getAnnotation(Test.class).priority();
        }).reversed());

        for (Method m : classMethods) {
            if (m.isAnnotationPresent(Before.class)){
                if (methods.size() > 0 && methods.get(0).isAnnotationPresent(Before.class)){
                    throw new RuntimeException("Before annotation method > 1");
                }
                methods.add(0,m);
            }
        }
        for (Method m : classMethods) {
            if (m.isAnnotationPresent(After.class)){
                if (methods.size() > 0 && methods.get(methods.size() - 1).isAnnotationPresent(After.class)){
                    throw new RuntimeException("Before annotation method > 1");
                }
                methods.add(m);
            }
        }
        for (Method m : methods){
            try {
                m.invoke(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }


    }
}
