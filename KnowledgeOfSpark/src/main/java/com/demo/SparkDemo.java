package com.demo;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;

/**
 * @author jieguangzhi
 * @date 2021-10-28
 */
public class SparkDemo {

    public static void main(String[] args) {
        final SparkConf sparkConf = new SparkConf().setAppName("Luke-Spark-Demo").setMaster("local[2]");
        final SparkContext sc = new SparkContext(sparkConf);


    }
}
