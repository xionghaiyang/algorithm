package com.sean.leetcode.LeetCode3074;

/**
 * @Author xionghaiyang
 * @Date 2025-12-24 10:37
 * @Description https://leetcode.cn/problems/apple-redistribution-into-boxes
 * 3074. 重新分装苹果
 * 给你一个长度为 n 的数组 apple 和另一个长度为 m 的数组 capacity 。
 * 一共有 n 个包裹，其中第 i 个包裹中装着 apple[i] 个苹果。
 * 同时，还有 m 个箱子，第 i 个箱子的容量为 capacity[i] 个苹果。
 * 请你选择一些箱子来将这 n 个包裹中的苹果重新分装到箱子中，返回你需要选择的箱子的 最小 数量。
 * 注意，同一个包裹中的苹果可以分装到不同的箱子中。
 * 1 <= n == apple.length <= 50
 * 1 <= m == capacity.length <= 50
 * 1 <= apple[i], capacity[i] <= 50
 * 输入数据保证可以将包裹中的苹果重新分装到箱子中。
 */
public class Solution {

    public int minimumBoxes(int[] apple, int[] capacity) {
        int sum = 0;
        for (int x : apple) {
            sum += x;
        }
        int size = 0;
        for (int y : capacity) {
            size = Math.max(size, y);
        }
        int[] cnt = new int[size + 1];
        for (int y : capacity) {
            cnt[y]++;
        }
        int res = 0;
        for (int i = size; i >= 0; i--) {
            int c = cnt[i];
            if (c == 0) {
                continue;
            }
            int limit = i * c;
            if (limit < sum) {
                res += c;
                sum -= limit;
            } else {
                res += (sum - 1) / i + 1;
                break;
            }
        }
        return res;
    }

}
