package com.demo;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.shardingsphere.sql.parser.api.SQLParserEngine;
import org.apache.shardingsphere.sql.parser.api.SQLVisitorEngine;
import org.apache.shardingsphere.sql.parser.mysql.parser.MySQLParserFacade;
import org.apache.shardingsphere.sql.parser.sql.common.statement.SQLStatement;

/**
 * @author jieguangzhi
 * @date 2021-04-30
 */
public class App {
    public static void main(String[] args) {
        String sql = "select id,a as b,c from test";
        ParseTree tree = new SQLParserEngine("MySQL").parse(sql, true);
        SQLVisitorEngine sqlVisitorEngine = new SQLVisitorEngine("MySQL", "STATEMENT");
        SQLStatement sqlStatement = sqlVisitorEngine.visit(tree);
        System.out.println(sqlStatement);
    }
}
