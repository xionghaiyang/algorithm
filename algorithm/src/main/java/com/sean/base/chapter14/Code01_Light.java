package com.sean.base.chapter14;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-31 19:57
 * @Description: TODO
 */
public class Code01_Light {

    public int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    //str[index...]位置，自由选择放灯还是放灯
    //str[0...index-1]位置呢？已经做完决定了，哪些放了灯的位置，存在lights里
    //要求选出能照亮所有的方案，并且在这些有效的方案中，返回最少需要几个灯
    private int process(char[] str, int index, Set<Integer> lights) {
        if (index == str.length) {//结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') {//当前位置是点的话
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {//str还没结束
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }

    public int minLight2(String road) {
        char[] str = road.toCharArray();
        int i = 0;
        int light = 0;
        while (i < str.length) {
            if (str[i] == 'X') {
                i++;
            } else {
                light++;
                if (i + 1 == str.length) {
                    break;
                } else {//有i位置 i+1 X .
                    if (str[i + 1] == 'X') {
                        i = i + 2;
                    } else {
                        i = i + 3;
                    }
                }
            }
        }
        return light;
    }

    //更简洁的写法
    //两个X之间，数一下.的数量，然后除以3，向上取整
    //把灯数累加
    public int minLight3(String road) {
        char[] str = road.toCharArray();
        int cur = 0;
        int light = 0;
        for (char c : str) {
            if (c == 'X') {
                light += (cur + 2) / 3;
                cur = 0;
            } else {
                cur++;
            }
        }
        light += (cur + 2) / 3;
        return light;
    }

    public String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        Code01_Light solution = new Code01_Light();
        System.out.println("test begin!");
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            String test = solution.randomString(len);
            int res1 = solution.minLight1(test);
            int res2 = solution.minLight2(test);
            int res3 = solution.minLight3(test);
            if (res1 != res2 || res1 != res3) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
        System.out.println("test finish!");
    }

}
