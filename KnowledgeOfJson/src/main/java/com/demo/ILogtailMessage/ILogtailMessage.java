package com.demo.ILogtailMessage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author jieguangzhi
 * @date 2024-07-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ILogtailMessage {
    @JsonProperty(value = "Time")
    private Long time;

    @JsonProperty(value = "Contents")
    private List<Map<String, String>> contents;

}
