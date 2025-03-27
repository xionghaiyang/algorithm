package com.sean.other;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-03-26 22:17
 * @Description 匹配瓶子颜色游戏
 */
public class MatchBottleColorGame {

    public static class Task {
        private int[] arr;
        private int n;

        public Task() {
            n = 4 + (int) (5 * Math.random());
            System.out.println("数字的个数为" + n);
            arr = new int[n];
            Set<Integer> set = new HashSet<>();
            Random random = new Random();
            int i = 0;
            int x;
            while (i < n) {
                do {
                    x = 1 + (int) (n * Math.random());
                } while (set.contains(x));
                set.add(x);
                arr[i++] = x;
            }
        }

        public boolean check(int[] nums) {
            if (nums == null || nums.length != n) {
                return false;
            }
            int res = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] == nums[i]) {
                    res++;
                }
            }
            System.out.println("正确的个数是" + res);
            return res == n;
        }
    }

    private static void print(int[] arr) {
        if (arr == null) {
            return;
        }
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static int[] str2int(String[] str) {
        int n = str.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = Integer.parseInt(str[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task task = new Task();
        //print(task.arr);
        while (true) {
            System.out.println("请输入您的数字排列,以逗号分隔");
            if (task.check(str2int(sc.next().split(",")))) {
                System.out.println("恭喜您完成匹配");
                break;
            }
        }
        sc.close();
    }

}
