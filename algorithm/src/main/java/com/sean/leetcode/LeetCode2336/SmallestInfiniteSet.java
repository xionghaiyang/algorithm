package com.sean.leetcode.LeetCode2336;


import java.util.TreeSet;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-11-29 15:22
 * @Description: https://leetcode.cn/problems/smallest-number-in-infinite-set/
 * 2336. 无限集中的最小数字
 * 现有一个包含所有正整数的集合 [1, 2, 3, 4, 5, ...] 。
 * 实现 SmallestInfiniteSet 类：
 * SmallestInfiniteSet() 初始化 SmallestInfiniteSet 对象以包含 所有 正整数。
 * int popSmallest() 移除 并返回该无限集中的最小整数。
 * void addBack(int num) 如果正整数 num 不 存在于无限集中，则将一个 num 添加 到该无限集最后。
 */
public class SmallestInfiniteSet {

    private int thres;
    private TreeSet<Integer> set;

    public SmallestInfiniteSet() {
        thres = 1;
        set = new TreeSet<>();
    }

    public int popSmallest() {
        if (set.isEmpty()) {
            int res = thres;
            thres++;
            return res;
        }
        return set.pollFirst();
    }

    public void addBack(int num) {
        if (num < thres) {
            set.add(num);
        }
    }

}
