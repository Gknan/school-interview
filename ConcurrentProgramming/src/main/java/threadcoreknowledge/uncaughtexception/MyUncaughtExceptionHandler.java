package threadcoreknowledge.uncaughtexception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>自定义的未捕获异常处理器</h1>
 */
public class MyUncaughtExceptionHandler
        implements Thread.UncaughtExceptionHandler {

    private String name;

    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // 报警机制，通知机制，修复处理
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING, "线程异常，终止，" + t.getName());
        System.out.println(name + "捕获了异常");
    }
}
