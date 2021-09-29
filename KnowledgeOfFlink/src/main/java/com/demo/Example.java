package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

/**
 * @author jieguangzhi
 * @date 2021-09-29
 */
@Slf4j
public class Example {

    public static void main(String[] args) throws Exception {
        //处理流的执行环境
        StreamExecutionEnvironment streamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        //准备有界数据流(input)
        DataStream<Person> dataStream = streamExecutionEnvironment.fromElements(
                Person.builder().name("Luke").age(20).build(),
                Person.builder().name("Lynn").age(18).build(),
                Person.builder().name("Merry").age(26).build());

        DataStream<Person> result = dataStream.filter((FilterFunction<Person>) person -> person.age <= 20);

        result.addSink(new SinkFunction<Person>() {
            @Override
            public void invoke(Person value, Context context) throws Exception {
                log.info("光智自定的Sink:{}", value.toString());
            }
        }).name("LukeSink");

        streamExecutionEnvironment.execute();
    }
}
