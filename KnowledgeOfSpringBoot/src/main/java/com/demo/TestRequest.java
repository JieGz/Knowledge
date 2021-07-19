package com.demo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author jieguangzhi
 * @date 2021-05-10
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestRequest {
    private ViewerAggregateEnum aggregateEnum;

    private String name;

    private String address;
}
