package com.sean.leetcode.LeetCodeLCR109;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-11 10:16
 * @Description: https://leetcode.cn/problems/zlDJc7/
 * LCR 109. 打开转盘锁
 * 一个密码锁由 4 个环形拨轮组成，每个拨轮都有 10 个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。
 * 每次旋转都只能旋转一个拨轮的一位数字。
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串 target 代表可以解锁的数字，请给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 */
public class Solution {

    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        for (String deadend : deadends) {
            set.add(deadend);
        }
        if (set.contains("0000")) {
            return -1;
        }
        int step = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        Set<String> visit = new HashSet<>();
        visit.add("0000");
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String status = queue.poll();
                for (String next : getNext(status)) {
                    if (!visit.contains(next) && !set.contains(next)) {
                        if (next.equals(target)) {
                            return step;
                        }
                        queue.offer(next);
                        visit.add(next);
                    }
                }
            }
        }
        return -1;
    }

    private List<String> getNext(String status) {
        List<String> res = new ArrayList<>();
        char[] arr = status.toCharArray();
        for (int i = 0; i < 4; i++) {
            char c = arr[i];
            arr[i] = numPrev(c);
            res.add(new String(arr));
            arr[i] = numSucc(c);
            res.add(new String(arr));
            arr[i] = c;
        }
        return res;
    }

    private char numPrev(char c) {
        return c == '0' ? '9' : (char) (c - 1);
    }

    private char numSucc(char c) {
        return c == '9' ? '0' : (char) (c + 1);
    }

}
