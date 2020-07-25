package uuidtest;

import java.util.UUID;

/**
 * 演示 JDK 提供的两种 UUID 生成方式
 */
public class JDKUUID {

    public static void main(String[] args) {

        // 演示根据随机字节数组构造的 UUID
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString().replaceAll("-",""));
        UUID uuid2 = UUID.randomUUID();
        System.out.println(uuid2.toString().replaceAll("-",""));
        // 乱序

        // 基于名称构造 UUID
        byte[] bytes = {10, 20, 30};
        UUID uuidConstrutByBytes = UUID.nameUUIDFromBytes(bytes);
        System.out.println(uuidConstrutByBytes.toString().replaceAll("-", ""));

        byte[] bytes2 = {10, 20, 30, 40};
        UUID uuidConstrutByBytes2 = UUID.nameUUIDFromBytes(bytes2);
        System.out.println(uuidConstrutByBytes2.toString().replaceAll("-", ""));
    }
}
