package com.sean.base.chapter38;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-08 19:46
 * @Description: TODO
 */
public class Code02_EatGrass {

    //如果n份草，最终先手赢,返回“先手”
    //如果n份草，最终后手赢,返回“后手”
    public String whoWin(int n) {
        if (n < 5) {
            return n == 0 || n == 2 ? "后手" : "先手";
        }
        //进到这个过程里来，当前的先手，先选
        int want = 1;
        while (want <= n) {
            if (whoWin(n - want).equals("后手")) {
                return "先手";
            }
            if (want > n / 4) {
                break;
            }
            want *= 4;
        }
        return "后手";
    }

    public String winner2(int n) {
        if (n % 5 == 0 || n % 5 == 2) {
            return "后手";
        } else {
            return "先手";
        }
    }

    public static void main(String[] args) {
        Code02_EatGrass solution = new Code02_EatGrass();
        for (int i = 0; i <= 50; i++) {
            System.out.println(i + ":" + solution.whoWin(i));
        }
    }

}
