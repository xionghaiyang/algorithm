package com.sean.leetcode.LeetCode3227;

/**
 * @Author xionghaiyang
 * @Date 2025-09-12 10:30
 * @Description https://leetcode.cn/problems/vowels-game-in-a-string
 * 3227. 字符串元音游戏
 * 小红和小明在玩一个字符串元音游戏。
 * 给你一个字符串 s，小红和小明将轮流参与游戏，小红 先 开始：
 * 在小红的回合，她必须移除 s 中包含 奇数 个元音的任意 非空 子字符串。
 * 在小明的回合，他必须移除 s 中包含 偶数 个元音的任意 非空 子字符串。
 * 第一个无法在其回合内进行移除操作的玩家输掉游戏。
 * 假设小红和小明都采取 最优策略 。
 * 如果小红赢得游戏，返回 true，否则返回 false。
 * 英文元音字母包括：a, e, i, o, 和 u。
 * 1 <= s.length <= 10^5
 * s 仅由小写英文字母组成。
 */
public class Solution {

    public boolean doesAliceWin(String s) {
        for (char c : s.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                return true;
            }
        }
        return false;
    }

}
