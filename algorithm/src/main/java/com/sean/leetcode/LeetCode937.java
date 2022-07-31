package com.sean.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class LeetCode937 {

    public static class Pair {
        String log;
        int index;

        public Pair(String log, int index) {
            this.log = log;
            this.index = index;
        }
    }

    public static String[] reorderLogFiles(String[] logs) {
        int length = logs.length;
        Pair[] arr = new Pair[length];
        for (int i = 0; i < length; i++) {
            arr[i] = new Pair(logs[i], i);
        }
        Arrays.sort(arr, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                String log1 = o1.log;
                String log2 = o2.log;
                int index1 = o1.index;
                int index2 = o2.index;
                String[] split1 = log1.split(" ", 2);
                String[] split2 = log2.split(" ", 2);
                boolean isDogit1 = Character.isDigit(split1[1].charAt(0));
                boolean isDogit2 = Character.isDigit(split2[1].charAt(0));
                if (isDogit1 && isDogit2) {
                    return index1 - index2;
                }
                if (!isDogit1 && !isDogit2) {
                    int sc = split1[1].compareTo(split2[1]);
                    if (sc != 0) {
                        return sc;
                    }
                    return split1[0].compareTo(split2[0]);
                }
                return isDogit1 ? 1 : -1;
            }
        });
        String[] res = new String[length];
        for (int i = 0; i < length; i++) {
            res[i] = arr[i].log;
        }
        return res;
    }

}
