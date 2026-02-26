package com.sean.leetcode.LeetCode1404;

/**
 * @Author xionghaiyang
 * @Date 2026-02-26 08:11
 * @Description https://leetcode.cn/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one
 * 1404. 将二进制表示减到 1 的步骤数
 * 给你一个以二进制形式表示的数字 s 。
 * 请你返回按下述规则将其减少到 1 所需要的步骤数：
 * 如果当前数字为偶数，则将其除以 2 。
 * 如果当前数字为奇数，则将其加上 1 。
 * 题目保证你总是可以按上述规则将测试用例变为 1 。
 * 1 <= s.length <= 500
 * s 由字符 '0' 或 '1' 组成。
 * s[0] == '1'
 */
public class Solution {

    //状态机:
    //当前位为0且无进位：累计一次，进位置0
    //当前位为1且无进位：累计两次，进位置1
    //当前位为0且有进位：累计两次，进位置1
    //当前位为1且有进位：累计一次，进位置1
    public int numSteps(String s) {
        int n = s.length();
        int res = 0, carry = 0;
        for (int i = n - 1; i > 0; i--) {
            int cur = s.charAt(i) - '0';
            if (cur != carry) {
                res += 2;
                carry = 1;
            } else {
                res++;
            }
        }
        return res + carry;
    }

}
