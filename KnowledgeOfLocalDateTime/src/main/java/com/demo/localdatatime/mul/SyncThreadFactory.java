package com.demo.localdatatime.mul;

import javax.annotation.Nonnull;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * thread factory.
 *
 * @author jieguangzhi
 * @date 2022-08-31
 */
public class SyncThreadFactory implements ThreadFactory {

  private final String name;
  private final AtomicInteger threadNumber = new AtomicInteger(1);

  public SyncThreadFactory(String name) {
    this.name = name;
  }

  @Override
  public Thread newThread(@Nonnull Runnable runnable) {
    return new Thread(runnable, name + "-" + threadNumber.getAndIncrement());
  }
}
