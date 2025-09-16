package com.sean.leetcode.LeetCode1361;

/**
 * @Author xionghaiyang
 * @Date 2025-09-16 19:57
 * @Description https://leetcode.cn/problems/validate-binary-tree-nodes
 * 1361. 验证二叉树
 * 二叉树上有 n 个节点，按从 0 到 n - 1 编号，其中节点 i 的两个子节点分别是 leftChild[i] 和 rightChild[i]。
 * 只有 所有 节点能够形成且 只 形成 一颗 有效的二叉树时，返回 true；否则返回 false。
 * 如果节点 i 没有左子节点，那么 leftChild[i] 就等于 -1。
 * 右子节点也符合该规则。
 * 注意：节点没有值，本问题中仅仅使用节点编号。
 * n == leftChild.length == rightChild.length
 * 1 <= n <= 10^4
 * -1 <= leftChild[i], rightChild[i] <= n - 1
 */
public class Solution {

    public class UnionFind {
        private int[] parent;
        private int[] help;
        private int set;

        public UnionFind(int n) {
            parent = new int[n];
            help = new int[n];
            set = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            int index = 0;
            while (x != parent[x]) {
                help[index++] = x;
                x = parent[x];
            }
            for (index--; index >= 0; index--) {
                parent[help[index]] = x;
            }
            return x;
        }

        public boolean union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            //孩子已经有父亲了
            if (fy != y) {
                return false;
            }
            //父亲和孩子已经在一个集合中了
            if (fx == fy) {
                return false;
            }
            set--;
            parent[fy] = fx;
            return true;
        }

        public int getSet() {
            return set;
        }
    }

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            if (leftChild[i] == rightChild[i] && leftChild[i] != -1) {
                return false;
            }
            if ((leftChild[i] != -1 && !unionFind.union(i, leftChild[i])) || (rightChild[i] != -1 && !unionFind.union(i, rightChild[i]))) {
                return false;
            }
        }
        return unionFind.getSet() == 1;
    }

}
