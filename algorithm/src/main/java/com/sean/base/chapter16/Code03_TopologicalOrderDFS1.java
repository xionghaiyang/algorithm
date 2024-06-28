package com.sean.base.chapter16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-01 20:11
 * @Description: TODO
 */
public class Code03_TopologicalOrderDFS1 {

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
        public int deep;

        public Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
            f(cur, order);
        }
        List<Record> recordArr = new ArrayList<>();
        for (Record record : order.values()) {
            recordArr.add(record);
        }
        recordArr.sort((a, b) -> b.deep - a.deep);
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        for (Record record : recordArr) {
            res.add(record.node);
        }
        return res;
    }

    public Record f(DirectedGraphNode cur, Map<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        int follow = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            follow = Math.max(follow, f(next, order).deep);
        }
        Record res = new Record(cur, follow + 1);
        order.put(cur, res);
        return res;
    }

}
