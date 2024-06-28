package com.sean.leetcode.LeetCode826;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2024-05-17 09:47
 * @Description https://leetcode.cn/problems/most-profit-assigning-work/
 * 826. 安排工作以达到最大收益
 * 你有 n 个工作和 m 个工人。
 * 给定三个数组： difficulty, profit 和 worker ，其中:
 * difficulty[i] 表示第 i 个工作的难度，profit[i] 表示第 i 个工作的收益。
 * worker[i] 是第 i 个工人的能力，即该工人只能完成难度小于等于 worker[i] 的工作。
 * 每个工人 最多 只能安排 一个 工作，但是一个工作可以 完成多次 。
 * 举个例子，如果 3 个工人都尝试完成一份报酬为 $1 的同样工作，那么总收益为 $3 。
 * 如果一个工人不能完成任何工作，他的收益为 $0 。
 * 返回 在把工人分配到工作岗位后，我们所能获得的最大利润 。
 */
public class Solution {

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        List<int[]> jobs = new ArrayList<>();
        int n = difficulty.length;
        for (int i = 0; i < n; i++) {
            jobs.add(new int[]{difficulty[i], profit[i]});
        }
        Collections.sort(jobs, (a, b) -> a[0] - b[0]);
        Arrays.sort(worker);
        int res = 0, i = 0, best = 0;
        for (int w : worker) {
            while (i < n && w >= jobs.get(i)[0]) {
                best = Math.max(best, jobs.get(i)[1]);
                i++;
            }
            res += best;
        }
        return res;
    }

}
