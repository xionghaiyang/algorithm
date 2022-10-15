package com.sean.lintcode.LintCode2251;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-26 08:54
 * @Description: https://www.lintcode.com/problem/2251/?showListFe=true&page=1&pageSize=50
 * 2251 · 打印三个对象的名字和编号信息
 * 描述
 * 类 Father 中，有静态代码块，在类加载时会输出语句 Father's message:，私有字符串变量 name，初始化为 Liu Bei，私有整型变量 id，初始化为 1，
 * 还有一个无返回值的方法 introduction，打印对应的 name 和 id。
 * 类 Son 继承了 Father 类， 其中包含静态代码块，在类加载时会输出语句 Son's message:。
 * 类 Grandson 继承了 Father 类，包含静态代码块，在类加载时会输出语句 Grandson's message:。
 */
public class Main {

    public static void main(String[] args) {
        Father father = new Father();
        father.introduction();

        Son son = new Son("Liu Shan", 2);
        son.introduction();

        Grandson grandson = new Grandson("Liu Chen", 3);
        grandson.introduction();
    }

}
