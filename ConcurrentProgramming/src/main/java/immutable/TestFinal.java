package immutable;

/**
 * 测试 final 能否被修改
 */
public class TestFinal {

    public static void main(String[] args) {
        final Person person = new Person();
//        person = new Person();
        person.id = 2;
    }
}
