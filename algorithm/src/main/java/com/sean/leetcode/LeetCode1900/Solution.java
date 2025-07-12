package com.sean.leetcode.LeetCode1900;

/**
 * @Author xionghaiyang
 * @Date 2025-07-12 07:02
 * @Description https://leetcode.cn/problems/the-earliest-and-latest-rounds-where-players-compete
 * 1900. 最佳运动员的比拼回合
 * n 名运动员参与一场锦标赛，所有运动员站成一排，并根据 最开始的 站位从 1 到 n 编号（运动员 1 是这一排中的第一个运动员，运动员 2 是第二个运动员，依此类推）。
 * 锦标赛由多个回合组成（从回合 1 开始）。
 * 每一回合中，这一排从前往后数的第 i 名运动员需要与从后往前数的第 i 名运动员比拼，获胜者将会进入下一回合。
 * 如果当前回合中运动员数目为奇数，那么中间那位运动员将轮空晋级下一回合。
 * 例如，当前回合中，运动员 1, 2, 4, 6, 7 站成一排
 * 运动员 1 需要和运动员 7 比拼
 * 运动员 2 需要和运动员 6 比拼
 * 运动员 4 轮空晋级下一回合
 * 每回合结束后，获胜者将会基于最开始分配给他们的原始顺序（升序）重新排成一排。
 * 编号为 firstPlayer 和 secondPlayer 的运动员是本场锦标赛中的最佳运动员。
 * 在他们开始比拼之前，完全可以战胜任何其他运动员。
 * 而任意两个其他运动员进行比拼时，其中任意一个都有获胜的可能，因此你可以 裁定 谁是这一回合的获胜者。
 * 给你三个整数 n、firstPlayer 和 secondPlayer 。
 * 返回一个由两个值组成的整数数组，分别表示两位最佳运动员在本场锦标赛中比拼的 最早 回合数和 最晚 回合数。
 * 2 <= n <= 28
 * 1 <= firstPlayer < secondPlayer <= n
 */
public class Solution {

    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        int[][][][] memo = new int[n + 1][n + 1][n + 1][2];
        return dfs(n, firstPlayer, secondPlayer, memo);
    }

    private int[] dfs(int n, int first, int second, int[][][][] memo) {
        //AB相遇
        if (first + second == n + 1) {
            return new int[]{1, 1};
        }
        //保证A左边人数比B右边人数少
        //注:题目已经保证first < second
        if (first + second > n + 1) {
            int tmp = first;
            first = n + 1 - second;
            second = n + 1 - tmp;
        }
        int[] mem = memo[n][first][second];
        if (mem[0] > 0) {
            return mem;
        }
        //下一回合人数
        int m = (n + 1) / 2;
        //AB之间保留[minMid,maxMid)个人
        int minMid = second <= m ? 0 : second - n / 2 - 1;
        int maxMid = second <= m ? second - first : m - first;
        int earliest = Integer.MAX_VALUE;
        int latest = 0;
        //枚举A左侧保留left个人
        for (int left = 0; left < first; left++) {
            //枚举AB之间保留mid个人
            for (int mid = minMid; mid < maxMid; mid++) {
                //无需枚举B右侧保留多少人，因为剩下的m-2-left-mid个人都在B右侧
                int[] res = dfs(m, left + 1, left + mid + 2, memo);
                earliest = Math.min(earliest, res[0]);
                latest = Math.max(latest, res[1]);
            }
        }
        //加上当前回合数
        mem[0] = earliest + 1;
        mem[1] = latest + 1;
        return mem;
    }

    public int[] earliestAndLatest1(int n, int firstPlayer, int secondPlayer) {
        //AB一开始就相遇
        if (firstPlayer + secondPlayer == n + 1) {
            return new int[]{1, 1};
        }
        //保证A左边人数比B右边人数少
        //注:题目已经保证 firstPlayer < secondPlayer
        if (firstPlayer + secondPlayer > n + 1) {
            int tmp = firstPlayer;
            firstPlayer = n + 1 - secondPlayer;
            secondPlayer = n + 1 - tmp;
        }
        //计算最早回合数
        int earliest = calcEarliestRounds(n, firstPlayer, secondPlayer);
        //计算最晚回合数
        int latest = Math.min(32 - Integer.numberOfLeadingZeros(n - 1), n + 1 - secondPlayer);
        return new int[]{earliest, latest};
    }

    private int calcEarliestRounds(int n, int first, int second) {
        //初始回合
        int res = 1;
        //情况5：AB太靠左了
        if (first + second <= (n + 1) / 2) {
            while (first + second <= (n + 1) / 2) {
                res++;
                n = (n + 1) / 2;
            }
            //情况5a:AB不相邻
            //在上面循环的最后一回合，总是可以把局面调整为某些情况，使AB下回合就能相遇
            if (second - first > 1) {
                return res + 1;
            }
            //情况5b:AB相邻
            //上面循环结束后，转化为情况1
        }
        //情况1：AB相邻（由于AB不相遇，B不可能在中轴线右侧。注意我们已保证A左边人数比B右边人数少）
        if (second - first == 1) {
            //先过一回合
            res++;
            n = (n + 1) / 2;
            //在AB相邻的情况下，当且仅当n使偶数的时候相遇
            while (n % 2 > 0) {
                res++;
                n = (n + 1) / 2;
            }
            return res;
        }
        //情况2：B在中轴线或中轴线左侧
        if (second <= (n + 1) / 2) {
            //可以让AB左边人数一样多，下回合就能相遇
            return res + 1;
        }
        //情况3:AB之间恰有一个人
        if (second - first == 2) {
            //下回会AB必定相邻，变成情况1
            res++;
            n = (n + 1) / 2;
            while (n % 2 > 0) {
                res++;
                n = (n + 1) / 2;
            }
            return res;
        }
        //情况4c:A左侧有奇数个人，且B与A'相邻
        if (first % 2 == 0 && first + second == n) {
            //一回合后转化为4a
            res++;
        }
        //情况4a:A左侧有偶数个人
        //情况4b:左侧有奇数个人，且B与A'不相邻
        //下回合就能相遇
        return res + 1;
    }

    private int calcEarliestRounds1(int n, int first, int second) {
        int res = 1;
        if (first + second <= (n + 1) / 2) {
            //计算满足first + second > ceil(n/2^(k+1))的最小k
            int k = 32 - Integer.numberOfLeadingZeros((n - 1) / (first + second - 1)) - 1;
            //n = ceil(n/2^k)
            n = ((n - 1) >> k) + 1;
            res += k;
            if (second - first > 1) {
                return res + 1;
            }
        }
        if (second - first == 1 || second > (n + 1) / 2 && second - first == 2) {
            //先把n变成ceil(n/2)，然后计算需要多少次ceil(n/2)的操作才能把n变成偶数
            //这里把(n+1)/2和n-1合并，得到（n+1）/2-1=(n-1)/2
            return res + 1 + Integer.numberOfTrailingZeros((n - 1) / 2);
        }
        if (second > (n + 1) / 2 && first % 2 == 0 && first + second == n) {
            res++;
        }
        return res + 1;
    }

}
