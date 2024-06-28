package com.sean.lintcode.LintCode407;

/**
 * @Author xionghaiyang
 * @Date 2022-09-12 20:43
 * @Description https://www.lintcode.com/problem/407/?showListFe=true&page=1&pageSize=50
 * 407 · 加一
 * 描述
 * 给定一个非负数，表示一个数字数组，在该数的基础上+1，返回一个新的数组。
 * 该数字按照数位高低进行排列，最高位的数在列表的最前面。
 */
public class Solution {

    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int carry = 1;
        int index = n - 1;
        while (carry != 0 && index >= 0) {
            int sum = digits[index] + carry;
            if (sum >= 10) {
                digits[index--] = sum % 10;
                carry = 1;
                //carry = sum / 10;
            } else {
                digits[index--] = sum;
                carry = 0;
                break;
            }
        }
        if (carry == 0) {
            return digits;
        } else {
            int[] res = new int[n + 1];
            //res[0] = carry;
            res[0] = 1;
            for (int i = 1; i < n + 1; i++) {
                res[i] = digits[i - 1];
            }
            return res;
        }
    }

}
