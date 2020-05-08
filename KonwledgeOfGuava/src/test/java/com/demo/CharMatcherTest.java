package com.demo;

import com.google.common.base.CharMatcher;
import org.junit.Test;

/**
 * @author 揭光智
 * @date 2020/05/08
 */
public class CharMatcherTest {

    @Test
    public void charMatcherTest() {
        String taskName = "88拉拉拉";
        String number = CharMatcher.inRange('0', '9').or(CharMatcher.whitespace()).retainFrom(taskName);
        String Name = CharMatcher.inRange('0', '9').or(CharMatcher.whitespace()).removeFrom(taskName);
        System.out.println(number + "<=>" + Name);
    }

}
