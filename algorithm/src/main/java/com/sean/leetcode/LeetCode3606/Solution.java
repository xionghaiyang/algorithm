package com.sean.leetcode.LeetCode3606;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-07-07 12:08
 * @Description https://leetcode.cn/problems/coupon-code-validator
 * 3606. 优惠券校验器
 * 给你三个长度为 n 的数组，分别描述 n 个优惠券的属性：code、businessLine 和 isActive。
 * 其中，第 i 个优惠券具有以下属性：
 * code[i]：一个 字符串，表示优惠券的标识符。
 * businessLine[i]：一个 字符串，表示优惠券所属的业务类别。
 * isActive[i]：一个 布尔值，表示优惠券是否当前有效。
 * 当以下所有条件都满足时，优惠券被认为是 有效的 ：
 * code[i] 不能为空，并且仅由字母数字字符（a-z、A-Z、0-9）和下划线（_）组成。
 * businessLine[i] 必须是以下四个类别之一："electronics"、"grocery"、"pharmacy"、"restaurant"。
 * isActive[i] 为 true 。
 * 返回所有 有效优惠券的标识符 组成的数组，按照以下规则排序：
 * 先按照其 businessLine 的顺序排序："electronics"、"grocery"、"pharmacy"、"restaurant"。
 * 在每个类别内，再按照 标识符的字典序（升序）排序。
 * n == code.length == businessLine.length == isActive.length
 * 1 <= n <= 100
 * 0 <= code[i].length, businessLine[i].length <= 100
 * code[i] 和 businessLine[i] 由可打印的 ASCII 字符组成。
 * isActive[i] 的值为 true 或 false。
 */
public class Solution {

    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        Map<String, Integer> map = new HashMap<String, Integer>() {{
            put("electronics", 0);
            put("grocery", 1);
            put("pharmacy", 2);
            put("restaurant", 3);
        }};
        List<String>[] groups = new ArrayList[4];
        Arrays.setAll(groups, i -> new ArrayList<>());
        int n = code.length;
        for (int i = 0; i < n; i++) {
            Integer category = map.get(businessLine[i]);
            if (category != null && isActive[i] && check(code[i])) {
                groups[category].add(code[i]);
            }
        }
        List<String> res = new ArrayList<>();
        for (List<String> group : groups) {
            Collections.sort(group);
            res.addAll(group);
        }
        return res;
    }

    private boolean check(String code) {
        if (code.isEmpty()) {
            return false;
        }
        for (char c : code.toCharArray()) {
            if (c != '_' && !Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }

}
