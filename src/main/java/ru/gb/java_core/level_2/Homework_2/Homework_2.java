package ru.gb.java_core.level_2.Homework_2;

import java.util.*;

public class Homework_2 {

    public static void main(String[] args) {

        String[][] arr = { {"1","2","3","4"}, {"1","2","3","4"}, {"1","2","3","4"}, {"1","2","3","4"} };
        int arrayLength = 4;

        try {
//            arrayCheckSize(arr,arrayLength);
//            System.out.println(arrayCheckSize(arr,arrayLength));
//            arraySum(arr,arrayLength);
            System.out.println("Сумма элементов массива: " + arraySum(arr,arrayLength));
        } catch (MyArraySizeException e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (MyArrayDataException e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }


    // метод проверки массива на заданную размерность
    public static boolean arrayCheckSize(String[][] arr, int length) throws MyArraySizeException {
        if (arr.length != length) {
            throw new MyArraySizeException("Array size is incorrect: arr.length = [" + arr.length + "].");
        }
        else {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].length != length) {
                    throw new MyArraySizeException("Array size is incorrect: arr[" + i + "].length = [" + arr[i].length + "].");
                }
            }
        }
        return true;
    }

    public static int arraySum(String[][] arr, int length) throws MyArraySizeException, MyArrayDataException {
        int arraySum = 0;
        int isNumber;
        if (arrayCheckSize(arr, length)) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    try {
                        isNumber = Integer.parseInt(arr[i][j]);
                        arraySum += isNumber;
                    } catch (NumberFormatException e) {
                        throw new MyArrayDataException("Element is not a number: [" + i + "," + j + "].");
                    }
                }
            }
        }
        return arraySum;
    }
    
    
//                    else {
//                    for (int j = 0; j < arr[i].length; j++) {
//                        System.out.println(Integer.parseInt(arr[i][j]));
////                        if ( checkInt = Integer.parseInt(arr[i][j]) ) {
////                            throw new MyArrayDataException("Element is not a number: [" + i + "," + j + "].");
////                        } else {
////                            arraySum += Integer.parseInt(arr[i][j]);
////                        }
//                    }
//                }


}
