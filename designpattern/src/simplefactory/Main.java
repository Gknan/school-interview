package simplefactory;

/**
 * 主类，用于测试
 *
 * 缺点：
 * 简单工厂模式，违背了开放封闭原则，每次新添加一个功能，都需要在 switch-case 或者 if else
 * 处修改代码
 */
public class Main {

    public static void main(String[] args) {
        Shape circle = ShapeFactory.getShape("circle");
        circle.draw();

        Shape rectangle = ShapeFactory.getShape("rectangle");
        rectangle.draw();

        Shape squere = ShapeFactory.getShape("squere");
    }
}
