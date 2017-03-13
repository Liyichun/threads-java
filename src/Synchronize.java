/**
 * Created by yichunli on 2017/3/13.
 */
public class Synchronize {
    static boolean flag = true;
    static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread A = new Thread(new Wait(), "wait thread");
        A.start();
        Thread.sleep(2);
        Thread B = new Thread(new Notify(), "notify thread");
        B.start();
    }
    static class Wait implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                while(flag){
                    try {
                        System.out.println("current thread is " + Thread.currentThread() + ", and flag is " + flag);
                        lock.wait();
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + " flag is false");
            }
        }
    }
    static class Notify implements Runnable {

        @Override
        public void run() {
            synchronized (lock){
                flag = false;
                lock.notifyAll();
                try{
                    Thread.sleep(7);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
