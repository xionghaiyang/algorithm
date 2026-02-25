package com.sean.leetcode.LeetCode1356;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-02-25 13:42
 * @Description https://leetcode.cn/problems/sort-integers-by-the-number-of-1-bits
 * 1356. 根据数字二进制下 1 的数目排序
 * 给你一个整数数组 arr 。
 * 请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
 * 请你返回排序后的数组。
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 10^4
 */
public class Solution {

    public int[] sortByBits(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int x : arr) {
            list.add(x);
        }
        int[] bit = new int[10001];
        for (int i = 1; i < 10001; i++) {
            bit[i] = bit[i >> 1] + (i & 1);
        }
        list.sort((x, y) -> bit[x] != bit[y] ? bit[x] - bit[y] : x - y);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

}
