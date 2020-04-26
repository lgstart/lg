package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entry.Test;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Test test;

    private static final Logger logger = LoggerFactory.getLogger(MyController.class);
    /***********HTTP GET method*************/
    @GetMapping("/testGet")
    public JSONObject get(){
        String url = test.getHuiCalculatedOrderUrl();
        Integer id=test.getId();
        logger.info("查看从配置文件获取的url："+url);
        ResponseEntity<JSONObject> results = restTemplate.exchange(url+"/users/user?id="+id, HttpMethod.GET,null,JSONObject.class);
        logger.info("调用第三方接口返回的数据："+results);
        JSONObject object1=results.getBody();
        logger.info("调用第三方接口返回的body："+object1);
        return object1;
    }
    @PostMapping("/testPost")
    public JSONObject post(){
        logger.info("这里走了没有.");
        String url = test.getHuiCalculatedOrderUrl();
        //设置Http的Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        JSONObject object = new JSONObject();
        HttpEntity entity = new HttpEntity<>(object, headers);
        object.put("id",10);
        ResponseEntity<String> result = restTemplate.exchange(url+"/users/user",HttpMethod.POST,entity,String.class);

        JSONObject json= JSONObject.parseObject(result.getBody());
        return json;
    }
}
