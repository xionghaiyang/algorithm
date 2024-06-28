package com.sean.base.chapter16;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-01 20:33
 * @Description: TODO
 */
public class Code03_TopologySort {

    //点结构的描述
    public class Node {
        public int value;
        public int in;
        public int out;
        public ArrayList<Node> nexts;
        public ArrayList<Edge> edges;

        public Node(int value) {
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    public class Edge {
        public int weight;
        public Node from;
        public Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    public class Graph {
        public HashMap<Integer, Node> nodes;
        public HashSet<Edge> edges;

        public Graph() {
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }

    //directed graph and no loop
    public List<Node> sortedTopology(Graph graph) {
        //key某个节点，value剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        //只有剩余入度为0的点，才进入这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<Node> res = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            res.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return res;
    }

}
