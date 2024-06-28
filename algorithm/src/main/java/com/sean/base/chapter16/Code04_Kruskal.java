package com.sean.base.chapter16;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.CORBA.ValueBaseHelper;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-01 20:43
 * @Description: TODO
 * undirected graph only
 */
public class Code04_Kruskal {

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

    public class UnionFind {
        //key某一个节点，value key节点往上的节点
        private HashMap<Node, Node> fatherMap;
        //key某一个集合的代表节点,value key所在集合的节点个数
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Node findFather(Node node) {
            Stack<Node> path = new Stack<>();
            while (node != fatherMap.get(node)) {
                path.add(node);
                node = fatherMap.get(node);
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), node);
            }
            return node;
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aDai = findFather(a);
            Node bDai = findFather(b);
            if (aDai != bDai) {
                int aSetSize = sizeMap.get(aDai);
                int bSetSize = sizeMap.get(bDai);
                if (aSetSize <= bSetSize) {
                    fatherMap.put(aDai, bDai);
                    sizeMap.put(bDai, aSetSize + bSetSize);
                    sizeMap.remove(aDai);
                } else {
                    fatherMap.put(bDai, aDai);
                    sizeMap.put(aDai, aSetSize + bSetSize);
                    sizeMap.remove(bDai);
                }
            }
        }
    }

    public Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        //从小的边到大的边，依次弹出，小根堆
        PriorityQueue<Edge> heap = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        for (Edge edge : graph.edges) {
            heap.add(edge);
        }
        Set<Edge> res = new HashSet<>();
        while (!heap.isEmpty()) {
            Edge edge = heap.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                res.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return res;
    }

}
