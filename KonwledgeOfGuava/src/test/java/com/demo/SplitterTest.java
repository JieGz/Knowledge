package com.demo;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.StringJoiner;

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

    @Test
    public void splitterSql() {
        String sql = " 'a'  as  test1 ";
        List<String> list = Splitter.onPattern("\\ AS |\\ as ").trimResults().splitToList(sql);
        System.out.println(list);
    }

    @Test
    public void splitterDemo1() {
        String condition = "1";
        StringJoiner value = new StringJoiner(" ");
        List<String> conditions = Splitter.on(",").trimResults().splitToList(condition);
        if (conditions.size() > 1) {
            StringJoiner inCondition = new StringJoiner("," + " ");
            for (String whereCondition : conditions) {
                inCondition.add("'" + whereCondition + "'");
            }
            System.out.println(inCondition.toString());
            value.add("number").add("IN").add("(" + inCondition.toString() + ")");
        } else {
            value.add("number").add("=").add(condition);
        }
        System.out.println(value);
    }
}
