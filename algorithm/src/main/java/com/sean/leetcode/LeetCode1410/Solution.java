package com.sean.leetcode.LeetCode1410;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-11-23 20:13
 * @Description: https://leetcode.cn/problems/html-entity-parser/description/
 * 1410. HTML 实体解析器
 * 「HTML 实体解析器」 是一种特殊的解析器，它将 HTML 代码作为输入，并用字符本身替换掉所有这些特殊的字符实体。
 * HTML 里这些特殊字符和它们对应的字符实体包括：
 * 双引号：字符实体为 &quot; ，对应的字符是 " 。
 * 单引号：字符实体为 &apos; ，对应的字符是 ' 。
 * 与符号：字符实体为 &amp; ，对应对的字符是 & 。
 * 大于号：字符实体为 &gt; ，对应的字符是 > 。
 * 小于号：字符实体为 &lt; ，对应的字符是 < 。
 * 斜线号：字符实体为 &frasl; ，对应的字符是 / 。
 * 给你输入字符串 text ，请你实现一个 HTML 实体解析器，返回解析器解析后的结果。
 */
public class Solution {

    class EntityChar {
        String entity;
        char character;

        public EntityChar(String entity, char character) {
            this.entity = entity;
            this.character = character;
        }
    }

    List<EntityChar> entityList = new ArrayList<>();

    public String entityParser(String text) {
        entityList.add(new EntityChar("&quot;", '"'));
        entityList.add(new EntityChar("&apos;", '\''));
        entityList.add(new EntityChar("&amp;", '&'));
        entityList.add(new EntityChar("&gt;", '>'));
        entityList.add(new EntityChar("&lt;", '<'));
        entityList.add(new EntityChar("&frasl;", '/'));
        StringBuffer res = new StringBuffer();
        int length = text.length();
        int pos = 0;
        while (pos < length) {
            boolean isEntity = false;
            if (text.charAt(pos) == '&') {
                for (EntityChar entityChar : entityList) {
                    String entity = entityChar.entity;
                    char character = entityChar.character;
                    if (pos + entity.length() <= text.length() && text.substring(pos, pos + entity.length()).equals(entity)) {
                        res.append(character);
                        pos += entity.length();
                        isEntity = true;
                        break;
                    }
                }
            }
            if (!isEntity) {
                res.append(text.charAt(pos++));
                continue;
            }
        }
        return res.toString();
    }

}
