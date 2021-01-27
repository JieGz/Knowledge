package com.demo.stream;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jieguangzhi
 * @date 2021-01-27 21:22
 */
@Data
@Builder
@Accessors(fluent = true, chain = true)
public class ClassMate {
    /** 班级 */
    private String grade;

    /** 名字 */
    private String name;

    /** 性别 */
    private String sex;
}

