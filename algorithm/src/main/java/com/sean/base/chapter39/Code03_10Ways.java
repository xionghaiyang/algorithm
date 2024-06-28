package com.sean.base.chapter39;

import java.util.LinkedList;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-19 19:04
 * @Description: TODO
 */
public class Code03_10Ways {

    public long ways1(int N) {
        int zero = N;
        int one = N;
        LinkedList<Integer> path = new LinkedList<>();
        LinkedList<LinkedList<Integer>> ans = new LinkedList<>();
        process(zero, one, path, ans);
        long count = 0;
        for (LinkedList<Integer> cur : ans) {
            int status = 0;
            for (int num : cur) {
                if (num == 0) {
                    status++;
                } else {
                    status--;
                }
                if (status < 0) {
                    break;
                }
            }
            if (status == 0) {
                count++;
            }
        }
        return count;
    }

    private void process(int zero, int one, LinkedList<Integer> path, LinkedList<LinkedList<Integer>> ans) {
        if (zero == 0 && one == 0) {
            LinkedList<Integer> cur = new LinkedList<>();
            for (int num : path) {
                cur.add(num);
            }
            ans.add(cur);
        } else {
            if (zero == 0) {
                path.addLast(1);
                process(zero, one - 1, path, ans);
                path.removeLast();
            } else if (one == 0) {
                path.addLast(0);
                process(zero - 1, one, path, ans);
                path.removeLast();
            } else {
                path.addLast(1);
                process(zero, one - 1, path, ans);
                path.removeLast();
                path.addLast(0);
                process(zero - 1, one, path, ans);
                path.removeLast();
            }
        }
    }

    public long ways2(int N) {
        if (N < 0) {
            return 0;
        }
        if (N < 2) {
            return 1;
        }
        long a = 1;
        long b = 1;
        long limit = N << 1;
        for (long i = 1; i <= limit; i++) {
            if (i <= N) {
                a *= i;
            } else {
                b *= i;
            }
        }
        return (b / a) / (N + 1);
    }

    public static void main(String[] args) {
        Code03_10Ways solution = new Code03_10Ways();
        System.out.println("test begin!");
        for (int i = 0; i < 10; i++) {
            long ans1 = solution.ways1(i);
            long ans2 = solution.ways2(i);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish!");
    }

}
