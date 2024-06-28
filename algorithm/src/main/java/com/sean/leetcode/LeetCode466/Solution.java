package com.sean.leetcode.LeetCode466;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-02 16:14
 * @Description: https://leetcode.cn/problems/count-the-repetitions/
 * 466. 统计重复个数
 * 定义 str = [s, n] 表示 str 由 n 个字符串 s 连接构成。
 * 例如，str == ["abc", 3] =="abcabcabc" 。
 * 如果可以从 s2 中删除某些字符使其变为 s1，则称字符串 s1 可以从字符串 s2 获得。
 * 例如，根据定义，s1 = "abc" 可以从 s2 = "abdbec" 获得，仅需要删除加粗且用斜体标识的字符。
 * 现在给你两个字符串 s1 和 s2 和两个整数 n1 和 n2 。
 * 由此构造得到两个字符串，其中 str1 = [s1, n1]、str2 = [s2, n2] 。
 * 请你找出一个最大整数 m ，以满足 str = [str2, m] 可以从 str1 获得。
 */
public class Solution {

    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (n1 == 0) {
            return 0;
        }
        int s1cnt = 0, index = 0, s2cnt = 0;
        //map是我们用来找循环节的变量，它是一个哈希映射
        //我们如何找循环节？假设我们遍历了s1cnt个s1,此时匹配到了第s2cnt个s2中的第index个字符
        //如果我们之前遍历了s1cnt'个s1时，匹配到的是s2cnt'个s2中同样的第index个字符，那么就有循环节了
        //我们用(s1cnt',s2cnt',index)和(s1cnt,s2cnt,index)表示两个包含相同index的匹配结果
        //那么哈希映射中的键就是index，值就是(s1cnt',s2cnt')这个二元组
        //循环节就是：
        // -前s1cnt'个s1包含了s2cnt'个s2
        // -以后每(s1cnt-s1cnt')个s1包含了(s2cnt-s2cnt')个s2
        //那么还会剩下(n1-s1cnt')%(s1cnt-s1cnt')个s1,我们对这些与s2进行暴力匹配
        //注意s2要从第index个字符开始匹配
        Map<Integer, int[]> map = new HashMap<>();
        int[] preLoop = new int[2];
        int[] inLoop = new int[2];
        while (true) {
            //我们多遍历一个s1,看看能不能找到循环节
            s1cnt++;
            for (int i = 0; i < s1.length(); i++) {
                char c = s1.charAt(i);
                if (c == s2.charAt(index)) {
                    index++;
                    if (index == s2.length()) {
                        s2cnt++;
                        index = 0;
                    }
                }
            }
            //还没有找到循环节，所有的s1就用完了
            if (s1cnt == n1) {
                return s2cnt / n2;
            }
            //出现了之前的index，表示找到了循环节
            if (map.containsKey(index)) {
                int[] arr = map.get(index);
                int s1cntPrime = arr[0];
                int s2cntPrime = arr[1];
                //前s1cnt'个s1包含了s2cnt'个s2
                preLoop = new int[]{s1cntPrime, s2cntPrime};
                //以后的每(s1cnt-s1cnt')个s1包含了(s2cnt-s2cnt')个s2
                inLoop = new int[]{s1cnt - s1cntPrime, s2cnt - s2cntPrime};
                break;
            } else {
                map.put(index, new int[]{s1cnt, s2cnt});
            }
        }
        //res存储的是s1包含的s2的数量，考虑的之前的preLoop和inLoop
        int res = preLoop[1] + (n1 - preLoop[0]) / inLoop[0] * inLoop[1];
        //s1的末尾还剩下一些s1，我们暴力进行匹配
        int rest = (n1 - preLoop[0]) % inLoop[0];
        for (int i = 0; i < rest; i++) {
            for (int j = 0; j < s1.length(); j++) {
                char c = s1.charAt(j);
                if (c == s2.charAt(index)) {
                    index++;
                    if (index == s2.length()) {
                        res++;
                        index = 0;
                    }
                }
            }
        }
        //s1包含了res个s2,那么就包含res/n2个s2
        return res / n2;
    }

}
