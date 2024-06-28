package com.sean.base.chapter38;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-08 19:24
 * @Description: TODO
 */
public class Code03_MSumToN {

    public boolean isMSum1(int num) {
        for (int start = 1; start <= num; start++) {
            int sum = start;
            for (int j = start + 1; j <= num; j++) {
                if (sum + j > num) {
                    break;
                }
                if (sum + j == num) {
                    return true;
                }
                sum += j;
            }
        }
        return false;
    }

    public boolean isMSum2(int num) {
        return (num & (num - 1)) != 0;
    }

    public static void main(String[] args) {
        Code03_MSumToN solution = new Code03_MSumToN();
        for (int num = 1; num < 200; num++) {
            System.out.println(num + ":" + solution.isMSum1(num));
        }
        System.out.println("test begin!");
        for (int num = 1; num < 5000; num++) {
            if (solution.isMSum1(num) != solution.isMSum2(num)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test end!");
    }


}
