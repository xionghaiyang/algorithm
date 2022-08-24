package com.sean.lintcode.LintCode2188;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-08-23 11:32
 * @Description: https://www.lintcode.com/problem/2188/?showListFe=true&page=1&pageSize=50
 * 2188 · 获取索引为 1 的元素
 * 请编写代码，获取传入字符串中索引为 1 的元素。
 * 在本题的 Solution 类中有个 getIndexOne 方法，该方法有一个 String 类型的参数 str，str 代表传入的字符串。
 * 该方法要获取 str 中索引为 1 的元素，并且返回该元素，返回值为 char 类型。
 */
public class Solution {

    public char getIndexOne(String str) {
        return str.charAt(1);
    }

}
