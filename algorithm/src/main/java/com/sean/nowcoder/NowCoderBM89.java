package com.sean.nowcoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NowCoderBM89 {

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return res;
        }
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start != o2.start ? o1.start - o2.start : o1.end - o2.end;
            }
        });
        res.add(intervals.get(0));
        int count = 0;
        for (int i = 1; i < intervals.size(); i++) {
            Interval o1 = intervals.get(i);
            Interval origin = res.get(count);
            if (o1.start > origin.end) {
                res.add(o1);
                count++;
            } else {
                res.remove(count);
                Interval s = new Interval(origin.start, o1.end < origin.end ? origin.end : o1.end);
                res.add(s);
            }
        }
        return res;
    }

}
