package com.benjaminran.practice.sort;

import java.util.*;


public class Quicksort {

    public static <T extends Comparable<T>> T[] sort(T[] arr) {
        return quicksort(arr, 0, arr.length);
    }

    /**
     * @param arr
     * @param l inclusive left bound index
     * @param r inclusive right bound index
     */
    private static <T extends Comparable<T>> T[] quicksort(T[] arr, int l, int r) {
        if(r - l <= 1) return arr;
        // partition
        int i_pivot = l;
        T pivot = arr[i_pivot];
        int i_l = l;// leftmost unpartitioned index
        int i_r = r - 1;// rightmost unpartitioned index 
        while(i_r > i_l) {
            if(i_pivot == i_l) { // check rightmost
                if(arr[i_r].compareTo(pivot) < 0) {
                    arr[i_l++] = arr[i_r];
                    i_pivot = i_r;
                }
                else {
                    i_r--;
                }
            }
            else { // check leftmost
                if(arr[i_l].compareTo(pivot) > 0) {
                    arr[i_r--] = arr[i_l];
                    i_pivot = i_l;
                }
                else {
                    i_l++;
                }
            }
        }
        arr[i_pivot] = pivot;
        quicksort(arr, l, i_pivot);
        quicksort(arr, i_pivot + 1, r);
        return arr;
    }
}
