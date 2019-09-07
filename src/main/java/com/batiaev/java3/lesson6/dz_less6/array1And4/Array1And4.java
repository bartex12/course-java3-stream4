package com.batiaev.java3.lesson6.dz_less6.array1And4;

import java.util.Arrays;

public class Array1And4 {

    public Array1And4(){
    }

    public boolean is_1_Or_4(int[] arr){
        float sum = Arrays.stream(arr).sum();
        float size = arr.length;
        float result = sum/size;
        System.out.println("sum = " + sum + " size = " + size +
                " result = " + sum/size);
        return (result ==1f || result == 4f) ? false: true;
    }

}
//        for (int i: arr){
//            if (arr[i] == 1){
//                is1 = true;
//                continue;
//            }else if (arr[i] == 4){
//                    is4 = true;
//                }else {
//                System.out.println("Ошибка ввода - должны быть только 1 и 4");
//            }
//
//        }
//        if ((is1)&&(is4)){
//            return true;
//        }else {
//            return false;
//        }