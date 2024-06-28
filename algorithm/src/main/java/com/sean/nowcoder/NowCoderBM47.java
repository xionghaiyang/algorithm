package com.sean.nowcoder;

import java.util.Comparator;
import java.util.PriorityQueue;

public class NowCoderBM47 {

    public static int findKth(int[] arr, int n, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.offer(arr[i]);
            }
        }
        return priorityQueue.peek();
    }

}
