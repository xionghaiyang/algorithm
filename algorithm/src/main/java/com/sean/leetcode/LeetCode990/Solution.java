package com.sean.leetcode.LeetCode990;

/**
 * @Author xionghaiyang
 * @Date 2026-03-29 18:48
 * @Description https://leetcode.cn/problems/satisfiability-of-equality-equations
 * 990. 等式方程的可满足性
 * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。
 * 在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
 * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。
 * 1 <= equations.length <= 500
 * equations[i].length == 4
 * equations[i][0] 和 equations[i][3] 是小写字母
 * equations[i][1] 要么是 '='，要么是 '!'
 * equations[i][2] 是 '='
 */
public class Solution {

    public class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;

        public UnionFind() {
            parent = new int[26];
            size = new int[26];
            help = new int[26];
            for (int i = 0; i < 26; i++) {
                parent[i] = i;
                size[i] = 1;
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

        public void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx == fy) {
                return;
            }
            if (size[fx] >= size[fy]) {
                size[fx] += size[fy];
                parent[fy] = fx;
            } else {
                size[fy] += size[fx];
                parent[fx] = fy;
            }
        }

        public boolean isSameSet(int x, int y) {
            return find(x) == find(y);
        }
    }

    public boolean equationsPossible(String[] equations) {
        UnionFind unionFind = new UnionFind();
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                unionFind.union(equation.charAt(0) - 'a', equation.charAt(3) - 'a');
            }
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '!' && unionFind.isSameSet(equation.charAt(0) - 'a', equation.charAt(3) - 'a')) {
                return false;
            }
        }
        return true;
    }

}
