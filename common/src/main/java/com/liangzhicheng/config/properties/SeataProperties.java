package com.liangzhicheng.config.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties("spring.cloud.alibaba.seata")
public class SeataProperties {

    private String txServiceGroup;

}
