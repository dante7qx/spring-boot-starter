package org.dante.spirit.demo;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dante.spirit.demo.service.SpiritDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class SpiritDemoStarter {

    private final SpiritDemoService spiritDemoService;

    public SpiritDemoStarter(@Autowired(required = false) SpiritDemoService spiritDemoService) {
        this.spiritDemoService = spiritDemoService;
    }

    @PostConstruct
    public void start() {
        if(Objects.isNull(spiritDemoService)) {
            log.info("SpiritDemoService 不可用.");
        } else {
            log.info("SpiritDemoService 可用, 输出 {}", spiritDemoService.greet());
        }
    }
}
