package com.benjaminran.practice;

import java.util.*;

public class PermuteString {

    public static void main(String[] args) {
        for(String s: args) {
            System.out.format("%s: %s%n", s, String.join(" ", permuteRec(s)));
        }
    }

    public static Collection<String> permuteRec(String s) {
        HashSet<String> perms = new HashSet<>();
        if(s.length()<=1) {
            perms.add(s);
        }
        else {
            for(int i=0; i<s.length(); i++) {
                char c = s.charAt(i);
                String pretail = s.substring(1);
                String tail = "";
                if(i-1>0) tail += pretail.substring(0, i-1);
                tail += s.charAt(0);
                if(i+1<tail.length()) tail += tail.substring(i+1);
                for(String p: permuteRec(tail)) {
                    perms.add(c + p);
                }
            }
        }
        return perms;
    }
}
