package com.sean.nowcoder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class NowCoderBM46 {

    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (input == null || input.length == 0 || k == 0) {
            return ans;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < k; i++) {
            queue.offer(input[i]);
        }
        for (int i = k; i < input.length; i++) {
            if (queue.peek() > input[i]) {
                queue.poll();
                queue.offer(input[i]);
            }
        }
        for (int i = 0; i < k; i++) {
            ans.add(queue.poll());
        }
        return ans;
    }

}
