package com.roshine.threadpooldemo.threadpool2;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Roshine
 * @date 2018/8/6 22:06
 * @blog http://www.roshine.xyz
 * @email roshines1016@gmail.com
 * @github https://github.com/Roben1016
 * @phone 136****1535
 * @desc 线程单例
 */
public class ThreadPoolManage {
    private static ThreadPool threadPool;

    public static ThreadPool getThreadPool() {
        if (threadPool == null) {
            synchronized (ThreadPoolManage.class) {
                if (threadPool == null) {
                    //核心线程数，等于处理器个数乘2
                    int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;
                    int maximumPoolSize = 10;
                    long keepAliveTime = 0L;
                    threadPool = new ThreadPool(corePoolSize, maximumPoolSize, keepAliveTime);
                }
            }
        }
        return threadPool;
    }

    public static class ThreadPool {
        public static ThreadPoolExecutor executor = null;
        private int corePoolSize;
        private int maximumPoolSize;
        private long keepAliveTime;

        public ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        /**
         * 线程池：就是把一堆线程放在一起来管理。 1.通过一定的管理机制。来处理线程额执行顺序 2.管理最多可以同时执行的线程数。
         * 3.其他线程通过队列的形式，也就是排队的形式来管理线程的并发数。
         *
         * @param runnable
         */
        public void execute(Runnable runnable) {
            if (runnable == null) {
                return;
            }

            if (executor == null) {

                executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                        TimeUnit.MILLISECONDS,// 时间单位
                        new LinkedBlockingQueue<Runnable>(),// 线程队列
                        Executors.defaultThreadFactory(),//线程工厂
                        new ThreadPoolExecutor.AbortPolicy());
            }
            // 给线程池里面添加一个线程
            executor.execute(runnable);
        }
    }

}
