package com.sean.course03.lesson05;

/**
 * @Author xionghaiyang
 * @Date 2025-04-05 11:17
 * @Description Manacher算法
 * 假设字符串str长度为N，想返回最长回文子串的长度
 * 时间复杂度O(N)
 */
public class Code01_Manacher {

    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        //"12132" -> "#1#2#1#3#2#"
        char[] str = manacherString(s);
        //回文半径大小
        int[] pArr = new int[str.length];
        //中心点
        int C = -1;
        //最右的扩成功位置的下一个位置
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {
            //R第一个违规的位置，i>=R
            //i位置扩出来的答案，i位置扩的区域，至少是多大
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    private static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

    private static String getRandomString(int possibilities, int size) {
        char[] res = new char[(int) (size * Math.random()) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (char) ((int) (possibilities * Math.random()) + 'a');
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int testTimes = 5000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("出错了!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
