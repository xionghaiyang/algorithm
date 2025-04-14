package com.sean.leetcode.LeetCode1534;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-04-14 09:45
 * @Description https://leetcode.cn/problems/count-good-triplets
 * 1534. 统计好三元组
 * 给你一个整数数组 arr ，以及 a、b 、c 三个整数。
 * 请你统计其中好三元组的数量。
 * 如果三元组 (arr[i], arr[j], arr[k]) 满足下列全部条件，则认为它是一个 好三元组 。
 * 0 <= i < j < k < arr.length
 * |arr[i] - arr[j]| <= a
 * |arr[j] - arr[k]| <= b
 * |arr[i] - arr[k]| <= c
 * 其中 |x| 表示 x 的绝对值。
 * 返回 好三元组的数量 。
 * 3 <= arr.length <= 100
 * 0 <= arr[i] <= 1000
 * 0 <= a, b, c <= 1000
 */
public class Solution {

    //暴力解
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(arr[i] - arr[j]) <= a) {
                    for (int k = j + 1; k < n; k++) {
                        if (Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) {
                            res++;
                        }
                    }
                }
            }
        }
        return res;
    }

    public int countGoodTriplets1(int[] arr, int a, int b, int c) {
        Integer[] index = new Integer[arr.length];
        Arrays.setAll(index, i -> i);
        Arrays.sort(index, (i, j) -> arr[i] - arr[j]);
        int res = 0;
        for (int j : index) {
            List<Integer> left = new ArrayList<>();
            for (int i : index) {
                if (i < j && Math.abs(arr[i] - arr[j]) <= a) {
                    left.add(arr[i]);
                }
            }
            List<Integer> right = new ArrayList<>();
            for (int k : index) {
                if (k > j && Math.abs(arr[k] - arr[j]) <= b) {
                    right.add(arr[k]);
                }
            }
            int k1 = 0;
            int k2 = 0;
            for (int x : left) {
                while (k2 < right.size() && right.get(k2) <= x + c) {
                    k2++;
                }
                while (k1 < right.size() && right.get(k1) < x - c) {
                    k1++;
                }
                res += k2 - k1;
            }
        }
        return res;
    }

}
