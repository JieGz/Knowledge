package com.demo.design.pattern.prototype;

/**
 * @author 揭光智
 * @date 2019/03/11
 */
public class Mail implements Cloneable {

    /**
     * 收件人
     */
    private String receiver;

    /**
     * 邮件名称
     */
    private String subject;

    /**
     * 称谓
     */
    private String appellation;

    /**
     * 邮件内容
     */
    private String context;

    /**
     * 邮件的尾部，一般都是加上"XXX版权所有"等信息
     */
    private String tail;

    public Mail(AdvTemplete advTemplete) {
        this.subject = advTemplete.getAdbSubject();
        this.context = advTemplete.getAdvContext();
    }

    /**
     * Object类的clone方法的原理：是从Java堆内存中以二进制流的方式进行对象拷贝，重新分配一个内存块,那么调用clone方法的时候是
     * 不会执行对应的构造函数的
     * <p>
     * 并且Object类的clone方法只是拷贝本对象，其对象内部的数组和引用对象等引用类型都不拷贝,还是指向原生对象的内部元素地址，
     * 这种拷贝就叫浅拷贝。（但String类型和其它基本类型都会被拷贝）
     * 使用原型模式时，引用的成员变量必须满足两个条件才不会被拷由，第一个是类成员变量，第二个是可变的引用对象 <===> 不能是final的类成员引用对象
     * <p>
     * 如果要进行深拷贝,可变的类成员引用变量需要自行拷贝。加上  mail = (Mail) super.clone(); 后面
     *
     * @return Mail
     */
    @Override
    protected Mail clone() {
        Mail mail = null;
        try {
            mail = (Mail) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return mail;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }


    @Override
    public String toString() {
        return "Mail{" +
                "receiver='" + receiver + '\'' +
                ", subject='" + subject + '\'' +
                ", appellation='" + appellation + '\'' +
                ", context='" + context + '\'' +
                ", tail='" + tail + '\'' +
                '}';
    }
}
