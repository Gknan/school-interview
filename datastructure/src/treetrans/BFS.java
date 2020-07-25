package treetrans;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * BFS 图和树都是用；只不过对于 图 而言，多个重复节点判断
 */
public class BFS {

    public void bfs (Node node) {

        // bfs 借助队列实现层次
        // 借助 visited 保存已经访问过的节点
        LinkedList<Node> queue = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>();

        // 根节点先进展
        queue.add(node);
        visited.add(node);

        while (!queue.isEmpty()) {
            Node top = queue.poll();
            // TODO 操纵当前节点
            System.out.println(node);

            // 遍历 node 的下一个节点
            for (Node next: top.nexts) {
                if (!visited.contains(next)) {
                    queue.add(next);
                    visited.add(next);
                }
            }
        }
    }
}
