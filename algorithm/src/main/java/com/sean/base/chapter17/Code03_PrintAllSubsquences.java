package com.sean.base.chapter17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-02 20:46
 * @Description: TODO
 */
public class Code03_PrintAllSubsquences {

    public List<String> subs(String s) {
        char[] str = s.toCharArray();
        List<String> res = new ArrayList<>();
        process1(str, 0, res, "");
        return res;
    }

    //str固定参数
    //来到了str[index]字符,index是位置
    //str[0...index-1]已经走过了！之前的决定，都在path上
    //之前的决定不能改变了，就是path
    //str[index...]还能决定，之前已经确定，而后面还能自由选择的话
    //把所有生成的子序列，放入到res里去
    private void process1(char[] str, int index, List<String> res, String path) {
        if (index == str.length) {
            res.add(path);
            return;
        }
        process1(str, index + 1, res, path);
        process1(str, index + 1, res, path + str[index]);
    }

    public List<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        HashSet<String> set = new HashSet<>();
        process2(str, 0, set, "");
        List<String> res = new ArrayList<>();
        for (String cur : set) {
            res.add(cur);
        }
        return res;
    }

    private void process2(char[] str, int index, HashSet<String> set, String path) {
        if (index == str.length) {
            set.add(path);
            return;
        }
        process2(str, index + 1, set, path);
        process2(str, index + 1, set, path + str[index]);
    }

    public static void main(String[] args) {
        String test = "acccc";
        Code03_PrintAllSubsquences solution = new Code03_PrintAllSubsquences();
        List<String> res1 = solution.subs(test);
        for (String s : res1) {
            System.out.println(s);
        }
        System.out.println("==========================");
        List<String> res2 = solution.subsNoRepeat(test);
        for (String s : res2) {
            System.out.println(s);
        }
    }

}
