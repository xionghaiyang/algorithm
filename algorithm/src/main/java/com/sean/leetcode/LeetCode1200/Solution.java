package com.sean.leetcode.LeetCode1200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2026-01-26 12:12
 * @Description https://leetcode.cn/problems/minimum-absolute-difference
 * 1200. 最小绝对差
 * 给你个整数数组 arr，其中每个元素都 不相同。
 * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
 * 每对元素对 [a,b] 如下：
 * a , b 均为数组 arr 中的元素
 * a < b
 * b - a 等于 arr 中任意两个元素的最小绝对差
 * 2 <= arr.length <= 10^5
 * -10^6 <= arr[i] <= 10^6
 */
public class Solution {

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            int sub = arr[i] - arr[i - 1];
            if (sub < min) {
                min = sub;
                res.clear();
                List<Integer> pair = new ArrayList<>();
                pair.add(arr[i - 1]);
                pair.add(arr[i]);
                res.add(pair);
            } else if (sub == min) {
                List<Integer> pair = new ArrayList<>();
                pair.add(arr[i - 1]);
                pair.add(arr[i]);
                res.add(pair);
            }
        }
        return res;
    }

}
