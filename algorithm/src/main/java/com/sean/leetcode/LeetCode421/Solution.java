package com.sean.leetcode.LeetCode421;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-09-26 17:49
 * @Description https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array
 * 421. 数组中两个数的最大异或值
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * 1 <= nums.length <= 2 * 10^5
 * 0 <= nums[i] <= 2^31 - 1
 */
public class Solution {

    public int findMaximumXOR(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        int highBit = 31 - Integer.numberOfLeadingZeros(max);
        int res = 0, mask = 0;
        Set<Integer> seen = new HashSet<>();
        for (int i = highBit; i >= 0; i--) {
            seen.clear();
            mask |= 1 << i;
            int newRes = res | (1 << i);
            for (int num : nums) {
                num &= mask;
                if (seen.contains(newRes ^ num)) {
                    res = newRes;
                    break;
                }
                seen.add(num);
            }
        }
        return res;
    }

    public class Trie {
        Trie left = null;
        Trie right = null;
    }

    private Trie root = new Trie();
    private static final int HIGH_BIT = 30;

    public int findMaximumXOR1(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 1; i < n; i++) {
            add(nums[i - 1]);
            res = Math.max(res, check(nums[i]));
        }
        return res;
    }

    public void add(int num) {
        Trie cur = root;
        for (int i = HIGH_BIT; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (bit == 0) {
                if (cur.left == null) {
                    cur.left = new Trie();
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = new Trie();
                }
                cur = cur.right;
            }
        }
    }

    public int check(int num) {
        Trie cur = root;
        int res = 0;
        for (int i = HIGH_BIT; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (bit == 0) {
                if (cur.right != null) {
                    cur = cur.right;
                    res = res * 2 + 1;
                } else {
                    cur = cur.left;
                    res *= 2;
                }
            } else {
                if (cur.left != null) {
                    cur = cur.left;
                    res = res * 2 + 1;
                } else {
                    cur = cur.right;
                    res *= 2;
                }
            }
        }
        return res;
    }

}
