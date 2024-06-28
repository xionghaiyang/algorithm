package com.sean.leetcode.LeetCode1814;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-01-17 09:37
 * @Description: https://leetcode.cn/problems/count-nice-pairs-in-an-array/
 * 1814. 统计一个数组中好对子的数目
 * 给你一个数组 nums ，数组中只包含非负整数。
 * 定义 rev(x) 的值为将整数 x 各个数字位反转得到的结果。
 * 比方说 rev(123) = 321 ， rev(120) = 21 。
 * 我们称满足下面条件的下标对 (i, j) 是 好的 ：
 * 0 <= i < j < nums.length
 * nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
 * 请你返回好下标对的数目。由于结果可能会很大，请将结果对 10^9 + 7 取余 后返回。
 */
public class Solution {

    public int countNicePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int mod = 1_000_000_007;
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i : nums) {
            int temp = i, j = 0;
            while (temp > 0) {
                j = j * 10 + temp % 10;
                temp /= 10;
            }
            res = (res + map.getOrDefault(i - j, 0)) % mod;
            map.put(i - j, map.getOrDefault(i - j, 0) + 1);
        }
        return res;
    }

}
