package com.sean.leetcode.LeetCode3590;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-06-28 18:27
 * @Description https://leetcode.cn/problems/kth-smallest-path-xor-sum
 * 3590. 第 K 小的路径异或和
 * 给定一棵以节点 0 为根的无向树，带有 n 个节点，按 0 到 n - 1 编号。
 * 每个节点 i 有一个整数值 vals[i]，并且它的父节点通过 par[i] 给出。
 * 从根节点 0 到节点 u 的 路径异或和 定义为从根节点到节点 u 的路径上所有节点 i 的 vals[i] 的按位异或，包括节点 u。
 * 给定一个 2 维整数数组 queries，其中 queries[j] = [uj, kj]。
 * 对于每个查询，找到以 uj 为根的子树的所有节点中，第 kj 小 的 不同 路径异或和。
 * 如果子树中 不同 的异或路径和少于 kj，答案为 -1。
 * 返回一个整数数组，其中第 j 个元素是第 j 个查询的答案。
 * 在有根树中，节点 v 的子树包括 v 以及所有经过 v 到达根节点路径上的节点，即 v 及其后代节点。
 * 1 <= n == vals.length <= 5 * 10^4
 * 0 <= vals[i] <= 10^5
 * par.length == n
 * par[0] == -1
 * 对于 [1, n - 1] 中的 i，0 <= par[i] < n
 * 1 <= queries.length <= 5 * 10^4
 * queries[j] == [uj, kj]
 * 0 <= uj < n
 * 1 <= kj <= n
 * 输出保证父数组 par 表示一棵合法的树。
 */
public class Solution {

    // 查询类
    public class Query {
        int l, r, k, index, blk;

        Query(int l, int r, int k, int index, int blockSize) {
            this.l = l;
            this.r = r;
            this.k = k;
            this.index = index;
            this.blk = l / blockSize;  // 所属块号
        }
    }

    public int[] kthSmallest(int[] par, int[] vals, int[][] queries) {
        // 1. 构建树结构
        int n = par.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        for (int i = 1; i < n; i++)
            graph[par[i]].add(i);  // 建立父子关系

        // 2. 非递归DFS计算欧拉序和异或路径
        int[] inTime = new int[n], outTime = new int[n], xorPath = new int[n], euler = new int[n];
        xorPath[0] = vals[0];  // 根节点异或值
        int time = 0;
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{0, 0});  // [节点, 状态]

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            int u = cur[0], state = cur[1];
            if (state == 0) {  // 第一次访问
                inTime[u] = time;
                euler[time++] = xorPath[u];  // 记录欧拉序
                stack.push(new int[]{u, 1});  // 压入回溯状态
                for (int v : graph[u]) {
                    xorPath[v] = xorPath[u] ^ vals[v];  // 计算子节点异或值
                    stack.push(new int[]{v, 0});  // 压入子节点
                }
            } else {
                outTime[u] = time - 1;  // 记录离开时间
            }
        }

        // 3. 离散化处理异或值
        int[] nums = Arrays.copyOf(euler, n);
        Arrays.sort(nums);
        int[] map = new int[nums[n - 1] + 1];  // 值到离散化索引的映射
        map[nums[0]] = 0;
        int m = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                map[nums[i]] = m;
                nums[m++] = nums[i];  // 去重后的有序数组
            }
        }

        int[] A = new int[n];  // 离散化后的欧拉序数组
        for (int i = 0; i < n; i++)
            A[i] = map[euler[i]];

        // 4. 处理查询(Mo's算法)
        int Q = queries.length;
        int blockSize = (int) Math.sqrt(n) + 1;
        Query[] qs = new Query[Q];
        for (int i = 0; i < Q; i++) {
            int u = queries[i][0], k = queries[i][1];
            qs[i] = new Query(inTime[u], outTime[u], k, i, blockSize);  // 将子树查询转化为区间查询
        }

        // 按块排序查询(奇偶块优化)
        Arrays.sort(qs, (a, b) -> {
            if (a.blk != b.blk) return Integer.compare(a.blk, b.blk);
            return (a.blk & 1) == 1 ? Integer.compare(a.r, b.r) : Integer.compare(b.r, a.r);
        });

        // 5. 分块处理值域
        int valBlockSize = (int) Math.sqrt(m) + 1;
        int valBlockCount = (m + valBlockSize - 1) / valBlockSize;
        int[] freq = new int[m];  // 值出现次数
        int[] blkCnt = new int[valBlockCount];  // 块内不同值个数

        int[] ans = new int[Q];
        int L = 0, R = -1;  // 当前区间[L,R]

        for (Query q : qs) {
            // 调整区间边界
            while (L > q.l) add(A[--L], freq, blkCnt, valBlockSize);
            while (R < q.r) add(A[++R], freq, blkCnt, valBlockSize);
            while (L < q.l) remove(A[L++], freq, blkCnt, valBlockSize);
            while (R > q.r) remove(A[R--], freq, blkCnt, valBlockSize);

            // 查询第k小的不同值
            int k = q.k;
            int result = -1;
            for (int b = 0; b < valBlockCount; b++) {
                if (k > blkCnt[b]) {
                    k -= blkCnt[b];
                } else {
                    int start = b * valBlockSize;
                    int end = Math.min(start + valBlockSize, m);
                    for (int i = start; i < end; i++) {
                        if (freq[i] > 0 && --k == 0) {
                            result = nums[i];  // 映射回原始值
                            break;
                        }
                    }
                    break;
                }
            }
            ans[q.index] = result;
        }
        return ans;
    }

    // 辅助方法：增加值的计数
    private void add(int v, int[] freq, int[] blkCnt, int blockSize) {
        if (freq[v]++ == 0) blkCnt[v / blockSize]++;
    }

    // 辅助方法：减少值的计数
    private void remove(int v, int[] freq, int[] blkCnt, int blockSize) {
        if (--freq[v] == 0) blkCnt[v / blockSize]--;
    }

}
