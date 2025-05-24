package org.dante.spirit.demo.core;

import lombok.RequiredArgsConstructor;
import org.dante.spirit.demo.service.SpiritDemoService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnClass(SpiritDemoService.class)
@EnableConfigurationProperties(SpiritDemoProperties.class)
@ConditionalOnProperty(prefix = "spirit.demo", name = "enabled", havingValue = "true", matchIfMissing = true)
@RequiredArgsConstructor
public class SpiritAutoConfiguration {

    private final SpiritDemoProperties properties;

    @Bean
    @ConditionalOnMissingBean // Only create this bean if no other SpiritDemoService bean exists
    public SpiritDemoService spiritDemoService() {
        return new SpiritDemoService(properties);
    }

}
