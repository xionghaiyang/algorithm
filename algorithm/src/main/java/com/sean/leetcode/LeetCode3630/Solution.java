package com.sean.leetcode.LeetCode3630;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-08-09 12:25
 * @Description https://leetcode.cn/problems/partition-array-for-maximum-xor-and-and
 * 3630. 划分数组得到最大异或运算和与运算之和
 * 给你一个整数数组 nums。
 * 将数组划分为 三 个（可以为空）子序列 A、B 和 C，使得 nums 中的每个元素 恰好 属于一个子序列。
 * 你的目标是 最大化 以下值：XOR(A) + AND(B) + XOR(C)
 * 其中：
 * XOR(arr) 表示 arr 中所有元素的按位异或结果。
 * 如果 arr 为空，结果定义为 0。
 * AND(arr) 表示 arr 中所有元素的按位与结果。
 * 如果 arr 为空，结果定义为 0。
 * 返回可实现的最 大 值。
 * 注意: 如果有多种划分方式得到相同的 最大 和，你可以按其中任何一种划分。
 * 子序列 是指一个数组通过删除一些或不删除任何元素，不改变剩余元素的顺序得到的元素序列。
 * 1 <= nums.length <= 19
 * 1 <= nums[i] <= 10^9
 */
public class Solution {

    public class XorBasis {
        private int[] arr;

        public XorBasis(int n) {
            arr = new int[n];
        }

        public void insert(int x) {
            while (x > 0) {
                //x的最高位
                int i = 31 - Integer.numberOfLeadingZeros(x);
                //x和之前的基是线性无关的
                if (arr[i] == 0) {
                    //新增一个基，最高位为i
                    arr[i] = x;
                    return;
                }
                //保证参与maxXor的基的最高位互不相同的
                x ^= arr[i];
            }
            //正常循环结束，此时x = 0,说明一开始的x可以被已有基表除，不是一个线性无关基
        }

        public int maxXor() {
            int res = 0;
            //从高到低贪心：越高的位，越必须是1
            //由于每个位的基至多一个，所以每个位只需考虑异或一个基，若能变大，则异或之
            for (int i = arr.length - 1; i >= 0; i--) {
                res = Math.max(res, res ^ arr[i]);
            }
            return res;
        }
    }

    public long maximizeXorAndXor(int[] nums) {
        int n = nums.length;
        int maxValue = Arrays.stream(nums).max().getAsInt();
        int size = 32 - Integer.numberOfLeadingZeros(maxValue);
        int u = 1 << n;
        int[] subAnd = new int[u];
        int[] subXor = new int[u];
        int[] subOr = new int[u];
        subAnd[0] = -1;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int highBit = 1 << i;
            for (int mask = 0; mask < highBit; mask++) {
                subAnd[highBit | mask] = subAnd[mask] & x;
                subXor[highBit | mask] = subXor[mask] ^ x;
                subOr[highBit | mask] = subOr[mask] | x;
            }
        }
        subAnd[0] = 0;
        long res = 0;
        for (int i = 0; i < u; i++) {
            int j = (u - 1) ^ i;
            if (subAnd[i] + subOr[j] * 2L - subXor[j] > res) {
                res = Math.max(res, subAnd[i] + maxXor(j, subXor[j], nums, size));
            }
        }
        return res;
    }

    private long maxXor(int sub, int xor, int[] nums, int size) {
        XorBasis xorBasis = new XorBasis(size);
        for (int i = 0; i < nums.length; i++) {
            if ((sub >> i & 1) > 0) {
                //只考虑有偶数个1的比特位(xor在这些比特位上是0)
                xorBasis.insert(nums[i] & ~xor);
            }
        }
        return xor + xorBasis.maxXor() * 2L;
    }

}
