package com.sean.leetcode.LeetCode2982;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2024-05-30 09:03
 * @Description https://leetcode.cn/problems/find-longest-special-substring-that-occurs-thrice-ii/
 * 2982. 找出出现至少三次的最长特殊子字符串 II
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

    public int maximumLength2(String s) {
        int n = s.length();
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int left = 0, right = 0; left < n; left = right) {
            while (right < n && s.charAt(right) == s.charAt(left)) {
                right++;
            }
            map.computeIfAbsent(s.charAt(left), x -> new ArrayList<>()).add(right - left);
        }
        int res = -1;
        for (List<Integer> list : map.values()) {
            int low = 1, high = n - 2;
            while (low <= high) {
                int mid = (low + high) >> 1;
                int count = 0;
                for (int x : list) {
                    if (x >= mid) {
                        count += x - mid + 1;
                    }
                }
                if (count >= 3) {
                    res = Math.max(res, mid);
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return res;
    }

    public int maximumLength3(String s) {
        int n = s.length();
        int[][] chs = new int[26][3];
        for (int left = 0, right = 0; left < n; left = right) {
            while (right < n && s.charAt(right) == s.charAt(left)) {
                right++;
            }
            int ch = s.charAt(left) - 'a';
            int len = right - left;
            if (len > chs[ch][0]) {
                chs[ch][2] = chs[ch][1];
                chs[ch][1] = chs[ch][0];
                chs[ch][0] = len;
            } else if (len > chs[ch][1]) {
                chs[ch][2] = chs[ch][1];
                chs[ch][1] = len;
            } else if (len > chs[ch][2]) {
                chs[ch][2] = len;
            }
        }
        int res = 0;
        for (int[] ch : chs) {
            res = Math.max(res, Math.max(ch[0] - 2, Math.min(ch[0] - 1, ch[1])));
            res = Math.max(res, ch[2]);
        }
        return res != 0 ? res : -1;
    }

}
