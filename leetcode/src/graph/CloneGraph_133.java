package graph;

import java.util.*;

public class CloneGraph_133 {

    // 递归调用，一定要保存访问信息，但是不能再递归栈中保存，因为栈中是局部的，所以是 类中声明
    private HashMap<Node, Node> visited = new HashMap<>();

    // 递归函数的功能：返回当前节点的克隆节点
    public Node cloneGraph2(Node node) {
        // DFS

        // 若当前节点为 null，克隆节点为 null
        if (node == null) return node;

        // 当前节点不为空，但是当前节点已经访问过了，那么克隆节点肯定已经创建完毕 直接返回即可
        if (visited.containsKey(node)) return visited.get(node);

        // 若当前节点还没访问过，创建克隆节点，并更新 克隆节点的 neibors
        Node cloneNode = new Node(node.val, new ArrayList<>());

        // 添加到 visited 中 记录
        visited.put(node, cloneNode);

        // 根据 node 的 neibors 更新 cloneNode 的 neibor
        for (Node next: node.neighbors) {
            cloneNode.neighbors.add(cloneGraph2(next));
        }
        return cloneNode;
    }

    public Node cloneGraph(Node node) {
        // BFS 第一遍创建所有新的节点 并添加到 map(val, Node)
        // 若  node 为 空，直接返回
        if (node == null)
            return node;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);// 头节点添加到Queue 中
        // 避免多次访问，死循环，map 存储访问过的信息
        HashMap<Node, Node> visited = new HashMap<>();// key 原来的节点；value 原来节点对应的克隆节点

        // 进队列时记录 visited
        visited.put(node, new Node(node.val, new ArrayList<>()));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            // 弹出的节点一定是 visited 过的
            // 遍历该节点的邻居
            for (Node next: cur.neighbors) {
                // 若邻居没有访问托，加入visited，并进队列
                if (!visited.containsKey(next)) {
                    visited.put(next, new Node(next.val, new ArrayList<>()));

                    queue.offer(next);
                }
//                else {
                    // 若邻居被访问过，说明邻居的克隆节点已经存在，则更新
                    // 当前节点的克隆节点的 邻居为 当前的next 的克隆节点
//                    cur.neighbors.add(visited.get(next));
                // 至此，该邻接点一定在 visited中，根据 克隆图对应节点的邻接表
                    visited.get(cur).neighbors.add(visited.get(next));
//                }

//                queue.offer(next);
            }
        }

        return visited.get(node);
    }
}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}