package com.benjaminran.practice;

public class Pal {

    public static void main(String[] args) {
        for (String s: args)
            System.out.format("%s: %b%n", s, isPal(s));
    }

    public static boolean isPal(String s) {
        if(s.length()==0 || s.length()==1) return true;
        if(s.charAt(0) != s.charAt(s.length()-1)) return false;
        return isPal(s.substring(1, s.length()-1));
    }
    
}
