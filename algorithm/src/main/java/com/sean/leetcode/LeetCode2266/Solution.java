package com.sean.leetcode.LeetCode2266;

/**
 * @Author xionghaiyang
 * @Date 2025-01-19 14:03
 * @Description https://leetcode.cn/problems/count-number-of-texts/
 * 2266. 统计打字方案数
 * Alice 在给 Bob 用手机打字。
 * 数字到字母的 对应 如下图所示。
 * 为了 打出 一个字母，Alice 需要 按 对应字母 i 次，i 是该字母在这个按键上所处的位置。
 * 比方说，为了按出字母 's' ，Alice 需要按 '7' 四次。
 * 类似的， Alice 需要按 '5' 两次得到字母  'k' 。
 * 注意，数字 '0' 和 '1' 不映射到任何字母，所以 Alice 不 使用它们。
 * 但是，由于传输的错误，Bob 没有收到 Alice 打字的字母信息，反而收到了 按键的字符串信息 。
 * 比方说，Alice 发出的信息为 "bob" ，Bob 将收到字符串 "2266622" 。
 * 给你一个字符串 pressedKeys ，表示 Bob 收到的字符串，请你返回 Alice 总共可能发出多少种文字信息 。
 * 由于答案可能很大，将它对 10^9 + 7 取余 后返回。
 * 1 <= pressedKeys.length <= 10^5
 * pressedKeys 只包含数字 '2' 到 '9' 。
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private static final int MAX = 100_001;
    private static final long[] f = new long[MAX];
    private static final long[] g = new long[MAX];
    private static boolean initialized = false;

    public Solution() {
        if (initialized) {
            return;
        }
        initialized = true;
        f[0] = g[0] = 1;
        f[1] = g[1] = 1;
        f[2] = g[2] = 2;
        f[3] = g[3] = 4;
        for (int i = 4; i < MAX; i++) {
            f[i] = (f[i - 1] + f[i - 2] + f[i - 3]) % MOD;
            g[i] = (g[i - 1] + g[i - 2] + g[i - 3] + g[i - 4]) % MOD;
        }
    }

    public int countTexts(String pressedKeys) {
        long res = 1;
        char[] str = pressedKeys.toCharArray();
        int n = str.length;
        for (int i = 0, cnt = 0; i < n; i++) {
            char c = str[i];
            cnt++;
            if (i == n - 1 || c != str[i + 1]) {
                res = res * (c != '7' && c != '9' ? f[cnt] : g[cnt]) % MOD;
                cnt = 0;
            }
        }
        return (int) res;
    }

}
