package com.sean.leetcode.LeetCode839;

/**
 * @Author xionghaiyang
 * @Date 2025-09-09 12:34
 * @Description https://leetcode.cn/problems/similar-string-groups
 * 839. 相似字符串组
 * 如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。
 * 如果这两个字符串本身是相等的，那它们也是相似的。
 * 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。
 * 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。
 * 注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。
 * 形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
 * 给你一个字符串列表 strs。
 * 列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。
 * 请问 strs 中有多少个相似字符串组？
 * 1 <= strs.length <= 300
 * 1 <= strs[i].length <= 300
 * strs[i] 只包含小写字母。
 * strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。
 */
public class Solution {

    public class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int set;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            set = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
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

        public boolean isSameSet(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            return fx == fy;
        }

        public void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx == fy) {
                return;
            }
            set--;
            if (size[fx] >= size[fy]) {
                size[fx] += size[fy];
                parent[fy] = fx;
            } else {
                size[fy] += size[fx];
                parent[fx] = fy;
            }
        }

        public int getSet() {
            return set;
        }
    }

    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (check(strs[i], strs[j])) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.getSet();
    }

    private boolean check(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        int n = str1.length();
        int cnt = 0;
        int[] c = new int[2];
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                if (cnt == 0) {
                    c[0] = str1.charAt(i);
                    c[1] = str2.charAt(i);
                } else if (cnt == 1) {
                    if (c[0] != str2.charAt(i) || c[1] != str1.charAt(i)) {
                        return false;
                    }
                } else {
                    return false;
                }
                cnt++;
            }
        }
        return true;
    }

}
