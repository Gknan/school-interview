package simplefactory;

public class CircleShape implements Shape {

    public CircleShape() {
        System.out.println("圆形的构造器");
    }

    @Override
    public void draw() {
        System.out.println("这是一个圆形");
    }
}
