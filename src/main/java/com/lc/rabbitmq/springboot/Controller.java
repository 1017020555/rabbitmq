package com.lc.rabbitmq.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    Productor productor;
    @GetMapping(value = "/he")
    public void hello(){

        productor.send();
    }

}
