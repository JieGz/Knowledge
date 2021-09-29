package com.demo.stream;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jieguangzhi
 * @date 2021-09-15
 */
@Data
public class ClassMateTree {
    private String name;
    private String v;
    private List<ClassMateTree> children = new ArrayList<>();
}
