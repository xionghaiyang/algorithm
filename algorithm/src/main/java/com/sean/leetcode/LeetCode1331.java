package com.sean.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2022/7/28 9:16
 */
public class LeetCode1331 {


    /**
     * https://leetcode.cn/problems/rank-transform-of-an-array/
     * 给你一个整数数组 arr ，请你将数组中的每个元素替换为它们排序后的序号。
     * 序号代表了一个元素有多大。序号编号的规则如下：
     * 序号从 1 开始编号。
     * 一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。
     * 每个数字的序号都应该尽可能地小。
     */

    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        int[] copy = new int[n];
        System.arraycopy(arr, 0, copy, 0, n);
        Arrays.sort(copy);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : copy) {
            if (!map.containsKey(num)) {
                map.put(num, map.size() + 1);
            }
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = map.get(arr[i]);
        }
        return res;
    }

}
