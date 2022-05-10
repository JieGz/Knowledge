package com.demo;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.yaml.snakeyaml.Yaml;

import java.util.Properties;

/**
 * @author jieguangzhi
 * @date 2022-05-10
 */
public class NacosApp {
    public static void main(String[] args) {
        try {
            String serverAddr = "http://ehlxm.nacos.duowan.com:6801";
            String dataId = "venus-logagent";
            String group = "DEFAULT_GROUP";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            properties.put("namespace", "sl-jdp");
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, group, 50000);
            System.out.println(content);
            final Yaml yaml = new Yaml();
            final Config config = yaml.loadAs(content, Config.class);
            System.out.println(config);
        } catch (NacosException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
