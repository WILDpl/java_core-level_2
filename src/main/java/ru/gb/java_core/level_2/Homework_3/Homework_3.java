package ru.gb.java_core.level_2.Homework_3;

import java.util.*;

public class Homework_3 {

    public static void main(String[] args) {

        String[] arrTest = {"Апрель", "Март", "Февраль", "Май", "Июнь", "Январь", "Февраль", "Март", "Октябрь",
                "Апрель", "Июнь", "Январь", "Февраль", "Ноябрь", "Декабрь", "Июль", "Август", "Сентябрь", "Март", "Июнь"};
        int initCapacity = 20;

        analysisArrayList(arrTest, initCapacity);

    }

    private static void analysisArrayList(String[] arr, int initCapacity) {
        ArrayList<String> fullArray = new ArrayList<>(initCapacity);

        fullArray.addAll(Arrays.asList(arr));

        Set<String> distinctArray = new HashSet<>(fullArray);

        System.out.printf("Полная коллекция данных [%d]: %s\n", fullArray.size(), fullArray);
        System.out.printf("Коллекция уникальных данных [%d]: %s\n\n", distinctArray.size(), distinctArray);
        System.out.println("Количество повторяющихся элементов в полной коллекции:");

        for (String e : distinctArray) {
            int count = 0;
            for (String s : fullArray) {
                if (Objects.equals(e, s)) {
                    count++;
                }
            }
            System.out.println(e + ": " + count);
        }

    }

}
