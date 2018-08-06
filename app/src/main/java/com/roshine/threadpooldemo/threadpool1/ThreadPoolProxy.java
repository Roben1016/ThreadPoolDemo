package com.roshine.threadpooldemo.threadpool1;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Roshine
 * @date 2018/8/6 21:42
 * @blog http://www.roshine.xyz
 * @email roshines1016@gmail.com
 * @github https://github.com/Roben1016
 * @phone 136****1535
 * @desc 线程代理
 */
public class ThreadPoolProxy {
    private ThreadPoolExecutor mExecutor;
    private int corePoolSize;
    private int maximumPoolSize;

    public ThreadPoolProxy(int corePoolSize, int maximumPoolSize){
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
    }

    private void initThreadPoolExecutor(){
        if(mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()){
            long keepAliveTime = 3000;
            mExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize
                    ,keepAliveTime, TimeUnit.MILLISECONDS
                    ,new LinkedBlockingDeque<Runnable>()
                    , Executors.defaultThreadFactory()
                    ,new ThreadPoolExecutor.DiscardPolicy());
        }
    }

    /**
     执行任务和提交任务的区别?
     1.有无返回值
     execute->没有返回值
     submit-->有返回值
     2.Future的具体作用?
     1.有方法可以接收一个任务执行完成之后的结果,其实就是get方法,get方法是一个阻塞方法
     2.get方法的签名抛出了异常===>可以处理任务执行过程中可能遇到的异常
     */
    /**
     * 执行任务
     */
    public void execute(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.execute(task);
    }

    /**
     * 提交任务
     */
    public Future<?> submit(Runnable task) {
        initThreadPoolExecutor();
        return mExecutor.submit(task);
    }

    /**
     * 移除任务
     */
    public void remove(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.remove(task);
    }


}
