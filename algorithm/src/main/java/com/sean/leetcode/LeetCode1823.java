package com.sean.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode1823 {

    public static int findTheWinner1(int n, int k) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }
        while (queue.size() > 1) {
            for (int i = 1; i < k; i++) {
                queue.offer(queue.poll());
            }
            queue.poll();
        }
        return queue.peek();
    }

    public static int findTheWinner(int n, int k) {
        if (n == 1) {
            return 1;
        }
        return (findTheWinner(n - 1, k) + k - 1) % n + 1;
    }

}
