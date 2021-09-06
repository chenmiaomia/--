import java.text.SimpleDateFormat;
import java.util.Date;

public class GetPhoneRunnable implements Runnable {
    Phones phones = null;
    String name = null;

    public GetPhoneRunnable(Phones instance, String name) {
        this.phones = instance;
        this.name = name;
    }

    public void run() {
        try {
            phones.lock.lock();
            get();
            phones.lock.unlock();
        } catch (InterruptedException e) {
            System.out.println("锁出错了");
        }
    }

    public void get() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        if (phones.count > 0) {
            System.out.println("用户:" + name + " 服务线程:" + Thread.currentThread().getName() + " 抢到手机编号:" + phones.count + " 执行时间为:" + df.format(new Date()));
            phones.count -= 1;
        } else System.out.println("用户:" + name + " 服务线程:" + Thread.currentThread().getName() + " 没抢到手机");
    }
}