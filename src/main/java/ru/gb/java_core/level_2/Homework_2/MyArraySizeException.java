package ru.gb.java_core.level_2.Homework_2;

public class MyArraySizeException extends IndexOutOfBoundsException {
    public MyArraySizeException() {
    }

    public MyArraySizeException(String s) {
        super(s);
    }
}
