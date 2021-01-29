package ru.geekbrains.lesson_geekbrains;

public class Methods_Tests {

    @Before
    public static void test1(){
        System.out.println("test1 Before");
    }
    @Test
    public static void test5(){
        System.out.println("test5");
    }
    @Test
    public static void test3(){
        System.out.println("test3");
    }
    @Test(priority = 6)
    public static void test4(){
        System.out.println("test4");
    }
    @Test(priority = 4)
    public static void test7(){
        System.out.println("test7");
    }
    @After
    public static void test8(){
        System.out.println("test8 After");
    }

}
