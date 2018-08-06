package com.roshine.threadpooldemo.threadpool1;

/**
 * @author Roshine
 * @date 2018/8/6 21:52
 * @blog http://www.roshine.xyz
 * @email roshines1016@gmail.com
 * @github https://github.com/Roben1016
 * @phone 136****1535
 * @desc 线程动态代理工厂
 */
public class ThreadPoolProxyFactory {
    private static ThreadPoolProxy normalThreadPoolProxu;
    private static ThreadPoolProxy downloadThreadPoolProxu;

    public static ThreadPoolProxy getNormalThreadPool(){
        if (normalThreadPoolProxu == null) {
            synchronized (ThreadPoolProxyFactory.class){
                if (normalThreadPoolProxu == null) {
                    normalThreadPoolProxu = new ThreadPoolProxy(Runtime.getRuntime().availableProcessors()*2,5);
                }
            }
        }
        return normalThreadPoolProxu;
    }

    public static ThreadPoolProxy getDownloadThreadPool(){
        if (downloadThreadPoolProxu == null) {
            synchronized (ThreadPoolProxyFactory.class){
                if (downloadThreadPoolProxu == null) {
                    downloadThreadPoolProxu = new ThreadPoolProxy(3,3);
                }
            }
        }
        return downloadThreadPoolProxu;
    }

}
