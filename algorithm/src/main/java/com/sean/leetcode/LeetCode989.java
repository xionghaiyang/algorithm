package com.sean.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 数组形式的整数加法
 * https://leetcode-cn.com/problems/add-to-array-form-of-integer/
 */
public class LeetCode989 {

    public static List<Integer> addToArrayForm(int[] num, int k) {
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = num.length - 1; i >= 0; i--) {
            k += num[i];
            res.addFirst(k % 10);
            k /= 10;
        }
        while (k != 0) {
            res.addFirst(k % 10);
            k /= 10;
        }
        return res;
    }

    private static void printList(List<Integer> list) {
        for (Integer integer : list) {
            System.out.print(integer);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printList(addToArrayForm(new int[]{1, 2, 0, 0}, 34));
        printList(addToArrayForm(new int[]{2, 7, 4}, 181));
        printList(addToArrayForm(new int[]{2, 1, 5}, 806));
        printList(addToArrayForm(new int[]{9,9,9,9,9,9,9,9,9,9}, 1));
    }

}
