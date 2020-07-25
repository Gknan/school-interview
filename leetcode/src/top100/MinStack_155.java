package top100;

/*
设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。

push(x) -- 将元素 x 推入栈中。
pop() -- 删除栈顶的元素。
top() -- 获取栈顶元素。
getMin() -- 检索栈中的最小元素。

示例:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.

分析：
底层维护两个栈，stack stackMin

入栈时， stack 直接入栈； stackMin 拿出栈顶 top 若 top < ele 压入 top ；否则 压入 ele

方法一是辅助栈的思想；能否不借助辅助栈，只是用一个变量呢
方法二：
入栈时，min 记录当前为栈顶的最小值，若 x <= min 先把原来的min压入栈 更新 min为x，入栈
出栈时，若出栈元素是 min，则再出现元素复制给 min

方法三：
再理一下上边的思路，我们每次存入的是 原来值 - 当前最小值。
当原来值大于等于当前最小值的时候，我们存入的肯定就是非负数，所以出栈的时候就是 栈中的值 + 当前最小值 。
当原来值小于当前最小值的时候，我们存入的肯定就是负值，此时的值我们不入栈，用 min 保存起来，同时将差值入栈。
当后续如果出栈元素是负数的时候，那么要出栈的元素其实就是 min。此外之前的 min 值，我们可以通过栈顶的值和当前 min 值进行还原，就是用 min 减去栈顶元素即可。
上边的解法的一个缺点就是由于我们保存的是差值，所以可能造成溢出，所以我们用了数据范围更大的 long 类型。

 */
import java.util.Stack;

public class MinStack_155 {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;


    /** initialize your data structure here. */
    public MinStack_155() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
//        if (minStack.isEmpty()) {
//            minStack.push(x);
//        } else {
//            int top = minStack.peek();
//            if (top <= x) {
//                minStack.push(top);
//            } else {
//                minStack.push(x);
//            }
//        }
        if (!minStack.isEmpty() && minStack.peek() <= x) {
            minStack.push(minStack.peek());
        } else {
            minStack.push(x);
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack_155 minStack = new MinStack_155();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
