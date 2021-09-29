package com.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jieguangzhi
 * @date 2021-09-29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    public String name;
    public Integer age;
}
