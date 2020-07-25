package simplefactory;

/**
 * 测试反射改进后的简单工厂模式
 * 缺点：虽然符合开放-关闭原则，但是每次传入的都是产品类的全路径，比较麻烦
 * 可以通过 反射 + 配置文件的形式改善。这种方式使用比较多。
 */
public class Main2 {

    public static void main(String[] args) {
        Shape circle = (Shape) ShapeFactory2.getClass(CircleShape.class);
        circle.draw();

        RectangleShape rectangle = (RectangleShape)ShapeFactory2.getClass(RectangleShape.class);
        rectangle.draw();

    }
}
