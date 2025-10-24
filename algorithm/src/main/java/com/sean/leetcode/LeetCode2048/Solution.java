package com.sean.leetcode.LeetCode2048;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-09 20:40
 * @Description: https://leetcode.cn/problems/next-greater-numerically-balanced-number
 * 2048. 下一个更大的数值平衡数
 * 如果整数  x 满足：对于每个数位 d ，这个数位 恰好 在 x 中出现 d 次。那么整数 x 就是一个 数值平衡数 。
 * 给你一个整数 n ，请你返回 严格大于 n 的 最小数值平衡数 。
 * 0 <= n <= 10^6
 */
public class Solution {

    public int nextBeautifulNumber(int n) {
        out:
        while (true) {
            n++;
            int[] cnt = new int[10];
            for (int x = n; x > 0; x /= 10) {
                cnt[x % 10]++;
            }
            for (int x = n; x > 0; x /= 10) {
                if (cnt[x % 10] != x % 10) {
                    continue out;
                }
            }
            return n;
        }
    }

    public int nextBeautifulNumber1(int n) {
        //补一个前导零
        char[] s = ("0" + n).toCharArray();
        int m = s.length;
        final int MAX = 10;
        int[] cnt = new int[MAX];
        for (int i = 1; i < m; i++) {
            cnt[s[i] - '0']++;
        }
        //从右往左尝试
        for (int i = m - 1; i >= 0; i--) {
            if (i > 0) {
                //撤销
                cnt[s[i] - '0']--;
            }
            //增大s[i]为j
            for (int j = s[i] - '0' + 1; j < MAX; j++) {
                cnt[j]++;
                //后面[i+1,m-1]需要补满0 < cnt[k] < k 的数字k,剩余数位可以随便填
                //统计可以随便填的数位个数
                int free = m - 1 - i;
                for (int k = 0; k < MAX; k++) {
                    int c = cnt[k];
                    if (k < c) {//不合法
                        free = -1;
                        break;
                    }
                    if (c > 0) {
                        free -= k - c;
                    }
                }
                if (free < 0) {//不合法
                    cnt[j]--;
                    continue;
                }
                //对于可以随便填的数位，计算字典序最小的填法
                List<Integer> list = new ArrayList<>();
                for (int k = 1; k < MAX; k++) {
                    if (cnt[k] == 0) {
                        list.add(k);
                    }
                }
                List<Integer> missing = zeroOneKnapsack(list, free);
                if (missing == null) {//无解
                    cnt[j]--;
                    continue;
                }
                for (int v : missing) {
                    //用负数表示可以随便填的数
                    cnt[v] = -v;
                }
                StringBuilder res = new StringBuilder("0" + n);
                res.setCharAt(i, (char) ('0' + j));
                res.setLength(i + 1);
                for (int k = 1; k < MAX; k++) {
                    int c = cnt[k];
                    for (int l = 0; l < (c > 0 ? k - c : -c); l++) {
                        res.append((char) ('0' + k));
                    }
                }
                return Integer.parseInt(res.toString());
            }
        }
        return -1;
    }

    private List<Integer> zeroOneKnapsack(List<Integer> list, int target) {
        int n = list.size();
        boolean[][] f = new boolean[n + 1][target + 1];
        f[n][0] = true;
        //倒着DP,这样后面可以正着(从小到大)选
        for (int i = n - 1; i >= 0; i--) {
            int v = list.get(i);
            for (int j = 0; j <= target; j++) {
                if (j < v) {
                    f[i][j] = f[i + 1][j];
                } else {
                    f[i][j] = f[i + 1][j] || f[i + 1][j - v];
                }
            }
        }
        if (!f[0][target]) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0, j = target; i < n; i++) {
            int v = list.get(i);
            if (j >= v && f[i + 1][j - v]) {
                res.add(v);
                j -= v;
            }
        }
        return res;
    }

}
