package com.demo;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class UseTest {
	public static void main(String[] args) {
		List<String> list = Lists.newArrayList("a", "b", "c");
		String str = StringUtils.join(list, "`" + "," + "`");
		str = "`" + str + "`";
		System.out.println(str);
	}
}
