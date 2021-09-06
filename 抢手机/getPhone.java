class Phones {
    public int count = 10;//有多少个手机被抢
    Lock lock = new Lock();//锁
}

public class getPhone {

    static int noOfThreads = 10;//线程池中服务线程数量
    static int maxNoOfTask = 100;//任务队列最大任务数量
    static int personNum = 200;//抢手机人数

    public static void main(String[] args) throws Exception {
        Phones phones = new Phones();//新建一个手机类 代表有多少个手机
        ThreadPool linkThreadPool = new ThreadPool(noOfThreads, maxNoOfTask);
        for (int i = 1; i <= personNum; i++) {
            //把每一个人想抢手机的行为放在线程池的任务队列中
            linkThreadPool.execute(new GetPhoneRunnable(phones, "Person" + i));
        }
        linkThreadPool.waitUntilAllTasksFinished();//等待所有的任务结束
        linkThreadPool.stop();//关闭线程池
    }

}
