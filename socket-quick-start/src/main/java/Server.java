import sun.reflect.generics.scope.Scope;

import javax.jws.soap.SOAPBinding;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        // 监听
        ServerSocket serverSocket = new ServerSocket(2000);

        System.out.println("服务器准备就绪...");
        System.out.println("服务器信息：" + serverSocket.getInetAddress() + " : port: " + serverSocket.getLocalPort());

        // 等待客户端连接
        for(;;) {
            // 得到客户端 阻塞到这里直到等到连接 socket
            Socket client = serverSocket.accept();
            // 客户端构建异步线程 开启一个线程处理器
            ClientHandler clientHandler = new ClientHandler(client);
            // 启动线程
            clientHandler.start();
        }

    }

    /**
     * 客户端连接异步处理类
     */
    private static class ClientHandler extends Thread {
        private Socket socket;
        private boolean flag = true;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            // 打印连接到服务器的客户端信息
            System.out.println("新客户端连接：" + socket.getInetAddress()
                    + "P:" + socket.getPort());

            try {
                // 得到打印数据，用户数据输出，服务器回送数据使用
                PrintStream socketOutput = new PrintStream(socket.getOutputStream());
                // 输入流，接收数据 接收服务器端的数据
                BufferedReader socketInput =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));

                do {
                    // 客户端拿到数据 从socket 的读取传送的数据
                    String s = socketInput.readLine();
                    if ("bye".equalsIgnoreCase(s)) {
                        flag =false;
                        // 回送 向 client socket 响应
                        socketOutput.println("bye");
                    } else {
                        // 打印到屏幕，回送数据长度
                        System.out.println(s);
                        socketOutput.println("回送：" + s.length());
                    }
                } while (flag);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("异常");
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("客户端已关闭：" + socket.getInetAddress() + "P:" + socket.getPort());
        }
    }
}
