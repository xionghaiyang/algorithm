package com.sean.leetcode.LeetCode4065;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-27 17:34
 * @Description: https://leetcode.cn/problems/rearrange-array-by-removing-distinct-values
 * 4065. 移除不同值重排数组
 * 给你一个整数数组 nums。
 * 初始时，你有一个 空 数组 ans。
 * 重复执行以下操作，直到 nums 变为 空 ：
 * 找出当前 nums 中 所有不同 的值。
 * 将当前 nums 中每个 不同 的值各移除一个，并按 升序 将这些值依次添加到 ans 中。
 * 返回数组 ans。
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 */
public class Solution {

    public int[] rearrangeArray(int[] nums) {
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }
        int[] cnt = new int[max + 1];
        for (int x : nums) {
            cnt[x]++;
        }
        int n = nums.length;
        int[] res = new int[n];
        int i = 0;
        while (i < n) {
            for (int j = 1; j <= max; j++) {
                if (cnt[j] > 0) {
                    res[i++] = j;
                    cnt[j]--;
                }
            }
        }
        return res;
    }

}
