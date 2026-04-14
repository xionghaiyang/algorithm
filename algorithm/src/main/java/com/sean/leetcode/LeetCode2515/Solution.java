package com.sean.leetcode.LeetCode2515;

/**
 * @Author xionghaiyang
 * @Date 2026-04-15 06:12
 * @Description https://leetcode.cn/problems/shortest-distance-to-target-string-in-a-circular-array
 * 2515. 到目标字符串的最短距离
 * 给你一个下标从 0 开始的 环形 字符串数组 words 和一个字符串 target 。
 * 环形数组 意味着数组首尾相连。
 * 形式上， words[i] 的下一个元素是 words[(i + 1) % n] ，而 words[i] 的前一个元素是 words[(i - 1 + n) % n] ，其中 n 是 words 的长度。
 * 从 startIndex 开始，你一次可以用 1 步移动到下一个或者前一个单词。
 * 返回到达目标字符串 target 所需的最短距离。
 * 如果 words 中不存在字符串 target ，返回 -1 。
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] 和 target 仅由小写英文字母组成
 * 0 <= startIndex < words.length
 */
public class Solution {

    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int res = -1;
        for (int i = 0; i <= (n + 1) / 2; i++) {
            if (words[(startIndex - i + n) % n].equals(target) || words[(startIndex + i) % n].equals(target)) {
                res = i;
                break;
            }
        }
        return res;
    }

}
