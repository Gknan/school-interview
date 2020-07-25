package simplefactory;

public class RectangleShape implements Shape {

    public RectangleShape() {
        System.out.println("长方形的构造器");
    }

    @Override
    public void draw() {
        System.out.println("这是一个长方形");
    }
}
