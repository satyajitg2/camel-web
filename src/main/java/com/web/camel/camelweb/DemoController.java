package com.web.camel.camelweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController
{
    @GetMapping("/rest/gello")
    public String hello()
    {
        return "Hello Camel-web User!";
    }

    @GetMapping("/rest/emp")
    public String emp()
    {
        return "This is camel web DemoController emp.!";
    }
}

