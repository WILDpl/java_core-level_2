package ru.gb.java_core.level_2.Homework_3;

import java.util.*;

public class Homework_3 {

    public static void main(String[] args) {

        String[] arrTest = {"Апрель", "Март", "Февраль", "Май", "Июнь", "Январь", "Февраль", "Март", "Октябрь",
                "Апрель", "Июнь", "Январь", "Февраль", "Ноябрь", "Декабрь", "Июль", "Август", "Сентябрь", "Март", "Июнь"};
        int initCapacity = 20;

//        analysisArrayList(arrTest, initCapacity);

        Phonebook book = new Phonebook();
        book.addNewRow("Иванов И.В.","7(000)00-00-001");
        book.addNewRow("Петров А.С.","7(000)00-00-002");
        book.addNewRow("Иванов И.Х.","7(000)00-00-003");
        book.addNewRow("Иванов И.И.","7(000)00-00-004");
        book.addNewRow("Сидоров А.А.","7(000)00-00-005");
        book.addNewRow("Кузнецов В.С.","7(000)00-00-006");
        book.addNewRow("Пупкин И.И.","7(000)00-00-007");
        book.addNewRow("Уткин В.С.","7(000)00-00-008");
        System.out.println(book.getPhoneBook());
        book.findPhone("Иванов");




    }

    // п.1
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

    // п.2
    public static class Phonebook {
        HashMap<String, String> phoneBook = new HashMap<>();

        public Phonebook() {
        }

        public void addNewRow(String name, String phone) {
            phoneBook.put(name, phone);
        }

        public void findPhone(String name) {
            for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
                if (entry.getKey().contains(name)) {
                    System.out.println(entry.getValue());
                }
            }
        }

        public HashMap<String, String> getPhoneBook() {
            return phoneBook;
        }
    }


}
