package com.sean.leetcode.LeetCode2516;

/**
 * @Author xionghaiyang
 * @Date 2024-09-27 20:28
 * @Description https://leetcode.cn/problems/take-k-of-each-character-from-left-and-right/
 * 2516. 每种字符至少取 K 个
 * 给你一个由字符 'a'、'b'、'c' 组成的字符串 s 和一个非负整数 k 。
 * 每分钟，你可以选择取走 s 最左侧 还是 最右侧 的那个字符。
 * 你必须取走每种字符 至少 k 个，返回需要的 最少 分钟数；如果无法取到，则返回 -1 。
 */
public class Solution {

    public int takeCharacters(String s, int k) {
        int[] cnt = new int[3];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        int res = n;
        if (cnt[0] >= k && cnt[1] >= k && cnt[2] >= k) {
            res = Math.min(res, n);
        } else {
            return -1;
        }
        int left = 0;
        for (int right = 0; right < n; right++) {
            cnt[s.charAt(right) - 'a']--;
            while (left < right && (cnt[0] < k || cnt[1] < k || cnt[2] < k)) {
                cnt[s.charAt(left) - 'a']++;
                left++;
            }
            if (cnt[0] >= k && cnt[1] >= k && cnt[2] >= k) {
                res = Math.min(res, n - (right - left + 1));
            }
        }
        return res;
    }

}
