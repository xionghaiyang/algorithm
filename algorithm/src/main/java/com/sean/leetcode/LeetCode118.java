package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode118 {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    list.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(list);
        }
        return res;
    }

    private static void printList(List<List<Integer>> list) {
        for (int i = 0; i < list.size(); i++) {
            List<Integer> listIntegers = list.get(i);
            listIntegers.forEach(num -> System.out.print(num + " "));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printList(generate(5));
    }

}
