package com.sean.base.chapter19;

import java.util.SplittableRandom;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-03 22:00
 * @Description: TODO
 */
public class Code02_ConvertToLetterString {

    //str只含有数字字符0-9
    //返回有多少种转化方案
    public int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    //str[0...i-1]转化无需过问
    //str[i...]去转化，返回有多少种转化方法
    private int process(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        //i没到最后，说明有字符
        if (str[i] == '0') {//之前的决定有问题
            return 0;
        }
        //str[i]!='0'
        //可能性一，i单转
        int ways = process(str, i + 1);
        if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
            ways += process(str, i + 2);
        }
        return ways;
    }

    //从右往左的动态规划
    //就是上面方法的动态规划版本
    //dp[i]表示:str[i...]有多少种转化方式
    public int dp1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (str[i] != '0') {
                int ways = dp[i + 1];
                if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
                    ways += dp[i + 2];
                }
                dp[i] = ways;
            }
        }
        return dp[0];
    }

    //从左到右的动态规划
    public int dp2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        if (str[0] == '0') {
            return 0;
        }
        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            if (str[i] == '0') {
                //如果此时str[i]=='0',那么他一定要拉前一个字符(i-1的字符)一起拼的
                //那么就要求前一个字符不能也是'0'，否则拼不了
                //前一个字符不是'0'就够了吗？不够，还得要求拼完了要么是10，要么是20，如果更大的话，拼不了
                //如果str[0..i-2]都不存在分解方案，那么i和i-1拼成了也不行，因为之前的搞定不了
                if (str[i - 1] == '0' || str[i - 1] > '2' || (i - 2 >= 0 && dp[i - 2] == 0)) {
                    return 0;
                } else {
                    dp[i] = i - 2 >= 0 ? dp[i - 2] : 1;
                }
            } else {
                dp[i] = dp[i - 1];
                if (str[i - 1] != '0' && (str[i - 1] - '0') * 10 + str[i] - '0' <= 26) {
                    dp[i] += i - 2 >= 0 ? dp[i - 2] : 1;
                }
            }
        }
        return dp[N - 1];
    }

    private String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        Code02_ConvertToLetterString solution = new Code02_ConvertToLetterString();
        int N = 30;
        int testTime = 1000000;
        System.out.println("测试开始!");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = solution.randomString(len);
            int res0 = solution.number(s);
            int res1 = solution.dp1(s);
            int res2 = solution.dp2(s);
            if (res0 != res1 || res0 != res2) {
                System.out.println(s);
                System.out.println(res0);
                System.out.println(res1);
                System.out.println(res2);
                break;
            }
        }
        System.out.println("测试结束!");
    }

}
