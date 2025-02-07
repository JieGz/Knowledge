package com.demo.stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jieguangzhi
 * @date 2021-03-29
 */
@Data
@AllArgsConstructor
@Builder
public class Student {
    private String name;
    private String grade;
    private List<String> course = new ArrayList<>();

    private int money = 10;
}
