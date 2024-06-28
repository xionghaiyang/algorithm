package com.sean.leetcode.LeetCode1735;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-27 13:09
 * @Description: https://leetcode.cn/problems/count-ways-to-make-array-with-product/description/
 * 1735. 生成乘积数组的方案数
 * 给你一个二维整数数组 queries ，其中 queries[i] = [ni, ki] 。
 * 第 i 个查询 queries[i] 要求构造长度为 ni 、每个元素都是正整数的数组，且满足所有元素的乘积为 ki ，请你找出有多少种可行的方案。
 * 由于答案可能会很大，方案数需要对 10^9 + 7 取余 。
 * 请你返回一个整数数组 answer，满足 answer.length == queries.length ，其中 answer[i]是第 i 个查询的结果。
 */
public class Solution {

    int MOD = (int) 1e9 + 7;

    public int[] waysToFillArray(int[][] queries) {
        Map<Integer, Integer> map = new HashMap<>();
        //预处理10000以下的素数，使得prime[i]=k(k为i的任一素数因子，例如prime[12]=3或者prime[12]=2)
        int[] prime = new int[10001];
        for (int i = 2; i <= 10000; i++) {
            if (prime[i] > 0) {
                continue;
            } else {
                prime[i] = i;
                for (int k = 2; k * prime[i] <= 10000; k++) {
                    prime[k * prime[i]] = i;
                }
            }
        }
        int[] res = new int[queries.length];
        int index = 0;
        for (int[] query : queries) {
            int n = query[0];
            int k = query[1];
            map.clear();
            //通过prime数组快速获得k的所有素数因子
            while (k > 1) {
                map.put(prime[k], map.getOrDefault(prime[k], 0) + 1);
                k /= prime[k];
            }
            //tmp表示单词询问的结果
            int tmp = 1;
            //分别求每个素数因子的去重后的全排列数（即组合数）
            for (int key : map.keySet()) {
                int t = map.get(key);
                tmp = (int) (((long) tmp * C(n + t - 1, t)) % MOD);
            }
            res[index++] = tmp;
        }
        return res;
    }

    private int C(int n, int k) {
        if (k == 0) {
            return 1;
        }
        int res = 1;
        for (int i = n; i >= n - k + 1; i--) {
            res = (int) (((long) res * i) % MOD);
        }
        int t = 1;
        for (int i = 2; i <= k; i++) {
            t = (int) (((long) t * i) % MOD);
        }
        //快速幂求乘法逆元
        t = power(t, MOD - 2);
        return (int) (((long) res * t) % MOD);
    }

    //快速幂
    private int power(int n, int k) {
        if (k == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        int res = 1;
        while (k > 0) {
            if (k % 2 == 1) {
                res = (int) (((long) res * n) % MOD);
            }
            n = (int) (((long) n * n) % MOD);
            k >>= 1;
        }
        return res;
    }

}
