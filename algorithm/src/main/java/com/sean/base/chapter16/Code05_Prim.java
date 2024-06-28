package com.sean.base.chapter16;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-01 21:05
 * @Description: TODO
 * undirected graph only
 */
public class Code05_Prim {

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

    public Set<Edge> primMST(Graph graph) {
        //解锁的边进入小根堆
        PriorityQueue<Edge> heap = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        //哪些点被解锁出来了
        HashSet<Node> nodeSet = new HashSet<>();
        //依次挑选的边在res里
        HashSet<Edge> res = new HashSet<>();
        for (Node node : graph.nodes.values()) {//随便挑了一个点
            //node是开始点
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);
                for (Edge edge : node.edges) {//由一个点解锁所有相连的边
                    heap.add(edge);
                }
                while (!heap.isEmpty()) {
                    //弹出解锁的边中，最小的边
                    Edge edge = heap.poll();
                    //可能的一个新的点
                    Node toNode = edge.to;
                    if (!nodeSet.contains(toNode)) {//不含有的时候就是新的点
                        nodeSet.add(toNode);
                        res.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            heap.add(nextEdge);
                        }
                    }
                }
            }
            //break;//仅针对是森林的情况
        }
        return res;
    }

}
