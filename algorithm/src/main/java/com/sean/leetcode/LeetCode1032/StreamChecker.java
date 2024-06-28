package com.sean.leetcode.LeetCode1032;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-24 08:15
 * @Description: https://leetcode.cn/problems/stream-of-characters/
 * 1032. 字符流
 * 设计一个算法：接收一个字符流，并检查这些字符的后缀是否是字符串数组 words 中的一个字符串。
 * 例如，words = ["abc", "xyz"] 且字符流中逐个依次加入 4 个字符 'a'、'x'、'y' 和 'z' ，
 * 你所设计的算法应当可以检测到 "axyz" 的后缀 "xyz" 与 words 中的字符串 "xyz" 匹配。
 * 按下述要求实现 StreamChecker 类：
 * StreamChecker(String[] words) ：构造函数，用字符串数组 words 初始化数据结构。
 * boolean query(char letter)：从字符流中接收一个新字符，
 * 如果字符流中的任一非空后缀能匹配 words 中的某一字符串，返回 true ；否则，返回 false。
 * 输入：
 * ["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
 * [[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
 * 输出：
 * [null, false, false, false, true, false, true, false, false, false, false, false, true]
 */
public class StreamChecker {

    class Trie {
        boolean start = false;
        Map<Character, Trie> children;

        Trie() {
            children = new HashMap<>();
        }
    }

    Trie parent;
    List<Character> list = new ArrayList<>();

    public StreamChecker(String[] words) {
        parent = new Trie();
        Trie cur;
        for (String word : words) {
            int n = word.length();
            cur = parent;
            for (int i = n - 1; i >= 0; i--) {
                char c = word.charAt(i);
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new Trie());
                }
                cur = cur.children.get(c);
                if (i == 0) {
                    cur.start = true;
                }
            }
        }
    }

    public boolean query(char letter) {
        list.add(letter);
        Trie cur = parent;
        for (int i = list.size() - 1; i >= 0; i--) {
            Character c = list.get(i);
            if (cur.children.containsKey(c)) {
                cur = cur.children.get(c);
                if (cur.start) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

}


