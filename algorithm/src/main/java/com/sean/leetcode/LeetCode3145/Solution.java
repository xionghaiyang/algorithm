package com.sean.leetcode.LeetCode3145;

/**
 * @Author xionghaiyang
 * @Date 2024-08-23 15:54
 * @Description https://leetcode.cn/problems/find-products-of-elements-of-big-array/
 * 3145. 大数组元素的乘积
 * 一个非负整数 x 的 强数组 指的是满足元素为 2 的幂且元素总和为 x 的最短有序数组。
 * 下表说明了如何确定 强数组 的示例。
 * 可以证明，x 对应的强数组是独一无二的。
 * 数字	二进制表示	强数组
 * 1	00001	[1]
 * 8	01000	[8]
 * 10	01010	[2, 8]
 * 13	01101	[1, 4, 8]
 * 23	10111	[1, 2, 4, 16]
 * 我们将每一个升序的正整数 i （即1，2，3等等）的 强数组 连接得到数组 big_nums ，
 * big_nums 开始部分为 [1, 2, 1, 2, 4, 1, 4, 2, 4, 1, 2, 4, 8, ...] 。
 * 给你一个二维整数数组 queries ，其中 queries[i] = [fromi, toi, modi] ，你需要计算 (big_nums[fromi] * big_nums[fromi + 1] * ... * big_nums[toi]) % modi 。
 * 请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
 * 1 <= queries.length <= 500
 * queries[i].length == 3
 * 0 <= queries[i][0] <= queries[i][1] <= 10^15
 * 1 <= queries[i][2] <= 10^5
 */
public class Solution {

    public int[] findProductsOfElements(long[][] queries) {
        int n = queries.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            long[] query = queries[i];
            long er = sumE(query[1] + 1);
            long el = sumE(query[0]);
            res[i] = pow(2, er - el, query[2]);
        }
        return res;
    }

    private long sumE(long k) {
        long res = 0;
        long n = 0;
        //之前填的1的个数
        long cnt1 = 0;
        //之前填的1的幂次之和
        long sumI = 0;
        for (long i = 63 - Long.numberOfLeadingZeros(k + 1); i >= 0; i--) {
            //新增的幂次个数
            long c = (cnt1 << i) + (i << i >> 1);
            if (c <= k) {
                k -= c;
                res += (sumI << i) + ((i * (i - 1) / 2) << i >> 1);
                sumI += i;
                cnt1++;
                //填1
                n |= 1L << i;
            }
        }
        //剩余的k个幂次，由n的低k个1补充
        while (k-- > 0) {
            res += Long.numberOfTrailingZeros(n);
            //去掉最低位的1（置为0）
            n &= n - 1;
        }
        return res;
    }

    private int pow(long x, long n, long mod) {
        //注意mod可能等于1
        long res = 1 % mod;
        for (; n > 0; n /= 2) {
            if (n % 2 == 1) {
                res = res * x % mod;
            }
            x = x * x % mod;
        }
        return (int) res;
    }

}
