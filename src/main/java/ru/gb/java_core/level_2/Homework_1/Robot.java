package ru.gb.java_core.level_2.Homework_1;

public class Robot implements Run, Jump, Action {
    private static final String kind = "Робот";
    private String name;
    private int runningDistance;
    private int jumpHeight;
    private boolean status;
    private String distance = "(start)> ";

    public Robot(String name, int runningDistance, int jumpHeight, boolean status) {
        this.name = name;
        this.runningDistance = runningDistance;
        this.jumpHeight = jumpHeight;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public String getDistance() {
        return distance;
    }

    @Override
    public void jump(int height) {
        if (status) {
            System.out.printf(kind + " %s не умеет прыгать.\n", name);
            status = false;
        }
    }

    @Override
    public void run(int length) {
        if (status) {
            if (length <= runningDistance) {
                System.out.printf(kind + " %s преодолел дистанцию %d м успешно.\n", name, length);
                runningDistance -= length;
                status = true;
                distance += "__" + length + "__";
            } else {
                System.out.printf(kind + " %s не смог преодолеть дистанцию %d м (проехал только %d м).\n", name, length, runningDistance);
                status = false;
            }
        }
    }

    @Override
    public void action() {
        System.out.println(getDistance() + " <(finish)");
    }
}

