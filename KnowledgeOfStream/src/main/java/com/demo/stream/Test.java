package com.demo.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.With;

/**
 * @author jieguangzhi
 * @date 2022-01-18
 */
@AllArgsConstructor
@Data
public class Test {

    @With
    private final int foo;
}
