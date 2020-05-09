package com.demo;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;

/**
 * @author 揭光智
 * @date 2020/05/09
 */
public class SplitterTest {

    @Test
    public void splitter_on() {
        final String content = "a- b -";
        List<String> list = Splitter.on("-").splitToList(content);
        System.out.println(list.size());
        System.out.println(" b ".equals(list.get(1)));
        System.out.println("".equals(list.get(2)));
        list = Splitter.on("-").trimResults().splitToList(content);
        System.out.println(list.size());
        System.out.println(" b ".equals(list.get(1)));
        System.out.println("".equals(list.get(2)));
        list = Splitter.on("-").trimResults().omitEmptyStrings().splitToList(content);
        System.out.println(list.size());
    }

    @Test
    public void splitter_pattern() {
        final String content = "a b c- d - e#f#g#";
        List<String> list = Splitter.onPattern("\\ |\\-|\\#").trimResults().omitEmptyStrings().splitToList(content);
        System.out.println(list.size());
        System.out.println(list.toString());
    }
}
