package app.util;

public class Random {
    public static Integer getRandomNumber() {
        int max = 1000;
        int min = 1;
        return (int) ((Math.random() * (max - min)) + min);
    }
}
