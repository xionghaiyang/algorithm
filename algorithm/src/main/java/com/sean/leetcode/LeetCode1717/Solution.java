package com.sean.leetcode.LeetCode1717;

/**
 * @Author xionghaiyang
 * @Date 2025-07-23 05:22
 * @Description https://leetcode.cn/problems/maximum-score-from-removing-substrings
 * 1717. 删除子字符串的最大得分
 * 给你一个字符串 s 和两个整数 x 和 y 。
 * 你可以执行下面两种操作任意次。
 * 删除子字符串 "ab" 并得到 x 分。
 * 比方说，从 "cabxbae" 删除 ab ，得到 "cxbae" 。
 * 删除子字符串"ba" 并得到 y 分。
 * 比方说，从 "cabxbae" 删除 ba ，得到 "cabxe" 。
 * 请返回对 s 字符串执行上面操作若干次能得到的最大得分。
 * 1 <= s.length <= 10^5
 * 1 <= x, y <= 10^4
 * s 只包含小写英文字母。
 */
public class Solution {

    public int maximumGain(String s, int x, int y) {
        return x >= y ? getScore(s.toCharArray(), x, y, 'a', 'b') : getScore(s.toCharArray(), y, x, 'b', 'a');
    }

    private int getScore(char[] str, int x, int y, char a, char b) {
        //ab优先删除，栈中只会存在2种元素，只要栈有a,栈顶就一定是a
        int cntA = 0, cntB = 0;
        int res = 0;
        for (char c : str) {
            if (c == a) {
                //碰到a就递增cntA
                cntA++;
            } else if (c == b) {
                //碰到b就检查cntA,大于0就减少cntA并加分
                if (cntA > 0) {
                    cntA--;
                    res += x;
                } else {
                    cntB++;
                }
            } else {
                //遇到其他符号，就清空栈，此时栈的结构一定是连续的b+连续的a,ba的数目就是min(cntA,cntB)
                res += y * Math.min(cntA, cntB);
                cntA = 0;
                cntB = 0;
            }
        }
        //有可能最后一个元素是a或者b,栈没有被清空
        res += y * Math.min(cntA, cntB);
        return res;
    }

}
