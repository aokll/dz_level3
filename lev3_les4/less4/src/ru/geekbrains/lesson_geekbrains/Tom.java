package ru.geekbrains.lesson_geekbrains;

public class Tom {
    static Object mon = new Object();
    public int sd(int quit) {
        synchronized (mon) {
            while (true) {
                if (quit == 1) {
                    System.out.print("A");
                    try {
                        mon.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if (quit == 2) {
                    System.out.print("B");
                    try {
                        mon.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if (quit == 3) {
                    System.out.print("C");
                    try {
                        mon.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                };
            }
        }
    }

    }