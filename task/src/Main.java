import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.CountDownLatch;
import java.io.*;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore product = new Semaphore(10);
        Semaphore buylock = new Semaphore(1);
        task mytask = new task();
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(10);
        client thisclient[] =new client[101];
        ExecutorService fixedThreadPool =Executors.newFixedThreadPool(10);
        for (int i=1;i<=100;i++){
            final int index=i;
            thisclient[i] =new client(index);
        }
        for (int i=1;i<=100;i++){
            final int index=i;
            new Thread(()->{
                try {
                    start.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep((long) (200*Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    product.acquire();
                    buylock.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fixedThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        thisclient[index].buy(mytask.giveproduct());
                        try {
                            Thread.sleep((long) (50 + Math.random() * 500));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        buylock.release(1);
                        end.countDown();
                    }
                });
            }).start();
        }
        start.countDown();
        end.await();
        fixedThreadPool.shutdownNow();
    }
    
}

