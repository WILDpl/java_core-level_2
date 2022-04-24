package ru.gb.java_core.level_2.Homework_1;

import javax.sound.midi.*;

public class Homework_1 {

    public static void main(String[] args) {

        // вариант через наследование интерфейсов
        Action[] actions = {
            (Action) new Human("Супермен", 1000, 10, true),
            (Action) new Cat("Матроскин", 50, 5, true),
            (Action) new Robot("R2S2", 500, 0, true),
        };

//        System.out.printf("(start)__%d__|%d|__%d__|%d|__%d__|%d|__%d__(finish)\n",50,1,350,3,500,5,100);

        for (Action action : actions) {
            action.run(50);
            action.jump(1);
            action.run(300);
            action.jump(3);
            action.run(500);
            action.jump(5);
            action.run(100);
            action.action();
        }

        //
        System.out.println();
        //

        // вариант через каждый интерфейс
        Run[] runs = {
            new Human("Супермен", 1000, 10, true),
            new Cat("Матроскин", 50, 5, true),
            new Robot("R2D2", 500, 0, true),
        };

        Jump[] jumps = {
                new Human("Супермен", 1000, 10, true),
                new Cat("Матроскин", 50, 5, true),
                new Robot("R2D2", 500, 0, true),
        };

        for (Run run : runs) {
            run.run(1000);
        }

        for (Jump jump : jumps) {
            jump.jump(5);
        }
    }

}
