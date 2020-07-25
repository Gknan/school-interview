package simplefactory;

/**
 * 工厂类 更具传入的参数进行不同构造器的选择
 * 提供 getShape 静态方法
 */
public class ShapeFactory {

    public static Shape getShape(String shape) {

        if (shape == null) return null;

        if ("circle".equals(shape)) {
            return new CircleShape();
        } else if ("rectangle".equals(shape)) {
            return new RectangleShape();
        };

        return null;
    }
}
