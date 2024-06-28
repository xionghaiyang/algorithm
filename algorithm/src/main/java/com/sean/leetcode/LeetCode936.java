package com.sean.leetcode;

import java.util.*;

public class LeetCode936 {

    class Node {
        Set<Integer> made;
        Set<Integer> todo;

        Node(Set<Integer> made, Set<Integer> todo) {
            this.made = made;
            this.todo = todo;
        }
    }

    public int[] movesToStamp(String stamp, String target) {
        int m = stamp.length();
        int n = target.length();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] done = new boolean[n];
        Stack<Integer> res = new Stack<>();
        List<Node> list = new ArrayList<>();
        for (int i = 0; i <= n - m; i++) {
            Set<Integer> made = new HashSet<>();
            Set<Integer> todo = new HashSet<>();
            for (int j = 0; j < m; j++) {
                if (target.charAt(i + j) == stamp.charAt(j)) {
                    made.add(i + j);
                } else {
                    todo.add(i + j);
                }
            }
            list.add(new Node(made, todo));
            if (todo.isEmpty()) {
                res.push(i);
                for (int j = i; j < i + m; j++) {
                    if (!done[j]) {
                        queue.add(j);
                        done[j] = true;
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            int i = queue.poll();
            for (int j = Math.max(0, i - m + 1); j <= Math.min(n - m, i); j++) {
                if (list.get(j).todo.contains(i)) {
                    list.get(j).todo.remove(i);
                    if (list.get(j).todo.isEmpty()) {
                        res.push(j);
                        for (int md : list.get(j).made) {
                            if (!done[md]) {
                                queue.add(md);
                                done[md] = true;
                            }
                        }
                    }
                }
            }
        }
        for (boolean b : done) {
            if (!b) {
                return new int[0];
            }
        }
        int[] ret = new int[res.size()];
        int index = 0;
        while (!res.isEmpty()) {
            ret[index++] = res.pop();
        }
        return ret;
    }

}
