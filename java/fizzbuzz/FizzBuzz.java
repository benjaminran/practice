package com.benjaminran.practice;

public class FizzBuzz {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        System.out.println((new FizzBuzz()).fb(n));
    }

    public String fb(int n) {
        String result = "";
        for(int i=1; i<=n; i++) {
            String next;
            if(i % 15 == 0) next = "fizzbuzz";
            else if (i % 5 == 0) next = "fizz";
            else if (i % 3 == 0) next = "buzz";
            else next = Integer.toString(i);
            result += next + " ";
        }
        return result;
    }
    
}
