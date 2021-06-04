package com.demo.thread.future.task;

import com.demo.thread.lock.User;
import com.google.common.base.Stopwatch;
import lombok.Builder;
import lombok.Data;

/**
 * @author jieguangzhi
 * @date 2021-06-03
 */
@Data
@Builder
public class SchedulerBean {
    private Stopwatch stopwatch;

    private Boolean flag;

    private String exceptionMessage;

    private User user;
}
