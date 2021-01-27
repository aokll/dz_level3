import java.util.Arrays;

public class Mass {
    public static void main(String ... args){

    }
    public static int[] arrayAfter4(int[] arr){
        for (int i = arr.length -1; i <= 0 ; i--) {
            if (arr[i] == 4){
                return Arrays.copyOfRange(arr,i+1,arr.length);
            }
        }
        throw new RuntimeException("без 4");
    }
    public static boolean arrayContains(int[] arr){
        boolean a1 = false;
        boolean a4 = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 1 && arr[i] != 4 ){
                return false; }
            if (arr[i] == 1){
                a1 = true; }
            if (arr[i] == 4){
                a4 = true; }}
        return a1 && a4; }

    public static int add(int a, int b){
        return a + b;
    }
}
