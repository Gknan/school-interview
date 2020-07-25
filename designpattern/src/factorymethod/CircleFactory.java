package factorymethod;

import simplefactory.CircleShape;
import simplefactory.Shape;

public class CircleFactory implements Factory {
    @Override
    public Shape getShape() {
        return new CircleShape();
    }
}
