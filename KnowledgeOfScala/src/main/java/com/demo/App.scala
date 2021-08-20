package com.demo

/**
 * @author jieguangzhi
 * @date 2021-08-09
 */
object App {

  def main(args: Array[String]): Unit = {
    println("Scala HelloWorld!")
    //测试使用Scala调用Java提供的方法
    HelloWorld.test()
  }

  def scalaMethod(): Unit = {
    println("这个是Scala方法")
  }
}
