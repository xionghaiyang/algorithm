package com.sean.leetcode.LeetCode2951;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2024-05-28 09:12
 * @Description https://leetcode.cn/problems/find-the-peaks/
 * 2951. 找出峰值
 * 给你一个下标从 0 开始的数组 mountain 。
 * 你的任务是找出数组 mountain 中的所有 峰值。
 * 以数组形式返回给定数组中 峰值 的下标，顺序不限 。
 * 注意：
 * 峰值 是指一个严格大于其相邻元素的元素。
 * 数组的第一个和最后一个元素 不 是峰值。
 */
public class Solution {

    public List<Integer> findPeaks(int[] mountain) {
        List<Integer> res = new ArrayList<>();
        int n = mountain.length;
        for (int i = 1; i < n - 1; i++) {
            if (mountain[i] > mountain[i - 1] && mountain[i] > mountain[i + 1]) {
                res.add(i++);
            }
        }
        return res;
    }

}
