package com.sean.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: xionghaiyang
 * @Date: 2022/7/27 9:13
 */
public class LeetCode592 {

    /**
     * https://leetcode.cn/problems/fraction-addition-and-subtraction/
     * 给定一个表示分数加减运算的字符串 expression ，你需要返回一个字符串形式的计算结果。 
     * 这个结果应该是不可约分的分数，即最简分数。 
     * 如果最终结果是一个整数，例如 2，你需要将它转换成分数形式，其分母为 1。
     * 所以在上述例子中, 2 应该被转换为 2/1。
     */

    class Fraction {

        private int numerator;
        private int denominator;

        public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public Fraction divide() {
            if (this.numerator == 0) {
                this.denominator = 1;
                return this;
            }
            int gcd = getGCD(numerator, denominator);
            this.numerator = this.numerator / gcd;
            this.denominator = this.denominator / gcd;
            return this;
        }

        public Fraction add(Fraction that) {
            divide();
            that.divide();
            return new Fraction(this.numerator * that.denominator + this.denominator * that.numerator, this.denominator * that.denominator)
                    .divide();
        }

        private int getGCD(int a, int b) {
            if (a < 0) {
                return getGCD(-a, b);
            }
            if (a < b) {
                return getGCD(b, a);
            }
            if (a % b == 0) {
                return b;
            }
            return getGCD(b, a % b);
        }

        @Override
        public String toString() {
            divide();
            return new StringBuilder()
                    .append(numerator)
                    .append("/")
                    .append(denominator)
                    .toString();
        }

    }

    public String fractionAddition(String expression) {
        Queue<Fraction> queue = new LinkedList<>();
        int n = expression.length();
        int index = 0;
        while (index < n) {
            char c = expression.charAt(index);
            index++;
            int sign = 1;
            int a = 0;
            if (Character.isDigit(c)) {
                a = c - '0';
            } else if (c == '-') {
                sign = -1;
            }
            while (index < n && Character.isDigit(expression.charAt(index))) {
                a = a * 10 + (expression.charAt(index++) - '0');
            }
            index++;
            int b = 0;
            while (index < n && Character.isDigit(expression.charAt(index))) {
                b = b * 10 + (expression.charAt(index++) - '0');
            }
            queue.offer(new Fraction(sign * a, b));
        }
        Fraction cur = new Fraction(0, 1);
        while (!queue.isEmpty()) {
            Fraction fraction = queue.poll();
            cur = cur.add(fraction);
        }
        return cur.toString();
    }

}
