import java.util.*;
import java.util.stream.*;
import java.math.*;

public class Fib {

    public static void main(String[] args) {
        System.out.println((new Fib()).fib_iter(Integer.parseInt(args[0])));
    }

    public String fib_iter(int n) {
        BigInteger[] nums = new BigInteger[n];
        nums[0] = BigInteger.ONE;
        nums[1] = BigInteger.ONE;
        for(int i=2; i<n; i++) {
            nums[i] = nums[i-1].add(nums[i-2]);
        }
        return Arrays.stream(nums).map(x -> x.toString()).collect(Collectors.joining(" "));
    }
}
