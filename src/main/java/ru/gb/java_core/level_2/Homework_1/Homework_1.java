package ru.gb.java_core.level_2.Homework_1;

public class Homework_1 {

    public static void main(String[] args) {

        Action[] actions = {
            (Action) new Human("Superman", 1000, 100, false),
            (Action) new Cat("Matroskin", 50, 10, false),
            (Action) new Robot("R2S2", 500, 0, false),
        };

        for (Action action : actions) {
            action.run(500);
            action.jump(10);
        }

        //
        System.out.println();
        //

        Run[] runs = {
            new Human("Superman", 1000, 100, false),
            new Cat("Matroskin", 50, 10, false),
            new Robot("R2D2", 500, 0, false),
        };

        Jump[] jumps = {
                new Human("Superman", 1000, 100, false),
                new Cat("Matroskin", 50, 10, false),
                new Robot("R2D2", 500, 0, false),
        };

        for (Run run : runs) {
            run.run(1000);
        }

        for (Jump jump : jumps) {
            jump.jump(10);
        }
    }

}
