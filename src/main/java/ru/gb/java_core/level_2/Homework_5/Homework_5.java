package ru.gb.java_core.level_2.Homework_5;

import java.util.*;

/*
1. Необходимо написать два метода, которые делают следующее:
1) Создают одномерный длинный массив, например:

static final int size = 10000000;
static final int h = size / 2;
float[] arr = new float[size];

2) Заполняют этот массив единицами;
3) Засекают время выполнения: long a = System.currentTimeMillis();
4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
5) Проверяется время окончания метода System.currentTimeMillis();
6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);

Отличие первого метода от второго:
Первый просто бежит по массиву и вычисляет значения.
Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.

Пример деления одного массива на два:

System.arraycopy(arr, 0, a1, 0, h);
System.arraycopy(arr, h, a2, 0, h);

Пример обратной склейки:

System.arraycopy(a1, 0, arr, 0, h);
System.arraycopy(a2, 0, arr, h, h);

Примечание:
System.arraycopy() – копирует данные из одного массива в другой:
System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение,
откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
По замерам времени:
Для первого метода надо считать время только на цикл расчета:

for (int i = 0; i < size; i++) {
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
}

Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
 */

/*
* План:
* +1. создать массив и заполнить его данными
* +2. рассчитать массив в один поток
* +3. проверить массив на четность и кратность деления на заданное количество частей
* +4. создать метод для разбиения массива на заданное количество частей
* +5. создать метод для создания многопоточности по заданному количеству частей массива
* +6. создать метод для расчета частей разбитого массива
* +7. создать метод для склеивания массива из заданного количества частей
* */

public class Homework_5 extends Thread {

    public static void main(String[] args) throws InterruptedException {
        int size = 10_000_000;  // размер массива
        float value = 1f;   // значение для заполнения массива
        int part = 4;   // количество частей массива

        float[] initFloatArray = createAndFillFloatArray(size, value); // сгенерированный и заполненный массив

        arrayOneThread(initFloatArray); // рассчет массива в один поток

        arrayMultipleThread(size, part, initFloatArray);    // расчет массива в несколько потоков

    }

    // создаем массив заданного размера и заполняем его заданным значением

    public static float[] createAndFillFloatArray(int size, float value) {
        float[] arr = new float[size];
        Arrays.fill(arr, value);
        return arr;
    }
    // проверка массива на кратность делителю

    public static boolean checkArrayForSplit(int size, int part) {
        return size % part == 0;
    }
    // рассчитываем сгенерированный массив в один поток

    public static void arrayOneThread(float[] arr) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("One thread time: " + (System.currentTimeMillis() - startTime) + " ms.\n");
    }
    // разбиваем массив на заданное количество частей с помощью двухмерного массива

    public static float[][] splitArray(float[] arr, int size, int part) {
        int onePart = size / part;  // количество частей
        int onePartSize = 0;    // размер одной части
        float[][] arrays = new float[part][onePart];    // создаем необходимый разбитый массив
        if (checkArrayForSplit(size, part)) {
            for (int i = 0; i < arrays.length; i++) {
                System.arraycopy(arr, onePartSize, arrays[i], 0, onePart);  // заполняем разбитый массив данными
                onePartSize+=onePart;   // берем следующую часть исходного массива
            }
        } else {
            System.out.println("Multiple thread time: The array is not evenly divisible by the specified number of parts.");
            arrays = new float[0][0];
        }
        return arrays;
    }
    // собираем массив из разбитого двухмерного массива

    public static float[] mergeArray(float[][] arr, int size) {
        float[] array = new float[size];    // пустой массив для сборки данных
        int onePartSize = 0;    // размер одной части
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, array, onePartSize, arr[i].length);  // заполняем общий массив данными
            onePartSize+=arr[i].length;   // берем следующую часть разбитого массива
//            System.out.println(onePartSize);
        }
        return array;
    }
    // метод создание нескольких потоков под рассчет каждого разделенного масиива

    public static void arrayManyThread(float[][] arr) throws InterruptedException {
        int onePart = arr.length;   // количество частей массива
        Thread[] threads = new Thread[onePart];
        for (int i = 0; i < onePart; i++) {
            int part = i;
            threads[i] = new Thread(() -> arrayManyCalc(arr, part));
            threads[i].start();
        }

        long startTimeI = System.currentTimeMillis();
        for (int i = 0; i < onePart; i++) {
            threads[i].join();
            System.out.println("--Multiple (" + i + ") thread Time: " + (System.currentTimeMillis() - startTimeI) + " ms.");
        }

    }
    // пересчитываем элементы разделенного массива в заданной части

    public static void arrayManyCalc(float[][] arr, int part) {
        for (int i = 0; i < arr[part].length; i++) {
            arr[part][i] = (float) (arr[part][i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    // рассчитываем сгенерированный массив в несколько потоков
    private static void arrayMultipleThread(int size, int part, float[] initFloatArray) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        long startTime1 = System.currentTimeMillis();
//        splitArray(initFloatArray, size, part); // разбиваем исходный массив на указанное количество частей
        float[][] splittedArray = splitArray(initFloatArray, size, part);    // разбитый на части массив

        if (splittedArray.length != 0) {
            System.out.println("Intermediate timeouts of multiple (" + part + ") threads:");
            System.out.println("-splittedArray: " + (System.currentTimeMillis() - startTime1) + " ms.");

            long startTime2 = System.currentTimeMillis();
            arrayManyThread(splittedArray); // прогоняем массивы в разных потоках
            System.out.println("-arrayManyThread: " + (System.currentTimeMillis() - startTime2) + " ms.");

            long startTime3 = System.currentTimeMillis();
//        mergeArray(splittedArray, size);    // склеиваем массив из разбитого массива
            float[] mergedFloatArray = mergeArray(splittedArray, size); // склеенный массив
            System.out.println("-mergedFloatArray: " + (System.currentTimeMillis() - startTime3) + " ms.\n");

            System.out.println("Multiple (" + part + ") thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
//            System.out.println(Arrays.toString(mergedFloatArray));
        }
    }

}




