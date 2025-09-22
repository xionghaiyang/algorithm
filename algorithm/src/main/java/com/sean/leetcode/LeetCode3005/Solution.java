package com.sean.leetcode.LeetCode3005;

/**
 * @Author xionghaiyang
 * @Date 2025-09-22 19:22
 * @Description https://leetcode.cn/problems/count-elements-with-maximum-frequency
 * 3005. 最大频率元素计数
 * 给你一个由 正整数 组成的数组 nums 。
 * 返回数组 nums 中所有具有 最大 频率的元素的 总频率 。
 * 元素的 频率 是指该元素在数组中出现的次数。
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class Solution {

    public int maxFrequencyElements(int[] nums) {
        int[] cnt = new int[101];
        int maxFrequency = 0;
        int res = 0;
        for (int num : nums) {
            cnt[num]++;
            if (cnt[num] > maxFrequency) {
                res = cnt[num];
                maxFrequency = cnt[num];
            } else if (cnt[num] == maxFrequency) {
                res += maxFrequency;
            }
        }
        return res;
    }

}
