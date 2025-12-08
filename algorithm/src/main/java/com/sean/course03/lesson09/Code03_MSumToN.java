package com.sean.course03.lesson09;

/**
 * @Author xionghaiyang
 * @Date 2025-12-08 21:08
 * @Description 定义一种数：可以表示成若干（数量>1）连续正数和的数
 * 比如:
 * 5 = 2+3，5就是这样的数
 * 12 = 3+4+5，12就是这样的数
 * 1不是这样的数，因为要求数量大于1个、连续正数和
 * 2 = 1 + 1，2也不是，因为等号右边不是连续正数
 * 给定一个参数N，返回是不是可以表示成若干连续正数和的数
 */
public class Code03_MSumToN {

    public static boolean isMSum1(int num) {
        for (int i = 1; i <= num; i++) {
            int sum = i;
            for (int j = i + 1; j <= num; j++) {
                sum += j;
                if (sum > num) {
                    break;
                } else if (sum == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isMSum2(int num) {
        if (num < 3) {
            return false;
        }
        return (num & (num - 1)) != 0;
    }

    public static void main(String[] args) {
        for (int num = 1; num < 200; num++) {
            System.out.println(num + ":" + isMSum1(num));
        }
        System.out.println("test begin");
        for (int num = 1; num < 5000; num++) {
            if (isMSum1(num) != isMSum2(num)) {
                System.out.println("error");
            }
        }
        System.out.println("test end");
    }

}
