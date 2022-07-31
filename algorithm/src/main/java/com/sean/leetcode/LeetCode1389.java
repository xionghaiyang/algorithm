package com.sean.leetcode;

import java.util.ArrayList;

/**
 * 按既定顺序创建目标数组
 * https://leetcode-cn.com/problems/create-target-array-in-the-given-order/
 */
public class LeetCode1389 {

    public static int[] createTargetArray(int[] nums, int[] index) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < index.length; i++) {
            list.add(index[i], nums[i]);
        }
        int[] target = new int[index.length];
        for (int i = 0; i < target.length; i++) {
            target[i] = list.get(i);
        }
        return target;
    }

    private static void printArr(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                System.out.print(arr[i] + ",");
            } else {
                System.out.print(arr[i]);
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        printArr(createTargetArray(new int[]{0, 1, 2, 3, 4},new int[]{0, 1, 2, 2, 1}));
        printArr(createTargetArray(new int[]{1, 2, 3, 4, 0},new int[]{0, 1, 2, 3, 0}));
        printArr(createTargetArray(new int[]{1},new int[]{0}));
    }

}
