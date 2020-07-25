package treetrans;

import java.util.ArrayList;

public class Node {

    public int val;

    public Node next;
    public Node prev;

    public ArrayList<Node> nexts;

    public Node(int val, Node next, Node prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }
}
