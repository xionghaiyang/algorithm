package com.sean.leetcode.LeetCode2092;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-26 15:50
 * @Description: https://leetcode.cn/problems/find-all-people-with-secret
 * 2092. 找出知晓秘密的所有专家
 * 给你一个整数 n ，表示有 n 个专家从 0 到 n - 1 编号。
 * 另外给你一个下标从 0 开始的二维整数数组 meetings ，
 * 其中 meetings[i] = [xi, yi, timei] 表示专家 xi 和专家 yi 在时间 timei 要开一场会。
 * 一个专家可以同时参加 多场会议 。
 * 最后，给你一个整数 firstPerson 。
 * 专家 0 有一个 秘密 ，最初，他在时间 0 将这个秘密分享给了专家 firstPerson 。
 * 接着，这个秘密会在每次有知晓这个秘密的专家参加会议时进行传播。
 * 更正式的表达是，每次会议，如果专家 xi 在时间 timei 时知晓这个秘密，那么他将会与专家 yi 分享这个秘密，反之亦然。
 * 秘密共享是 瞬时发生 的。
 * 也就是说，在同一时间，一个专家不光可以接收到秘密，还能在其他会议上与其他专家分享。
 * 在所有会议都结束之后，返回所有知晓这个秘密的专家列表。
 * 你可以按 任何顺序 返回答案。
 * 2 <= n <= 10^5
 * 1 <= meetings.length <= 10^5
 * meetings[i].length == 3
 * 0 <= xi, yi <= n - 1
 * xi != yi
 * 1 <= timei <= 10^5
 * 1 <= firstPerson <= n - 1
 */
public class Solution {

    public class UnionFind {
        private int[] parent;
        private int[] help;

        public UnionFind(int n) {
            parent = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            int i = 0;
            while (x != parent[x]) {
                help[i++] = x;
                x = parent[x];
            }
            for (i--; i >= 0; i--) {
                parent[help[i]] = x;
            }
            return x;
        }

        public boolean isSameSet(int x, int y) {
            return find(x) == find(y);
        }

        public void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            parent[fy] = fx;
        }

        public void reset(int x) {
            parent[x] = x;
        }
    }

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
        UnionFind unionFind = new UnionFind(n);
        unionFind.union(firstPerson, 0);
        int m = meetings.length;
        for (int i = 0; i < m; ) {
            int start = i, time = meetings[i][2];
            for (; i < m && meetings[i][2] == time; i++) {
                unionFind.union(meetings[i][0], meetings[i][1]);
            }
            for (int j = start; j < i; j++) {
                int x = meetings[j][0], y = meetings[j][1];
                if (!unionFind.isSameSet(x, 0)) {
                    unionFind.reset(x);
                }
                if (!unionFind.isSameSet(y, 0)) {
                    unionFind.reset(y);
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (unionFind.isSameSet(i, 0)) {
                res.add(i);
            }
        }
        return res;
    }

}
