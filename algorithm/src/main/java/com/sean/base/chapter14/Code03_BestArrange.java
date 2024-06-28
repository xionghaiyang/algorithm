package com.sean.base.chapter14;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-31 20:51
 * @Description: TODO
 */
public class Code03_BestArrange {

    public class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public int bestArrange1(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        return process(programs, 0, 0);
    }

    //还剩下的会议都放在programs里
    //done之前已经安排了多少会议的数量
    //timeLine目前来到的时间点是什么
    //目前来到timeLine的时间点，已经安排了done多的会议，剩下的会议programs可以自由安排
    //返回能安排的最多会议数量
    private int process(Program[] programs, int done, int timeLine) {
        if (programs.length == 0) {
            return done;
        }
        //还剩下会议
        int max = done;
        //当前安排的会议是什么会，每一个都枚举
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine) {
                Program[] next = copyButExcept(programs, i);
                max = Math.max(max, process(next, done + 1, programs[i].end));
            }
        }
        return max;
    }

    private Program[] copyButExcept(Program[] programs, int i) {
        Program[] res = new Program[programs.length - 1];
        int index = 0;
        for (int k = 0; k < programs.length; k++) {
            if (k != i) {
                res[index++] = programs[k];
            }
        }
        return res;
    }

    //会议的开始时间和结束时间，都是数值，不会<0
    public int bestArrange2(Program[] programs) {
        Arrays.sort(programs, (a, b) -> (a.end - b.end));
        int timeLine = 0;
        int res = 0;
        //依次遍历每一个会议，结束时间早的会议先遍历
        for (int i = 0; i < programs.length; i++) {
            if (timeLine <= programs[i].start) {
                res++;
                timeLine = programs[i].end;
            }
        }
        return res;
    }

    public Program[] generatePrograms(int programSize, int timeMax) {
        Program[] res = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < res.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                res[i] = new Program(r1, r2 + 1);
            } else {
                res[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int testTimes = 1000000;
        System.out.println("test begin!");
        boolean succeed = true;
        Code03_BestArrange solution = new Code03_BestArrange();
        for (int i = 0; i < testTimes; i++) {
            Program[] programs = solution.generatePrograms(programSize, timeMax);
            if (solution.bestArrange1(programs) != solution.bestArrange2(programs)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
        System.out.println("test finish!");
    }

}
