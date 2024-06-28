package com.sean.leetcode.LeetCode645;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-08 13:59
 * @Description: https://leetcode.cn/problems/set-mismatch/
 * 645. 错误的集合
 * 集合 s 包含从 1 到 n 的整数。
 * 不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
 * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 */
public class Solution {

    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        int n = nums.length;
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            count[nums[i] - 1]++;
        }
        for (int i = 0; i < n; i++) {
            if (count[i] == 2) {
                res[0] = i + 1;
            } else if (count[i] == 0) {
                res[1] = i + 1;
            }
        }
        return res;
    }

    public int[] findErrorNums1(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }
        int lowbit = xor & (-xor);
        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if ((num & lowbit) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        for (int i = 1; i <= n; i++) {
            if ((i & lowbit) == 0) {
                num1 ^= i;
            } else {
                num2 ^= i;
            }
        }
        for (int num : nums) {
            if (num == num1) {
                return new int[]{num1, num2};
            }
        }
        return new int[]{num2, num1};
    }

}
