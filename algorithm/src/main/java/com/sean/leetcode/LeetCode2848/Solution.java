package com.sean.leetcode.LeetCode2848;

import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2024-09-16 11:40
 * @Description https://leetcode.cn/problems/points-that-intersect-with-cars/
 * 2848. 与车相交的点
 * 给你一个下标从 0 开始的二维整数数组 nums 表示汽车停放在数轴上的坐标。
 * 对于任意下标 i，nums[i] = [starti, endi] ，其中 starti 是第 i 辆车的起点，endi 是第 i 辆车的终点。
 * 返回数轴上被车 任意部分 覆盖的整数点的数目。
 */
public class Solution {

    public int numberOfPoints(List<List<Integer>> nums) {
        int maxEnd = 0;
        for (List<Integer> interval : nums) {
            maxEnd = Math.max(maxEnd, interval.get(1));
        }
        int[] count = new int[maxEnd + 1];
        for (List<Integer> interval : nums) {
            for (int i = interval.get(0); i <= interval.get(1); i++) {
                count[i]++;
            }
        }
        int res = 0;
        for (int i = 0; i <= maxEnd; i++) {
            if (count[i] > 0) {
                res++;
            }
        }
        return res;
    }

    public int numberOfPoints1(List<List<Integer>> nums) {
        int maxEnd = 0;
        for (List<Integer> interval : nums) {
            maxEnd = Math.max(maxEnd, interval.get(1));
        }
        int[] diff = new int[maxEnd + 2];
        for (List<Integer> interval : nums) {
            diff[interval.get(0)]++;
            diff[interval.get(1) + 1]--;
        }
        int res = 0, count = 0;
        for (int i = 1; i <= maxEnd; i++) {
            count += diff[i];
            if (count > 0) {
                res++;
            }
        }
        return res;
    }

}
