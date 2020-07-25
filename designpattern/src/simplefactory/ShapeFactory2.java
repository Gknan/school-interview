package simplefactory;

/**
 * 使用反射解决简单工厂每次增加新的类都需要修改产品工厂
 */
public class ShapeFactory2 {

    public static Object getClass(Class<? extends Shape> clazz) {
        Object object = null;

        try {
            object = Class.forName(clazz.getName()).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return object;
    }
}
