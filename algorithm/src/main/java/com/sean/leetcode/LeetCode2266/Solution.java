package com.sean.leetcode.LeetCode2266;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-01-19 14:03
 * @Description https://leetcode.cn/problems/count-number-of-texts/
 * 2266. 统计打字方案数
 * Alice 在给 Bob 用手机打字。数字到字母的 对应 如下图所示。
 * 为了 打出 一个字母，Alice 需要 按 对应字母 i 次，i 是该字母在这个按键上所处的位置。
 * 比方说，为了按出字母 's' ，Alice 需要按 '7' 四次。
 * 类似的， Alice 需要按 '5' 两次得到字母  'k' 。
 * 注意，数字 '0' 和 '1' 不映射到任何字母，所以 Alice 不 使用它们。
 * 但是，由于传输的错误，Bob 没有收到 Alice 打字的字母信息，反而收到了 按键的字符串信息 。
 * 比方说，Alice 发出的信息为 "bob" ，Bob 将收到字符串 "2266622" 。
 * 给你一个字符串 pressedKeys ，表示 Bob 收到的字符串，请你返回 Alice 总共可能发出多少种文字信息 。
 * 由于答案可能很大，将它对 10^9 + 7 取余 后返回。
 */
public class Solution {

    public int countTexts(String pressedKeys) {
        int mod = 1_000_000_007;
        int n = pressedKeys.length();
        //连续按多次3个字母按键对应的方案数
        List<Long> dp3 = new ArrayList<>(Arrays.asList(1L, 1L, 2L, 4L));
        //连续按多次4个字母按键对应的方案数
        List<Long> dp4 = new ArrayList<>(Arrays.asList(1L, 1L, 2L, 4L));
        for (int i = 4; i <= n; i++) {
            dp3.add((dp3.get(i - 1) + dp3.get(i - 2) + dp3.get(i - 3)) % mod);
            dp4.add((dp4.get(i - 1) + dp4.get(i - 2) + dp4.get(i - 3) + dp4.get(i - 4)) % mod);
        }
        //总方案数
        long res = 1;
        //当前字符连续出现的次数
        int cnt = 1;
        for (int i = 1; i < n; i++) {
            if (pressedKeys.charAt(i) == pressedKeys.charAt(i - 1)) {
                cnt++;
            } else {
                //对按键对应字符数量讨论并更新总方案数
                if (pressedKeys.charAt(i - 1) == '7' || pressedKeys.charAt(i - 1) == '9') {
                    res = (res * dp4.get(cnt)) % mod;
                } else {
                    res = (res * dp3.get(cnt)) % mod;
                }
                cnt = 1;
            }
        }
        //更新最后一段连续字符子串对应的方案数
        if (pressedKeys.charAt(n - 1) == '7' || pressedKeys.charAt(n - 1) == '9') {
            res = (res * dp4.get(cnt)) % mod;
        } else {
            res = (res * dp3.get(cnt)) % mod;
        }
        return (int) res;
    }

}
