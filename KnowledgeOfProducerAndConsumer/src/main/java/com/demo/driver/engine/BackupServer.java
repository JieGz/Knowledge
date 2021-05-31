package com.demo.driver.engine;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author jieguangzhi
 * @date 2021-05-11
 */
public class BackupServer {
    /**
     * 判断底层的服务是否处于繁忙状态
     *
     * @return return true is busy
     */
    public static boolean isBusy() {
        Random random = new Random();
        int state = random.nextInt(10) + 20;
        try {
            TimeUnit.MILLISECONDS.sleep(state);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }
}
