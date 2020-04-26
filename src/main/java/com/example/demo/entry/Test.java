package com.example.demo.entry;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "system")
public class Test {
    public String huiCalculatedOrderUrl;
    public Integer id;
}
