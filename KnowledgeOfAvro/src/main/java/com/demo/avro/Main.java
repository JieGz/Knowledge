package com.demo.avro;

import com.demo.avro.data.User;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;

import java.io.File;
import java.io.IOException;

/**
 * @author jieguangzhi
 * @date 2024-07-10
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");

//        final User user = User.newBuilder()
//                .setName("Luke")
//                .setFavoriteColor("red")
//                .setFavoriteNumber(9)
//                .build();

        final Schema schema = new Schema.Parser().parse(new File("src/main/resources/avro/user.avsc"));
        final GenericData.Record record = new GenericData.Record(schema);
        record.put("name", "Luke");
        record.put("favorite_number", 9);
        record.put("favorite_color", "red");


    }
}