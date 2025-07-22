package com.sean.leetcode.LeetCode149;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author xionghaiyang
 * @Date 2025-07-22 17:56
 * @Description https://leetcode.cn/problems/max-points-on-a-line
 * 149. 直线上最多的点数
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。
 * 求最多有多少个点在同一条直线上。
 * 1 <= points.length <= 300
 * points[i].length == 2
 * -10^4 <= xi, yi <= 10^4
 * points 中的所有点 互不相同
 */
public class Solution {

    public class Info {
        private int x;
        private int y;

        public Info(int x, int y) {
            if (x == 0) {
                y = 1;
            } else if (y == 0) {
                x = 1;
            } else {
                if (x < 0) {
                    x = -x;
                    y = -y;
                }
                int gcdXY = gcd(Math.abs(x), Math.abs(y));
                x /= gcdXY;
                y /= gcdXY;
            }
            this.x = x;
            this.y = y;
        }

        private int gcd(int a, int b) {
            while (b != 0) {
                int temp = a % b;
                a = b;
                b = temp;
            }
            return a;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return x == info.x &&
                    y == info.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public int maxPoints(int[][] points) {
        int n = points.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            Map<Info, Integer> map = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                Info key = new Info(x, y);
                map.compute(key, (k, v) -> v == null ? 1 : v + 1);
                res = Math.max(res, map.get(key));
            }
        }
        return res + 1;
    }

}
