package com.sean.leetcode.LeetCodeInterview1726;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2026-03-27 19:16
 * @Description https://leetcode.cn/problems/sparse-similarity-lcci
 * 面试题 17.26. 稀疏相似度
 * 两个(具有不同单词的)文档的交集(intersection)中元素的个数除以并集(union)中元素的个数，就是这两个文档的相似度。
 * 例如，{1, 5, 3} 和 {1, 7, 2, 3} 的相似度是 0.4，其中，交集的元素有 2 个，并集的元素有 5 个。
 * 给定一系列的长篇文档，每个文档元素各不相同，并与一个 ID 相关联。
 * 它们的相似度非常“稀疏”，也就是说任选 2 个文档，相似度都很接近 0。
 * 请设计一个算法返回每对文档的 ID 及其相似度。
 * 只需输出相似度大于 0 的组合。
 * 请忽略空文档。
 * 为简单起见，可以假定每个文档由一个含有不同整数的数组表示。
 * 输入为一个二维数组 docs，docs[i] 表示 id 为 i 的文档。
 * 返回一个数组，其中每个元素是一个字符串，代表每对相似度大于 0 的文档，其格式为 {id1},{id2}: {similarity}，其中 id1 为两个文档中较小的 id，similarity 为相似度，精确到小数点后 4 位。
 * 以任意顺序返回数组均可。
 */
public class Solution {

    public List<String> computeSimilarities(int[][] docs) {
        List<String> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[][] help = new int[docs.length][docs.length];
        for (int i = 0; i < docs.length; i++) {
            for (int j = 0; j < docs[i].length; j++) {
                if (!map.containsKey(docs[i][j])) {
                    map.put(docs[i][j], new ArrayList<>());
                } else {
                    for (int k : map.get(docs[i][j])) {
                        help[i][k]++;
                    }
                }
                map.get(docs[i][j]).add(i);
            }
            for (int k = 0; k < docs.length; k++) {
                if (help[i][k] > 0) {
                    res.add(new StringBuilder()
                            .append(k)
                            .append(",")
                            .append(i)
                            .append(": ")
                            .append(String.format("%.4f", (double) help[i][k] / (docs[i].length + docs[k].length - help[i][k])))
                            .toString());
                }
            }
        }
        return res;
    }

}
