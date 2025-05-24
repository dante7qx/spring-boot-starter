package org.dante.spirit;

import io.undertow.UndertowOptions;
import io.undertow.server.handlers.BlockingHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class SpiritUsecaseAppApplication {

    public static void main(String[] args) {
        // 设置系统属性启用虚拟线程
//        System.setProperty("spring.threads.virtual.enabled", "true");
        SpringApplication.run(SpiritUsecaseAppApplication.class, args);
    }

    @Bean
    public UndertowServletWebServerFactory undertowServletWebServerFactory() {
        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();

        // 通过 yaml 配置
//        factory.addBuilderCustomizers(builder -> {});

        // 配置虚拟线程执行器
        factory.addDeploymentInfoCustomizers(deploymentInfo -> {
            deploymentInfo.setExecutor(Executors.newThreadPerTaskExecutor(
                Thread.ofVirtual().name("spt-vt-", 0).factory()
            ));
        });

        return factory;
    }
}

/*
并发测试
for i in {1..10}; do
  curl -s "http://localhost:8080/hello" &
done
wait
 */