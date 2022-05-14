package com.jby.algorithms.DesignPatternAlgorithms;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 剑指offer 面试题02  单例模式
 */
public class Question01 { }


/**
 * volatile + 双重检查锁
 *  1） 延迟初始化。和懒汉模式一致
 *  2） 在同步前通过判读singleton是否初始化，减少不必要的同步开销。
 *  3） 线程安全。同步创建Singleton对象
 *  4） 静态私有变量singleton使用volatile修饰，防止多线程环境中出现获取到未完全初始化的对象而导致的问题
 */
class Singleton1 implements Serializable{
    private volatile static Singleton1 singleton;  //1:volatile修饰
    public static Singleton1 getSingleton() {
        if (singleton == null) {  //2:减少不要同步，优化性能
            synchronized (Singleton1.class) {  // 3：同步，线程安全
                if (singleton == null) {

                    /**
                     * singleton = new Singleton1() 可以解释为如下三步：
                     * memory=allocate(); //1：分配内存空间
                     * ctorInstance();   //2:初始化对象
                     * singleton=memory; //3:设置singleton指向刚排序的内存空间
                     *
                     * 线程A在执行上面伪代码时，2和3可能会发生重排序。
                     * 如果发生重排序，步骤变为1->3->2,线程A执行到第3步时，singleton是非null的，指向了一个没有初始化完成的对象空间。
                     * 此时线程B调用getsingleton方法，在判断singleton不为null后，直接返回singleton。此时线程B访问的将是个还没初始化完毕的对象。
                     * 当声明对象的引用为volatile后，伪代码的2、3的重排序在多线程中将被禁止!
                     */
                    singleton = new Singleton1();  //4：创建singleton 对象
                }
            }
        }
        return singleton;
    }

    // 防止通过反射修改构造函数的访问修饰符来创建多个单例对象
    private Singleton1 (){
        if(singleton!=null){
            throw new RuntimeException("singleton obj already created !");
        }
    }
    //防止反序列化获取多个对象的漏洞。
    //反序列化时，readResolve()中返回的对象直接替换在反序列化过程中创建的对象
    private Object readResolve() throws ObjectStreamException {
        if(singleton!=null){
            return singleton;
        }
        return getSingleton();
    }
}

/**
 * 使用 私有的静态内部类 的方式
 * JVM在执行类的初始化阶段，会获得一个可以同步多个线程对 同一个类 的初始化的锁，从而保证静态内部类的初始化是线程安全的，
 */
class Singleton2 implements Serializable {
    private static class SingletonClassInstance {
        private static final Singleton2 instance = new Singleton2();
    }
    public static Singleton2 getInstance() {
        // 首次调用getInstance（）方法时触发 SingletonClassInstance的初始化，完成单例对象的安全创建。
        return SingletonClassInstance.instance;
    }

    //防止反射获取多个对象的漏洞
    private Singleton2() {
        if (null != SingletonClassInstance.instance){
            throw new RuntimeException("singleton obj already created !");
        }
    }
    //防止反序列化获取多个对象的漏洞。
    //反序列化时，readResolve()中返回的对象直接替换在反序列化过程中创建的对象
    private Object readResolve() throws ObjectStreamException {
        if(SingletonClassInstance.instance!=null){
            return SingletonClassInstance.instance;
        }
        return getInstance();
    }
}

