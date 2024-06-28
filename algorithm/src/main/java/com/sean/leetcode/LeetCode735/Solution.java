package com.sean.leetcode.LeetCode735;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-07 22:06
 * @Description: https://leetcode.cn/problems/asteroid-collision/?envType=study-plan-v2&envId=leetcode-75
 * 735. 行星碰撞
 * 给定一个整数数组 asteroids，表示在同一行的行星。
 * 对于数组中的每一个元素，其绝对值表示行星的大小，
 * 正负表示行星的移动方向（正表示向右移动，负表示向左移动）。
 * 每一颗行星以相同的速度移动。
 * 找出碰撞后剩下的所有行星。
 * 碰撞规则：两个行星相互碰撞，较小的行星会爆炸。
 * 如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 */
public class Solution {

    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) {
            return asteroids;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for (int asteroid : asteroids) {
            boolean flag = true;
            while (flag && asteroid < 0 && !stack.isEmpty() && stack.peek() > 0) {
                if (stack.peek() == -asteroid) {
                    stack.pop();
                    flag = false;
                } else if (stack.peek() < -asteroid) {
                    stack.pop();
                } else {
                    flag = false;
                }
            }
            if (flag) {
                stack.push(asteroid);
            }
        }
        int size = stack.size();
        int[] res = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }

}
