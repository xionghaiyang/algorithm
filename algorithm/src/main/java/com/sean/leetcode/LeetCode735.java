package com.sean.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode735 {

    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0) {
            return new int[0];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for (int asteroid : asteroids) {
            boolean alive = true;
            while (alive && asteroid < 0 && !stack.isEmpty() && stack.peek() > 0) {
                //如果当前元素的绝对值大于堆顶元素的绝对值，则当前元素不会爆炸
                alive = -asteroid > stack.peek();
                //如果栈顶的元素绝对值小于等于当前元素的绝对值，则栈顶的元素会爆炸
                if (stack.peek() <= -asteroid) {
                    stack.pop();
                }
            }
            if (alive) {
                stack.push(asteroid);
            }
        }
        int size = stack.size();
        int[] res = new int[size];
        for (size--; size >= 0; size--) {
            res[size] = stack.pop();
        }
        return res;
    }

}
