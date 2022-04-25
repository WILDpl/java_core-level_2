package ru.gb.java_core.level_2.Homework_2;

import java.util.*;

public class Homework_2 {

    public static void main(String[] args) {

        String[][] arr = { {"1","2","3","4"}, {"1","#","3","4"}, {"1","2","3","4"}, {"1","2","3","4","5"} };
        int arrayLength = 4;

//        System.out.println(Arrays.deepToString(arr));
//        System.out.println(arrayCheckSize(arr, 4));

        if (!arrayCheckSize(arr,arrayLength)) {
            throw new MyArraySizeException("Array size is incorrect.");
        }

    }

    // метод проверки массива на заданную размерность
    public static boolean arrayCheckSize(String[][] arr, int length) {
        boolean arrCheck = true;

        if (arr.length == length) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].length != length) {
                    arrCheck = false;
                    break;
                }
            }
        } else {
            arrCheck = false;
        }

        return arrCheck;
    }


}
