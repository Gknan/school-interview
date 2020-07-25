package factorymethod;

import simplefactory.RectangleShape;
import simplefactory.Shape;

public class RectangleFactory implements Factory {
    @Override
    public Shape getShape() {
        return new RectangleShape();
    }
}
