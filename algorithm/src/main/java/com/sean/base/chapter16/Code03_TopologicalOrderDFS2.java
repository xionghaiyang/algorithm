package com.sean.base.chapter16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-01 20:21
 * @Description: TODO
 */
public class Code03_TopologicalOrderDFS2 {

    public class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int label) {
            this.label = label;
            neighbors = new ArrayList<>();
        }
    }

    public class Record {
        public DirectedGraphNode node;
        public long nodes;

        public Record(DirectedGraphNode node, long nodes) {
            this.node = node;
            this.nodes = nodes;
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
            f(cur, order);
        }
        ArrayList<Record> recordArr = new ArrayList<>();
        for (Record record : order.values()) {
            recordArr.add(record);
        }
        recordArr.sort((a, b) -> a.nodes == b.nodes ? 0 : (a.nodes > b.nodes ? -1 : 1));
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        for (Record record : recordArr) {
            res.add(record.node);
        }
        return res;
    }

    //当前来到cur点，请返回cur点所到之处，所有的电磁
    //返回（cur,点次）
    //缓存！order
    //key：某一个点的电次，之前算过了
    //value:点次是多少
    private Record f(DirectedGraphNode cur, Map<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        long nodes = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            nodes += f(next, order).nodes;
        }
        Record res = new Record(cur, nodes + 1);
        order.put(cur, res);
        return res;
    }

}
