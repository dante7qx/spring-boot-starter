package org.dante.spirit.demo.service;

import lombok.RequiredArgsConstructor;
import org.dante.spirit.demo.core.SpiritDemoProperties;

@RequiredArgsConstructor
public class SpiritDemoService {

    private final SpiritDemoProperties properties;

    public String greet() {
        return properties.getMessage() + "!";
    }
}
