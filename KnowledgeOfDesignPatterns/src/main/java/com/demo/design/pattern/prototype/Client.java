package com.demo.design.pattern.prototype;

import java.util.Random;

/**
 * @author 揭光智
 * @date 2019/03/11
 */
public class Client {

    /**
     * 发送账单的数量，这个值是从数据库中获得
     */
    private static int MAX_COUNT = 6;

    public static void main(String[] args) {
        int i = 0;
        Mail mail = new Mail(new AdvTemplete());
        mail.setTail("XX银行版权所有");
        while (i < MAX_COUNT) {
            Mail cloneMail = mail.clone();
            cloneMail.setAppellation(getRandString(5) + " 先生(女士)");
            cloneMail.setReceiver(getRandString(5) + "@" + getRandString(8) + ".com");
            System.out.println(mail);
            System.out.println(cloneMail);
            sendMail(cloneMail);
            ++i;
        }
    }

    /**
     * 发送邮件
     *
     * @param mail 邮件
     */
    private static void sendMail(Mail mail) {
        System.out.println("标题：" + mail.getSubject() + "\t收件人：" + mail.getReceiver() + "\t...发送成功！");
    }

    /**
     * 获得指定长度的随机字符串
     *
     * @param maxLength 长度
     */
    public static String getRandString(int maxLength) {
        String source = "abcdefghijklmnopqrskuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < maxLength; ++i) {
            sb.append(source.charAt(random.nextInt(source.length())));
        }
        return sb.toString();
    }
}
