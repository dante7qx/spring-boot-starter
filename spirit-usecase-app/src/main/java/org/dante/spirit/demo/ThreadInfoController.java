package org.dante.spirit.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;
import java.util.Map;

@RestController
class ThreadInfoController {

    @GetMapping("/cur-thread")
    public String hello() throws InterruptedException {
        Thread current = Thread.currentThread();
        long startTime = System.currentTimeMillis();

        // 模拟阻塞操作
        Thread.sleep(1000);

        long endTime = System.currentTimeMillis();

        return String.format(
                "Thread: %s (ID: %d)\nVirtual: %s\nProcessing time: %dms\n",
                current.getName() != null ? current.getName() : "VirtualThread-" + current.threadId(),
                current.threadId(),
                current.isVirtual(),
                endTime - startTime
        );
    }

    @GetMapping("/thread-stats")
    public Map<String, Object> getThreadStats() {
        Map<String, Object> stats = new HashMap<>();

        // 当前线程信息
        Thread current = Thread.currentThread();
        stats.put("currentThread", Map.of(
                "name", current.getName() != null ? current.getName() : "unnamed",
                "id", current.threadId(),
                "isVirtual", current.isVirtual(),
                "state", current.getState().toString()
        ));

        // 系统信息
        stats.put("availableProcessors", Runtime.getRuntime().availableProcessors());

        // JVM 线程统计
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        stats.put("jvmThreadCount", threadBean.getThreadCount());
        stats.put("jvmDaemonThreadCount", threadBean.getDaemonThreadCount());
        stats.put("jvmPeakThreadCount", threadBean.getPeakThreadCount());

        return stats;
    }
}
