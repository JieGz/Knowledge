package com.demo.quartz;

import lombok.Data;

/**
 * @author jieguangzhi
 * @date 2021-05-18
 */
@Data
public class TestBean implements Comparable<TestBean> {
    private String priority;
    private String b;
    private String c;
    private String d;

    @Override
    public int compareTo(TestBean bean) {
        return getPriority().compareTo(bean.getPriority());
    }
}
