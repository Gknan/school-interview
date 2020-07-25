import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException {
        // 初始化一个 socket
        Socket socket = new Socket();
        // 超时时间
        socket.setSoTimeout(3000);

        // 端口号 2000 超时时间 3000ms
        // 发送连接时要指明服务器 IP 和 端口 IP 对应 IP 端口对应 TCP
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000), 3000);

        System.out.println("已发起服务器连接，并进入后序流程...");
        System.out.println("客户端信息：" + socket.getLocalAddress() + " port: " + socket.getLocalPort());
        System.out.println("已发起服务器连接，并进入后序流程..." + socket.getInetAddress() + " : port:" + socket.getPort());

        // 发送数据
        try {
            // 发送接收数据
            todo(socket);
        } catch (Exception e) {
            System.out.println("异常关闭");
        } finally {
            socket.close();
        }

        System.out.println("客户端已退出");

    }

    public static void todo(Socket client) throws IOException {

        // 构建键盘输入流
        InputStream inputStream = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));

        // 得到 socket 输出流，转换为打印流
        OutputStream outputStream = client.getOutputStream();
        PrintStream socketPrintStream =new PrintStream(outputStream);

        // 得到  socket 输入流，转换为 BufferReader 从服务端接收数控
        // 从 socket 输入信息获取流，socket 输入流就是从装的是从服务器中返回的数据
        InputStream inputStream1 = client.getInputStream();
        BufferedReader scoketBefferReader =
                new BufferedReader(new InputStreamReader(inputStream1));

        boolean flag = true;
        do {
            // 从键盘读一行
            String s = input.readLine();
            // 讲键盘的输入的信息读出后发送给 socket 的输入流，通过他发送到服务器
            socketPrintStream.println(s);

            // 从服务端读取一行
            String echo = scoketBefferReader.readLine();
            if ("bye".equalsIgnoreCase(echo)) {
                flag = false;
            } else {
                System.out.println(echo);
            }
        } while (flag);

        // 资源释放
        scoketBefferReader.close();
        socketPrintStream.close();
    }
}
