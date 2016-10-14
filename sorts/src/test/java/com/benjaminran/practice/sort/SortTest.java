import org.junit.Test;
import static org.junit.Assert.*;
import com.benjaminran.practice.sort.*;

public class SortTest {

    @Test public void testMergesort() {
            int[] arr = int[]{1,3,5,2,7,9,8,0};
            Mergesort.sort(arr);
            Assert.assertArrayEquals(arr, int[]{0,1,2,3,5,7,8,9});
    }

    private static boolean isSorted(int[] arr) {
        if(arr.length==0) return true;
        prev = arr[0];
        for(int i=1; i<arr.length; i++) {
            if(arr[i] < prev) {
                return false;
            }
        }
        return true;
    }
}
