package ru.gb.java_core.level_2.Homework_2;

import java.util.*;

public class Homework_2 {

    public static void main(String[] args) {

        // массивы: arr3 - с 3 ошибками; arr2 - с 2 ошибками; arr1 - с 1 ошибкой; arr - без ошибок;
        String[][] arr3 = { {"1","@","3","4"}, {"1","2","3","4"}, {"1","2","3","4"}, {"1","2","3","4","5"},{"1"} };
        String[][] arr2 = { {"1","@","3","4"}, {"1","2","3","4"}, {"1","2","3","4"}, {"1","2","3","4","5"} };
        String[][] arr1 = { {"1","@","3","4"}, {"1","2","3","4"}, {"1","2","3","4"}, {"1","2","3","4",} };
        String[][] arr = { {"1","2","3","4"}, {"1","2","3","4"}, {"1","2","3","4"}, {"1","2","3","4"} };
        int arrayLength = 4;

        try {
            System.out.println("Сумма элементов массива: " + arraySum(arr,arrayLength));
        } catch (MyArraySizeException | MyArrayDataException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
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

    // метод суммирования элементов массива с преобразованием строк в число
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

}