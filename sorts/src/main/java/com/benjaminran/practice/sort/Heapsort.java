package com.benjaminran.practice.sort;

import java.util.*;


public class Heapsort {
        
    public static <T extends Comparable<T>> T[] sort(T[] arr) {
        MinHeap<T> heap = new MinHeap<T>(arr.length);
        for(T elem: arr) {
            heap.insert(elem);
        }
        for(int i=0; i<arr.length; i++) {
            arr[i] = heap.pop();
        }
        return arr;
    }

    private static class MinHeap<T extends Comparable<T>> {
        
        T[] tree;
        int tail;// first unpopulated index
        
        public MinHeap(int capacity) {
            tree = (T[]) new Comparable[capacity];
            tail = 0;
        }

        public void insert(T elem) {
            tree[tail] = elem;
            swim(tail++);
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
                int smallerChild = (tree[left(index)].compareTo(tree[right(index)]) <= 0) ? left(index) : right(index);
                if(tree[index].compareTo(tree[smallerChild]) > 0){
                    T elem = tree[smallerChild];
                    tree[smallerChild] = tree[index];
                    tree[index] = tree[smallerChild];
                    sink(smallerChild);
                }
            }
        }

        private void swim(int index) {
            if(index!=0) {
                if(tree[index].compareTo(tree[parent(index)]) < 0) {
                    T elem = tree[index];
                    tree[index] = tree[parent(index)];
                    tree[parent(index)] = elem;
                    swim(parent(index));
                }
            }
        }
        
        private int left(int index) {
            return 2 * index + 1;
        }

        private int right(int index) {
            return 2 * index + 2;
        }

        private int parent(int index) {
            return (index % 2 == 0) ? index / 2 : index / 2 - 1;
        }
    }
}
