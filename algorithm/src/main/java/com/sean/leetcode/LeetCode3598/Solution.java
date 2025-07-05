package com.sean.leetcode.LeetCode3598;

/**
 * @Author xionghaiyang
 * @Date 2025-07-05 17:18
 * @Description https://leetcode.cn/problems/longest-common-prefix-between-adjacent-strings-after-removals
 * 3598. 相邻字符串之间的最长公共前缀
 * 给你一个字符串数组 words，对于范围 [0, words.length - 1] 内的每个下标 i，执行以下步骤：
 * 从 words 数组中移除下标 i 处的元素。
 * 计算修改后的数组中所有 相邻对 之间的 最长公共前缀 的长度。
 * 返回一个数组 answer，其中 answer[i] 是移除下标 i 后，相邻对之间最长公共前缀的长度。
 * 如果 不存在 相邻对，或者 不存在 公共前缀，则 answer[i] 应为 0。
 * 字符串的前缀是从字符串的开头开始延伸到任意位置的子字符串。
 * 1 <= words.length <= 10^5
 * 1 <= words[i].length <= 10^4
 * words[i] 仅由小写英文字母组成。
 * words[i] 的长度总和不超过 10^5。
 */
public class Solution {

    //移除words[i]后，问题变成:
    //计算前缀[0,i-1]中的相邻lcp长度的最大值
    //计算words[i-1]和words[i+1]的lcp的长度
    //计算后缀[i+1,n-1]中的相邻lcp长度的最大值
    //三者取最大值，即为res[i]
    public int[] longestCommonPrefix(String[] words) {
        int n = words.length;
        int[] res = new int[n];
        //不存在相邻对
        if (n == 1) {
            return res;
        }
        //后缀[i,n-1]中的相邻lcp长度的最大值
        int[] sufMax = new int[n];
        for (int i = n - 2; i > 0; i--) {
            sufMax[i] = Math.max(sufMax[i + 1], lcp(words[i], words[i + 1]));
        }
        res[0] = sufMax[1];
        //前缀[0,i-1]中的相邻lcp长度的最大值
        int preMax = 0;
        for (int i = 1; i < n - 1; i++) {
            res[i] = Math.max(Math.max(preMax, lcp(words[i - 1], words[i + 1])), sufMax[i + 1]);
            preMax = Math.max(preMax, lcp(words[i - 1], words[i]));
        }
        res[n - 1] = preMax;
        return res;
    }

    private int lcp(String s, String t) {
        int n = Math.min(s.length(), t.length());
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                return i;
            }
        }
        return n;
    }

    public int[] longestCommonPrefix1(String[] words) {
        int n = words.length;
        int max1 = -1, max2 = -1, max3 = -1;
        int i1 = -2, i2 = -2;
        for (int i = 0; i < n - 1; i++) {
            int lcp = lcp(words[i], words[i + 1]);
            if (lcp > max1) {
                max3 = max2;
                max2 = max1;
                max1 = lcp;
                i2 = i1;
                i1 = i;
            } else if (lcp > max2) {
                max3 = max2;
                max2 = lcp;
                i2 = i;
            } else if (lcp > max3) {
                max3 = lcp;
            }
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int lcp = 0 < i && i < n - 1 ? lcp(words[i - 1], words[i + 1]) : 0;
            if (i != i1 && i != i1 + 1) {
                res[i] = Math.max(max1, lcp);
            } else if (i != i2 && i != i2 + 1) {
                res[i] = Math.max(max2, lcp);
            } else {
                res[i] = Math.max(max3, lcp);
            }
        }
        return res;
    }

}
