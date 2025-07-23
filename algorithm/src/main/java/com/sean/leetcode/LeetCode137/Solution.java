package com.sean.leetcode.LeetCode137;

/**
 * @Author xionghaiyang
 * @Date 2025-07-23 18:36
 * @Description https://leetcode.cn/problems/single-number-ii
 * 137. 只出现一次的数字 II
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。
 * 请你找出并返回那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
 * 1 <= nums.length <= 3 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 */
public class Solution {

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int x : nums) {
                cnt += (x >> i) & 1;
            }
            res |= (cnt % 3) << i;
        }
        return res;
    }

    //(a,b) + x -> 新(a,b)
    //(0,0) + 0 -> (0,0)
    //(0,0) + 1 -> (0,1)
    //(0,1) + 0 -> (0,1)
    //(0,1) + 1 -> (1,0)
    //(1,0) + 0 -> (1,0)
    //(1,0) + 1 -> (0,0)
    // 新a = a'bx + ab'x'
    // 新b = a'b'x + a'bx'
    public int singleNumber1(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            int a1 = (~a & b & num) | (a & ~b & ~num);
            int b1 = (~a & ~b & num) | (~a & b & ~num);
            a = a1;
            b = b1;
        }
        return b;
    }

}
