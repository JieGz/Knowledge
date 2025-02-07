package com.demo.localdatatime.mul;

import java.util.concurrent.TimeUnit;

/**
 * sleep util.
 *
 * @author jieguangzhi
 * @date 2022-10-10
 */
public class SleepUtils {

  /**
   * Performs a Thread.sleep using this time unit.
   *
   * @param ms the minimum time to sleep.
   */
  public static void sleep(long ms) {
    try {
      TimeUnit.MILLISECONDS.sleep(ms);
    } catch (InterruptedException e) {
      //
    }
  }
}
