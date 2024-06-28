package com.sean.leetcode;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2022/7/30 3:07
 */
public class LeetCode952 {

    /**
     * https://leetcode.cn/problems/largest-component-size-by-common-factor/
     * 给定一个由不同正整数的组成的非空数组 nums ，考虑下面的图：
     * 有 nums.length 个节点，按从 nums[0] 到 nums[nums.length - 1] 标记；
     * 只有当 nums[i] 和 nums[j] 共用一个大于 1 的公因数时，nums[i] 和 nums[j]之间才有一条边。
     * 返回 图中最大连通组件的大小 。
     */

    public int largestComponentSize(int[] nums) {
        int m = Arrays.stream(nums).max().getAsInt();
        UnionFind unionFind = new UnionFind(m + 1);
        for (int num : nums) {
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    unionFind.union(num, i);
                    unionFind.union(num, num / i);
                }
            }
        }
        int[] counts = new int[m + 1];
        int res = 0;
        for (int num : nums) {
            int root = unionFind.find(num);
            counts[root]++;
            res = Math.max(res, counts[root]);
        }
        return res;
    }

    class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            int index = 0;
            while (i != parent[i]) {
                help[index++] = i;
                i = parent[i];
            }
            for (index--; index >= 0; index--) {
                parent[help[index]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
            }
        }

    }

}
