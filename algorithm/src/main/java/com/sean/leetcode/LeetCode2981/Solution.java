package com.sean.leetcode.LeetCode2981;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2024-05-29 08:10
 * @Description https://leetcode.cn/problems/find-longest-special-substring-that-occurs-thrice-i/
 * 2981. 找出出现至少三次的最长特殊子字符串 I
 * 给你一个仅由小写英文字母组成的字符串 s 。
 * 如果一个字符串仅由单一字符组成，那么它被称为 特殊 字符串。
 * 例如，字符串 "abc" 不是特殊字符串，而字符串 "ddd"、"zz" 和 "f" 是特殊字符串。
 * 返回在 s 中出现 至少三次 的 最长特殊子字符串 的长度，如果不存在出现至少三次的特殊子字符串，则返回 -1 。
 * 子字符串 是字符串中的一个连续 非空 字符序列。
 */
public class Solution {

    public int maximumLength(String s) {
        List<Integer>[] groups = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            groups[i] = new ArrayList<>();
        }
        int n = s.length();
        int len = 0;
        for (int i = 0; i < n; i++) {
            len++;
            if (i + 1 == n || s.charAt(i) != s.charAt(i + 1)) {
                groups[s.charAt(i) - 'a'].add(len);
                len = 0;
            }
        }
        int res = 0;
        for (List<Integer> group : groups) {
            if (group.isEmpty()) {
                continue;
            }
            group.sort(Collections.reverseOrder());
            group.add(0);
            group.add(0);
            res = Math.max(res, Math.max(group.get(0) - 2, Math.max(Math.min(group.get(0) - 1, group.get(1)), group.get(2))));
        }
        return res > 0 ? res : -1;
    }

    public int maximumLength1(String s) {
        int res = -1;
        int n = s.length();
        List<Integer>[] chs = new List[26];
        for (int i = 0; i < 26; i++) {
            chs[i] = new ArrayList<>();
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt++;
            if (i + 1 == n || s.charAt(i) != s.charAt(i + 1)) {
                int ch = s.charAt(i) - 'a';
                chs[ch].add(cnt);
                cnt = 0;
                for (int j = chs[ch].size() - 1; j > 0; j--) {
                    if (chs[ch].get(j) > chs[ch].get(j - 1)) {
                        Collections.swap(chs[ch], j, j - 1);
                    } else {
                        break;
                    }
                }
                if (chs[ch].size() > 3) {
                    chs[ch].remove(chs[ch].size() - 1);
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            if (chs[i].size() > 0 && chs[i].get(0) > 2) {
                res = Math.max(res, chs[i].get(0) - 2);
            }
            if (chs[i].size() > 1 && chs[i].get(0) > 1) {
                res = Math.max(res, Math.min(chs[i].get(0) - 1, chs[i].get(1)));
            }
            if (chs[i].size() > 2) {
                res = Math.max(res, chs[i].get(2));
            }
        }
        return res;
    }

}
