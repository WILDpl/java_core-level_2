package ru.gb.java_core.level_2.Homework_1;

public class Cat implements Run, Jump, Action {
    private static final String kind = "Кот";
    private String name;
    private int runningDistance;
    private int jumpHeight;
    private boolean status;
    private String distance = "(start)> ";

    public Cat(String name, int runningDistance, int jumpHeight, boolean status) {
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
            if (height <= jumpHeight) {
                System.out.printf(kind + " %s перепрыгнул стену высотой %d м успешно.\n", name, height);
                status = true;
                distance += "|" + height + "|";
            } else {
                System.out.printf(kind + " %s не смог перепрыгнуть стену высотой %d м (подпрыгнул только на %d м).\n", name, height, jumpHeight);
                status = false;
            }
        }
    }

    @Override
    public void run(int length) {
        if (status) {
            if (length <= runningDistance) {
                System.out.printf(kind + " %s пробежал дистанцию %d м успешно.\n", name, length);
                runningDistance -= length;
                status = true;
                distance += "__" + length + "__";
            } else {
                System.out.printf(kind + " %s не смог пробежать дистанцию %d м (пробежал только %d м).\n", name, length, runningDistance);
                status = false;
            }
        }
    }

    @Override
    public void action() {
        System.out.println(getDistance() + " <(finish)");
    }
}

