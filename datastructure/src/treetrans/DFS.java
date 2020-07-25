package treetrans;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 最简单的设计，就是直接将 DFS 的遍历边的过程中，
 * 只要有一个节点加入，加入后返回即可
 */
public class DFS {

    public void dfs(Node node) {
        if (node == null) return;

//        LinkedList<Node> queue = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        HashSet<Node> visited = new HashSet<>();

        stack.push(node);
        visited.add(node);

        System.out.println(node.val);

        while (!visited.isEmpty()) {
            Node cur = stack.pop();

            for (Node next: cur.nexts) {
                if (!visited.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    visited.add(next);

                    System.out.println(next);
                    break;
                }
            }
        }
    }
}
