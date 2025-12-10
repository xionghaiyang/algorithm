package com.sean.course03.lesson11;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-12-10 19:45
 * @Description 设计一种结构，可以向此结构中添加和删除元素，并可等可能返回结构中的元素
 */
public class Code01_RandomPool {

    public class Poll<T> {
        private Map<T, Integer> keyIndexMap;
        private Map<Integer, T> indexKeyMap;
        private int size;

        public Poll() {
            keyIndexMap = new HashMap<>();
            indexKeyMap = new HashMap<>();
            size = 0;
        }

        public void insert(T key) {
            if (!keyIndexMap.containsKey(key)) {
                keyIndexMap.put(key, size);
                indexKeyMap.put(size++, key);
            }
        }

        public void delete(T key) {
            if (keyIndexMap.containsKey(key)) {
                int deleteIndex = keyIndexMap.get(key);
                int lastIndex = --size;
                T lastKey = indexKeyMap.get(lastIndex);
                keyIndexMap.put(lastKey, deleteIndex);
                indexKeyMap.put(deleteIndex, lastKey);
                keyIndexMap.remove(key);
                indexKeyMap.remove(lastIndex);
            }
        }

        public T getRandom() {
            if (size == 0) {
                return null;
            }
            int randomIndex = (int) (size * Math.random());
            return indexKeyMap.get(randomIndex);
        }
    }

    public static void main(String[] args) {
        Poll<String> pool = new Code01_RandomPool().new Poll<>();
        pool.insert("zhang");
        pool.insert("san");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
    }

}
