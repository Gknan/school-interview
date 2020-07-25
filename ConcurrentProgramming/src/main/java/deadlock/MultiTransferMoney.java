package deadlock;

import java.util.Random;

/**
 * <h1>多人同时转账</h1>
 */
public class MultiTransferMoney {
    private static final int NUM_ACCOUNTS = 500;
    private static final int NUM_MONEY = 1000;
    private static final int NUM_THREADS = 20;
    private static final int NUM_ITERATIONS = 1000000;

    public static void main(String[] args) {
        Random rand = new Random();
        TransferMoney.Account[] accounts = new TransferMoney.Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new TransferMoney.Account(NUM_MONEY);
        }


        class TranserThread extends Thread {

            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = rand.nextInt(NUM_ACCOUNTS);
                    int toAcct = rand.nextInt(NUM_ACCOUNTS);
                    int amount = rand.nextInt(NUM_MONEY);
                    TransferMoney.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                }
                System.out.println("该线程运行结束");
            }
        }
        
        for (int i = 0; i < NUM_THREADS; i++) {
            new TranserThread().start();
        }


    }
}
