package com.sean.leetcode;

import java.util.*;

public class LeetCode433 {

    public int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        Set<String> bankSet = new HashSet<>();
        for (String s : bank) {
            bankSet.add(s);
        }
        if (!bankSet.contains(end)) {
            return -1;
        }
        Set<String> visited = new HashSet<>();
        char[] keys = {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (keys[k] != cur.charAt(j)) {
                            StringBuffer sb = new StringBuffer(cur);
                            sb.setCharAt(j, keys[k]);
                            String next = sb.toString();
                            if (!visited.contains(next) && bankSet.contains(next)) {
                                if (next.equals(end)) {
                                    return step;
                                }
                                queue.offer(next);
                                visited.add(next);
                            }
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

}
