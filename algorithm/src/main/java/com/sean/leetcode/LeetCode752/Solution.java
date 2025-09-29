package com.sean.leetcode.LeetCode752;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-09-29 21:26
 * @Description https://leetcode.cn/problems/open-the-lock
 * 752. 打开转盘锁
 * 你有一个带有四个圆形拨轮的转盘锁。
 * 每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。
 * 每次旋转都只能旋转一个拨轮的一位数字。
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 * 1 <= deadends.length <= 500
 * deadends[i].length == 4
 * target.length == 4
 * target 不在 deadends 之中
 * target 和 deadends[i] 仅由若干位数字组成
 */
public class Solution {

    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        for (String str : deadends) {
            set.add(str);
        }
        if (set.contains("0000")) {
            return -1;
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        Set<String> visited = new HashSet<>();
        visited.add("0000");
        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String status = queue.poll();
                for (String nextStatus : getStatus(status)) {
                    if (!set.contains(nextStatus) && !visited.contains(nextStatus)) {
                        if (nextStatus.equals(target)) {
                            return res;
                        }
                        queue.offer(nextStatus);
                        visited.add(nextStatus);
                    }
                }
            }
        }
        return -1;
    }

    private char getNext(char d) {
        return d == '9' ? '0' : (char) (d + 1);
    }

    private char getLast(char d) {
        return d == '0' ? '9' : (char) (d - 1);
    }

    private List<String> getStatus(String status) {
        List<String> res = new ArrayList<>();
        char[] str = status.toCharArray();
        for (int i = 0; i < 4; i++) {
            char cur = str[i];
            str[i] = getNext(cur);
            res.add(String.valueOf(str));
            str[i] = getLast(cur);
            res.add(String.valueOf(str));
            str[i] = cur;
        }
        return res;
    }

}
