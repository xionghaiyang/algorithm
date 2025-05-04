package com.sean.course02.lesson07;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2025-05-04 09:55
 * @Description 给定很多线段，每个线段都有两个数[start, end]，
 * 表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 */
public class Code01_CoverMax {

    public static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
                cover = Math.max(cover, cur);
            }
        }
        return cover;
    }

    public static class Line {
        public int start;
        public int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int maxCover2(int[][] m) {
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        Arrays.sort(lines, (a, b) -> a.start - b.start);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
                heap.poll();
            }
            heap.add(lines[i].end);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    private static int[][] generateLines(int N, int L, int R) {
        int size = (int) (N * Math.random()) + 1;
        int[][] res = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) ((R - L + 1) * Math.random());
            int b = L + (int) ((R - L + 1) * Math.random());
            if (a == b) {
                b = a + 1;
            }
            res[i][0] = Math.min(a, b);
            res[i][1] = Math.max(a, b);
        }
        return res;
    }

    public static void main(String[] args) {
        Line line1 = new Line(4, 9);
        Line line2 = new Line(1, 4);
        Line line3 = new Line(7, 15);
        Line line4 = new Line(2, 4);
        Line line5 = new Line(4, 6);
        Line line6 = new Line(3, 7);
        PriorityQueue<Line> heap = new PriorityQueue<>((a, b) -> a.start - b.start);
        heap.add(line1);
        heap.add(line2);
        heap.add(line3);
        heap.add(line4);
        heap.add(line5);
        heap.add(line6);
        while (!heap.isEmpty()) {
            Line cur = heap.poll();
            System.out.println(cur.start + "," + cur.end);
        }
        System.out.println("测试开始");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int res1 = maxCover1(lines);
            int res2 = maxCover2(lines);
            if (res1 != res2) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
