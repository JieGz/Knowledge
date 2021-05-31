package com.demo.driver.job;

import com.demo.driver.Job;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jieguangzhi
 * @date 2021-05-11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchedulerJob implements Job, Comparable<SchedulerJob> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int compareTo(SchedulerJob schedulerJob) {
        return schedulerJob.getName().compareTo(getName());
    }

    @Override
    public String toString() {
        return "SchedulerJob{" +
                "name='" + name + '\'' +
                '}';
    }
}
