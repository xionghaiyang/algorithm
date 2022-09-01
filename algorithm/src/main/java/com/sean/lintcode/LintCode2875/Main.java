package com.sean.lintcode.LintCode2875;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-30 08:26
 */
public class Main {

    public static void main(String[] args) {
        try {
            int num = Integer.parseInt(args[0]);
            if (num < 1 || num > 100000000) {
                throw new Exception("num range does not match.");
            }
            Solution solution = new Solution();
            solution.printNum(num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
