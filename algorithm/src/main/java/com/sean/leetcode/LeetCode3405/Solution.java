package com.sean.leetcode.LeetCode3405;

/**
 * @Author xionghaiyang
 * @Date 2025-06-17 05:55
 * @Description https://leetcode.cn/problems/count-the-number-of-arrays-with-k-matching-adjacent-elements
 * 3405. 统计恰好有 K 个相等相邻元素的数组数目
 * 给你三个整数 n ，m ，k 。长度为 n 的 好数组 arr 定义如下：
 * arr 中每个元素都在 闭 区间 [1, m] 中。
 * 恰好 有 k 个下标 i （其中 1 <= i < n）满足 arr[i - 1] == arr[i] 。
 * 请你返回可以构造出的 好数组 数目。
 * 由于答案可能会很大，请你将它对 10^9 + 7 取余 后返回。
 * 1 <= n <= 10^5
 * 1 <= m <= 10^5
 * 0 <= k <= n - 1
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private static final int MAX = 100_000;
    private boolean flag = false;
    //fac[i] = i!
    private long[] fac = new long[MAX];
    //invF[i] = (fac[i]^(MOD-2))(mod MOD)
    private long[] invF = new long[MAX];

    private void init() {
        if (flag) {
            return;
        }
        flag = true;

        fac[0] = 1;
        for (int i = 1; i < MAX; i++) {
            fac[i] = fac[i - 1] * i % MOD;
        }

        invF[MAX - 1] = pow(fac[MAX - 1], MOD - 2);
        for (int i = MAX - 1; i > 0; i--) {
            invF[i - 1] = invF[i] * i % MOD;
        }
    }

    //x^n
    private long pow(long x, int n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }

    //C(n,m) = n!/[m!(n-m)!]
    // (a/b)(mod p) = (a * b^(p-2))(mod p)
    private long C(int n, int m) {
        return fac[n] * invF[m] % MOD * invF[n - m] % MOD;
    }

    //长度为n的数组共有(n-1)对相邻元素，其中需要k对相邻元素相同，(n-k-1)对相邻元素不同(隔板)
    //这样其实整个数组就有(n-k)段子数组，每段子数组的元素都相同，C(n-1,n-k-1)=C(n-1,k)
    //第一段子数组有m种
    //第二段以及后面所有段需要和前一段取值不同有(m-1)种，共(n-k-1)段，所以共(m-1)^(n-k-1)种
    //f(n,m,k) = C(n-1,k) * m * (m-1)^(n-k-1)
    public int countGoodArrays(int n, int m, int k) {
        init();
        return (int) (C(n - 1, k) * m % MOD * pow(m - 1, n - k - 1) % MOD);
    }

}
