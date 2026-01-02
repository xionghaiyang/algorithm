package com.sean.leetcode.LeetCode961;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2026-01-02 09:10
 * @Description https://leetcode.cn/problems/n-repeated-element-in-size-2n-array
 * 961. 在长度 2N 的数组中找出重复 N 次的元素
 * 给你一个整数数组 nums ，该数组具有以下属性：
 * nums.length == 2 * n.
 * nums 包含 n + 1 个 不同的 元素
 * nums 中恰有一个元素重复 n 次
 * 找出并返回重复了 n 次的那个元素。
 * 2 <= n <= 5000
 * nums.length == 2 * n
 * 0 <= nums[i] <= 10^4
 * nums 由 n + 1 个 不同的 元素组成，且其中一个元素恰好重复 n 次
 */
public class Solution {

    public int repeatedNTimes(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return num;
            }
        }
        return -1;
    }

    public int repeatedNTimes1(int[] nums) {
        int res = 0, hp = 0, n = nums.length;
        for (int i = 1; i < n; i++) {
            int x = nums[i];
            if (x == nums[0]) {
                return x;
            }
            if (hp == 0) {
                res = x;
                hp = 1;
            } else {
                hp += x == res ? 1 : -1;
            }
        }
        return res;
    }

}
