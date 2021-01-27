import org.junit.Assert;
import org.junit.Test;

public class Tests {
    @Test
    public void test1arrayAfter4(){
        int[] in = new  int[]{1,3,4,5,6,7};
        int[] out = new  int[]{5,6,8};
        Assert.assertArrayEquals(out, MyClass.arrayAfter4(in));
    }
    @Test(expected = RuntimeException.class)
    public void test2arrayAfter4(){
        int[] in = new int[]{1,3,5,6,6};
        MyClass.arrayAfter4(in);
    }
    @Test
    public void test1ContainsOnly1and4(){
        int[] in = new int[]{1,3,4,5};
        MyClass.assertFalse(MyClass.arrayContainsOnly1and4(in));
    }
    @Test
    public void test2ContainsOnly1and4(){
        int[] in = new int[]{1,4,1};
        MyClass.assertTrue(MyClass.arrayContainsOnly1and4(in));
    }
    @Test
    public void testAdd(){
        Assert.assertEquals(5,MyClass.add(2,3));
    }

}
