package com.demo

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author jieguangzhi
 * @date 2022-01-23
 */
object WordCountScala {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
    sparkConf.setAppName("WordCount")
    //本地运行
    sparkConf.setMaster("local")

    val sparkContext = new SparkContext(sparkConf)

    //单词统计
    val fileRDD: RDD[String] = sparkContext.textFile(Thread.currentThread().getContextClassLoader.getResource("text.txt").toString)
    val words:RDD[String] = fileRDD.flatMap(_.split(" "))

    val pairWord:RDD[(String,Int)] = words.map((x:String)=>{new Tuple2(x,1)})

    val res: RDD[(String,Int)] = pairWord.reduceByKey((x: Int, y: Int) => {
      x + y
    })

    res.foreach(println)
  }
}
