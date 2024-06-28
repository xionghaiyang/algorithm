package com.sean.lintcode.LintCode2391;

import java.util.HashSet;

/**
 * @Author xionghaiyang
 * @Date 2022-09-04 12:30
 * @Description https://www.lintcode.com/problem/2391/?showListFe=true&page=1&pageSize=50
 * 2391 · 创建 HashSet 集合并添加数据
 * 描述
 * 请编写代码，创建一个 HashSet 集合，向集合中添加四条数据并且返回该集合。
 * HashSet 不允许存放重复值。
 * HashSet 是无序的，即不会记录插入的顺序。
 * 在本题的 Solution 类中有个 createHashSet 方法，该方法有四个 String 类型的参数 str1、str2，str3，str4，
 * 它们分别代表向集合中添加的四条数据。该方法要创建一个 HashSet 集合，向集合中添加四条数据并且返回这个集合。返回值为 HashSet 类型。
 */
public class Solution {

    public HashSet<String> createHashSet(String str1, String str2, String str3, String str4) {
        HashSet<String> res = new HashSet<>();
        res.add(str1);
        res.add(str2);
        res.add(str3);
        res.add(str4);
        return res;
    }

}
