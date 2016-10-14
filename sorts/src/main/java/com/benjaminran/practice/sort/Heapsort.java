package com.benjaminran.practice.sort;

import java.util.*;


public class Heapsort {
        
    public static <T extends Comparable<T>> T[] sort(T[] arr) {

    }

    private static class MinHeap<T extends Comparable<T>> {
        
        T[] tree;
        int tail;// first unpopulated index
        
        public MinHeap(int capacity) {
            tree = (T[]) new Comparable<T>[capacity];
            tail = 0;
        }

        public void insert(T elem) {
            tree[tail++] = elem;
        }

        public T pop() {
            T elem = tree[0];
            tree[0] = tree[tail-1];
            tree[tail-1] = null;
            tail--;
            sink(0);
            return elem;
        }

        private void sink(int index) {
            if(tree[index]==null) {
                int smallerChild = (tree[left(index)].compareTo(tree[right(index)]) <= 0) left(index) : right(index);
                if(tree[index].compareTo(tree[biggerChild]) > 0){
                    
                }
            }
        }

        private void swim(int index) {
            
        }
        
        private void left(int index) {
            return 2 * index + 1;
        }

        private void right(int index) {
            return 2 * index + 2;
        }
    }
}
