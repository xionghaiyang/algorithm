package com.sean.base.chapter07;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-20 19:20
 * @Description: 基础类型可以使用一个类包装
 */
public class Inner<T> {

    public T value;

    public Inner(T v) {
        value = v;
    }
}
