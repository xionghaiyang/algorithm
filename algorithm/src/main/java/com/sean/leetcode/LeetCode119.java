package com.sean.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角 II
 * https://leetcode-cn.com/problems/pascals-triangle-ii/
 */
public class LeetCode119 {

    public static List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            ArrayList<Integer> cur = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    cur.add(1);
                } else {
                    cur.add(res.get(j - 1) + res.get(j));
                }
            }
            res = cur;
        }
        return res;
    }

    private static void printList(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printList(getRow(3));
    }
}
