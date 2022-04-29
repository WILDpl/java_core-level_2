package ru.gb.java_core.level_2.Homework_3;

import java.util.*;

public class Homework_3 {

    public static void main(String[] args) {

        /* п.1
        Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
        Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
        Посчитать сколько раз встречается каждое слово.
        */
        String[] arrTest = {"Апрель", "Март", "Февраль", "Май", "Июнь", "Январь", "Февраль", "Март", "Октябрь",
                "Апрель", "Июнь", "Январь", "Февраль", "Ноябрь", "Декабрь", "Июль", "Август", "Сентябрь", "Март", "Июнь"};
        int initCapacity = arrTest.length;

        analysisArrayList(arrTest, initCapacity);

        System.out.println();

        /* п.2
        Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
        В этот телефонный справочник с помощью метода add() можно добавлять записи.
        С помощью метода get() искать номер телефона по фамилии.
        Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
        тогда при запросе такой фамилии должны выводиться все телефоны.
        * */
        Phonebook book = new Phonebook();
        book.addNewRow("Иванова И.В.","7(000)00-00-001");
        book.addNewRow("Петров А.С.","7(000)00-00-002");
        book.addNewRow("Иванов И.Х.","7(000)00-00-003");
        book.addNewRow("Иванов И.И.","7(000)00-00-004");
        book.addNewRow("Сидоров А.А.","7(000)00-00-005");
        book.addNewRow("Кузнецов В.С.","7(000)00-00-006");
        book.addNewRow("Пупкин И.И.","7(000)00-00-007");
        book.addNewRow("Уткин В.С.","7(000)00-00-008");
        book.getPhoneBook();
        book.findPhone("Иванов");

        System.out.println();

        Phonebook2 book2 = new Phonebook2();
        book2.addNewRow("7(000)00-00-001", "Иванова");
        book2.addNewRow("7(000)00-00-002", "Петров");
        book2.addNewRow("7(000)00-00-003","Иванов");
        book2.addNewRow("7(000)00-00-004", "Иванов");
        book2.addNewRow("7(000)00-00-005","Сидоров");
        book2.addNewRow("7(000)00-00-006","Кузнецов");
        book2.addNewRow("7(000)00-00-007","Пупкин");
        book2.addNewRow("7(000)00-00-008","Уткин");
        book2.getPhoneBook();
        book2.findPhone("Иванов");

    }

    // п.1
    private static void analysisArrayList(String[] arr, int initCapacity) {
        ArrayList<String> fullArray = new ArrayList<>(initCapacity);

        fullArray.addAll(Arrays.asList(arr));

        Set<String> distinctArray = new HashSet<>(fullArray);

        System.out.printf("Полная коллекция данных [%d]: %s\n", fullArray.size(), fullArray);
        System.out.printf("Коллекция уникальных данных [%d]: %s\n\n", distinctArray.size(), distinctArray);
        System.out.println("Количество повторений элементов в полной коллекции:");

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

    // п.2.1 contains
    public static class Phonebook {
        HashMap<String, String> phoneBook = new HashMap<>();

        public Phonebook() {
        }

        public void addNewRow(String name, String phone) {
            phoneBook.put(name, phone);
        }

        public void findPhone(String name) {
            System.out.printf("Поиск: %s\n", "%" + name + "%");
            for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
                if (entry.getKey().contains(name)) {
                    System.out.println(entry.getKey() + "  \t\t" + entry.getValue());
                }
            }
        }

        public void getPhoneBook() {
            StringBuilder book = new StringBuilder();
            book.append("Телефонный справочник:\n");
            for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
                // можно было вывести только номера телефонов, но решил вывести полную информацию
                book.append(entry.getKey()).append("  \t\t").append(entry.getValue()).append("\n");
            }
            System.out.println(book);
        }
    }

    // п.2.2 equals
    public static class Phonebook2 {
        HashMap<String, String> phoneBook = new HashMap<>();

        public Phonebook2() {
        }

        public void addNewRow(String name, String phone) {
            phoneBook.put(name, phone);
        }

        public void findPhone(String name) {
            System.out.printf("Поиск: %s\n", name);
            for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
                if (entry.getValue().equals(name)) {
                    System.out.println(entry.getKey() + "  \t\t" + entry.getValue());
                }
            }
        }

        public void getPhoneBook() {
            StringBuilder book = new StringBuilder();
            book.append("Телефонный справочник:\n");
            for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
                // можно было вывести только номера телефонов, но решил вывести полную информацию
                book.append(entry.getKey()).append("  \t\t").append(entry.getValue()).append("\n");
            }
            System.out.println(book);
        }
    }

}
