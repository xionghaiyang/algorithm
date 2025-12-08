package com.sean.course03.lesson09;

/**
 * @Author xionghaiyang
 * @Date 2025-12-08 20:44
 * @Description 给定一个正整数N，表示有N份青草统一堆放在仓库里
 * 有一只牛和一只羊，牛先吃，羊后吃，它俩轮流吃草
 * 不管是牛还是羊，每一轮能吃的草量必须是：
 * 1，4，16，64…(4的某次方)
 * 谁最先把草吃完，谁获胜
 * 假设牛和羊都绝顶聪明，都想赢，都会做出理性的决定
 * 根据唯一的参数N，返回谁会赢
 */
public class Code02_EatGrass {

    //n份青草放在一堆
    //先手后手都绝顶聪明
    //返回"先手"或者"后手"表示他赢了
    public static String winner1(int n) {
        //0   1  2  3  4
        //后 先 后 先 先
        if (n < 5) {
            return (n == 0 || n == 2) ? "后手" : "先手";
        }
        //当前先手决定吃的草数
        int base = 1;
        //当前是先手在选
        while (base <= n) {
            //当前一共n份草，先手吃掉的是base份,n-base是留给后手的草
            if (winner1(n - base).equals("后手")) {
                return "先手";
            }
            if (base > n / 4) {
                break;
            }
            base *= 4;
        }
        return "后手";
    }

    public static String winner2(int n) {
        if (n % 5 == 0 || n % 5 == 2) {
            return "后手";
        } else {
            return "先手";
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            System.out.println(i + ":" + winner1(i));
        }
    }

}
