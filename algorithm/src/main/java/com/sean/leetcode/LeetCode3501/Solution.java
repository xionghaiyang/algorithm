package com.sean.leetcode.LeetCode3501;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-22 06:41
 * @Description: https://leetcode.cn/problems/maximize-active-section-with-trade-ii
 * 3501. 操作后最大活跃区段数 II
 * 给你一个长度为 n 的二进制字符串 s ，其中：
 * '1' 表示一个 活跃 区段。
 * '0' 表示一个 非活跃 区段。
 * 你最多可以进行一次 操作 来最大化 s 中活跃区段的数量。
 * 在一次操作中，你可以：
 * 将一个被 '0' 包围的连续 '1' 区块转换为全 '0'。
 * 然后，将一个被 '1' 包围的连续 '0' 区块转换为全 '1'。
 * 此外，你还有一个 二维数组 queries，其中 queries[i] = [li, ri] 表示子字符串 s[li...ri]。
 * 对于每个查询，确定在对子字符串 s[li...ri] 进行最优交换后，字符串 s 中 可能的最大 活跃区段数。
 * 返回一个数组 answer，其中 answer[i] 是 queries[i] 的结果。
 * 注意
 * 对于每个查询，仅对 s[li...ri] 处理时，将其看作是在两端都加上一个 '1' 后的字符串，形成 t = '1' + s[li...ri] + '1'。
 * 这些额外的 '1' 不会对最终的活跃区段数有贡献。
 * 各个查询相互独立。
 * 1 <= n == s.length <= 10^5
 * 1 <= queries.length <= 10^5
 * s[i] 只有 '0' 或 '1'。
 * queries[i] = [li, ri]
 * 0 <= li <= ri < n
 */
public class Solution {

    public class Pair {
        private int left;
        private int right;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public class SparseTable {
        private final int[][] st;

        public SparseTable(List<Pair> list) {
            int n = list.size() - 1;
            int size = 32 - Integer.numberOfLeadingZeros(n);
            st = new int[n][size];
            for (int i = 0; i < n; i++) {
                st[i][0] = list.get(i).right - list.get(i).left + list.get(i + 1).right - list.get(i + 1).left;
            }
            for (int j = 1; j < size; j++) {
                for (int i = 0; i + (1 << j) <= n; i++) {
                    st[i][j] = Math.max(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
                }
            }
        }

        //查询区间最大值,[left,right)
        public int query(int left, int right) {
            if (left >= right) {
                return 0;
            }
            int k = 32 - Integer.numberOfLeadingZeros(right - left) - 1;
            return Math.max(st[left][k], st[right - (1 << k)][k]);
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        char[] str = s.toCharArray();
        int n = s.length(), total1 = 0;
        List<Pair> list = new ArrayList<>();
        list.add(new Pair(-1, -1));
        for (int i = 0, start = 0; i < n; i++) {
            if (i == n - 1 || str[i] != str[i + 1]) {
                if (str[i] == '1') {
                    total1 += i - start + 1;
                } else {
                    list.add(new Pair(start, i + 1));
                }
                start = i + 1;
            }
        }
        list.add(new Pair(n + 1, n + 1));
        SparseTable st = new SparseTable(list);
        List<Integer> res = new ArrayList<>(queries.length);
        for (int[] query : queries) {
            int ql = query[0], qr = query[1] + 1;
            int i = Collections.binarySearch(list, new Pair(ql, 0), (a, b) -> a.left - b.left);
            if (i < 0) {
                i = ~i;
            }
            int j = Collections.binarySearch(list, new Pair(0, qr + 1), (a, b) -> a.right - b.right);
            if (j < 0) {
                j = ~j;
            }
            j--;
            int max = 0;
            if (i <= j) {
                int full = st.query(i, j);
                int sl = merge(list.get(i - 1).right - ql, list.get(i).right - list.get(i).left);
                int sr = merge(qr - list.get(j + 1).left, list.get(j).right - list.get(j).left);
                max = Math.max(full, Math.max(sl, sr));
            } else if (i == j + 1) {
                max = merge(list.get(i - 1).right - ql, qr - list.get(j + 1).left);
            }
            res.add(total1 + max);
        }
        return res;
    }

    private int merge(int x, int y) {
        return x > 0 && y > 0 ? x + y : 0;
    }

}
