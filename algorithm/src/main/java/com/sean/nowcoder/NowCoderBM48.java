package com.sean.nowcoder;

import java.util.ArrayList;

public class NowCoderBM48 {

    private static ArrayList<Integer> list = new ArrayList<>();

    public static void Insert(Integer num) {
        if (list.isEmpty()) {
            list.add(num);
        } else {
            int index = 0;
            for (; index < list.size(); index++) {
                if (num <= list.get(index)) {
                    break;
                }
            }
            list.add(index, num);
        }
    }

    public static Double GetMedian() {
        int size = list.size();
        if (size % 2 == 1) {
            return (double) list.get(size / 2);
        } else {
            double a = (double) list.get(size / 2);
            double b = (double) list.get(size / 2 - 1);
            return (a + b) / 2;
        }
    }

}
