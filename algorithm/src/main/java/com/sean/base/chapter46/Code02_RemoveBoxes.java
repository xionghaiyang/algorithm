package com.sean.base.chapter46;

import com.sun.org.apache.bcel.internal.generic.LADD;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-29 15:36
 * @Description: TODO
 */
public class Code02_RemoveBoxes {

    //arr[L..R]消除，而且前面跟着K个arr[L]这个数
    //返回：所有东西都消掉，最大得分
    public int func1(int[] arr, int L, int R, int K) {
        if (L > R) {
            return 0;
        }
        int ans = func1(arr, L + 1, R, 0) + (K + 1) * (K + 1);
        //前面的K个X,和arr[L]数，合在一起了，现在有K+1个arr[L]位置的数
        for (int i = L + 1; i <= R; i++) {
            if (arr[i] == arr[L]) {
                ans = Math.max(ans, func1(arr, L + 1, i - 1, 0) + func1(arr, i, R, K + 1));
            }
        }
        return ans;
    }

    public int removeBoxes1(int[] boxes) {
        int N = boxes.length;
        int[][][] dp = new int[N][N][N];
        int ans = process1(boxes, 0, N - 1, 0, dp);
        return ans;
    }

    private int process1(int[] boxes, int L, int R, int K, int[][][] dp) {
        if (L > R) {
            return 0;
        }
        if (dp[L][R][K] > 0) {
            return dp[L][R][K];
        }
        int ans = process1(boxes, L + 1, R, 0, dp) + (K + 1) * (K + 1);
        for (int i = L + 1; i <= R; i++) {
            if (boxes[i] == boxes[L]) {
                ans = Math.max(ans, process1(boxes, L + 1, i - 1, 0, dp) + process1(boxes, i, R, K + 1, dp));
            }
        }
        dp[L][R][K] = ans;
        return ans;
    }

    public int removeBoxes2(int[] boxes) {
        int N = boxes.length;
        int[][][] dp = new int[N][N][N];
        int ans = process2(boxes, 0, N - 1, 0, dp);
        return ans;
    }

    private int process2(int[] boxes, int L, int R, int K, int[][][] dp) {
        if (L > R) {
            return 0;
        }
        if (dp[L][R][K] > 0) {
            return dp[L][R][K];
        }
        int last = L;
        while (last + 1 <= R && boxes[last + 1] == boxes[L]) {
            last++;
        }
        int pre = K + last - L;
        int ans = (pre + 1) * (pre + 1) + process2(boxes, last + 1, R, 0, dp);
        for (int i = last + 2; i <= R; i++) {
            if (boxes[i] == boxes[L] && boxes[i - 1] != boxes[L]) {
                ans = Math.max(ans, process2(boxes, last + 1, i - 1, 0, dp) + process2(boxes, i, R, pre + 1, dp));
            }
        }
        dp[L][R][K] = ans;
        return ans;
    }

}
