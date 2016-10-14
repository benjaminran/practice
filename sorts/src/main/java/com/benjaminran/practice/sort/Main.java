package com.benjaminran.practice.sort;

import java.util.*;


class Main {

    public static void main(String[] args) {
        ArrayList<Integer> input = new ArrayList();
        Scanner s = new Scanner(System.in);
        while(s.hasNextInt()) {
            input.add(s.nextInt());
        }
        Integer[] arr = new Integer[input.size()];
        input.toArray(arr);
        System.out.print("Unsorted:");
        for(int i=0; i<arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println();

        Quicksort.sort(arr);

        System.out.print("Sorted:  ");
        for(int i=0; i<arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    
    }
}
