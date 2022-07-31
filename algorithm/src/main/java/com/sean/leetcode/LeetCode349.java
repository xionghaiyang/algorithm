package com.sean.leetcode;

import java.util.HashSet;

/**
 * 两个数组的交集
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 */
public class LeetCode349 {

    public static int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> hashSet = new HashSet<>();
        HashSet<Integer> resSet = new HashSet<>();
        for (int num : nums1) {
            hashSet.add(num);
        }
        for (int num : nums2) {
            if (hashSet.contains(num)) {
                resSet.add(num);
            }
        }
        int[] res = new int[resSet.size()];
        int i = 0;
        for (Integer num : resSet) {
            res[i] = num;
            i++;
        }
        return res;
    }

    private static void printArr(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        printArr(intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2}));
        printArr(intersection(new int[]{4,9,5}, new int[]{9,4,9,8,4}));
    }

}
