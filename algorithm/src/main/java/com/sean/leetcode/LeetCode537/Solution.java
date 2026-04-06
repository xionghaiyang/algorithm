package com.sean.leetcode.LeetCode537;

/**
 * @Author xionghaiyang
 * @Date 2026-04-06 20:20
 * @Description https://leetcode.cn/problems/complex-number-multiplication
 * 537. 复数乘法
 * 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
 * 实部 是一个整数，取值范围是 [-100, 100]
 * 虚部 也是一个整数，取值范围是 [-100, 100]
 * i2 == -1
 * 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
 * num1 和 num2 都是有效的复数表示。
 */
public class Solution {

    public class Complex {
        private int x;
        private int y;

        public Complex(String num) {
            String[] split = num.split("\\+|i");
            this.x = Integer.parseInt(split[0]);
            this.y = Integer.parseInt(split[1]);
        }

        public Complex(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Complex multi(Complex that) {
            return new Complex(this.x * that.x - this.y * that.y, this.x * that.y + this.y * that.x);
        }

        @Override
        public String toString() {
            return x + "+" + y + "i";
        }
    }

    public String complexNumberMultiply(String num1, String num2) {
        return new Complex(num1).multi(new Complex(num2)).toString();
    }

}
