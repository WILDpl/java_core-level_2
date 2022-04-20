package ru.gb.java_core.level_2.Homework_1;

public class Human implements Run, Jump, Action {
    private static final String kind = "Человек";
    private String name;
    private int runningDistance;
    private int jumpHeight;
    private boolean statusRun;
    private boolean statusJump;

    public Human(String name, int runningDistance, int jumpHeight, boolean status) {
        this.name = name;
        this.runningDistance = runningDistance;
        this.jumpHeight = jumpHeight;
        this.statusRun = status;
    }

    public String getName() {
        return name;
    }

    public int getRunningDistance() {
        return runningDistance;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public boolean isStatusRun() {
        return statusRun;
    }

    public boolean isStatusJump() {
        return statusJump;
    }

    @Override
    public void jump(int height) {
        if (height <= jumpHeight) {
            System.out.printf(kind + " %s перепрыгнул стену высотой %d успешно.\n", name, height);
            statusJump = true;
        } else {
            System.out.printf(kind + " %s не смог перепрыгнуть стену высотой %d (подпрыгнул только на %d м).\n", name, height, jumpHeight);
        }
    }

    @Override
    public void run(int length) {
        if (length <= runningDistance) {
            System.out.printf(kind + " %s пробежал дистанцию %d успешно.\n", name, length);
            statusRun = true;
        } else {
            System.out.printf(kind + " %s не смог пробежать дистанцию %d (пробежал только %d м).\n", name, length, runningDistance);
        }
    }
}

