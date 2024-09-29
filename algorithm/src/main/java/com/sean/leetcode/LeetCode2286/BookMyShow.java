package com.sean.leetcode.LeetCode2286;

/**
 * @Author xionghaiyang
 * @Date 2024-09-29 20:55
 * @Description https://leetcode.cn/problems/booking-concert-tickets-in-groups
 * 2286. 以组为单位订音乐会的门票
 * 一个音乐会总共有 n 排座位，编号从 0 到 n - 1 ，每一排有 m 个座椅，编号为 0 到 m - 1 。
 * 你需要设计一个买票系统，针对以下情况进行座位安排：
 * 同一组的 k 位观众坐在 同一排座位，且座位连续 。
 * k 位观众中 每一位 都有座位坐，但他们 不一定 坐在一起。
 * 由于观众非常挑剔，所以：
 * 只有当一个组里所有成员座位的排数都 小于等于 maxRow ，这个组才能订座位。
 * 每一组的 maxRow 可能 不同 。
 * 如果有多排座位可以选择，优先选择 最小 的排数。
 * 如果同一排中有多个座位可以坐，优先选择号码 最小 的。
 * 请你实现 BookMyShow 类：
 * BookMyShow(int n, int m) ，初始化对象，n 是排数，m 是每一排的座位数。
 * int[] gather(int k, int maxRow) 返回长度为 2 的数组，表示 k 个成员中 第一个座位 的排数和座位编号，这 k 位成员必须坐在 同一排座位，且座位连续 。
 * 换言之，返回最小可能的 r 和 c 满足第 r 排中 [c, c + k - 1] 的座位都是空的，且 r <= maxRow 。
 * 如果 无法 安排座位，返回 [] 。
 * boolean scatter(int k, int maxRow) 如果组里所有 k 个成员 不一定 要坐在一起的前提下，都能在第 0 排到第 maxRow 排之间找到座位，那么请返回 true 。
 * 这种情况下，每个成员都优先找排数 最小 ，然后是座位编号最小的座位。
 * 如果不能安排所有 k 个成员的座位，请返回 false 。
 */
public class BookMyShow {

    private int n;
    private int m;
    private int[] minTree;
    private long[] sumTree;

    /**
     * @param n 是排数
     * @param m 是每一排的座位数
     */
    public BookMyShow(int n, int m) {
        this.n = n;
        this.m = m;
        this.minTree = new int[4 * n];
        this.sumTree = new long[4 * n];
    }

    /**
     * @param k
     * @param maxRow
     * @return 返回长度为 2 的数组，表示 k 个成员中 第一个座位 的排数和座位编号，这 k 位成员必须坐在 同一排座位，且座位连续 。
     * 换言之，返回最小可能的 r 和 c 满足第 r 排中 [c, c + k - 1] 的座位都是空的，且 r <= maxRow 。
     * 如果 无法 安排座位，返回 [] 。
     */
    public int[] gather(int k, int maxRow) {
        int i = queryMinRow(1, 0, n - 1, m - k);
        if (i > maxRow) {
            return new int[0];
        }
        long used = querySum(1, 0, n - 1, i, i);
        modify(1, 0, n - 1, i, (int) (used + k));
        return new int[]{i, (int) used};
    }

    private int queryMinRow(int i, int left, int right, int val) {
        if (left == right) {
            if (minTree[i] > val) {
                return n;
            }
            return left;
        }
        int mid = (left + right) / 2;
        if (minTree[i * 2] <= val) {
            return queryMinRow(i * 2, left, mid, val);
        } else {
            return queryMinRow(i * 2 + 1, mid + 1, right, val);
        }
    }

    private long querySum(int i, int left, int right, int left2, int right2) {
        if (left2 <= left && right <= right2) {
            return sumTree[i];
        }
        int mid = (left + right) / 2;
        long sum = 0;
        if (mid >= left2) {
            sum += querySum(i * 2, left, mid, left2, right2);
        }
        if (mid + 1 <= right2) {
            sum += querySum(i * 2 + 1, mid + 1, right, left2, right2);
        }
        return sum;
    }

    private void modify(int i, int left, int right, int index, int val) {
        if (left == right) {
            minTree[i] = val;
            sumTree[i] = val;
            return;
        }
        int mid = (left + right) / 2;
        if (index <= mid) {
            modify(i * 2, left, mid, index, val);
        } else {
            modify(i * 2 + 1, mid + 1, right, index, val);
        }
        minTree[i] = Math.min(minTree[i * 2], minTree[i * 2 + 1]);
        sumTree[i] = sumTree[i * 2] + sumTree[i * 2 + 1];
    }

    /**
     * @param k
     * @param maxRow
     * @return 如果组里所有 k 个成员 不一定 要坐在一起的前提下，都能在第 0 排到第 maxRow 排之间找到座位，那么请返回 true 。
     * 这种情况下，每个成员都优先找排数 最小 ，然后是座位编号最小的座位。
     * 如果不能安排所有 k 个成员的座位，请返回 false 。
     */
    public boolean scatter(int k, int maxRow) {
        long usedTotal = querySum(1, 0, n - 1, 0, maxRow);
        if ((maxRow + 1L) * m - usedTotal < k) {
            return false;
        }
        int i = queryMinRow(1, 0, n - 1, m - 1);
        while (true) {
            long used = querySum(1, 0, n - 1, i, i);
            if (m - used >= k) {
                modify(1, 0, n - 1, i, (int) (used + k));
                break;
            }
            k -= m - used;
            modify(1, 0, n - 1, i, m);
            i++;
        }
        return true;
    }

}
