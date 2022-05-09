package com.demo;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/**
 * @author jieguangzhi
 * @date 2022-05-09
 */
public class YamlApp {
    public static void main(String[] args) {
        final Yaml yaml = new Yaml();
        final InputStream inputStream = YamlApp.class.getClassLoader().getResourceAsStream("config.yml");
        final User user = yaml.loadAs(inputStream, User.class);
        System.out.println(user);
    }
}
