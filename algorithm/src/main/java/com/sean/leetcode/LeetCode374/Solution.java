package com.sean.leetcode.LeetCode374;

/**
 * @Author xionghaiyang
 * @Date 2025-08-13 17:10
 * @Description https://leetcode.cn/problems/guess-number-higher-or-lower
 * 374. 猜数字大小
 * 我们正在玩猜数字游戏。
 * 猜数字游戏的规则如下：
 * 我会从 1 到 n 随机选择一个数字。
 * 请你猜选出的是哪个数字。
 * 如果你猜错了，我会告诉你，我选出的数字比你猜测的数字大了还是小了
 * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有三种可能的情况：
 * -1：你猜的数字比我选出的数字大 （即 num > pick）。
 * 1：你猜的数字比我选出的数字小 （即 num < pick）。
 * 0：你猜的数字与我选出的数字相等。（即 num == pick）。
 * 返回我选出的数字。
 */
public class Solution extends GuessGame {

    public int guessNumber(int n) {
        int res = -1, left = 1, right = n;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int rs = guess(mid);
            if (rs == 0) {
                res = mid;
                break;
            } else if (rs == -1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

}

class GuessGame {

    private int pick;

    public GuessGame() {
    }

    public GuessGame(int pick) {
        this.pick = pick;
    }

    public int guess(int num) {
        if (num == pick) {
            return 0;
        } else if (num > pick) {
            return -1;
        } else {
            return 1;
        }
    }

}
