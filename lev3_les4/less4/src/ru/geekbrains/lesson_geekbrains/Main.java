package ru.geekbrains.lesson_geekbrains;

//Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5
// раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.

public class Main {

    public static void main(String[] args) {

        Tom t = new Tom();

            Thread t1 = new Thread(() -> {
                t.sd(1);
            });
            Thread t2 = new Thread(() -> {
                t.sd(2);
            });
            Thread t3 = new Thread(() -> {
                t.sd(3);
            });
            t1.start();
            t2.start();
            t3.start();
        
    }

}
