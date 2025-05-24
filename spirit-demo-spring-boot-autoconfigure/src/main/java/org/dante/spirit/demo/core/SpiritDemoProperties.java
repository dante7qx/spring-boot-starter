package org.dante.spirit.demo.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spirit.demo")
@Data
public class SpiritDemoProperties {

    /**
     * 是否启用
     */
    private Boolean enabled = Boolean.TRUE;

    private String message = "spirit-demo-spring-boot-starter";
}
