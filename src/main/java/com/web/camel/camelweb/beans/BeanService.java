package com.web.camel.camelweb.beans;

import org.springframework.stereotype.Component;

@Component
public class BeanService {
    public String beanServiceHelloWorld(String methodParam)
    {
        return "BeanService Hello World!" + methodParam;
    }
}
