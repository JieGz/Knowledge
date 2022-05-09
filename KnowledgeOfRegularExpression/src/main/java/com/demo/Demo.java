package com.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jieguangzhi
 * @date 2021-02-04 11:04
 */
public class Demo {
    public static Pattern SQL_FORMAT = Pattern.compile("--.*\n|--.*\r\n");

    public static void main(String[] args) {
        String sql = "-- 我是注释\n SELECT 1;\n -- 我也是注释\r\n -- 我也也是注释\n SELECT 2;\n SELECT 3; ";

        final Matcher m = SQL_FORMAT.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String group = m.group(0);
            group = "NEW_LINE" + group + "NEW_LINE";
            m.appendReplacement(sb, group);
        }
        m.appendTail(sb);
        System.out.println(sb);

        System.out.println("------------");

        String newSql = sb.toString();
        System.out.println(newSql);
        String s = newSql.replaceAll("\n", "");
        System.out.println("------------");
         String new_line = s.replaceAll("NEW_LINE NEW_LINE|NEW_LINE", "\n").trim();
        System.out.println(new_line);
        System.out.println("------------");
        System.out.println(SqlCommentUtil.format(sql));

    }
}
