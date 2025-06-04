package com.sean.leetcode.LeetCode1061;

/**
 * @Author xionghaiyang
 * @Date 2025-06-05 06:57
 * @Description https://leetcode.cn/problems/lexicographically-smallest-equivalent-string
 * 1061. 按字典序排列最小的等效字符串
 * 给出长度相同的两个字符串s1 和 s2 ，还有一个字符串 baseStr 。
 * 其中  s1[i] 和 s2[i]  是一组等价字符。
 * 举个例子，如果 s1 = "abc" 且 s2 = "cde"，那么就有 'a' == 'c', 'b' == 'd', 'c' == 'e'。
 * 等价字符遵循任何等价关系的一般规则：
 * 自反性 ：'a' == 'a'
 * 对称性 ：'a' == 'b' 则必定有 'b' == 'a'
 * 传递性 ：'a' == 'b' 且 'b' == 'c' 就表明 'a' == 'c'
 * 例如， s1 = "abc" 和 s2 = "cde" 的等价信息和之前的例子一样，那么 baseStr = "eed" , "acd" 或 "aab"，这三个字符串都是等价的，
 * 而 "aab" 是 baseStr 的按字典序最小的等价字符串
 * 利用 s1 和 s2 的等价信息，找出并返回 baseStr 的按字典序排列最小的等价字符串。
 * 1 <= s1.length, s2.length, baseStr <= 1000
 * s1.length == s2.length
 * 字符串s1, s2, and baseStr 仅由从 'a' 到 'z' 的小写英文字母组成。
 */
public class Solution {

    public class UnionFind {
        private int[] parent;
        private int[] help;

        public UnionFind() {
            parent = new int[26];
            help = new int[26];
            for (int i = 0; i < 26; i++) {
                parent[i] = i;
            }
        }

        public void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx < fy) {
                parent[fy] = fx;
            } else if (fx > fy) {
                parent[fx] = fy;
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
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        UnionFind unionFind = new UnionFind();
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            unionFind.union(s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }
        StringBuilder res = new StringBuilder();
        for (char c : baseStr.toCharArray()) {
            res.append((char) (unionFind.find(c - 'a') + 'a'));
        }
        return res.toString();
    }

}
