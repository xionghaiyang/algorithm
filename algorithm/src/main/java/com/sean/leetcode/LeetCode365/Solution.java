package com.sean.leetcode.LeetCode365;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-28 16:10
 * @Description: https://leetcode.cn/problems/water-and-jug-problem/
 * 365. 水壶问题
 * 有两个水壶，容量分别为 jug1Capacity 和 jug2Capacity 升。
 * 水的供应是无限的。
 * 确定是否有可能使用这两个壶准确得到 targetCapacity 升。
 * 如果可以得到 targetCapacity 升水，最后请用以上水壶中的一或两个来盛放取得的 targetCapacity 升水。
 * 你可以：
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 */
public class Solution {

    public boolean canMeasureWater(int x, int y, int target) {
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{0, 0});
        Set<Long> seen = new HashSet<>();
        while (!stack.isEmpty()) {
            if (seen.contains(hash(stack.peek()))) {
                stack.pop();
                continue;
            }
            seen.add(hash(stack.peek()));
            int[] state = stack.pop();
            int remain_x = state[0], remain_y = state[1];
            if (remain_x == target || remain_y == target || remain_x + remain_y == target) {
                return true;
            }
            //把x壶灌满
            stack.push(new int[]{x, remain_y});
            //把y壶灌满
            stack.push(new int[]{remain_x, y});
            //把x壶倒空
            stack.push(new int[]{0, remain_y});
            //把y壶倒空
            stack.push(new int[]{remain_x, 0});
            //把x壶的水灌进y壶，直至灌满或倒空
            stack.push(new int[]{remain_x - Math.min(remain_x, y - remain_y), remain_y + Math.min(remain_x, y - remain_y)});
            //把y壶的水灌进x壶，直至灌满或倒空
            stack.push(new int[]{remain_x + Math.min(remain_y, x - remain_x), remain_y - Math.min(remain_y, x - remain_x)});
        }
        return false;
    }

    private long hash(int[] state) {
        return (long) state[0] * 1000001 + state[1];
    }

}
