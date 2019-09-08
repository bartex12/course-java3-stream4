package com.batiaev.java3.lesson7.menodichka_lesson7.classReflection;

//Методы getMethods() и getDeclaredMethods() возвращают массив объектов типа Method,
// в которых содержится полная информация о методах класса. Если известно имя метода
// и набор входных параметров, то можно получить ссылку на него с помощью
// getMethod() или getDeclaredMethod().

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodsReflection_Demo {
    public static void main(String[] args) {

        //***************методы через getDeclaredMethods************
        Method[] methods = CatConstructor.class.getDeclaredMethods();
        for (Method m: methods){
            System.out.println(m.getReturnType()+ "|||" + m.getName() + "|||"+ Arrays.toString(m.getParameterTypes()));
        }

        try {
            Method method1 = CatConstructor.class.getMethod("meow", int.class);
            Method method2 = CatConstructor.class.getMethod("jamp");
            System.out.println(method1 + " | " + method2);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //void|||meow|||[int]
        //void|||jamp|||[]
        //public void com.batiaev.java3.lesson7.menodichka_lesson7.classReflection.CatConstructor.meow(int) | public void com.batiaev.java3.lesson7.menodichka_lesson7.classReflection.CatConstructor.jamp()

        //***************методы через getMethods************
        System.out.println("*****************************");
        Method[] methods2 = CatConstructor.class.getMethods(); //все методы выводит -  и суперклассов тоже
        for (Method m: methods2){
            System.out.println(m.getReturnType()+ "|||" + m.getName() + "|||"+ Arrays.toString(m.getParameterTypes()));
        }
        //void|||jamp|||[]
        //void|||meow|||[int]
        //void|||wait|||[]
        //void|||wait|||[long, int]
        //void|||wait|||[long]
        //boolean|||equals|||[class java.lang.Object]
        //class java.lang.String|||toString|||[]
        //int|||hashCode|||[]
        //class java.lang.Class|||getClass|||[]
        //void|||notify|||[]
        //void|||notifyAll|||[]

        //***************** Динамический вызов метода ********************
        //Java Reflection позволяет динамически вызвать метод,
        // даже если во время компиляции его имя было неизвестно.
        //этом примере сначала в классе Cat находим метод meow. Затем вызываем у него invoke(),
        // который у выбранного объекта вызывает этот метод и принимает два параметра.
        // Первый – это объект класса Cat, а второй – набор аргументов, передаваемых методу meow().
        //Если у метода модификатор доступа private, то получить к нему доступ можно
        // по аналогии с нашим примером о private-поле.
        System.out.println("*****************************");
        try {
            CatConstructor catConstructor = new CatConstructor("Барсик");
            Method method3 = CatConstructor.class.getMethod("meow", int.class);
            method3.invoke(catConstructor,5);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //Барсик: meow - 5 dB

    }
}
