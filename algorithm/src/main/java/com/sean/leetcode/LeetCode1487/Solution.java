package com.sean.leetcode.LeetCode1487;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-03 08:20
 * @Description: https://leetcode.cn/problems/making-file-names-unique/
 * 1487. 保证文件名唯一
 * 给你一个长度为 n 的字符串数组 names 。
 * 你将会在文件系统中创建 n 个文件夹：在第 i 分钟，新建名为 names[i] 的文件夹。
 * 由于两个文件 不能 共享相同的文件名，因此如果新建文件夹使用的文件名已经被占用，
 * 系统会以 (k) 的形式为新文件夹的文件名添加后缀，其中 k 是能保证文件名唯一的 最小正整数 。
 * 返回长度为 n 的字符串数组，其中 ans[i] 是创建第 i 个文件夹时系统分配给该文件夹的实际名称。
 */
public class Solution {

    public String[] getFolderNames(String[] names) {
        int n = names.length;
        Map<String, Integer> map = new HashMap<>();
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            String name = names[i];
            if (!map.containsKey(name)) {
                res[i] = name;
                map.put(name, 1);
            } else {
                int k = map.get(name);
                while (map.containsKey(addSuffix(name, k))) {
                    k++;
                }
                res[i] = addSuffix(name, k);
                map.put(name, k + 1);
                map.put(addSuffix(name, k), 1);
            }
        }
        return res;
    }

    private String addSuffix(String name, int k) {
        return name + "(" + k + ")";
    }

}
