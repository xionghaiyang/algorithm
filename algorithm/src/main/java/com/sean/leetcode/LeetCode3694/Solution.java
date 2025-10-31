package com.sean.leetcode.LeetCode3694;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-10-31 20:15
 * @Description https://leetcode.cn/problems/distinct-points-reachable-after-substring-removal
 * 3694. 删除子字符串后不同的终点
 * 给你一个由字符 'U'、'D'、'L' 和 'R' 组成的字符串 s，表示在无限的二维笛卡尔网格上的移动。
 * 'U': 从 (x, y) 移动到 (x, y + 1)。
 * 'D': 从 (x, y) 移动到 (x, y - 1)。
 * 'L': 从 (x, y) 移动到 (x - 1, y)。
 * 'R': 从 (x, y) 移动到 (x + 1, y)。
 * 你还得到了一个正整数 k。
 * 你 必须 选择并移除 恰好一个 长度为 k 的连续子字符串 s。
 * 然后，从坐标 (0, 0) 开始，按顺序执行剩余的移动。
 * 返回可到达的 不同 最终坐标的数量。
 * 1 <= s.length <= 10^5
 * s 只包含 'U'、'D'、'L' 和 'R'。
 * 1 <= k <= s.length
 */
public class Solution {

    public int distinctPoints(String s, int k) {
        int n = s.length();
        Map<Character, int[]> map = new HashMap<Character, int[]>() {{
            put('U', new int[]{0, 1});
            put('D', new int[]{0, -1});
            put('L', new int[]{-1, 0});
            put('R', new int[]{1, 0});
        }};
        Set<Long> set = new HashSet<>();
        for (int i = 0, x = 0, y = 0; i < n; i++) {
            int[] dir = map.get(s.charAt(i));
            x += dir[0];
            y += dir[1];
            int left = i - k + 1;
            if (left < 0) {
                continue;
            }
            set.add((long) (x + n) << 20 | (y + n));
            int[] d = map.get(s.charAt(left));
            x -= d[0];
            y -= d[1];
        }
        return set.size();
    }

    public int distinctPoints1(String s, int k) {
        final int[][] dirs = new int[128][];
        dirs['L'] = new int[]{-1, 0};
        dirs['R'] = new int[]{1, 0};
        dirs['D'] = new int[]{0, -1};
        dirs['U'] = new int[]{0, 1};
        Set<Long> set = new HashSet<>();
        int n = s.length();
        set.add((long) n << 20 | n);
        for (int i = k, x = 0, y = 0; i < n; i++) {
            char in = s.charAt(i);
            char out = s.charAt(i - k);
            x += dirs[in][0] - dirs[out][0];
            y += dirs[in][1] - dirs[out][1];
            set.add((long) (x + n) << 20 | (y + n));
        }
        return set.size();
    }

}
