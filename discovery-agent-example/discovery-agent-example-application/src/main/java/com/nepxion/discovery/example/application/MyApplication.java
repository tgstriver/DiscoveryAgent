package com.nepxion.discovery.example.application;

import com.nepxion.discovery.example.sdk.MyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class MyApplication {

    private static final Logger LOG = LoggerFactory.getLogger(MyApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);

        invoke();
    }

    public static void invoke() {
        RestTemplate restTemplate = new RestTemplate();

        for (int i = 1; i <= 10; i++) {
            restTemplate.getForEntity("http://localhost:8080/index/" + i, String.class).getBody();
        }
    }

    @GetMapping("/index/{value}")
    public String index(@PathVariable(value = "value") String value) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put(value, "MyContext");

        MyContext.getCurrentContext().setAttributes(attributes);

        LOG.info("【主】线程ThreadLocal：{}", MyContext.getCurrentContext().getAttributes());

        new Thread(() -> {
            LOG.info("【子】线程ThreadLocal：{}", MyContext.getCurrentContext().getAttributes());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            LOG.info("Sleep 5秒之后，【子】线程ThreadLocal：{} ", MyContext.getCurrentContext().getAttributes());
        }).start();

        return "";
    }
}