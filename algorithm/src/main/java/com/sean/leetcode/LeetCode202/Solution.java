package com.sean.leetcode.LeetCode202;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-23 21:12
 * @Description: https://leetcode.cn/problems/happy-number/?envType=study-plan-v2&envId=top-interview-150
 * 202. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」 定义为：
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 */
public class Solution {

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    public boolean isHappy1(int n) {
        int slow = n;
        int fast = getNext(n);
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return fast == 1;
    }

    private int getNext(int n) {
        int res = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            res += d * d;
        }
        return res;
    }

    public boolean isHappy2(int n) {
//        Set<Integer> set = new HashSet<Integer>() {{
//            add(4);
//            add(16);
//            add(37);
//            add(58);
//            add(89);
//            add(145);
//            add(42);
//            add(20);
//        }};
        Set<Integer> set = new HashSet<>(Arrays.asList(4, 16, 37, 58, 89, 145, 42, 20));
        while (n != 1 && !set.contains(n)) {
            n = getNext(n);
        }
        return n == 1;
    }

}
