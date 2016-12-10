import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{0,2,3,4,8,2};
        System.out.println(median(Arrays.asList(arr)));
    }
    
    public static void main2(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int d = input.nextInt();
        int[] exp = new int[n];
        for (int i=0; i<exp.length; i++) {
            exp[i] = input.nextInt();
        }
        // construct window := { exp[i] | windowStart <= i < windowEnd }
        int windowEnd = d;
        int windowStart = 0;
        List<Integer> window = new ArrayList<>(d);
        for(int i=0; i<d; i++) {
            window.add(i, exp[i]);
        }
        Collections.sort(window);
        // slide window
        int notifications = 0;
        while (windowEnd < exp.length) {
            System.out.println("Window: "+window);
            System.out.println("Median: "+median(window));
            if(exp[windowEnd] >= 2 * median(window)) {
                notifications++;
            }
            window.remove((Integer)exp[windowStart++]);
            window.add(exp[windowEnd++]);
        }
        System.out.println(notifications);
    }
    
    private static double median(List<Integer> window) {
        if(window.size()%2==0) {
            return ((double) window.get(window.size()/2-1) + window.get(window.size()/2)) / 2;
        }
        else {
            return window.get(window.size()/2);
        }
    }
}
