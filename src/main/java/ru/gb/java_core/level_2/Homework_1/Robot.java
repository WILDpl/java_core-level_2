package ru.gb.java_core.level_2.Homework_1;

public class Robot implements Run, Jump, Action {
    private static final String kind = "Робот";
    private String name;
    private int runningDistance;
    private int jumpHeight;
    private boolean statusRun;
    private boolean statusJump;

    public Robot(String name, int runningDistance, int jumpHeight, boolean status) {
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
         System.out.printf(kind + " %s не умеет прыгать.\n", name);
    }

    @Override
    public void run(int length) {
        if (length <= runningDistance) {
            System.out.printf(kind + " %s проехал дистанцию %d успешно.\n", name, length);
            statusRun = true;
        } else {
            System.out.printf(kind + " %s не смог проехать дистанцию %d (проехал только %d м).\n", name, length, runningDistance);
        }
    }
}

