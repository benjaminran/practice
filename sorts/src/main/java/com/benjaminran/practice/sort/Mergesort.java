package com.benjaminran.practice.sort;

import java.util.*;

public class Mergesort {
    
    public static int[] sort(int[] arr) {
        return mergesort(arr, 0, arr.length);
    }

    /**
     * @param arr
     * @param l inclusive left bound index
     * @param r exclusive right bound index
     */
    private static int[] mergesort(int[] arr, int l, int r) {
        if(r - l <= 1) return arr;
        // sort halves
        int split = (l + r) / 2;
        mergesort(arr, l, split);
        mergesort(arr, split, r);
        // copy active part of array
        int[] tmp = new int[r-l];
        System.arraycopy(arr, l, tmp, 0, tmp.length);
        // merge
        int t_l = 0;
        int t_r = split - l;
        int a_i = l;
        while(t_l < split - l && t_r < tmp.length) {
            if(tmp[t_l] <= tmp[t_r]) {
                arr[a_i] = tmp[t_l++];
            }
            else {
                arr[a_i] = tmp[t_r++];
            }
            a_i++;
        }
        if(t_l < split - l) {
            while(t_l < split - l) {
                arr[a_i++] = tmp[t_l++];
            }
        }
        else {
            while(t_r < r - l) {
                arr[a_i++] = tmp[t_r++];
            }
        }
        return arr;
    }
}