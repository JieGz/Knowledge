package com.demo.json;

import com.demo.ILogtailMessage.ILogtailMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author jieguangzhi
 * @date 2024-07-05
 */
public class ILogtailParser {
    public static void main(String[] args) throws JsonProcessingException {
        String message = "{\"Time\":1716278371,\"Contents\":[{\"Key\":\"__tag__:__path__\",\"Value\":\"/logtail_host/data/core_files/sl-metrics/drc-deployer-trade--default--0-n2trd/metrics-drc-deployer.log\"},{\"Key\":\"__tag__:__user_defined_id__\",\"Value\":\"shopline\"},{\"Key\":\"content\",\"Value\":\"{\\\"appName\\\":\\\"drc-deployer\\\",\\\"clusterName\\\":\\\"shopline-foshan-kaipule-test\\\",\\\"code\\\":\\\"0\\\",\\\"count\\\":25,\\\"deploymentName\\\":\\\"trade--default--0\\\",\\\"duration\\\":0,\\\"env\\\":\\\"test\\\",\\\"eventTime\\\":1716278371019,\\\"hostIp\\\":\\\"10.98.32.39\\\",\\\"key\\\":\\\"ceq_poll_batch_size/shopline_fs_trade_test_shard2__ec_pay_go_tidb_cdc_test__payout_sub_order_tidb_test\\\",\\\"labelMap\\\":{\\\"task_name\\\":\\\"payout_sub_order_tidb_test\\\",\\\"tg_name\\\":\\\"ec_pay_go_tidb_cdc_test\\\",\\\"destination\\\":\\\"shopline_fs_trade_test_shard2__ec_pay_go_tidb_cdc_test__payout_sub_order_tidb_test\\\",\\\"team\\\":\\\"trade\\\",\\\"task_type\\\":\\\"DRC\\\"},\\\"namespace\\\":\\\"aurogon\\\",\\\"podIp\\\":\\\"10.98.223.93\\\",\\\"podName\\\":\\\"drc-deployer-trade--default--0-n2trd\\\",\\\"sdkLang\\\":\\\"j\\\",\\\"type\\\":\\\"aggr\\\",\\\"v\\\":\\\"1.2.7\\\",\\\"value\\\":25.0}\"}]}";
        ObjectMapper mapper = new ObjectMapper();
        final ILogtailMessage logtailMessage = mapper.readValue(message, ILogtailMessage.class);
        final List<Map<String, String>> contents = logtailMessage.getContents();
        for (Map<String, String> content : contents) {
            if (Objects.equals(content.get("Key"), "content")) {
                final String log = content.get("Value");
                final Map<String, Object> element = mapper.readValue(log, Map.class);
                element.forEach((k, v) -> {
                    System.out.println("k:" + k + " v:" + v);
                });
            }
        }
    }
}
