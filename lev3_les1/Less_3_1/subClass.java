package Less_3_1;

import java.util.ArrayList;
import java.util.Arrays;

public class subClass extends mainClass{

    void mas(Object[] arr, int x, int y) {
        Object buf;
        System.out.print(Arrays.toString(arr));
                    buf = arr[x];
                    arr[x] = arr[y];
                    arr[y] = buf;
        System.out.print(Arrays.toString(arr));
        }

       <T> void asList(T[] arr ){
           ArrayList<T> alt = new ArrayList<>(Arrays.asList(arr));
        }
    }