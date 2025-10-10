package com.sean.leetcode.LeetCode179;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-10-10 16:38
 * @Description https://leetcode.cn/problems/largest-number
 * 179. 最大数
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10^9
 */
public class Solution {

    public String largestNumber(int[] nums) {
        int n = nums.length;
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums[i];
        }
        Arrays.sort(numsArr, (x, y) -> {
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return Long.compare(y * sx + x, x * sy + y);
        });
        if (numsArr[0] == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        for (int num : numsArr) {
            res.append(num);
        }
        return res.toString();
    }

}
