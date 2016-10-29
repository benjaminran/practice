package com.benjaminran.practice.datastructures;

import java.util.*;
import com.benjaminran.practice.datastructures.tree.balanced.*;

class Main {

    public static void main(String[] args) {
        Tree tree = new TwoThreeTree();
        Scanner s = new Scanner(System.in);
        while(s.hasNextInt()) {
            tree.add(s.nextInt());
        }
        System.out.println(tree);
    }
}
